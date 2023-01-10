package com.api.vote.repositories;

import com.api.vote.models.ElectionModel;
import com.api.vote.models.ElectorModel;
import com.api.vote.models.VoteModel;
import com.api.vote.models.custom.CountVotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<VoteModel, Long>{

    List<VoteModel> findAllByElectionModel(ElectionModel electionModel);

    VoteModel findVoteModelByElectionModel(ElectionModel electionModel);

    // count all votes grouped by president number
    @Query(value = "select new com.api.vote.models.custom.CountVotes(count (v.electorModel), v.presidenteNumber) from VoteModel as v where v.electionModel = ?1 group by v.presidenteNumber order by count (v.electorModel) desc")
    List<CountVotes> votesPerPresident(ElectionModel electionModel);
    @Query(value = "select new com.api.vote.models.custom.CountVotes(count (v.electorModel), v.governadorNumber) from VoteModel as v where v.electionModel = ?1 group by v.governadorNumber order by count (v.electorModel) desc")
    List<CountVotes> votesPerGovernador(ElectionModel electionModel);
    @Query(value = "select new com.api.vote.models.custom.CountVotes(count (v.electorModel), v.senadorNumber) from VoteModel as v where v.electionModel = ?1 group by v.senadorNumber order by count (v.electorModel) desc")
    List<CountVotes> votesPerSenador(ElectionModel electionModel);
    @Query(value = "select new com.api.vote.models.custom.CountVotes(count (v.electorModel), v.deputadoEstadualNumber) from VoteModel as v where v.electionModel = ?1 group by v.deputadoEstadualNumber order by count (v.electorModel) desc")
    List<CountVotes> votesPerDeputadoEstadual(ElectionModel electionModel);
    @Query(value = "select new com.api.vote.models.custom.CountVotes(count (v.electorModel), v.deputadoFederalNumber) from VoteModel as v where v.electionModel = ?1 group by v.deputadoFederalNumber order by count (v.electorModel) desc")
    List<CountVotes> votesPerDeputadoFederal(ElectionModel electionModel);

    @Query("select count(v) from VoteModel v where v.electionModel = ?1")
    long countByElectionModel(ElectionModel electionModel);

//    @Query("select v from VoteModel v where v.electionModel = ?1 and v.electorModel = ?2")
    Boolean existsByElectionModelAndElectorModel(ElectionModel electionModel, ElectorModel electorModel);
}
