package com.sendtion.ncov.ui.home;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import com.just.agentweb.AgentWeb;
import com.sendtion.ncov.IBackInterface;
import com.sendtion.ncov.R;
import com.sendtion.ncov.X5WebView;

public class HomeFragment extends Fragment {

//    private AgentWeb mAgentWeb;
    private X5WebView mWebView;
    private String url;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        IBackInterface backInterface = (IBackInterface) getActivity();
        if (backInterface != null) {
            backInterface.setSelectedFragment(this);//将fragment传递到Activity中
        }

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //String[] data1 = getResources().getStringArray(R.array.data1);
        String[] data2 = getResources().getStringArray(R.array.data2);

        mWebView = root.findViewById(R.id.web_view_home);
        url = data2[0];

//        LinearLayout layout = root.findViewById(R.id.layout_home_content);
//        AgentWeb.PreAgentWeb mPreAgentWeb = AgentWeb.with(this)
//                .setAgentWebParent(layout, new LinearLayout.LayoutParams(-1, -1))
//                .useDefaultIndicator()
//                .createAgentWeb()
//                .ready();
//        mAgentWeb = mPreAgentWeb.go(data2[0]);

        AppCompatSpinner spinner = root.findViewById(R.id.spinner_home);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                tv.setGravity(Gravity.CENTER_HORIZONTAL);//设置文本居中显示，在XML文件中无法实现
                url = data2[position];
//                mAgentWeb = mPreAgentWeb.go(url);

                mWebView.loadUrl(url);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return root;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser){
            mWebView.loadUrl(url);
        }
    }

    @Override
    public void onPause() {
//        if (mAgentWeb != null) {
//            mAgentWeb.getWebLifeCycle().onPause();
//        }
        super.onPause();

    }

    @Override
    public void onResume() {
//        if (mAgentWeb != null) {
//            mAgentWeb.getWebLifeCycle().onResume();
//        }
        super.onResume();
    }

    @Override
    public void onDestroyView() {
//        if (mAgentWeb != null) {
//            mAgentWeb.getWebLifeCycle().onDestroy();
//        }
        if (mWebView != null)
            mWebView.destroy();
        super.onDestroyView();
    }

    public boolean onBackPressed() {
        if (mWebView != null && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
//        if (mAgentWeb != null &&  mAgentWeb.back()) {
//            return true;
//        }
        return false;
    }
}