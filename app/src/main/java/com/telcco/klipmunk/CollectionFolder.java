package com.telcco.klipmunk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CollectionFolder {
    @SerializedName("topicName")
    @Expose
    private String topicName;
    @SerializedName("totalclips")
    @Expose
    private Integer totalclips;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("topicId")
    @Expose
    private String topicId;
    @SerializedName("pin")
    @Expose
    private Boolean pin;
    @SerializedName("videoTitle")
    @Expose
    private String videoTitle;
    @SerializedName("videoUrl")
    @Expose
    private String videoUrl;
    @SerializedName("nextclip1")
    @Expose
    private String nextclip1;
    @SerializedName("nextclip2")
    @Expose
    private String nextclip2;
    @SerializedName("videoThumbnail")
    @Expose
    private String videoThumbnail;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Integer getTotalclips() {
        return totalclips;
    }

    public void setTotalclips(Integer totalclips) {
        this.totalclips = totalclips;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public Boolean getPin() {
        return pin;
    }

    public void setPin(Boolean pin) {
        this.pin = pin;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getNextclip1() {
        return nextclip1;
    }

    public void setNextclip1(String nextclip1) {
        this.nextclip1 = nextclip1;
    }

    public String getNextclip2() {
        return nextclip2;
    }

    public void setNextclip2(String nextclip2) {
        this.nextclip2 = nextclip2;
    }

    public String getVideoThumbnail() {
        return videoThumbnail;
    }

    public void setVideoThumbnail(String videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }
}
