package com.example.vladislav.numberlist.contacts;

public class Contact {

    private String phoneNumber;
    private String username;

    public Contact(String phoneNumber, String username) {
        this.phoneNumber = phoneNumber;
        this.username = username;
    }

    public Contact getContact(){
        Contact newContact = new Contact(phoneNumber, username);
        return newContact;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }
}
