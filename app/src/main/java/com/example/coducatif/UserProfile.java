package com.example.coducatif;

public class UserProfile {
    private String fullName;
    private String nickName;
    private String dob;
    private String phone;

    public UserProfile(String fullName, String nickName, String dob, String phone) {
        this.fullName = fullName;
        this.nickName = nickName;
        this.dob = dob;
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getDob() {
        return dob;
    }

    public String getPhone() {
        return phone;
    }
}