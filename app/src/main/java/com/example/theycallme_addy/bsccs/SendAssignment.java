package com.example.theycallme_addy.bsccs;


import java.util.Map;

public class SendAssignment {

    public  String asgTitle;
    public  String subDate;
    public  String asgQuestions;
    private Map<String, String> timestamp;


    public SendAssignment(){

    }

    public SendAssignment( String asgTitle, String subDate, String asgQuestions , Map timestamp) {
        this.asgTitle = asgTitle;
        this.subDate = subDate;
        this.asgQuestions = asgQuestions;
        this.timestamp = timestamp;

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


}

