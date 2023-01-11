package com.api.vote.services;

import com.api.vote.models.CandidateModel;
import com.api.vote.models.ElectionModel;
import com.api.vote.models.ElectorModel;
import com.api.vote.models.VoteModel;
import com.api.vote.models.custom.CountVotes;
import com.api.vote.models.custom.ElectionFinishReturn;
import com.api.vote.repositories.CandidateRepository;
import com.api.vote.repositories.ElectionRepository;
import com.api.vote.repositories.VoteRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ElectionService {
    final ElectionRepository electionRepository;

    final VoteRepository voteRepository;

    final CandidateRepository candidateRepository;

    private String error;

    public ElectionService(ElectionRepository electionRepository, VoteRepository voteRepository, CandidateRepository candidateRepository) {
        this.electionRepository = electionRepository;
        this.voteRepository = voteRepository;
        this.candidateRepository = candidateRepository;
    }

    @Transactional
    public ElectionModel finishLastElection() {
        Optional<ElectionModel> electionModelOptional = Optional.ofNullable(electionRepository.findOneLastOpen());
        if (!electionModelOptional.isPresent()) {
            this.error = "No open election found";

            return null;
        }

        return electionModelOptional.get();
    }

    @Transactional
    public ElectionModel finishLastElection(ElectionModel electionModel) {
        electionModel.setFinishedAt(LocalDateTime.now());
        return electionRepository.save(electionModel);
    }

    public HashMap calcResults(ElectionModel electionModel) {

        // Get all votes from last election
        Long totalVotes = voteRepository.countByElectionModel(electionModel);

        if (totalVotes == 0) {
            this.error = "No votes found for this election (year: " + electionModel.getYear() + ", turn: " + electionModel.getTurn() + ")";

            return null;
        }

        List<CountVotes> votesByPresident = voteRepository.votesPerPresident(electionModel);
        List<CountVotes> votesByGovernador = voteRepository.votesPerGovernador(electionModel);
        List<CountVotes> votesBySenador = voteRepository.votesPerSenador(electionModel);
        List<CountVotes> votesByDeputadoEstadual = voteRepository.votesPerDeputadoEstadual(electionModel);
        List<CountVotes> votesByDeputadoFederal = voteRepository.votesPerDeputadoFederal(electionModel);

        if (electionModel.getTurn().equals("1")) {
            HashMap<String, String> map = new HashMap<>();
            map.put("presidente", this.calcWinner(votesByPresident, electionModel, totalVotes));
            map.put("governador", this.calcWinner(votesByGovernador, electionModel, totalVotes));
            map.put("senador", votesBySenador.get(0).getNumber());
            map.put("deputadoEstadual", votesByDeputadoEstadual.get(0).getNumber());
            map.put("deputadoFederal", votesByDeputadoFederal.get(0).getNumber());

            return map;
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("presidente", votesByPresident.get(0).getNumber());
        map.put("governador", votesByGovernador.get(0).getNumber());

        return map;
    }

    private String calcWinner(List<CountVotes> votes, ElectionModel electionModel, Long totalVotes) {

        // se o primeiro candidato tiver mais de 50% dos votos, ele é o vencedor, do contrário, segue para o segundo turno
        if (votes.get(0).getVoteQuantity() > totalVotes / 2) {
            return votes.get(0).getNumber();
        } else {
            // criar uma nova eleição se não existir com os dois candidatos que mais receberam votos
            ElectionModel electionModel1 = this.createSecondTurnElection("2", electionModel.getYear());
            CandidateModel fistBro = candidateRepository.findByNumber(votes.get(0).getNumber());
            System.out.println(votes.get(0).getNumber());
            System.out.println(fistBro.toString());
            fistBro.setElectionModel(electionModel1);
            candidateRepository.save(fistBro);

            return null;
        }
    }

    public ElectionModel createSecondTurnElection(String turn, String year) {

        Optional<ElectionModel> electionServiceOptional = Optional.ofNullable(electionRepository.findFirstOpenSecondTurn(year));

        if (electionServiceOptional.isPresent()) {
            return electionServiceOptional.get();
        }

        ElectionModel secondTurnElection = new ElectionModel();
        secondTurnElection.setTurn(turn);
        secondTurnElection.setYear(year);
        secondTurnElection.setCreatedAt(LocalDateTime.now());
        secondTurnElection.setFinishedAt(null);
        secondTurnElection.setUpdatedAt(LocalDateTime.now());

        return electionRepository.save(secondTurnElection);
    }

    public Optional<ElectionModel> getLastOpenElection() {
        return Optional.ofNullable(electionRepository.findOneLastOpen());
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
