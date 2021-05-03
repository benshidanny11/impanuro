package com.dannyp.impanuroapp.items;

public class AdviceItem {
    String date,titlte,description,status,comment,imageLink;
    int monthNumber;

    public AdviceItem( int monthNumber,String date, String titlte, String description, String status, String comment, String imageLink) {
        this.date = date;
        this.titlte = titlte;
        this.description = description;
        this.status = status;
        this.comment = comment;
        this.imageLink = imageLink;
        this.monthNumber = monthNumber;
    }

    public String getDate() {
        return date;
    }

    public String getTitlte() {
        return titlte;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getComment() {
        return comment;
    }

    public String getImageLink() {
        return imageLink;
    }

    public int getMonthNumber() {
        return monthNumber;
    }
}
