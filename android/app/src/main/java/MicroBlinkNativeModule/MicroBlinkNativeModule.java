package MicroBlinkNativeModule;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;

public class MicroBlinkNativeModule extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactApplicationContextContext;
    public static String MICROBLINK_LOG_TAG = "MicroBlink";

    MicroBlinkNativeModule(ReactApplicationContext context) {
        reactApplicationContextContext = context;
    }

    @NonNull
    @Override
    public String getName() {
        return "MicroBlinkNativeModule";
    }

    @ReactMethod
    public void scanReceipt(Boolean storeUserFrames, Promise promise) {
        Log.d(MICROBLINK_LOG_TAG, "scanReceipt called.");
        if (MicroBlinkActivity.scanPromise != null) {
            MicroBlinkActivity.scanPromise.reject("Force cancel", "A new scan call came in before this current promise is dismissed.");
        }
        MicroBlinkActivity.scanPromise = promise;
        Intent intent = new Intent(reactApplicationContextContext, MicroBlinkActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        reactApplicationContextContext.startActivity(intent);
    }
}
