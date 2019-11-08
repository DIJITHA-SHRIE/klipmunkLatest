package com.telcco.klipmunk;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ForgotPasswordViewModel extends ViewModel {

    public MutableLiveData<ForgotPasswordResponse> emailforget = new MutableLiveData<>();

    private LogInRepo logInRepo = new LogInRepo();


    void setForgotPassword(String email) {
        // emailforget = logInRepo.getForgotPassword(email);
        emailforget.setValue(logInRepo.getForgotPassword(email).getValue());

    }

    public MutableLiveData<ForgotPasswordResponse> getForgotPassword() {

        return emailforget;
    }
}
