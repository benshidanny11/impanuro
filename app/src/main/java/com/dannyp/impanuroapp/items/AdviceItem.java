package com.dannyp.impanuroapp.items;

public class AdviceItem {
    String day,date,titlte,description,status,clientNumber;

    public AdviceItem(String day, String date, String titlte, String description, String status, String clientNumber) {
        this.day = day;
        this.date = date;
        this.titlte = titlte;
        this.description = description;
        this.status = status;
    }

    public String getDay() {
        return day;
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

    public String getClientNumber() {
        return clientNumber;
    }
}
