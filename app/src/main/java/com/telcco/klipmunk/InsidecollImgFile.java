package com.telcco.klipmunk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InsidecollImgFile {
    @SerializedName("files")
    @Expose
    private List<Object> files = null;

    public List<Object> getFiles() {
        return files;
    }

    public void setFiles(List<Object> files) {
        this.files = files;
    }
}
