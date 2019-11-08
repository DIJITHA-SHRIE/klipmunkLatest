package com.telcco.klipmunk;

public class SignupModel {
    String name,email,password,password2;

    public SignupModel(String name, String email, String password, String password2) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.password2 = password2;
    }

    public String getFirstname() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPassword2() {
        return password2;
    }
}
