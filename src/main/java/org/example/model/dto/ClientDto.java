package org.example.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.model.enums.UserStatus;

import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto {

    private long id;
    private String managersFirstName;
    private String managersSecondName;
    private UserStatus status;
    private String taxCode;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ClientDto(long id, String managersFirstName,
                     String managersSecondName,
                     UserStatus status, String taxCode,
                     String firstName, String lastName, String email, String address,
                     String phone, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.managersFirstName = managersFirstName;
        this.managersSecondName = managersSecondName;
        this.status = status;
        this.taxCode = taxCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getManagersFirstName() {
        return managersFirstName;
    }

    public void setManagersFirstName(String managersFirstName) {
        this.managersFirstName = managersFirstName;
    }

    public String getManagersSecondName() {
        return managersSecondName;
    }

    public void setManagersSecondName(String managersSecondName) {
        this.managersSecondName = managersSecondName;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "managersFirstName='" + managersFirstName + '\'' +
                ", managersSecondName='" + managersSecondName + '\'' +
                ", status=" + status +
                ", taxCode='" + taxCode + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}