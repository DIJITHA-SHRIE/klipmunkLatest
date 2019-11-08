package com.telcco.klipmunk;

/**
 * Created by PHD on 2/7/2019.
 */

public abstract class ListItem {
    public static final int TYPE_DATE = 0;
    public static final int TYPE_GENERAL = 1;

    abstract public int getType();
}
