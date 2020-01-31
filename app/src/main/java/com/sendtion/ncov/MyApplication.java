package com.sendtion.ncov;

import android.app.Application;
import android.util.Log;

import com.sendtion.ncov.util.SystemUtils;
import com.sendtion.ncov.util.Utils;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (!SystemUtils.isMainProcess(this)){
            return;
        }

        Utils.init(this);

        initUMeng();
        initX5();
    }

    private void initUMeng() {
        //友盟初始化，在清单文件配置appKey和channel
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
        if (BuildConfig.DEBUG){
            UMConfigure.setLogEnabled(true);
        }
    }

    private void initX5() {
        QbSdk.setDownloadWithoutWifi(true);
        //x5内核初始化接口//搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.initX5Environment(this,  new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.e("---", " X5WebView is finished " + arg0);
            }
            @Override
            public void onCoreInitFinished() {

            }
        });
    }

}
