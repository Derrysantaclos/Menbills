package com.example.menbills;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends Activity {
    private WebView mywebView;
    private Button refreshButton;
    private Boolean postMethod;
    public String method;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshButton=findViewById(R.id.refreshButton);
        postMethod=false;


        mywebView=(WebView) findViewById(R.id.webview);
        WebSettings webSettings = mywebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //for Speed up downloading and rendering

        webSettings.setSaveFormData(true);

        webSettings.setAppCacheEnabled(false);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //you can placed in any palce as your need
                if (postMethod){
                    mywebView.goBack();
                }else{
                    mywebView.reload();
                }
            }
        });
        mywebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                refreshButton.setVisibility(View.INVISIBLE);
            }

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                if (request.getMethod().toLowerCase().equals("post")){
                    postMethod = true;
                }
                return super.shouldInterceptRequest(view, request);

            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                refreshButton.setVisibility(View.VISIBLE);
            }
        });
        mywebView.loadUrl("https://menbills.com.ng/shop");







    }
    @Override
    public void onBackPressed(){
        if(mywebView.canGoBack()) {
            mywebView.goBack();
        }
        else{
            super.onBackPressed();
        }

    }
//    public void refreshPage(){
//        mywebView=(WebView) findViewById(R.id.webview);
//        mywebView.loadUrl( "javascript:window.location.reload( true )" );
//    }
//
//    public class mywebClient extends WebViewClient{
//
//        @Override
//        public void onPageStarted(WebView view, String url, Bitmap favicon){
//            super.onPageStarted(view,url,favicon);
//
//
//        }
//
//        @Override
//        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
//            super.onReceivedHttpError(view, request, errorResponse);
//            refreshButton.setVisibility(View.VISIBLE);
//        }
//
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view,String url){
//            view.loadUrl(url);
//            return true;
//        }
//
//        @SuppressWarnings("deprecation")
//        @Override
//        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//            refreshButton.setVisibility(View.VISIBLE);
//        }
//
//    }
//






}