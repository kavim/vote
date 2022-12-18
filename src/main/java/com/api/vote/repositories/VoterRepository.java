package com.api.vote.repositories;

import com.api.vote.models.VoterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterRepository extends JpaRepository<VoterModel, Long> {
}
