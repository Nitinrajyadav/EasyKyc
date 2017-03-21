package com.nitin.ratify.views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.nitin.ratify.RatifyApp;
import com.nitin.ratify.repository.local.LocalCache;
import com.nitin.ratify.repository.pojo.User;
import com.nitin.ratify.repository.remote.EndPoints;
import com.nitin.ratify.repository.remote.JSONRequest;
import com.nitin.ratify.repository.remote.KEYS;
import com.nitin.ratify.repository.remote.RequestTags;
import com.nitin.ratify.utils.CommonUtils;

import com.nitin.ratify.R;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int SCAN_QR_REQUEST_CODE = 101;
    public static final String USER_KYC_ID_KEY = "user_kyc_id";

    private ProgressDialog mProgressDialog;
    private LocalCache mLocalCacheInstance;


    private FloatingActionButton fab;
    private View userDetailsRootView;
    private ImageView user_img;
    private TextView tv_user_name;
    private TextView tv_user_details;

    @Override

    protected void onResume() {
        if (mProgressDialog == null) {
            mProgressDialog = CommonUtils.getProgressDialog(this);
        }
        if (mLocalCacheInstance == null) {
            mLocalCacheInstance = LocalCache.getInstance();
        }
        super.onResume();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        userDetailsRootView = (View) findViewById(R.id.user_content_root);
        user_img = (ImageView) findViewById(R.id.user_img);
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);
        tv_user_details = (TextView) findViewById(R.id.tv_user_details);


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent scannerIntent = new Intent(MainActivity.this, ScannerActivity.class);
                startActivityForResult(scannerIntent, SCAN_QR_REQUEST_CODE);
            }
        });

    }

    private void setUserDetailsToUi(User user) {

        if (user == null) {
            return;
        }

        userDetailsRootView.setVisibility(View.VISIBLE);
        tv_user_name.setText(user.getNameEng());
        tv_user_details.setText(user.toString());
        fetchUserPic(user.getBhamashahId());

    }


    private void makeFetchUserDetailsRequest(JSONObject payload) {
        if (mProgressDialog == null) {
            mProgressDialog = CommonUtils.getProgressDialog(this);
        }
        mProgressDialog.show();
        JSONRequest fetchUserDetails = new JSONRequest(
                Request.Method.POST,
                EndPoints.VERIFICATION_FROM_ID_URL,
                payload,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: " + response);

                        mLocalCacheInstance.parseUserJson(response);

                        setUserDetailsToUi(mLocalCacheInstance.getVerifiedUser());

                        mProgressDialog.hide();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: " + error);
                        mProgressDialog.hide();
                    }
                }
        );

        fetchUserDetails.setRetryPolicy(RatifyApp.getDefaultRetryPolice());
        RatifyApp.getInstance().addToRequestQueue(fetchUserDetails, RequestTags.MAIN_REQUESTS_TAG);

    }

    private void fetchUserPic(String clientBhamashahId) {
        String url = "https://apitest.sewadwaar.rajasthan.gov.in/app/live/Service/hofMembphoto/" + clientBhamashahId + "/0?client_id=ad7288a4-7764-436d-a727-783a977f1fe1";

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

        jsonrequest.setRetryPolicy(RatifyApp.getDefaultRetryPolice());
        RatifyApp.getInstance().addToRequestQueue(jsonrequest, RequestTags.MAIN_REQUESTS_TAG);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SCAN_QR_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            String kycId = data.getStringExtra(USER_KYC_ID_KEY);

            if (kycId != null) {

                Log.e(TAG, "onActivityResult:   kycid-->" + kycId);
                try {
                    JSONObject payload = new JSONObject();
                    payload.put(KEYS.KEY_KYC_ID, kycId);
                    makeFetchUserDetailsRequest(payload);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onPause() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
        RatifyApp.getInstance().cancelPendingRequests(RequestTags.MAIN_REQUESTS_TAG);
        super.onPause();
    }
}
