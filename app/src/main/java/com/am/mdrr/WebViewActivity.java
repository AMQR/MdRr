package com.am.mdrr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by AM on 2016/7/18.
 */
public class WebViewActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private WebView mWebView;
    private String loadUrl;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Intent intent = getIntent();
        title = intent.getStringExtra("Title");
        loadUrl = intent.getStringExtra("LoadUrl");

        mToolbar = (Toolbar) findViewById(R.id.mToolbar);
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);

        loadWev();
    }

    private void loadWev() {
        mWebView = (WebView) findViewById(R.id.mWebView);

        //支持javascript
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        //支持页面缩放
        //webView.getSettings().setBuiltInZoomControls(true);
        //提升渲染优先级
        //webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        //不加载网络中的图片资源
        //webView.getSettings().setBlockNetworkImage(true);
        //HTML5 Cache
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        //优先从本地cache中载入，其次才是从网络中载入，即使内容已经过期
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                //Android TV中可以在这里返回true，按键交由onKeyDown方法处理
                return super.shouldOverrideKeyEvent(view, event);
            }
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

            }
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });
        /*可以从web server中的日志文件查看相应的http请求，如linux下的apache日志文件路径是/var/log/apache2*/
        //webView.loadUrl("file:///android_asset/demo.html");
        //webView.loadUrl("http://blog.csdn.net/djstavav");

        mWebView.loadUrl(loadUrl);

    }
}
