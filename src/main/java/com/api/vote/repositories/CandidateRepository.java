package com.api.vote.repositories;

import com.api.vote.models.CandidateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository <CandidateModel, Long>{
    boolean existsByName(String name);

    boolean existsByNumber(String number);

    CandidateModel findCandidateModelByNumber(String number);

}
