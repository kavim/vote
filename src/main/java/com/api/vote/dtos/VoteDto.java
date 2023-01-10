package com.api.vote.dtos;

import javax.validation.constraints.NotNull;

public class VoteDto {
    private String document;
    private String presidenteNumber;
    private String governadorNumber;
    private String senadorNumber;
    private String deputadoFederalNumber;
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
