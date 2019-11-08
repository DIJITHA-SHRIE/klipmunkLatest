package com.telcco.klipmunk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telcco.klipmunk.UtilConstants.FOLDER_NAME;
import static com.telcco.klipmunk.UtilConstants.USER_ID;

public class InsideCollection extends AppCompatActivity {
    @BindView(R.id.collection_topic)
    TextView collection_topic;
    @BindView(R.id.insideColl_rev)
    RecyclerView insideColl_rev;

    InsideCollViewModel insideCollViewModel;
    ArrayList<InsideCollVideoList> videoList = new ArrayList<>();
    ArrayList<InsideCollVideoList> viewArticle = new ArrayList<>();

    InsideCollAdp adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_collection);
        ButterKnife.bind(this);
        this.getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>Collection </font>"));

        insideCollViewModel = ViewModelProviders.of(this).get(InsideCollViewModel.class);
        Log.i("FolderNameIns",getIntent().getStringExtra(FOLDER_NAME));
        insideCollViewModel.getInsideCollectionRes(getIntent().getStringExtra(USER_ID),getIntent().getStringExtra(FOLDER_NAME));

        collection_topic.setText(getIntent().getStringExtra(FOLDER_NAME));

        insideCollViewModel.getInsideCollection().observe(this,insideCollRes -> {
            videoList=insideCollRes.getVideoList();
            setupRecyclerView();

        });

        insideCollViewModel.getInsideCollection().observe(this,insideCollRes ->{

            viewArticle=insideCollRes.getViewArticle();
            viewRecyclerView();


                });
    }


    private void setupRecyclerView() {
        if (adapter == null) {
            adapter = new InsideCollAdp( InsideCollection.this,videoList);
            insideColl_rev.setLayoutManager(new LinearLayoutManager(this));
            insideColl_rev.setAdapter(adapter);
            insideColl_rev.setItemAnimator(new DefaultItemAnimator());
            insideColl_rev.setNestedScrollingEnabled(true);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    private void viewRecyclerView(){
        if(adapter==null){
            adapter=new InsideCollAdp(InsideCollection.this,viewArticle);
            insideColl_rev.setLayoutManager(new LinearLayoutManager(this));
            insideColl_rev.setAdapter(adapter);
            insideColl_rev.setItemAnimator(new DefaultItemAnimator());
            insideColl_rev.setNestedScrollingEnabled(true);

        }else {
            adapter.notifyDataSetChanged();
        }
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
