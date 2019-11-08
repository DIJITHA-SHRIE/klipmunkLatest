package com.telcco.klipmunk;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

public class OtpViewModel extends ViewModel {



    public void onClick(View view){
        Log.i("VerifyClicked","clicked");

        Context context = view.getContext();
        Intent intent = new Intent(context, AccessContacts.class);
        context.startActivity(intent);

    }
}
