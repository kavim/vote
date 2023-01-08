package com.api.vote.services;

import com.api.vote.models.ElectorModel;
import com.api.vote.repositories.ElectorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ElectorService {
    final ElectorRepository electorRepository;

    public ElectorService(ElectorRepository electorRepository) {
        this.electorRepository = electorRepository;
    }

    @Transactional
    public ElectorModel save(ElectorModel electorModel) {
        return electorRepository.save(electorModel);
    }
}
