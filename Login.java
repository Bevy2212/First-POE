/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ndprog;

/**
 *
 *  @author Berberly
 */
public class Login {
    
    private String registeredUsername;
    private String registeredPassword;

    public boolean checkUserName(String username) {
        boolean isValid = username.contains("_") && username.length() <= 5;
        if (isValid) {
            this.registeredUsername = username;
        }
        return isValid;
    }

    public boolean checkPasswordComplexity(String password) {
        boolean isValid = password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
        if (isValid) {
            this.registeredPassword = password;
        }
        return isValid;
    }

    public String registerUser(String username, String password, String firstName, String lastName) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }

        return "Registration successful!";
    }

    public boolean loginUser(String username, String password) {
        return username.equals(this.registeredUsername) && password.equals(this.registeredPassword);
    }

    public String returnLoginStatus(boolean loginSuccess, String firstName, String lastName) {
        if (loginSuccess) {
            return "Welcome to EasyKanban. Welcome " + firstName + ", " + lastName + ". Welcome to EasyKanban.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    public
    Object returnLoginStatus(boolean success) {
        return null;
    }
}
