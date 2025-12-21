package br.com.techchallenge.rating.model.api;

public class RatingResult {
    private String id;
    private Boolean critical;

    public RatingResult() {
    }

    public RatingResult(String id, Boolean critical) {
        this.id = id;
        this.critical = critical;
    }

    public String getId() {
        return id;
    }

    public Boolean getCritical() {
        return critical;
    }
}