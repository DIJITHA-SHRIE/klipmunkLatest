package com.telcco.klipmunk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewFolderResponse {
    @SerializedName("folderName")
    @Expose
    private String folderName;
    @SerializedName("newFolderAdded")
    @Expose
    private Boolean newFolderAdded;

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public Boolean getNewFolderAdded() {
        return newFolderAdded;
    }

    public void setNewFolderAdded(Boolean newFolderAdded) {
        this.newFolderAdded = newFolderAdded;
    }
}
