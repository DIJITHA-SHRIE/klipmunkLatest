
package com.telcco.klipmunk;

import java.util.List;
import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Commoncon {

    @SerializedName("connectionlist")
    private List<Object> mConnectionlist;
    @SerializedName("count")
    private Long mCount;

    public List<Object> getConnectionlist() {
        return mConnectionlist;
    }

    public void setConnectionlist(List<Object> connectionlist) {
        mConnectionlist = connectionlist;
    }

    public Long getCount() {
        return mCount;
    }

    public void setCount(Long count) {
        mCount = count;
    }

}
