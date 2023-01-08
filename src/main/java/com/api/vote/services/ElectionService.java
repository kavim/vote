package com.api.vote.services;

import com.api.vote.models.CandidateModel;
import com.api.vote.models.ElectionModel;
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
    public HashMap finish() {
        return this.finishLastElectionAndCalcResults();
    }

    private HashMap finishLastElectionAndCalcResults() {

        Optional<ElectionModel> electionModelOptional = Optional.ofNullable(electionRepository.findOneLastOpen());
        if (!electionModelOptional.isPresent()) {
            this.error = "No open election found";

            return null;
        }

        ElectionModel electionModel = new ElectionModel();
        electionModel.setId(electionModelOptional.get().getId());
        electionModel.setTurn(electionModelOptional.get().getTurn());
        electionModel.setYear(electionModelOptional.get().getYear());
        electionModel.setCreatedAt(electionModelOptional.get().getCreatedAt());
        electionModel.setUpdatedAt(electionModelOptional.get().getUpdatedAt());
        electionModel.setFinishedAt(LocalDateTime.now());
        electionRepository.save(electionModel);

        return this.calcResults(electionModel);
    }

    private HashMap calcResults(ElectionModel electionModel) {

        // Get all votes from last election
        Long totalVotes = voteRepository.countByElectionModel(electionModel);

        if (totalVotes == 0) {
            System.out.println("No votes found");
            this.error = "No votes found for this election";

            System.out.println("teste");
            System.out.println(this.error);
            System.out.println(this.getError());

            return null;
        }

        List<CountVotes> votesByPresident = voteRepository.votesPerPresident(electionModel);
        List<CountVotes> votesByGovernador = voteRepository.votesPerGovernador(electionModel);
        List<CountVotes> votesBySenador = voteRepository.votesPerSenador(electionModel);
        List<CountVotes> votesByDeputadoEstadual = voteRepository.votesPerDeputadoEstadual(electionModel);
        List<CountVotes> votesByDeputadoFederal = voteRepository.votesPerDeputadoFederal(electionModel);

        HashMap<String, String> map = new HashMap<>();
        map.put("presidente", this.calcWinner(votesByPresident, electionModel, totalVotes));
        map.put("governador", this.calcWinner(votesByGovernador, electionModel, totalVotes));
        map.put("senador", votesBySenador.get(0).getNumber());
        map.put("deputadoEstadual", votesByDeputadoEstadual.get(0).getNumber());
        map.put("deputadoFederal", votesByDeputadoFederal.get(0).getNumber());

        return map;
    }

    private String calcWinner(List<CountVotes> votes, ElectionModel electionModel, Long totalVotes) {

        // se o primeiro candidato tiver mais de 50% dos votos, ele é o vencedor, do contrário, segue para o segundo turno
        if (votes.get(0).getVoteQuantity() > totalVotes / 2) {
            return votes.get(0).getNumber();
        } else {

            // criar uma nova eleição com os dois candidatos que mais receberam votos
            this.createSecondTurnElection(votes.get(0).getNumber(), votes.get(1).getNumber(), electionModel);

            return "go to second turn";
        }
    }

    private boolean createSecondTurnElection(String number1, String number2, ElectionModel electionModel) {

        Optional<ElectionModel> electionModelOptional = Optional.ofNullable(electionRepository.findFirstOpenSecondTurn());

        System.out.println(electionModelOptional.toString());

        var secondTurnElection = new ElectionModel();
        if (!electionModelOptional.isPresent()) {
            secondTurnElection.setCreatedAt(LocalDateTime.now());
            secondTurnElection.setUpdatedAt(LocalDateTime.now());
            secondTurnElection.setYear(electionModel.getYear());
            secondTurnElection.setTurn("2");

            System.out.println("Criando pela primeira vez");

        } else {
            System.out.println("Já tem segundo turno então vamos só atualizar");
            secondTurnElection.setId(electionModelOptional.get().getId());
            secondTurnElection.setCreatedAt(electionModelOptional.get().getCreatedAt());
            secondTurnElection.setUpdatedAt(electionModelOptional.get().getUpdatedAt());
            secondTurnElection.setYear(electionModelOptional.get().getYear());
            secondTurnElection.setTurn(electionModelOptional.get().getTurn());
        }

        electionRepository.save(secondTurnElection);

        CandidateModel fistBro = candidateRepository.findCandidateModelByNumber(number1);
        CandidateModel secondBro = candidateRepository.findCandidateModelByNumber(number2);

        fistBro.setElectionId(secondTurnElection);
        candidateRepository.save(fistBro);
        secondBro.setElectionId(secondTurnElection);
        candidateRepository.save(secondBro);

        return true;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
