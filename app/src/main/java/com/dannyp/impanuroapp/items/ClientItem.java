package com.dannyp.impanuroapp.items;

public class ClientItem {
   private String clientNumber;
    private String clientEmail;

    public ClientItem(String clientNumber, String clientEmail) {
        this.clientNumber = clientNumber;
        this.clientEmail = clientEmail;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public String getClientEmail() {
        return clientEmail;
    }
}
