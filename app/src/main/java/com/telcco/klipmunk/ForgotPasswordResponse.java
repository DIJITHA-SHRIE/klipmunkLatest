
package com.telcco.klipmunk;

import com.google.gson.annotations.SerializedName;


public class ForgotPasswordResponse {

    @SerializedName("msg")
    private String mMsg;

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
        mMsg = msg;
    }

}
