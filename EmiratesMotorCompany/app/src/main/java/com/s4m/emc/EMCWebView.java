package com.s4m.emc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class EMCWebView extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emcweb_view);

        webView = (WebView) findViewById(R.id.announcement_webview);
        webView.loadDataWithBaseURL(null, getIntent().getExtras().getString("emchtmldata"), "text/html", "UTF-8", null);
    }
}
