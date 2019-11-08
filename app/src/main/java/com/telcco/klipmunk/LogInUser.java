package com.telcco.klipmunk;

import android.util.Patterns;

import java.util.regex.Pattern;

public class LogInUser {
    private String email,password;

    public LogInUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEmailValid(){
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }

    public boolean isPasswordLengthGreaterThan6(){
        return getPassword().length()>=6;
    }
}
