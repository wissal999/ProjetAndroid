package com.example.mpdamproject2021.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    private String fullname;
    private String username;
    private String phone;
    private String email;
    private String password;
    @SerializedName("success")
    private String success;


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public User(String fullname, String username, String phone, String email, String password, String success) {
        this.fullname = fullname;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.success = success;
    }
}
