package com.example.theycallme_addy.bsccs;

import java.util.Map;

public class RetreiveAnswer {


    public String answer;
    private Long timestamp;

    public RetreiveAnswer() {

    }

    public RetreiveAnswer(String answer, Long timestamp) {
        this.answer = answer;
        this.timestamp = timestamp;

    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}

