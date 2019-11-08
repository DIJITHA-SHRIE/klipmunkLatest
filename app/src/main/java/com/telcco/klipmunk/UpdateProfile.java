package com.telcco.klipmunk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class UpdateProfile extends AppCompatActivity {

    @BindView(R.id.ed_userid)
    TextView tv_userid;
    @BindView(R.id.ed_username)
    EditText ed_username;
    @BindView(R.id.ed_dob)
    EditText ed_dob;
    @BindView(R.id.ed_hobbies)
    EditText ed_hobbies;
    @BindView(R.id.ed_interest)
    EditText ed_interest;
    @BindView(R.id.ed_fieldstudy)
    EditText ed_fieldstudy;
    @BindView(R.id.ed_mobile)
    EditText ed_mobile;
    @BindView(R.id.ed_bio)
    EditText ed_bio;
    @BindView(R.id.btn_update)
    Button btn_update;
    @BindView(R.id.btn_upcancel)
    Button btn_upcancel;

    private UpdateProfileViewModel updateProfileViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        ButterKnife.bind(this);

        btn_update.setOnClickListener(v -> {
            String username = ed_username.getText().toString();
            String dob = ed_dob.getText().toString();
            String hobbies = ed_hobbies.getText().toString();
            String interest = ed_interest.getText().toString();
            String fieldstudy = ed_fieldstudy.getText().toString();
            String mobile = ed_mobile.getText().toString();
            String bio = ed_bio.getText().toString();

            // TODO:: change this user id from local storage
            doUpdateProfile("5d7c7bdacab0b6075ee851ca", username, dob, hobbies, interest, fieldstudy, mobile, bio);

        });

        updateProfileViewModel = ViewModelProviders.of(this).get(UpdateProfileViewModel.class);

        updateProfileViewModel.getUpdateProfileResponse().observe(this, updateProfileResponse -> {

            try {

                if (updateProfileResponse.getUsername() != null) {
                    ed_username.setText(updateProfileResponse.getUsername());
                }
                if (updateProfileResponse.getProfile().getHobbies() != null) {
                    ed_hobbies.setText(updateProfileResponse.getProfile().getHobbies().toString());
                }
                if (updateProfileResponse.getProfile().getFieldstudy() != null) {
                    ed_fieldstudy.setText(updateProfileResponse.getProfile().getFieldstudy());
                }
                if (updateProfileResponse.getProfile().getInterest() != null) {
                    ed_interest.setText(updateProfileResponse.getProfile().getInterest().toString());
                }
                if (updateProfileResponse.getProfile().getBio() != null) {
                    ed_bio.setText(updateProfileResponse.getProfile().getBio());
                }
                if (updateProfileResponse.getProfile().getMobile() != null) {
                    ed_mobile.setText(updateProfileResponse.getProfile().getMobile().intValue());
                }
                if (updateProfileResponse.getProfile().getDob() != null) {
                    ed_dob.setText(updateProfileResponse.getProfile().getDob());
                }
            }catch (Exception e){

                Toast.makeText(this,"not getting the response",Toast.LENGTH_SHORT).show();
            }


        });
        // setting page is not created yet..

     /*   btn_upcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p=new Intent(UpdateProfile.this,Setting.class);
                startActivity(p);
            }
        });*/
    }

    private void doUpdateProfile(String userid, String username, String dob, String hobbies, String interest, String fieldstudy, String mobile, String bio) {

        updateProfileViewModel.setUpdateProfileViewModel(userid, username, dob, hobbies, interest, fieldstudy, mobile, bio);
    }
}
