package com.telcco.klipmunk;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.SparseArray;

import com.google.android.exoplayer2.ui.PlayerView;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;

public class YouTubeExtractAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube_extract);
        extractYoutubeUrl();
    }

    private void extractYoutubeUrl() {
        @SuppressLint("StaticFieldLeak") YouTubeExtractor mExtractor = new YouTubeExtractor(this) {
            @Override
            protected void onExtractionComplete(SparseArray<YtFile> sparseArray, VideoMeta videoMeta) {
                if (sparseArray != null) {
                    playVideo(sparseArray.get(18).getUrl());
                }
            }
        };
        mExtractor.extract(getIntent().getStringExtra(UtilConstants.VIDEO_URL), true, true);
    }

    private void playVideo(String downloadUrl) {
        PlayerView mPlayerView = findViewById(R.id.mPlayerView);
        mPlayerView.setPlayer(ExoPlayerManager.getSharedInstance(YouTubeExtractAct.this).getPlayerView().getPlayer());
        ExoPlayerManager.getSharedInstance(YouTubeExtractAct.this).playStream(downloadUrl);
    }
}
