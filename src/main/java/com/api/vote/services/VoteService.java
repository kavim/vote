package com.api.vote.services;

import com.api.vote.dtos.VoteDto;
import com.api.vote.models.ElectionModel;
import com.api.vote.models.ElectorModel;
import com.api.vote.models.VoteModel;
import com.api.vote.repositories.VoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.lang.reflect.Parameter;
import java.util.Optional;

@Service
public class VoteService {
    final VoteRepository voteRepository;

    final ElectionService electionService;

    private String error;

    public VoteService(VoteRepository voteRepository, ElectionService electionService) {
        this.voteRepository = voteRepository;
        this.electionService = electionService;
    }

    @Transactional
    public VoteModel save(VoteModel voteModel) {
        return voteRepository.save(voteModel);
    }

    public Boolean checkVote(ElectionModel electionModel, ElectorModel electorModel) {
        return voteRepository.existsByElectionModelAndElectorModel(electionModel, electorModel);
    }
}
