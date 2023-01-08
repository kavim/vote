package com.api.vote.models.custom;

import java.util.HashMap;

public class ElectionFinishReturn {
    private String message;

    private String finishedAt;

    private HashMap<String, String> votes;

    public ElectionFinishReturn(String message, String finishedAt, HashMap<String, String> votes) {
        this.message = message;
        this.finishedAt = finishedAt;
        this.votes = votes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(String finishedAt) {
        this.finishedAt = finishedAt;
    }

    public HashMap<String, String> getVotes() {
        return votes;
    }

    public void setVotes(HashMap<String, String> votes) {
        this.votes = votes;
    }
}
