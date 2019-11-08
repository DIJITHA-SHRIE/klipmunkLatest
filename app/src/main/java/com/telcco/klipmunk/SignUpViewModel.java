package com.telcco.klipmunk;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends AndroidViewModel {
    public MutableLiveData<String> emailAdd = new MutableLiveData<>();
    public MutableLiveData<String> passwordAdd = new MutableLiveData<>();
    public MutableLiveData<String> nameAdd = new MutableLiveData<>();
    MutableLiveData<SignUpResponse> signUpResponseMutableLiveData;
    MutableLiveData<SignupModel> signupModelMutableLiveData ;

    public MutableLiveData<Integer> busy;

    public MutableLiveData<Integer> getBusy() {

        if (busy == null) {
            busy = new MutableLiveData<>();
            busy.setValue(8);
        }

        return busy;
    }


    LogInRepo logInRepo;

    ApiConstants apiCall;

    public SignUpViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<SignUpResponse> getUser(){
        if(signUpResponseMutableLiveData == null){                                    // to observe
            signUpResponseMutableLiveData = new MutableLiveData<>();
        }
        return  signUpResponseMutableLiveData;
    }

    public MutableLiveData<SignupModel> getUserInput(){
        if(signupModelMutableLiveData == null){
            signupModelMutableLiveData = new MutableLiveData<>();                  // to observe
        }
        return  signupModelMutableLiveData;
    }

    public void onClick(View view){
        getBusy().setValue(0);
        SignupModel logInUser = new SignupModel(nameAdd.getValue(),emailAdd.getValue(),passwordAdd.getValue(),passwordAdd.getValue());
        signupModelMutableLiveData.setValue(logInUser);

    }

    public void onGoogleSign(View view){
        getBusy().setValue(0);


    }

    public void init(String name,String email,String password){
      /*  if(signUpResponseMutableLiveData != null){
            return;
        }*/
       /* logInRepo = LogInRepo.getInstance();
        signUpResponseMutableLiveData = getSignup(name,email, password,password);*/


    }

    public void getSignup(String name, String email,String password,String password2){
        apiCall =RetrofitService.createService(ApiConstants.class);

        apiCall.signupAPI(name, email,password,password2).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call,
                                   Response<SignUpResponse> response) {
                if (response.isSuccessful()){
                    busy.setValue(8);
                    signUpResponseMutableLiveData.postValue(response.body());
                }
                else{
                    busy.setValue(8);
                    Log.i("signUpRetroFailure","signUpFailure");
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                busy.setValue(8);
                signUpResponseMutableLiveData.postValue(null);
                Log.i("MVVMThrow",t.getLocalizedMessage());

            }
        });


    }



}
