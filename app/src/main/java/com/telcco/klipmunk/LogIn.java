package com.telcco.klipmunk;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.telcco.klipmunk.databinding.ActivityLogInBinding;


import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.internal.Util;

public class LogIn extends AppCompatActivity {
    @BindView(R.id.admin)
    TextView tag_login;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.signUp_text)
    TextView signUp_text;
    @BindString(R.string.tagLine)
    String tagline_str;
    @BindView(R.id.tv_forgotpass)
    TextView tv_forgotpass;
    private static final int MY_STORAGE_REQUEST_CODE = 100;
    private static final int DRAW_OVER_OTHER_APP_PERMISSION = 123;
    private static final int REQUEST_MEDIA_PROJECTION = 1;
    public static Intent dataG;

    private LogInViewModel logInViewModel;
    private ActivityLogInBinding binding;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DRAW_OVER_OTHER_APP_PERMISSION) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (!Settings.canDrawOverlays(this)) {
                    //Permission is not available. Display error text.
                    errorToast();
                    finish();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (requestCode == REQUEST_MEDIA_PROJECTION) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O || Settings.canDrawOverlays(this)) {
                dataG = data;
                Intent in = new Intent(LogIn.this, FloatingWidgetService.class);
                in.putExtra("activity_background", true);
                in.putExtra("resultCode", resultCode);
                startService(in);
                finish();
            } else {
                errorToast();
            }

        } else {
            Toast.makeText(this, "You denied the permission.", Toast.LENGTH_SHORT).show();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logInViewModel = ViewModelProviders.of(this).get(LogInViewModel.class);
        binding = DataBindingUtil.setContentView(LogIn.this, R.layout.activity_log_in);
        binding.setLifecycleOwner(this);
        binding.setLoginViewModel(logInViewModel);

        ButterKnife.bind(this);
        tag_login.setText(Html.fromHtml(tagline_str));

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_STORAGE_REQUEST_CODE);
        }

        askForSystemOverlayPermission();

        //logInViewModel.init("diji93@gmail.com", "diji@1234");


        logInViewModel.getUserInput().observe(this, new Observer<LogInUser>() {
            @Override
            public void onChanged(LogInUser logInUser) {
                logInViewModel.getLogIn(logInUser.getEmail().trim(), logInUser.getPassword().trim());
            }
        });

        logInViewModel.getUser().observe(this, new Observer<LogInResponse>() {
            @Override
            public void onChanged(LogInResponse logInResponse) {

              //  Boolean status = logInResponse.getSuccess();
                try{
                if(logInResponse!=null){
                LogInResponseUserDetail userDetail = logInResponse.getUserDetail();
                Log.i("MVVMO/P", userDetail.getName());
                    Intent in = new Intent(LogIn.this, HomeFragment.class);
                    Log.i("UserId",userDetail.getId());
                    in.putExtra(UtilConstants.USER_ID,userDetail.getId());
                    startActivity(in);
                    finish();

                }
                else{
                    Log.i("MVVMO/P", "Login failure");
                   // Toast.makeText(getApplicationContext(),"Credentials Mismatch",Toast.LENGTH_LONG).show();
                    callSnackBar("Credentials Mismatch");

                }
                }catch(Exception e){
                    //Toast.makeText(getApplicationContext(),"User is not registered please Signup",Toast.LENGTH_SHORT).show();
                    callSnackBar("Not Registered User Please Signup");
                }
                }

        });


        tv_forgotpass.setOnClickListener(v -> {
            Intent i=new Intent(LogIn.this,ForgotPassword.class);
            startActivity(i);
        });


    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_STORAGE_REQUEST_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "storage permission granted", Toast.LENGTH_LONG).show();

            } else {

                Toast.makeText(this, "storage permission denied", Toast.LENGTH_LONG).show();

            }

        }
    }

    /*@OnClick(R.id.signUp_text)
    public void onSignUp() {
        Intent in = new Intent(LogIn.this, MainActivity.class);
        startActivity(in);
        finish();

    }*/

   /* @OnClick(R.id.login)
    public void onLogIN() {

      *//*  MediaProjectionManager mediaProjectionManager = (MediaProjectionManager)
                getSystemService(Context.MEDIA_PROJECTION_SERVICE);

        startActivityForResult(
                mediaProjectionManager.createScreenCaptureIntent(),
                REQUEST_MEDIA_PROJECTION);*//*

        Intent in = new Intent(LogIn.this, ViewArticles.class);
        startActivity(in);
        finish();
    }*/

    private void askForSystemOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            Intent localIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            localIntent.setData(Uri.parse("package:" + getPackageName()));
            startActivityForResult(localIntent, DRAW_OVER_OTHER_APP_PERMISSION);

        }
    }

    private void errorToast() {
        Toast.makeText(this, "Draw over other app permission not available. Can't start the application without the permission.", Toast.LENGTH_LONG).show();
    }

    public void callSnackBar(String message){
        Snackbar.make(binding.snackLogin,message,Snackbar.LENGTH_LONG).show();

    }



}
