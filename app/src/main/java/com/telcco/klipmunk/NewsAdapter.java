package com.telcco.klipmunk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by PHD on 12/23/2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    ArrayList<ListItem> consolidatedList = new ArrayList<>();

    public void updateList(ArrayList<ListItem> list){
        consolidatedList = list;
        notifyDataSetChanged();
    }

    public NewsAdapter(Context context, ArrayList<ListItem> consolidatedList) {
        this.consolidatedList = consolidatedList;
        this.mContext = context;


    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,  int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {

            case ListItem.TYPE_GENERAL:
                View v1 = inflater.inflate(R.layout.news_xml, parent,
                        false);
                viewHolder = new GeneralViewHolder(v1);
                break;

            case ListItem.TYPE_DATE:
                View v2 = inflater.inflate(R.layout.datelayout, parent, false);
                viewHolder = new DateViewHolder(v2);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch (viewHolder.getItemViewType()) {

            case ListItem.TYPE_GENERAL:

               final  GeneralItem generalItem   = (GeneralItem) consolidatedList.get(position);
                GeneralViewHolder generalViewHolder= (GeneralViewHolder) viewHolder;
                generalViewHolder.txtTitle.setText(generalItem.getScreensModel().getNotes());
                File file = new File(generalItem.getScreensModel().getPath());
                Uri imageuri = Uri.fromFile(file);
                Picasso.with(mContext).load(imageuri).into(generalViewHolder.imageview);


                ((GeneralViewHolder) viewHolder).imageview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent in = new Intent(mContext,ScreenFullView.class);
                        in.putExtra("Image_Path_For_FullView",generalItem.getScreensModel().getPath());
                        mContext.startActivity(in);
                    }
                });

                break;

            case ListItem.TYPE_DATE:
                DateItem dateItem = (DateItem) consolidatedList.get(position);
                DateViewHolder dateViewHolder = (DateViewHolder) viewHolder;

                dateViewHolder.txtTitle.setText(dateItem.getDate());
                // Populate date item data here

                break;
        }
    }





    // ViewHolder for date row item
    class DateViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtTitle;


        public DateViewHolder(View v) {
            super(v);
            this.txtTitle = (TextView) v.findViewById(R.id.text_date);


        }
    }

    // View holder for general row item
    class GeneralViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtTitle;
        ImageView imageview;

        public GeneralViewHolder(View v) {
            super(v);
            this.txtTitle = (TextView) v.findViewById(R.id.screen_notes);
            this.imageview=(ImageView)v.findViewById(R.id.screens_id);

        }
    }

    @Override
    public int getItemViewType(int position) {
        return consolidatedList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return consolidatedList != null ? consolidatedList.size() : 0;
    }


}
