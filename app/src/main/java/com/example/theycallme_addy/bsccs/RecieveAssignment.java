package com.example.theycallme_addy.bsccs;


public class RecieveAssignment {


        public  String asgTitle;
        public  String subDate;
        public  String asgQuestions;
        private Long timestamp;


        public RecieveAssignment(){

        }

        public RecieveAssignment( String asgTitle, String subDate, String asgQuestions , Long timestamp) {
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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
