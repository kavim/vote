package com.api.vote.repositories;

import com.api.vote.models.ElectionModel;
import com.api.vote.models.ElectorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ElectionRepository extends JpaRepository<ElectionModel, Long> {
//    @Query("Select election from ElectionModel as election where finished_at is null order by id DESC")
    ElectionModel findFirstByOrderByIdDesc();

    ElectionModel findFirstByFinishedAtIsNotNullOrderByIdDesc();

    @Query(value = "Select * from elections as election where election.finished_at is null order by election.id DESC limit 1", nativeQuery = true)
    ElectionModel findOneLastOpen();

    @Query(value = "Select election from ElectionModel as election where election.finishedAt is null and election.turn = '2' and election.year = ?1 order by election.id DESC limit 1", nativeQuery = true)
    ElectionModel findFirstOpenSecondTurn(String year);

    @Modifying
    @Query("update ElectionModel election set election.finishedAt = :date where election.id = :id")
    void updatePhone(@Param(value = "id") long id, @Param(value = "date")LocalDateTime date);
}
