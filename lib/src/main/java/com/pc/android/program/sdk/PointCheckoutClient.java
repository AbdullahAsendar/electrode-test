package com.pc.android.program.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.modules.core.PermissionListener;
import com.pc.android.program.sdk.internal.event.EventManager;
import com.walmartlabs.ern.container.ElectrodeReactContainer;

public class PointCheckoutClient {

    private static final String EVENT_NAME = "onEvent";
    private static final String EVENT_EVENT = "event";
    private static final String EVENT_DATA = "data";
    private static final String ENV = "ENV";
    private static final String AUTH_TOKEN = "AUTH_TOKEN";
    private static final String THEME = "THEME";
    private static final String LANGUAGE = "LANGUAGE";
    private static final String HANDLE_EXCEPTIONS = "HANDLE_EXCEPTIONS";
    private static final String OPEN_SCREEN = "OPEN_SCREEN";

    private static PointCheckoutClient instance;
    private Activity activity;
    private PointCheckoutConfig config;

    @Nullable
    private PermissionListener mPermissionListener;

    private PointCheckoutClient(@NonNull Activity activity, @NonNull PointCheckoutConfig config){
        this.activity = activity;
        this.config = config;
    }

    public static void initialize(@NonNull Activity activity, @NonNull PointCheckoutConfig config){
        ElectrodeReactContainer.initialize(
                activity.getApplication(),
                new ElectrodeReactContainer.Config().isReactNativeDeveloperSupport(false));
        instance = new PointCheckoutClient(activity, config);
    }

    public static Bundle getBundle() {
        final Bundle initialProperties = new Bundle();
        initialProperties.putString(OPEN_SCREEN, PointCheckoutScreen.MAIN_SCREEN.getRoute());
        initialProperties.putString(ENV, instance.config.environment.name());
        initialProperties.putString(AUTH_TOKEN, instance.config.authToken);
        initialProperties.putString(THEME, instance.config.theme.serialize(instance.activity));
        initialProperties.putString(LANGUAGE, instance.config.language.getIso2());
        initialProperties.putString(HANDLE_EXCEPTIONS, Boolean.toString(instance.config.handleExceptions));
        return initialProperties;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void requestPermissions(String[] permissions, int requestCode, PermissionListener listener) {
        instance.mPermissionListener = listener;
        instance.activity.requestPermissions(permissions, requestCode);
    }

    public static void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        if (instance.mPermissionListener != null && instance.mPermissionListener.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            instance.mPermissionListener = null;
        }

    }

    public static void onBackPressed(PointCheckoutEventListener listener) {
        EventManager.instance.addEventListener(PointCheckoutEvent.EXIT_REQUEST, listener);
    }


    public static void onException(PointCheckoutEventListener listener) {
        EventManager.instance.addEventListener(PointCheckoutEvent.EXCEPTION, listener);
    }

    public void handleDeeplinkingUrl(String url) {
       //  rnLoader.onReactEvent(InternalEvent.DEEPLINKING, url);
    }

}
