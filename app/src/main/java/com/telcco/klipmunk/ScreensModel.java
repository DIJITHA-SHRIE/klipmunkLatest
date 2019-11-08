package com.telcco.klipmunk;

/**
 * Created by PHD on 1/12/2019.
 */

public class ScreensModel {
    String path,notes,tag,groupDate;

    public ScreensModel(String path, String notes,String groupDate) {
        this.path = path;
        this.notes = notes;
        this.groupDate=groupDate;
    }

    public String getPath() {
        return path;
    }

    public String getNotes() {
        return notes;
    }

    public String getTag() {
        return tag;
    }

    public String getGroupDate(){
        return groupDate;
    }
}
