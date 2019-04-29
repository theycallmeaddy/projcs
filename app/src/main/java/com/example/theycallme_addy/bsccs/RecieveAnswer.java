package com.example.theycallme_addy.bsccs;

import java.util.Map;

public class RecieveAnswer {
    public String answer;
    private Map<String, String> timestamp;

    public RecieveAnswer(){

    }

    public RecieveAnswer(String answer, Map timestamp){
        this.answer = answer;
        this.timestamp = timestamp;

    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Map<String, String> getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Map<String, String> timestamp) {
        this.timestamp = timestamp;
    }
}
