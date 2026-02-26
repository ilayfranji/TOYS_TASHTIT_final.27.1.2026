package com.ilay_f.model;

import com.ilay_f.model.BASE.BaseEntity;

import java.io.Serializable;
import java.util.Objects;

public class User extends BaseEntity implements Serializable {//סריאלייזבל מאפשר לילהעביר נתונים מאקטיביטי לאקטיביטי ( מעביר אותם כסטרינגים בין המסכים )
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

    public enum UserType{
        GUEST,REGISTERED,ADMIN
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(idFs, user.idFs) && Objects.equals(userName, user.userName) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(image, user.image) && userType == user.userType;
    }
}
