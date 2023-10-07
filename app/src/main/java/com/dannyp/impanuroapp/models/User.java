package com.dannyp.impanuroapp.models;

public class User {
    String id;
    String name;
    String email;
    String phoneNumber;

    public User(String name, String email, String phoneNumber) {

        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id!=null?id:"";
    }

    public String getName() {
         return name!=null?name:"";
    }

    public String getEmail() {
        return email!=null?email:"";
    }

    public String getPhoneNumber() {
        return phoneNumber!=null?phoneNumber:"";
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
