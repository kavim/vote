package com.api.vote.services;

import com.api.vote.models.VoterModel;
import com.api.vote.repositories.VoterRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class VoterService {
    final VoterRepository voterRepository;

    public VoterService(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }

    @Transactional
    public VoterModel save(VoterModel voterModel) {
        return voterRepository.save(voterModel);
    }
}
