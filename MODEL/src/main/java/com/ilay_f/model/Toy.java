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

    public Toy(){}
    public void SetName(String name) {this.name = name;}
    public String GetName() {return name;}
    public void SetDescription(String description) {this.description = description;}
    public String GetDescription() {return description;}
    public void SetImage(String image) {this.image = image;}
    public String GetImage() {return image;}
    public void SetPrice(double price) {this.price = price;}
    public double GetPrice() {return price;}
    public void SetQuantity(int quantity) {this.quantity = quantity;}
    public int GetQuantity() {return quantity;}
    public void SetCategoryId(String categoryId) {this.categoryId = categoryId;}
    public String GetCategoryId() {return categoryId;}
    public void SetDesignDate(long designDate) {this.designDate = designDate;}
    public long GetDesignDate() {return designDate;}

    //letaken
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}

