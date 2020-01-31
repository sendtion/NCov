package com.sendtion.ncov;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * 腾讯TBS封装
 */
public class X5WebView extends WebView {
    private ProgressBar progressbar;  //进度条
    private onWebViewListener mListener;

    public X5WebView(Context context) {
        super(context);
        initView(context);
    }

    public X5WebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        //创建进度条
        progressbar = new ProgressBar(context, null,
                android.R.attr.progressBarStyleHorizontal);
        //设置加载进度条的高度
        //进度条的高度，默认10px
        int progressHeight = 10;
        progressbar.setLayoutParams(new AbsoluteLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, progressHeight, 0, 0));
        //添加进度到WebView
        addView(progressbar);

        //开启js脚本支持
        getSettings().setJavaScriptEnabled(true);
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //适配手机大小
        getSettings().setUseWideViewPort(true);
        getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        getSettings().setLoadWithOverviewMode(true);
        getSettings().setSupportZoom(true);
        getSettings().setBuiltInZoomControls(true);
        getSettings().setDisplayZoomControls(false);
        getSettings().setSupportMultipleWindows(true);
        getSettings().setAppCacheEnabled(true);//开启 Application Caches 功能
        getSettings().setDatabaseEnabled(true);//开启 database storage API 功能
        //getSettings().setDatabasePath(SDCardUtil.getCacheDir());//设置数据库缓存路径
        //getSettings().setAppCachePath(SDCardUtil.getCacheDir());//设置Application Caches缓存目录
        getSettings().setDomStorageEnabled(true);// 开启 DOM storage API 功能
        getSettings().setGeolocationEnabled(true);
        getSettings().setAppCacheMaxSize(Long.MAX_VALUE);
        //getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);//设置 缓存模式

        setWebChromeClient(new WVChromeClient());
        setWebViewClient(new WVClient());
    }

    //进度显示
    private class WVChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }

            if (mListener != null) {
                mListener.onProgressChange(view, newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    private class WVClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //在当前Activity打开
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            //https忽略证书问题
            handler.proceed();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progressbar.setVisibility(GONE);
            if (mListener != null) {
                mListener.onPageFinish(view);
            }
            super.onPageFinished(view, url);
        }
    }

    public void setOnWebViewListener(onWebViewListener listener) {
        this.mListener = listener;
    }

    //进度回调接口
    public interface onWebViewListener {
        void onProgressChange(WebView view, int newProgress);

        void onPageFinish(WebView view);
    }

}
