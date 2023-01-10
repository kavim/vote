package com.api.vote.services;

import com.api.vote.models.CandidateModel;
import com.api.vote.repositories.CandidateRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {
    final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Transactional
    public CandidateModel save(CandidateModel candidateModel) {
        return candidateRepository.save(candidateModel);
    }

    public boolean existsByName(String name) {
        return candidateRepository.existsByName(name);
    }

    public boolean existsByNumber(String number) {
        return candidateRepository.existsByNumber(number);
    }

    public List<CandidateModel> findAll() {
        return candidateRepository.findAll();
    }

    public Optional<CandidateModel> findById(Long id) {
        return candidateRepository.findById(id);
    }

    public List<CandidateModel> findByPosition(String position) {
        return candidateRepository.findByPosition(position);
    }
}
