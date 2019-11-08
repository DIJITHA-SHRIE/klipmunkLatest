
package com.telcco.klipmunk;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Profile {

    @SerializedName("__v")
    private Long _V;
    @Expose
    private String _id;
    @Expose
    private String bio;
    @Expose
    private List<String> connection;
    @Expose
    private String dob;
    @Expose
    private String fieldstudy;
    @Expose
    private List<String> hobbies;
    @Expose
    private List<String> interest;
    @Expose
    private Long mobile;
    @Expose
    private List<String> pendingRequest;
    @Expose
    private List<String> publication;
    @Expose
    private List<String> sentRequest;
    @Expose
    private String userDetails;

    public Long get_V() {
        return _V;
    }

    public void set_V(Long _V) {
        this._V = _V;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<String> getConnection() {
        return connection;
    }

    public void setConnection(List<String> connection) {
        this.connection = connection;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getFieldstudy() {
        return fieldstudy;
    }

    public void setFieldstudy(String fieldstudy) {
        this.fieldstudy = fieldstudy;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public List<String> getInterest() {
        return interest;
    }

    public void setInterest(List<String> interest) {
        this.interest = interest;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public List<String> getPendingRequest() {
        return pendingRequest;
    }

    public void setPendingRequest(List<String> pendingRequest) {
        this.pendingRequest = pendingRequest;
    }

    public List<String> getPublication() {
        return publication;
    }

    public void setPublication(List<String> publication) {
        this.publication = publication;
    }

    public List<String> getSentRequest() {
        return sentRequest;
    }

    public void setSentRequest(List<String> sentRequest) {
        this.sentRequest = sentRequest;
    }

    public String getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(String userDetails) {
        this.userDetails = userDetails;
    }

}
