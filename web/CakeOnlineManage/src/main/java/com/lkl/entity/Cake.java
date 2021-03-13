package com.lkl.entity;

/**
 * @author 刘凯丽
 * @createTime 2020/10/28 16:38
 * @projectName first
 * @className Cake.java
 * @description 蛋糕类
 */
public class Cake {

    private int id;//id
    private String name;//名字
    private String description;//描述
    private float price;//价格
    private float size;//尺寸
    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

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



    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }



    @Override
    public String toString() {
        return "Cake [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", size=" + size + "]";
    }
}
