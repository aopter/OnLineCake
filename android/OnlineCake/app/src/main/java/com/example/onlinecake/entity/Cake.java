package com.example.onlinecake.entity;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class Cake {
    private int id;//id
    private String name;//名字
    private String description;//描述
    private float price;//价格
    private Bitmap bigImg;//大图
    private float size;//尺寸、、
    private List<Bitmap> images = new ArrayList<>();//小图
    private String sellerPhone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Bitmap getBigImg() {
        return bigImg;
    }

    public void setBigImg(Bitmap bigImg) {
        this.bigImg = bigImg;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public List<Bitmap> getImages() {
        return images;
    }

    public void setImages(List<Bitmap> images) {
        this.images = images;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    @Override
    public String toString() {
        return "Cake{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", bigImg=" + bigImg +
                ", size=" + size +
                ", images=" + images +
                ", sellerPhone='" + sellerPhone + '\'' +
                '}';
    }
}
