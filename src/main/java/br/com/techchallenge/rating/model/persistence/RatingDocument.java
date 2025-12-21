package br.com.techchallenge.rating.model.persistence;

import br.com.techchallenge.rating.model.utils.NotificationStatus;

import java.time.Instant;

public class RatingDocument {
    private String id;
    private Integer rating;
    private String description;
    private String email;
    private Boolean critical;
    private Instant createdAt;
    private Instant updatedAt;
    private NotificationStatus notificationStatus;

    public RatingDocument() {
    }

    public RatingDocument(String id, Integer rating, String description, String email, Boolean critical,
                          Instant createdAt, Instant updatedAt, NotificationStatus notificationStatus) {
        this.id = id;
        this.rating = rating;
        this.description = description;
        this.email = email;
        this.critical = critical;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.notificationStatus = notificationStatus;
    }

    public String getId() {
        return id;
    }

    public Integer getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getCritical() {
        return critical;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public NotificationStatus getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(NotificationStatus notificationStatus) {
        this.notificationStatus = notificationStatus;
        this.updatedAt = Instant.now();
    }
}
