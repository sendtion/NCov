package com.sendtion.ncov.ui.notifications;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.sendtion.ncov.BuildConfig;
import com.sendtion.ncov.R;

public class NotificationsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        TextView appVersion = root.findViewById(R.id.tv_app_version);
        appVersion.setText("版本："+ BuildConfig.VERSION_NAME + "_" + BuildConfig.VERSION_CODE);

        TextView appDownload = root.findViewById(R.id.tv_app_download);
        appDownload.setMovementMethod(LinkMovementMethod.getInstance());
        return root;
    }
}