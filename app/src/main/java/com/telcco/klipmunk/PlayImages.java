package com.telcco.klipmunk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayImages extends AppCompatActivity {
    @BindView(R.id.klip_image)
    ImageView klip_image;
    @BindView(R.id.playtopic)
    TextView playtopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_images);
        ButterKnife.bind(this);
        this.getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>Collection </font>"));
        Picasso.with(this).load(getIntent().getStringExtra(UtilConstants.IMAGE_URL)).placeholder(R.drawable.icon).into(klip_image);
        playtopic.setText(getIntent().getStringExtra(UtilConstants.TITLE_URL));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_articles,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
