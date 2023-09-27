package org.example.model.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class ManagerDto {

    private long id;
    private String firstName;
    private String lastName;
    private int status;

    private List<String> clientsName;

    private List<String> products;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ManagerDto(long id, String firstName, String lastName, int status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    public ManagerDto(long id, String firstName, String lastName, int status,
                      List<String> clientsName, List<String> products, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.clientsName = clientsName;
        this.products = products;
        this.createdAt = createdAt.toLocalDateTime();
        this.updatedAt = updatedAt.toLocalDateTime();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<String> getClientsName() {
        return clientsName;
    }

    public void setClientsName(List<String> clientsName) {
        this.clientsName = clientsName;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "ManagerDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status=" + status +
                ", clientsName=" + clientsName +
                ", products=" + products +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}