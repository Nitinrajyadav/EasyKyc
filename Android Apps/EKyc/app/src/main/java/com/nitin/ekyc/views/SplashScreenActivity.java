package com.nitin.ekyc.views;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.WriterException;
import com.nitin.ekyc.R;
import com.nitin.ekyc.utils.CommonUtils;

public class SplashScreenActivity extends Activity {

    private Button btnSignIn;
    private View mControlsView;
    private ImageView imvQRcode;
    public static final int QRcodeWidth = 200;

    private View.OnClickListener signInClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(SplashScreenActivity.this, SignInActivity.class));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        mControlsView = findViewById(R.id.fullscreen_content_controls);
        btnSignIn = (Button) findViewById(R.id.btn_sign_in);
        btnSignIn.setOnClickListener(signInClickListener);
        imvQRcode = (ImageView) findViewById(R.id.imv_qrcode);

        if (CommonUtils.canShowQr() && CommonUtils.getPrefUserBhamashahId() != null) {

            try {
                Bitmap qrCode = CommonUtils.getBitmapFromText(this, CommonUtils.getPrefUserBhamashahId());
                imvQRcode.setImageBitmap(qrCode);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }


    }


}



