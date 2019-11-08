package com.telcco.klipmunk;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UpdateProfileViewModel extends ViewModel {

    public MutableLiveData<UpdateProfileResponse> updateProfileResponseMutableLiveData = new MutableLiveData<>();


    private LogInRepo logInRepo = new LogInRepo();


    void setUpdateProfileViewModel(String userid, String username, String dob,String fieldstudy, String hobbies, String interest, String mobile, String bio) {


        updateProfileResponseMutableLiveData.setValue(logInRepo.getUpdateProfile(userid,username, dob, fieldstudy
                , hobbies, interest, mobile, bio).getValue());


    }



    public MutableLiveData<UpdateProfileResponse> getUpdateProfileResponse(){
        return updateProfileResponseMutableLiveData;
    }

}
