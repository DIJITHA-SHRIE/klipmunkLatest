
package com.telcco.klipmunk;


import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Connection {

    @SerializedName("clipcount")
    private Long mClipcount;
    @SerializedName("commoncon")
    private Commoncon mCommoncon;


    public Long getClipcount() {
        return mClipcount;
    }

    public void setClipcount(Long clipcount) {
        mClipcount = clipcount;
    }

    public Commoncon getCommoncon() {
        return mCommoncon;
    }

    public void setCommoncon(Commoncon commoncon) {
        mCommoncon = commoncon;
    }



}
