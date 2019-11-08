package com.telcco.klipmunk;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;
import com.telcco.klipmunk.databinding.ActivityNewSignUpBinding;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewSignUp extends AppCompatActivity {
    SignUpViewModel signUpViewModel;
    ActivityNewSignUpBinding binding;

    private GoogleSignInClient googleSignInClient;



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 0012) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Log.i("Sucess","successGoggleLogIN");

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("success", "signInResult:failed code=" + e.getStatusCode());

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        signUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
        binding = DataBindingUtil.setContentView(NewSignUp.this,R.layout.activity_new_sign_up);
        binding.setLifecycleOwner(this);
        binding.setSignUpViewModel(signUpViewModel);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(Scopes.EMAIL))
                .requestEmail()
                .requestServerAuthCode("386783177297-g3oedlsfr19bh2lrlssn1r6pigribpjv.apps.googleusercontent.com")
                .build();
        googleSignInClient = GoogleSignIn.getClient(this,gso);
        binding.googleSigIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = googleSignInClient.getSignInIntent();
                startActivityForResult(in,0012);
            }
        });


       // signUpViewModel.init("diji980","diji980@gmail.com","diji506@980");





      // to pass the values
        signUpViewModel.getUserInput().observe(this, new Observer<SignupModel>() {
            @Override
            public void onChanged(SignupModel signupModel) {
                signUpViewModel.getSignup(signupModel.getFirstname(),signupModel.getEmail().trim(),signupModel.getPassword(),signupModel.getPassword2());
            }
        });
      // to store data in server
        signUpViewModel.getUser().observe(this,signUpResponse -> {

                String res_name = signUpResponse.getName();
            if(res_name!=null) {
                Log.i("res_name", res_name);
                Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_LONG).show();
                Intent in = new Intent(NewSignUp.this,OTP.class);
                startActivity(in);
                finish();
            }
            else{
                Toast.makeText(getApplicationContext(),"Registration Failed",Toast.LENGTH_LONG).show();
            }

        });





    }


}
