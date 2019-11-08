package com.telcco.klipmunk.User.MVP;

import com.telcco.klipmunk.LogInResponse;

/**
 * Created by PHD on 1/29/2019.
 */

public interface UserInteractor {
    interface ViewLogInResponse{
        void onLogInsuccess(LogInResponse response);
        void onLogInError(String message);
    }
    void callRetroLogIn(String Email,String Password,ViewLogInResponse response);
}
