package com.example.coducatif;

public class Profile {
    private int userId;
    private String fullName;
    private String nickName;
    private String dob;
    private String phone;

    public Profile(int userId, String fullName, String nickName, String dob, String phone) {
        this.userId = userId;
        this.fullName = fullName;
        this.nickName = nickName;
        this.dob = dob;
        this.phone = phone;
    }

    // Getters
    public int getUserId() { return userId; }
    public String getFullName() { return fullName; }
    public String getNickName() { return nickName; }
    public String getDob() { return dob; }
    public String getPhone() { return phone; }
}