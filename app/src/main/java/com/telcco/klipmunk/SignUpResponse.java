package com.telcco.klipmunk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpResponse {

    @SerializedName("user")
    @Expose
    private SignUpResponseUserDetail user;

    public SignUpResponseUserDetail getUser() {
        return user;
    }

    public void setUser(SignUpResponseUserDetail user) {
        this.user = user;
    }

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
