package com.example.onlinecake.entity;

public class CustomerLogin {

    private String phone;
    private String passWord;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


    public String getPhone() {
        return phone;
    }

    public String getPassWord() {
        return passWord;
    }

    @Override
    public String toString() {
        return "CustomLogin{" +
                "phone='" + phone + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
