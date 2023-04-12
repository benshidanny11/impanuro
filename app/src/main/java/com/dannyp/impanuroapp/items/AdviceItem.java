package com.dannyp.impanuroapp.items;

public class AdviceItem {
    String adviceId,date,titlte,status,imageLink,advice,userId;
    int monthNumber;


    public AdviceItem(String adviceId,String date, String titlte, String status, String imageLink, String advice, int monthNumber) {
        this.adviceId=adviceId;
        this.date = date;
        this.titlte = titlte;
        this.status = status;
        this.imageLink = imageLink;
        this.advice = advice;
        this.monthNumber = monthNumber;
    }

    public String getAdvice() {
        return advice;
    }

    public String getDate() {
        return date;
    }

    public String getTitlte() {
        return titlte;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getAdviceId() {
        return adviceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageLink() {
        return imageLink;
    }

    public int getMonthNumber() {
        return monthNumber;
    }
}
