
package com.telcco.klipmunk;

import java.util.List;
import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class GetProfile {

    @SerializedName("bio")
    private String mBio;
    @SerializedName("clipcount")
    private Long mClipcount;
    @SerializedName("connection")
    private List<Connection> mConnection;
    @SerializedName("dob")
    private String mDob;
    @SerializedName("fieldstudy")
    private String mFieldstudy;
    @SerializedName("hobbies")
    private List<String> mHobbies;
    @SerializedName("interest")
    private List<String> mInterest;
    @SerializedName("mobile")
    private Long mMobile;

    @SerializedName("publication")
    private List<String> mPublication;



    @SerializedName("__v")
    private Long m_V;
    @SerializedName("_id")
    private String m_id;

    public String getBio() {
        return mBio;
    }

    public void setBio(String bio) {
        mBio = bio;
    }

    public Long getClipcount() {
        return mClipcount;
    }

    public void setClipcount(Long clipcount) {
        mClipcount = clipcount;
    }

    public List<Connection> getConnection() {
        return mConnection;
    }

    public void setConnection(List<Connection> connection) {
        mConnection = connection;
    }

    public String getDob() {
        return mDob;
    }

    public void setDob(String dob) {
        mDob = dob;
    }

    public String getFieldstudy() {
        return mFieldstudy;
    }

    public void setFieldstudy(String fieldstudy) {
        mFieldstudy = fieldstudy;
    }

    public List<String> getHobbies() {
        return mHobbies;
    }

    public void setHobbies(List<String> hobbies) {
        mHobbies = hobbies;
    }

    public List<String> getInterest() {
        return mInterest;
    }

    public void setInterest(List<String> interest) {
        mInterest = interest;
    }

    public Long getMobile() {
        return mMobile;
    }

    public void setMobile(Long mobile) {
        mMobile = mobile;
    }

    public List<String> getPublication() {
        return mPublication;
    }

    public void setPublication(List<String> publication) {
        mPublication = publication;
    }




    public Long get_V() {
        return m_V;
    }

    public void set_V(Long _V) {
        m_V = _V;
    }

    public String get_id() {
        return m_id;
    }

    public void set_id(String _id) {
        m_id = _id;
    }

}
