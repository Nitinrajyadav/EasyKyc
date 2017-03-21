package com.nitin.ekyc.views.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nitin.ekyc.R;
import com.nitin.ekyc.repository.local.LocalCache;
import com.nitin.ekyc.utils.CommonUtils;

/**
 * Created by Nitin on 3/16/2017.
 */

public class NotificationFragment extends Fragment {

    private static final String TAG = UserProfileFragment.class.getSimpleName();
    private ProgressDialog progressDialog;
    private LocalCache localCacheInstance;

    private View rootView;

    public static NotificationFragment getInstance() {
        NotificationFragment notificationFragment = new NotificationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", "llallala");
        notificationFragment.setArguments(bundle);
        return notificationFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (localCacheInstance == null) {
            localCacheInstance = LocalCache.getInstance();
        }
        if (progressDialog == null) {
            progressDialog = CommonUtils.getProgressDialog(getContext());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_user_profile, container, false);

        return rootView;
    }

    @Override
    public void onStop() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        super.onStop();
    }

}
