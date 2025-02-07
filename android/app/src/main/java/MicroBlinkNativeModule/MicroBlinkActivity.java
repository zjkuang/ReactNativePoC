package MicroBlinkNativeModule;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;
import com.microblink.camera.ui.CameraRecognizerContract;
import com.microblink.camera.ui.CameraRecognizerOptions;

public class MicroBlinkActivity extends AppCompatActivity {
    public static Promise scanPromise = null;
    private final ActivityResultLauncher<CameraRecognizerOptions> launcher = registerForActivityResult(new CameraRecognizerContract(), result -> {
        String resultString = result.toString();
        Log.d(MicroBlinkNativeModule.MICROBLINK_LOG_TAG, String.format("scan result: %s", resultString));
        if (scanPromise != null) {
            WritableMap map = Arguments.createMap();
            map.putString("result", "scanned");
            map.putString("data", resultString);
            scanPromise.resolve(map);
            scanPromise = null;
        }
        finish();
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(MicroBlinkNativeModule.MICROBLINK_LOG_TAG, "MicroBlinkActivity.onCreate");

        launcher.launch(new CameraRecognizerOptions.Builder().build());
    }
}
