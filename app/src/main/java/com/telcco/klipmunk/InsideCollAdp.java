package com.telcco.klipmunk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;
import com.telcco.klipmunk.User.ArticleWebViewActivity;

import java.util.ArrayList;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;
import butterknife.BindView;
import butterknife.ButterKnife;

public class InsideCollAdp extends RecyclerView.Adapter<InsideCollAdp.ViewHolder> {
    Activity context;

    public InsideCollAdp(Activity context, ArrayList<InsideCollVideoList> videoList) {
        this.context = context;
        this.videoList = videoList;

    }

    ArrayList<InsideCollVideoList> videoList;


    @NonNull
    @Override
    public InsideCollAdp.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.insidecolllay,parent,false);
        InsideCollAdp.ViewHolder viewHolder = new InsideCollAdp.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InsideCollAdp.ViewHolder holder, int position) {
        String title;

        //Title ,image
        if(videoList.get(position).getVideoId()!=null) {
            title=videoList.get(position).getVideoTitle();
            holder.inside_title.setText(videoList.get(position).getVideoTitle());
            Picasso.with(context).load("https://img.youtube.com/vi/" + videoList.get(position).getVideoId() + "/hqdefault.jpg").into(holder.inside_img);

        }
        // uncomment when audio key  is available
        else if(videoList.get(position).getAudioId()!=null){
            title=videoList.get(position).getAudioTitle();
            holder.inside_img.setImageResource(R.drawable.ic_headset_black_24dp);
            holder.inside_title.setText(videoList.get(position).getAudioTitle());

        }
        else if(videoList.get(position).getArticleId()!=null){
            title=videoList.get(position).getArticleTitle();
            if(!videoList.get(position).getArticleThumbnail().isEmpty()) {
                Picasso.with(context).load(videoList.get(position).getArticleThumbnail()).placeholder(R.drawable.icon).into(holder.inside_img);
            }
            else{
                holder.inside_img.setImageResource(R.drawable.icon);
            }holder.inside_title.setText(videoList.get(position).getArticleTitle());
        }


        else{
            title=videoList.get(position).getImageTitle();
            holder.inside_title.setText(videoList.get(position).getImageTitle());
            Picasso.with(context).load(videoList.get(position).getImageUrl()).placeholder(R.drawable.icon).into(holder.inside_img);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(videoList.get(position).getVideoUrl()!=null){
                    if(videoList.get(position).getVideoUrl().contains("www.youtube.com")){
                        Intent in = new Intent(context,YoutubeApiAct.class);      // for youtube player
                        in.putExtra(UtilConstants.VIDEO_URL,videoList.get(position).getVideoUrl());
                        in.putExtra(UtilConstants.TITLE_URL,title);
                        context.startActivity(in);
                    }
                    else{
                        Intent in = new Intent(context,PlayerActivity.class);      // for both video and audio means exo
                        in.putExtra(UtilConstants.VIDEO_URL,videoList.get(position).getVideoUrl());
                        in.putExtra(UtilConstants.TITLE_URL,title);
                        context.startActivity(in);
                    }

                }
                // uncomment when audio key  is available
                else if(videoList.get(position).getAudioUrl()!=null){
                    Intent in = new Intent(context,PlayerActivity.class);      // for both video and audio means exo
                    in.putExtra(UtilConstants.AUDIO_URL,videoList.get(position).getAudioUrl());
                    in.putExtra(UtilConstants.TITLE_URL,title);
                    context.startActivity(in);
                }
                else if(videoList.get(position).getImageUrl() != null){
                    Intent in = new Intent(context,PlayImages.class);      // for images
                    in.putExtra(UtilConstants.IMAGE_URL,videoList.get(position).getImageUrl());
                    in.putExtra(UtilConstants.TITLE_URL,title);
                    context.startActivity(in);
                }
                else if(videoList.get(position).getArticleUrl() != null){
                    Intent in = new Intent(context, ArticleWebViewActivity.class);
                    in.putExtra(UtilConstants.ARTICLE_URL,videoList.get(position).getArticleUrl());
                    in.putExtra(UtilConstants.TITLE_URL,title);
                    context.startActivity(in);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.inside_img)
        ImageView inside_img;
        @BindView(R.id.inside_title)
        TextView inside_title;
        @BindView(R.id.inside_notes)
        TextView inside_notes;
        @BindView(R.id.inside_url)
        TextView inside_url;
        @BindView(R.id.inside_time)
        TextView inside_time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
