package com.api.vote.dtos;

import javax.validation.constraints.NotBlank;

public class CandidateDto {
    @NotBlank
    private String name;

    @NotBlank
    private String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
