package com.api.vote.dtos;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

public class VoteDto {
    private String document;

    @Nullable
    private String presidenteNumber;

    @Nullable
    private String governadorNumber;

    @Nullable
    private String senadorNumber;

    @Nullable
    private String deputadoFederalNumber;

    @Nullable
    private String deputadoEstadualNumber;

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
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
}
