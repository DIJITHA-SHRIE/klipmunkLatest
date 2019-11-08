package com.telcco.klipmunk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentResponse {
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("videoScreenshot")
    @Expose
    private String videoScreenshot;
    @SerializedName("clipTimeStamp")
    @Expose
    private ClipTimeStamp clipTimeStamp;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("mention")
    @Expose
    private List<String> mention = null;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("_id")
    @Expose
    private String id;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getVideoScreenshot() {
        return videoScreenshot;
    }

    public void setVideoScreenshot(String videoScreenshot) {
        this.videoScreenshot = videoScreenshot;
    }

    public ClipTimeStamp getClipTimeStamp() {
        return clipTimeStamp;
    }

    public void setClipTimeStamp(ClipTimeStamp clipTimeStamp) {
        this.clipTimeStamp = clipTimeStamp;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getMention() {
        return mention;
    }

    public void setMention(List<String> mention) {
        this.mention = mention;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
