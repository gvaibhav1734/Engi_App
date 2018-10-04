package com.example.lenovo.engineer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {
    private final static String TAG = "WebViewActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Register");
        WebView webview = findViewById(R.id.webview_wv);
        WebSettings webSettings = webview.getSettings();
        Log.d(TAG,getIntent().getExtras().getString("link"));
        webview.loadUrl(getIntent().getExtras().getString("link"));

    }
}
