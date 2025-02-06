package MicroBlinkNativeModule;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;

public class MicroBlinkActivity extends AppCompatActivity {
    public static Promise scanPromise = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(MicroBlinkNativeModule.MICROBLINK_LOG_TAG, "MicroBlinkActivity.onCreate");

        // launcher.launch(new CameraRecognizerOptions.Builder().build());
        if (scanPromise != null) {
            WritableMap map = Arguments.createMap();
            map.putString("result", "scanned (pretended)");
            scanPromise.resolve(map);
            scanPromise = null;
        }
        finish();
    }
}
