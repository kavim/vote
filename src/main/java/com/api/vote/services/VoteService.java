package com.api.vote.services;

import com.api.vote.models.VoteModel;
import com.api.vote.repositories.VoteRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class VoteService {
    final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Transactional
    public VoteModel save(VoteModel voteModel) {
        return voteRepository.save(voteModel);
    }
}
