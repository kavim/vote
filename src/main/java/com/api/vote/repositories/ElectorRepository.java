package com.api.vote.repositories;

import com.api.vote.models.ElectorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectorRepository extends JpaRepository<ElectorModel, Long> {
    ElectorModel findElectorModelById(Long id);
}
