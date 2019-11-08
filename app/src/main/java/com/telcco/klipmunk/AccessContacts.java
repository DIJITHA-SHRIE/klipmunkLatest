package com.telcco.klipmunk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccessContacts extends AppCompatActivity {
    @BindView(R.id.allow)
    Button allow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_contacts);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.allow)
    public void onAllowClick(View view){
        Intent in = new Intent(AccessContacts.this,InviteContacts.class);
        startActivity(in);
    }

}
