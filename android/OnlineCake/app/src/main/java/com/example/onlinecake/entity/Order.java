package com.example.onlinecake.entity;

public class Order {
    private String customPhone;

    public String getCustomPhone() {
        return customPhone;
    }

    public void setCustomPhone(String customPhone) {
        this.customPhone = customPhone;
    }

    public int getCakeId() {
        return cakeId;
    }

    public void setCakeId(int cakeId) {
        this.cakeId = cakeId;
    }

    private int cakeId;
    private int count;
    private String state;



    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customPhone='" + customPhone + '\'' +
                ", cakeId=" + cakeId +
                ", count=" + count +
                ", state='" + state + '\'' +
                '}';
    }
}
