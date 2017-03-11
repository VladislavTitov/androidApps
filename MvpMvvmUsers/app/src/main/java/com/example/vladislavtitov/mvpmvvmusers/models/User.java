package com.example.vladislavtitov.mvpmvvmusers.models;

public class User {

    private String name;
    private String surname;
    private String email;

    public User(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User){
            if (this.name.equals(((User) obj).getName()) && this.surname.equals(((User) obj).getSurname())
                    && this.email.equals(((User) obj).getEmail())){
                return true;
            }
        }
        return false;
    }
}
