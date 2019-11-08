package com.telcco.klipmunk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CollectionSingleVideo {
    @SerializedName("folder")
    @Expose
    private String folder;
    @SerializedName("totalVideos")
    @Expose
    private Integer totalVideos;
    @SerializedName("videoTitle")
    @Expose
    private String videoTitle;
    @SerializedName("videoThumbnail")
    @Expose
    private String videoThumbnail;
    @SerializedName("nextArticle1")
    @Expose
    private String nextArticle1;
    @SerializedName("nextArticle2")
    @Expose
    private String nextArticle2;

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public Integer getTotalVideos() {
        return totalVideos;
    }

    public void setTotalVideos(Integer totalVideos) {
        this.totalVideos = totalVideos;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoThumbnail() {
        return videoThumbnail;
    }

    public void setVideoThumbnail(String videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }

    public String getNextArticle1() {
        return nextArticle1;
    }

    public void setNextArticle1(String nextArticle1) {
        this.nextArticle1 = nextArticle1;
    }

    public String getNextArticle2() {
        return nextArticle2;
    }

    public void setNextArticle2(String nextArticle2) {
        this.nextArticle2 = nextArticle2;
    }
}
