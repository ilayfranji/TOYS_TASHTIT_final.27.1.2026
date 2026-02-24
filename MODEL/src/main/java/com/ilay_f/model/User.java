package com.ilay_f.model;

import com.ilay_f.model.BASE.BaseEntity;

import java.io.Serializable;

public class User extends BaseEntity implements Serializable {
    private String idFs;//איי די ייחודי לכל משתמש שישמר בפייר סטור
    private String userName;
    private String email;
    private String password;
    private String image;
    private UserType userType;//משתנה שנלקח ממחלקה סוג משתמש אם הוא רשום, אורח או מנהל האפליקציה

    public User() {
    }

    public User(String idFs, String userName, String email, String password, String image, UserType userType) {
        this.idFs = idFs;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.image = image;
        this.userType = userType;
    }

    public String getIdFs() {
        return idFs;
    }

    public void setIdFs(String idFs) {
        this.idFs = idFs;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
