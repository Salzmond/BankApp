package org.example.model.dto;

public class ClientUpdateDto {

    private String address;
    private String phone;

    private String taxCode;

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

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    @Override
    public String toString() {
        return "ClientUpdateDto{" +
                "address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", taxCode='" + taxCode + '\'' +
                '}';
    }
}