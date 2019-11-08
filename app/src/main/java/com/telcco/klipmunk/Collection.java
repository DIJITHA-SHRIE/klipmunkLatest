package com.telcco.klipmunk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.internal.Util;

import static com.telcco.klipmunk.UtilConstants.USER_ID;

public class Collection extends AppCompatActivity {

    ArrayList<CollectionFolder> collectionResponseArrayList = new ArrayList<>();
    CollectionViewModel collectionViewModel;
    @BindView(R.id.collection_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.add_folder)
    FloatingActionButton add_folder;
    ColllectionAdapter adapter;
    ProgressBar progressBar;
    PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        ButterKnife.bind(this);
        this.getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>Collection </font>"));




        collectionViewModel = ViewModelProviders.of(this).get(CollectionViewModel.class);
        collectionViewModel.getCollectionRes(getIntent().getStringExtra(USER_ID));

        collectionViewModel.getCollection().observe(this, collectionResponse -> {
            ArrayList<CollectionFolder> collectionFolderList =collectionResponse.getTopicThumbnail();
            collectionResponseArrayList.addAll(collectionFolderList);
            setupRecyclerView();

        });

        collectionViewModel.getNewFolder().observe(this, newFolderResponse -> {
         String  folderSuccess =newFolderResponse.getFolderName();
           progressBar.setVisibility(View.GONE);
           Log.i("folderSuccess",folderSuccess);

           popupWindow.dismiss();
            Toast.makeText(getApplicationContext(),"Folder Created Successfully",Toast.LENGTH_LONG).show();

        });


    }

    private void setupRecyclerView() {
        if (adapter == null) {
            adapter = new ColllectionAdapter( collectionResponseArrayList,Collection.this,getIntent().getStringExtra(USER_ID));
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.add_folder)
    public void onAddFolder(View view){
        createPopUp();

    }

    private void createPopUp() {
        LayoutInflater inflater =(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.newfolder_lay,null);
        popupWindow = new PopupWindow(popUpView, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,true);
        popupWindow.showAtLocation(this.findViewById(R.id.collection_main), Gravity.CENTER,0,0);

        EditText topic =(EditText)popUpView.findViewById(R.id.folder_topic);
        EditText description =(EditText)popUpView.findViewById(R.id.folder_description);
        Button create_folder =(Button)popUpView.findViewById(R.id.create_folder);
        progressBar =(ProgressBar)popUpView.findViewById(R.id.progress_newfolder);
        TextView newtopic = (TextView)popUpView.findViewById(R.id.newtopic_folder);

        newtopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        create_folder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                HashMap<String,NewFolderRequest> newFolderhash = new HashMap<>();
                newFolderhash.put("newFolder",new NewFolderRequest(topic.getText().toString(),description.getText().toString()));
                collectionViewModel.getNewFolderRes(newFolderhash,getIntent().getStringExtra(USER_ID));

            }
        });



    }


}
