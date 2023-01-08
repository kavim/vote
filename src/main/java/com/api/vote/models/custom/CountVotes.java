package com.api.vote.models.custom;

public class CountVotes {
    private Long voteQuantity;
    private String number;

    public CountVotes(Long voteQuantity, String presidentNumber) {
        this.voteQuantity = voteQuantity;
        this.number = presidentNumber;
    }

    public Long getVoteQuantity() {
        return voteQuantity;
    }

    public void setVoteQuantity(Long voteQuantity) {
        this.voteQuantity = voteQuantity;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
