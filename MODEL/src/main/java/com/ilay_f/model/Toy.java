package com.ilay_f.model;

import com.ilay_f.model.BASE.BaseEntity;

import java.io.Serializable;

public class Toy extends BaseEntity implements Serializable
{
    private String name;
    private String description;
    private String image;    // String כדי שניתן יהיה לשמור
    //  במסד הנתונים
    private double price;
    private int quantity;
    private String categoryId;
    private long designDate;  // long התנהלות נכונה יותר מול
    //     מסד הנתונים


    public Toy() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public long getDesignDate() {
        return designDate;
    }

    public void setDesignDate(long designDate) {
        this.designDate = designDate;
    }

    //letaken
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}

