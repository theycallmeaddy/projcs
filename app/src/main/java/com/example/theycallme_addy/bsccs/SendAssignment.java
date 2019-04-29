package com.example.theycallme_addy.bsccs;


import java.util.Map;

public class SendAssignment {

    public  String asgTitle;
    public  String subDate,subMonth,subYear;
    public  String asgQuestions;
    private Map<String, String> timestamp;


    public SendAssignment(){

    }

    public SendAssignment( String asgTitle, String subDate, String subMonth, String subYear,  String asgQuestions , Map timestamp) {
        this.asgTitle = asgTitle;
        this.subDate = subDate;
        this.asgQuestions = asgQuestions;
        this.timestamp = timestamp;
        this.subDate = subDate;
        this.subMonth = subMonth;
        this.subYear = subYear;


    }

    public String getAsgTitle() {
        return asgTitle;
    }

    public void setAsgTitle(String asgTitle) {
        this.asgTitle = asgTitle;
    }

    public String getSubDate() {
        return subDate;
    }

    public void setSubDate(String subDate) {
        this.subDate = subDate;
    }

    public String getAsgQuestions() {
        return asgQuestions;
    }

    public void setAsgQuestions(String asgQuestions) {
        this.asgQuestions = asgQuestions;
    }

    public void setTimestamp(Map<String, String> timeStamp) {this.timestamp= timestamp;}
    public Map<String, String> getTimestamp() {return timestamp;}

    public String getSubMonth() {
        return subMonth;
    }

    public void setSubMonth(String subMonth) {
        this.subMonth = subMonth;
    }

    public String getSubYear() {
        return subYear;
    }

    public void setSubYear(String subYear) {
        this.subYear = subYear;
    }
}

