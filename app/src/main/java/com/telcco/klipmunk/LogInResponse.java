package com.telcco.klipmunk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogInResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("userDetail")
    @Expose
    private LogInResponseUserDetail userDetail;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LogInResponseUserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(LogInResponseUserDetail userDetail) {
        this.userDetail = userDetail;
    }
}
