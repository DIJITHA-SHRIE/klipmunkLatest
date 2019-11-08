package com.telcco.klipmunk;

import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScreenFullView extends AppCompatActivity {
    @BindView(R.id.full_view)
    ImageView full_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_full_view);
        ButterKnife.bind(this);
        File file = new File(getIntent().getStringExtra("Image_Path_For_FullView"));
        Uri imageuri = Uri.fromFile(file);
        Picasso.with(ScreenFullView.this).load(imageuri).into(full_view);
    }
}
