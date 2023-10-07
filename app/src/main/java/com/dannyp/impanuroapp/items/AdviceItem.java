package com.dannyp.impanuroapp.items;

import java.util.Objects;

public class AdviceItem {
    String adviceId,date,titlte,status,imageLink,advice,userId, paymentStatus, paymentRef;
    int monthNumber;


    public AdviceItem(String adviceId,String date, String titlte, String status, String imageLink, String advice, String paymentStatus,String paymentRef, int monthNumber) {
        this.adviceId=adviceId;
        this.date = date;
        this.titlte = titlte;
        this.status = status;
        this.imageLink = imageLink;
        this.advice = advice;
        this.monthNumber = monthNumber;
        this.paymentStatus=paymentStatus;
        this.paymentRef=paymentRef;

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

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getPaymentRef() {
        return paymentRef;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AdviceItem other = (AdviceItem) obj;
        return Objects.equals(this.titlte, other.titlte) &&
                Objects.equals(this.advice, other.advice) &&
                Objects.equals(this.adviceId, other.adviceId) &&
                Objects.equals(this.date, other.date) &&
                Objects.equals(this.status, other.status) &&
                Objects.equals(this.imageLink, other.imageLink) &&
                Objects.equals(this.userId, other.userId) && monthNumber == other.monthNumber;
    }

    // Implement hashCode() method to generate a unique hash code for each Person object
}
