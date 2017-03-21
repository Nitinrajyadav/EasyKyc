package com.nitin.ekyc.views.fragments;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.nitin.ekyc.KycApp;
import com.nitin.ekyc.R;
import com.nitin.ekyc.repository.local.CurrentUserHolder;
import com.nitin.ekyc.repository.local.LocalCache;
import com.nitin.ekyc.repository.remote.EndPoints;
import com.nitin.ekyc.repository.remote.JSONRequest;
import com.nitin.ekyc.repository.remote.RequestTags;
import com.nitin.ekyc.utils.CommonUtils;

import org.json.JSONObject;


/**
 * Created by Nitin on 3/15/2017.
 */

public class UserProfileFragment extends Fragment {

    private static final String TAG = UserProfileFragment.class.getSimpleName();
    private ProgressDialog progressDialog;
    private LocalCache localCacheInstance;

    private View rootView;

    private ImageView user_img;
    private TextView tvUserName;
    private TextView tvUserDetails;


    ImageView imvUserQr;
    Switch switchShowQr;

    public static UserProfileFragment getInstance() {
        UserProfileFragment userProfileFragment = new UserProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", "llallala");
        userProfileFragment.setArguments(bundle);
        return userProfileFragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
//            name = bundle.getString("name");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (localCacheInstance == null) {
            localCacheInstance = LocalCache.getInstance();
        }
        if (progressDialog == null) {
            progressDialog = CommonUtils.getProgressDialog(getContext());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_user_profile, container, false);

        CurrentUserHolder currentUserHolder = CurrentUserHolder.getInstance();

        user_img = (ImageView) rootView.findViewById(R.id.user_img);
        tvUserName = (TextView) rootView.findViewById(R.id.tv_user_name);
        tvUserDetails = (TextView) rootView.findViewById(R.id.tv_user_details);

        imvUserQr = (ImageView) rootView.findViewById(R.id.imv_user_qr);

        try {
            Bitmap bitmap = CommonUtils.getBitmapFromText(getContext(), CommonUtils.getPrefUserBhamashahId());
            imvUserQr.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }


        switchShowQr = (Switch) rootView.findViewById(R.id.switch_show_qr);
        switchShowQr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CommonUtils.setPrefCanShowQr(b);
            }
        });

        tvUserName.setText(currentUserHolder.getmUser().getNameEng());
        String bigString = currentUserHolder.getmUser().toString();
        tvUserDetails.setText(bigString);

        fetchUserPic(currentUserHolder.getmUser().getBhamashahId());

//        readBundle(getArguments());

        return rootView;
    }

    private void fetchUserPic(String clientId) {
        String url = "https://apitest.sewadwaar.rajasthan.gov.in/app/live/Service/hofMembphoto/" + clientId + "/0?client_id=ad7288a4-7764-436d-a727-783a977f1fe1";

        JSONRequest jsonrequest = new JSONRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject photoObj = response.getJSONObject("hof_Photo");
                            String imageData = photoObj.getString("PHOTO");
                            byte[] imageAsBytes = Base64.decode(imageData, imageData.length());
                            Bitmap bitmap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
                            user_img.setImageBitmap(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        jsonrequest.setRetryPolicy(KycApp.getDefaultRetryPolice());
        KycApp.getInstance().addToRequestQueue(jsonrequest, RequestTags.DASHBOARD_REQUESTS_TAG);
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

