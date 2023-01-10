package com.api.vote.services;

import com.api.vote.models.ElectorModel;
import com.api.vote.repositories.ElectorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    public Optional<ElectorModel> getElectorByDocument(String document) {
        return Optional.ofNullable(electorRepository.findByDocument(document));
    }

    public List<ElectorModel> findAll() {
        return electorRepository.findAll();
    }
}
