package com.api.vote.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
public class VoteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="election_id")
    private ElectionModel electionModel;
    @ManyToOne
    @JoinColumn(name="elector_id")
    private ElectorModel electorModel;
    @Column(name = "presidente_number", length = 50)
    private String presidenteNumber;
    @Column(name = "governador_number", length = 50)
    private String governadorNumber;
    @Column(name = "senador_number", length = 50)
    private String senadorNumber;
    @Column(name = "deputado_federal_number", length = 50)
    private String deputadoFederalNumber;
    @Column(name = "deputado_estadual_number", length = 50)
    private String deputadoEstadualNumber;
    @Column(name = "created_at", nullable = true)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ElectionModel getElectionModel() {
        return electionModel;
    }

    public void setElectionModel(ElectionModel electionModel) {
        this.electionModel = electionModel;
    }

    public ElectorModel getElectorModel() {
        return electorModel;
    }

    public void setElectorModel(ElectorModel electorModel) {
        this.electorModel = electorModel;
    }

    public String getPresidenteNumber() {
        return presidenteNumber;
    }

    public void setPresidenteNumber(String presidenteNumber) {
        this.presidenteNumber = presidenteNumber;
    }

    public String getGovernadorNumber() {
        return governadorNumber;
    }

    public void setGovernadorNumber(String governadorNumber) {
        this.governadorNumber = governadorNumber;
    }

    public String getSenadorNumber() {
        return senadorNumber;
    }

    public void setSenadorNumber(String senadorNumber) {
        this.senadorNumber = senadorNumber;
    }

    public String getDeputadoFederalNumber() {
        return deputadoFederalNumber;
    }

    public void setDeputadoFederalNumber(String deputadoFederalNumber) {
        this.deputadoFederalNumber = deputadoFederalNumber;
    }

    public String getDeputadoEstadualNumber() {
        return deputadoEstadualNumber;
    }

    public void setDeputadoEstadualNumber(String deputadoEstadualNumber) {
        this.deputadoEstadualNumber = deputadoEstadualNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
