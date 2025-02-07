package MicroBlinkNativeModule;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class MicroBlinkNativeModule extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactApplicationContext;
    public static String MICROBLINK_LOG_TAG = "MicroBlink";

    MicroBlinkNativeModule(ReactApplicationContext context) {
        reactApplicationContext = context;
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
        Intent intent = new Intent(reactApplicationContext, MicroBlinkActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        reactApplicationContext.startActivity(intent);
    }
}
