package com.telcco.klipmunk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.telcco.klipmunk.databinding.ActivityMainBinding;


import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tagline)
    TextView tagline;
    @BindView(R.id.terms)
    CheckBox terms;
    @BindString(R.string.tagLine)
    String tagline_str;
    @BindString(R.string.terms)
    String terms_str;
    @BindView(R.id.signup)
    Button signup;

    private SignUpViewModel signUpViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
        binding = DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setSignUpViewModel(signUpViewModel);

        ButterKnife.bind(this);
        tagline.setText(Html.fromHtml(tagline_str));
        terms.setText(Html.fromHtml(terms_str));

        signUpViewModel.getUserInput().observe(this, new Observer<SignupModel>() {
            @Override
            public void onChanged(SignupModel signupModel) {
                signUpViewModel.init(signupModel.getFirstname(),signupModel.getEmail(),signupModel.getPassword());
            }
        });

        signUpViewModel.getUser().observe(this,signUpResponse -> {
            String res_name = signUpResponse.getUser().getName();
            Log.i("res_name",res_name);

        });





    }
   /* @OnClick(R.id.signup)
    public void onSignUp(){
        Intent in = new Intent(MainActivity.this,ViewArticles.class);
        startActivity(in);
        finish();
    }
*/}
