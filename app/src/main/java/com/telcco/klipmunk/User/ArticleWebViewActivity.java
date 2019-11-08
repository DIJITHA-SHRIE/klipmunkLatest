package com.telcco.klipmunk.User;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import com.telcco.klipmunk.R;
import com.telcco.klipmunk.UtilConstants;

import java.util.Objects;

public class ArticleWebViewActivity extends AppCompatActivity {

    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_web_view);

        webView = (WebView)findViewById(R.id.idWebView);

        if(getIntent().getExtras() != null)
             buildUi();

        ((ImageView)findViewById(R.id.idIvFinish)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }


    @SuppressLint("SetJavaScriptEnabled")
    private void buildUi() {

        String title = Objects.requireNonNull(getIntent().getExtras()).getString(UtilConstants.TITLE_URL,"");
        ((TextView)findViewById(R.id.idTvTitle)).setText(title);

        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(getIntent().getExtras().getString(UtilConstants.ARTICLE_URL));
        webView.setWebViewClient(new MyBrowser());

    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
