package com.telcco.klipmunk;

/**
 * Created by PHD on 2/7/2019.
 */

public class DateItem  extends ListItem{
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int getType() {
        return TYPE_DATE;
    }
}
