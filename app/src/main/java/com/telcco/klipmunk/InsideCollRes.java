package com.telcco.klipmunk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class InsideCollRes {
    @SerializedName("clipList")
    @Expose
    private ArrayList<InsideCollVideoList> videoList = null;

    private ArrayList<InsideCollVideoList> viewArticle =null;

    public ArrayList<InsideCollVideoList> getVideoList() {
        return videoList;
    }

    public void setVideoList(ArrayList<InsideCollVideoList> videoList) {
        this.videoList = videoList;
    }

    public ArrayList<InsideCollVideoList> getViewArticle() {
        return viewArticle;
    }

    public void setViewArticle(ArrayList<InsideCollVideoList> articleList) {
        this.viewArticle = articleList;
    }
}
