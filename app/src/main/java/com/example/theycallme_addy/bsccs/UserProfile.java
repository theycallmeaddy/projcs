package com.example.theycallme_addy.bsccs;

public class UserProfile {

    public  String userName;
    public  String userEmail;
    public  String userRoll;
    public  Boolean id;
    public  String year;

    public UserProfile(){

    }

    public UserProfile( String userEmail, String userName, String userRoll, Boolean id, String year) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userRoll = userRoll;
        this.id = id;
        this.year = year;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserRoll() {
        return userRoll;
    }

    public void setUserRoll(String userRoll) {
        this.userRoll = userRoll;
    }

    public Boolean getId() {
        return id;
    }

    public void setId(Boolean id) { this.id = id; }

    public String getYear() { return year; }

    public void setYear(String year) { this.year = year; }
}
