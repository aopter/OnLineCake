package com.lkl.entity;

/**
 * @author 刘凯丽
 * @createTime 2020/10/29 12:58
 * @projectName first
 * @className Type.java
 * @description 蛋糕类型
 */
public class Type {
    private int typeId;
    private Type parentType;
    private String name;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public Type getParentType() {
        return parentType;
    }

    public void setParentType(Type parentType) {
        this.parentType = parentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
