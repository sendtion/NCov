package com.sendtion.ncov.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.just.agentweb.AgentWeb;
import com.sendtion.ncov.R;
import com.sendtion.ncov.X5WebView;

public class DashboardFragment extends Fragment {

//    private AgentWeb mAgentWeb;
    private X5WebView mWebView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        mWebView = root.findViewById(R.id.web_view_dashboard);
        mWebView.loadUrl("https://h5.peopleapp.com/txcx/index.html");

//        LinearLayout layout = root.findViewById(R.id.layout_dashboard_content);
//        mAgentWeb = AgentWeb.with(this)
//                .setAgentWebParent(layout, new LinearLayout.LayoutParams(-1, -1))
//                .useDefaultIndicator()
//                .createAgentWeb()
//                .ready()
//                .go("https://h5.peopleapp.com/txcx/index.html");
        return root;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser){
            mWebView.loadUrl("https://h5.peopleapp.com/txcx/index.html");
        }
    }

    @Override
    public void onPause() {
//        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    public void onResume() {
//        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onDestroyView() {
//        mAgentWeb.getWebLifeCycle().onDestroy();
        if (mWebView != null)
            mWebView.destroy();
        super.onDestroyView();
    }
}