package com.api.vote.repositories;

import com.api.vote.models.CandidateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository <CandidateModel, Long>{
    boolean existsByName(String name);

    boolean existsByNumber(String number);

    CandidateModel findByNumber(String number);

    List<CandidateModel> findByPosition(String position);
}
