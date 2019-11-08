package com.telcco.klipmunk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CollectionResponse {
    @SerializedName("topicThumbnail")
    @Expose
    private ArrayList<CollectionFolder> topicThumbnail = null;

    public ArrayList<CollectionFolder> getTopicThumbnail() {
        return topicThumbnail;
    }

    public void setTopicThumbnail(ArrayList<CollectionFolder> topicThumbnail) {
        this.topicThumbnail = topicThumbnail;
    }
}
