package com.telcco.klipmunk;

public class UpdateProfileModel {

    String userid,username,dob,hobbies,interest,mobile,bio,fieldstudy;

    public UpdateProfileModel(String userid, String username, String dob, String hobbies, String interest, String mobile, String bio,String fieldstudy) {
        this.userid = userid;
        this.username = username;
        this.dob = dob;
        this.hobbies = hobbies;
        this.interest = interest;
        this.mobile = mobile;
        this.bio = bio;
        this.fieldstudy=fieldstudy;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFieldstudy() {
        return fieldstudy;
    }

    public void setFieldstudy(String fieldstudy) {
        this.fieldstudy = fieldstudy;
    }
}
