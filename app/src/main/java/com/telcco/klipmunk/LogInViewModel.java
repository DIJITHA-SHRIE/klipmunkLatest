package com.telcco.klipmunk;

import androidx.annotation.MainThread;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.telcco.klipmunk.LogInUser;

import android.os.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInViewModel extends ViewModel {
    public MutableLiveData<String>  emailAdd = new MutableLiveData<>();
    public MutableLiveData<String> passwordAdd = new MutableLiveData<>();
    MutableLiveData<LogInResponse> userMutableLiveData ;
    private MutableLiveData<LogInUser> userData ;
    LogInRepo logInRepo;

    public MutableLiveData<String> getEmailAdd(){
        return emailAdd;
    }
    public MutableLiveData<String> getPasswordAdd(){
        return passwordAdd;
    }

    public MutableLiveData<Integer> busy;

    public MutableLiveData<Integer> getBusy() {

        if (busy == null) {
            busy = new MutableLiveData<>();
            busy.setValue(8);
        }

        return busy;
    }



    public MutableLiveData<LogInResponse> getUser(){
        if(userMutableLiveData == null){
            userMutableLiveData = new MutableLiveData<>();
        }
        return  userMutableLiveData;
    }

    public MutableLiveData<LogInUser> getUserInput(){
        if(userData == null){
            userData = new MutableLiveData<>();
        }
        return  userData;
    }

    public void onClick(View view){
        getBusy().setValue(0);

        LogInUser logInUser = new LogInUser(emailAdd.getValue(), passwordAdd.getValue());
                userData.setValue(logInUser);

    }

    public void init(String email,String password){
       /* if(userMutableLiveData!=null){
            return;
        }*/
       /* logInRepo = LogInRepo.getInstance();
        userMutableLiveData = logInRepo.getLogIn(email, password);
*/
    }


    public void onSignUp(View view){
        Context getContext = view.getContext();
        Intent in = new Intent(getContext,NewSignUp.class);
        getContext.startActivity(in);

    }

    public void getLogIn(String email, String password){
        ApiConstants  apiCall =RetrofitService.createService(ApiConstants.class);
        apiCall.loginAPI(email, password).enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call,
                                   Response<LogInResponse> response) {
                if (response.isSuccessful()){
                    busy.setValue(8);
                    userMutableLiveData.postValue(response.body());
                }
                else{
                    busy.setValue(8);
                    Log.i("fialureLogin","fialureLogin");
                    userMutableLiveData.postValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                busy.setValue(8);
                userMutableLiveData.postValue(null);
                Log.i("MVVMThrow",t.getLocalizedMessage());

            }
        });

    }


}
