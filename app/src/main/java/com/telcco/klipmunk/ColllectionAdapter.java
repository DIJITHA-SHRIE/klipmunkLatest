package com.telcco.klipmunk;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.telcco.klipmunk.UtilConstants.FOLDER_NAME;
import static com.telcco.klipmunk.UtilConstants.USER_ID;

public class ColllectionAdapter extends RecyclerView.Adapter<ColllectionAdapter.ViewHolder> {
    ArrayList<CollectionFolder> collectionResponseArrayList;
    Activity context;
    String userId;

    public ColllectionAdapter(ArrayList<CollectionFolder> collectionResponseArrayList, Activity context, String userId) {
        this.collectionResponseArrayList = collectionResponseArrayList;
        this.context = context;
        this.userId = userId;
    }

    @NonNull
    @Override
    public ColllectionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.profile_adp_lay,parent,false);
        ColllectionAdapter.ViewHolder viewHolder = new ColllectionAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ColllectionAdapter.ViewHolder holder, int position) {

        CollectionFolder item = collectionResponseArrayList.get(position);
        holder.topic.setText(item.getTopicName());
        holder.content.setText(item.getVideoTitle());
        if(item.getVideoThumbnail()!= null && !item.getVideoThumbnail().isEmpty()){
        Picasso.with(context).load(item.getVideoThumbnail()).placeholder(R.drawable.icon).into(holder.folder_img);}
        else{
            holder.folder_img.setImageResource(R.drawable.icon);
        }
        holder.klip1.setText(item.getNextclip1());
        holder.klip2.setText(item.getNextclip2());
        holder.totalVideos.setText(item.getTotalclips()+" Klips");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context,InsideCollection.class);
                Log.i("folderName",item.getTopicName());
                in.putExtra(FOLDER_NAME,item.getTopicName());
                in.putExtra(USER_ID,userId);
                context.startActivity(in);

            }
        });


    }

    @Override
    public int getItemCount() {
        return collectionResponseArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.topic)
        TextView topic;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.klip1)
        TextView klip1;
        @BindView(R.id.klip2)
        TextView klip2;
        @BindView(R.id.folder_img)
        ImageView folder_img;
        @BindView(R.id.totalVideos)
        TextView totalVideos;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
