package com.telcco.klipmunk;

import android.util.Log;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInRepo {
    private static LogInRepo logInRepo;
    private ApiConstants apiCall;

    final MutableLiveData<SignUpResponse> signUpData = new MutableLiveData<>();
    MutableLiveData<LogInResponse> loginData = new MutableLiveData<>();
    private MutableLiveData<ForgotPasswordResponse> forgotdata = new MutableLiveData<>();
    private MutableLiveData<UpdateProfileResponse> updatedata = new MutableLiveData<>();
    //private MutableLiveData<GetProfileResponse> getdata = new MutableLiveData<>();


    public static LogInRepo getInstance() {
        if (logInRepo == null) {
            logInRepo = new LogInRepo();
        }
        return logInRepo;
    }

    public LogInRepo() {
        apiCall = RetrofitService.createService(ApiConstants.class);
    }

    public MutableLiveData<LogInResponse> getLogIn(String email, String password) {

        apiCall.loginAPI(email, password).enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call,
                                   Response<LogInResponse> response) {
                if (response.isSuccessful()) {
                    loginData.postValue(response.body());
                } else {
                    Log.i("fialureLogin", "fialureLogin");
                }
            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                loginData.setValue(null);
                Log.i("MVVMThrow", t.getLocalizedMessage());

            }
        });

        return loginData;
    }

    public MutableLiveData<SignUpResponse> getSignup(String firstname, String email, String password, String password2) {

        apiCall.signupAPI(firstname, email, password, password2).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call,
                                   Response<SignUpResponse> response) {
                if (response.isSuccessful()) {
                    signUpData.postValue(response.body());
                } else {
                    Log.i("signUpRetroFailure", "signUpFailure");
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                signUpData.setValue(null);
                Log.i("MVVMThrow", t.getLocalizedMessage());

            }
        });

        return signUpData;
    }


    public MutableLiveData<ForgotPasswordResponse> getForgotPassword(String email) {

        apiCall.forgotAPI(email).enqueue(new Callback<ForgotPasswordResponse>() {

            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                if (response.isSuccessful()) {

                    forgotdata.postValue(response.body());

                } else {

                    Log.i("RetroFailure", "Failure");
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
                forgotdata.setValue(null);
                Log.i("MVVMThrow", t.getLocalizedMessage());

            }
        });

        return forgotdata;
    }

    public MutableLiveData<UpdateProfileResponse> getUpdateProfile(String userid,String username, String dob, String fieldstudy, String hobbies
            , String interest, String mobile, String bio) {

        apiCall.updateAPI(userid,username, dob, fieldstudy, hobbies, interest, mobile, bio).enqueue(new Callback<UpdateProfileResponse>() {

            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                if (response.isSuccessful()) {

                    updatedata.postValue(response.body());

                } else {

                    Log.i("RetroFailure", "Failure");
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
                updatedata.setValue(null);
                Log.i("MVVMThrow", t.getLocalizedMessage());

            }
        });

        return updatedata;
    }


   // public MutableLiveData<GetProfileResponse> getGetProfile()


}
