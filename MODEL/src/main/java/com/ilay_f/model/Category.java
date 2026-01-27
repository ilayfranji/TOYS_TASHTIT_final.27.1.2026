package com.ilay_f.model;

import com.ilay_f.model.BASE.BaseEntity;
import com.ilay_f.model.BASE.IHasNameAndPicture;

import java.io.Serializable;
import java.util.Objects;

public class Category extends BaseEntity
        implements Serializable, IHasNameAndPicture {

    private String categoryName;
    private String categoryPicture;

    public Category() {}

    public Category(String categoryName, String categoryPicture) {
        this.categoryName    = categoryName;
        this.categoryPicture = categoryPicture;
    }
    public String getCategoryPicture() {
        return categoryPicture;
    }

    public void setCategoryPicture(String categoryPicture) {
        this.categoryPicture = categoryPicture;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String getName() {
        return categoryName;
    }

    @Override
    public void setName(String name) {
        categoryName = name;
    }

    @Override
    public String getPicture() {
        return categoryPicture;
    }

    @Override
    public void setPicture(String picture) {
        categoryPicture = picture;
    }

    @Override
    public String toString() {
        return categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Category category)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(categoryName, category.categoryName) &&
                Objects.equals(categoryPicture, category.categoryPicture);
    }
}


