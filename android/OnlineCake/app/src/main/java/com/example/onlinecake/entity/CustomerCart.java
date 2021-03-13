package com.example.onlinecake.entity;

public class CustomerCart {
    private String customerPhone;
    private int cakeId;
    private int count;

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public int getCakeId() {
        return cakeId;
    }

    public void setCakeId(int cakeId) {
        this.cakeId = cakeId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CustomCart{" +
                "customerPhone='" + customerPhone + '\'' +
                ", cakeId=" + cakeId +
                ", count=" + count +
                '}';
    }
}
