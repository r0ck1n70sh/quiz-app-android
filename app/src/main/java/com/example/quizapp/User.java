package com.example.quizapp;

import java.io.Serializable;

public class User implements Serializable {
    private String firstName;
    private String lastName;
    public String username;

    public User(String givenUserName){
        username = givenUserName;
    }
    public void setName(String fName, String lName){
        firstName = fName;
        lastName = lName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}