package com.example.onlinecake.entity;

import android.graphics.Bitmap;

public class CustomerInfo {
    private String phone;
    private String address;
    private String email;
    private String nikeName;
    private Bitmap header;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public Bitmap getHeader() {
        return header;
    }

    public void setHeader(Bitmap header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "CustomInfo{" +
                "phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", nikeName='" + nikeName + '\'' +
                ", header=" + header +
                '}';
    }
}
