package com.pc.android.program.sdk;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;

public class PointCheckoutActivity extends com.walmartlabs.ern.container.miniapps.ProgramsdkActivity implements PermissionAwareActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PointCheckoutClient.onBackPressed(new PointCheckoutEventListener() {
            @Override
            public void onEvent(String value) {
                PointCheckoutActivity.this.finish();
            }
        });

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void requestPermissions(String[] permissions, int requestCode, PermissionListener listener) {
        PointCheckoutClient.requestPermissions(permissions, requestCode, listener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PointCheckoutClient.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
