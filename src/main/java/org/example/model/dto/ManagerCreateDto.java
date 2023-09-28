package org.example.model.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ManagerCreateDto {

    private String firstName;
    private String lastName;

    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ManagerCreateDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
        this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ManagerCreateDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}