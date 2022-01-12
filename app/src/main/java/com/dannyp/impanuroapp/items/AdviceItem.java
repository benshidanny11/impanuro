package com.dannyp.impanuroapp.items;

public class AdviceItem {
    String date,titlte,status,imageLink,advice;
    int monthNumber;

    public AdviceItem(String date, String titlte, String status, String imageLink, String advice, int monthNumber) {
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



    public String getStatus() {
        return status;
    }


    public String getImageLink() {
        return imageLink;
    }

    public int getMonthNumber() {
        return monthNumber;
    }
}
