package com.telcco.klipmunk;

import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class CounterScreenShot extends AppCompatActivity {
    @BindView(R.id.screenshot)
    ImageView screenshot;
    @BindView(R.id.spinner_coun_tag)
    Spinner spinner_coun_tag;
    @BindView(R.id.share)
    Button Share;

    ArrayList<String> items;
    DataBaseHelper db;
    Uri uri;
    String getSelectedTag,mediaPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_screen_shot);
        ButterKnife.bind(this);
        Intent in = getIntent();
        File myFile = (File)in.getSerializableExtra("bitmap");
        mediaPath=in.getStringExtra("MediaPath");

         uri = Uri.fromFile(myFile);
        System.out.println("CounterUri"+"---"+uri);
        Picasso.with(this).load(uri).into(screenshot);

        items = new ArrayList<>();
        db = new DataBaseHelper(CounterScreenShot.this);
        items = db.getTag();

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_single_choice,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_coun_tag.setAdapter(adapter);




    }


    @OnItemSelected(R.id.spinner_coun_tag)
    public void onItemSelected(int position){
        getSelectedTag= spinner_coun_tag.getItemAtPosition(position).toString();
        Log.i("getSelectedTag",getSelectedTag);
    }
    @OnClick(R.id.share)
    public void onShare(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String sdf_date = df.format(c);
        Log.i("CounterUriInString",mediaPath);

        db.insertPath(mediaPath,getSelectedTag,"Test",sdf_date);

        Intent in = new Intent(CounterScreenShot.this,ViewArticles.class);
        startActivity(in);
    }
}
