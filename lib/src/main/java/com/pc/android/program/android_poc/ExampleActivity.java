package com.pc.android.program.android_poc;

import android.app.Activity;

import com.pc.android.program.sdk.PointCheckoutActivity;
import com.pc.android.program.sdk.PointCheckoutClient;
import com.pc.android.program.sdk.PointCheckoutEnvironment;
import com.pc.android.program.sdk.PointCheckoutLanguage;
import com.pc.android.program.sdk.PointCheckoutScreen;
import com.pc.android.program.sdk.PointCheckoutTheme;

public class ExampleActivity extends PointCheckoutActivity {

    @Override
    public PointCheckoutClient getPointCheckoutClient(Activity activity) {
        PointCheckoutTheme theme = new PointCheckoutTheme();
        theme.setHeaderImageDrawable(R.drawable.header);

        return new PointCheckoutClient.Builder()//
                .activity(this)//
                .environment(PointCheckoutEnvironment.TEST)//
                .authToken("E2pRRacPQh-U_QzGaQRnKC8EIhRIldPx6qwih787pSW_qGuJyMKXrLYtTvGIBM6lLC7KiOcVNhs")//
                .language(PointCheckoutLanguage.ENGLISH)//
                .theme(theme)//
                .handleExceptions(true)//
                .build();
    }

    @Override
    public PointCheckoutScreen getInitalScreen() {
        return PointCheckoutScreen.MAIN_SCREEN;
    }

}


