package MicroBlinkNativeModule;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;
import com.microblink.camera.ui.CameraRecognizerContract;
import com.microblink.camera.ui.CameraRecognizerOptions;

public class MicroBlinkActivity extends AppCompatActivity {
    public static Promise scanPromise = null;
    private final ActivityResultLauncher<CameraRecognizerOptions> cameraRecognizerLauncher = registerForActivityResult(new CameraRecognizerContract(), result -> {
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
    private final ActivityResultLauncher<String> requestCameraPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                    cameraRecognizerLauncher.launch(new CameraRecognizerOptions.Builder().build());
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // feature requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                    String message = "Camera permission not granted.";
                    Log.i(MicroBlinkNativeModule.MICROBLINK_LOG_TAG, message);
                    if (scanPromise != null) {
                        WritableMap map = Arguments.createMap();
                        map.putString("result", "denied");
                        map.putString("message", message);
                        scanPromise.resolve(map);
                        scanPromise = null;
                    }
                    finish();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(MicroBlinkNativeModule.MICROBLINK_LOG_TAG, "MicroBlinkActivity.onCreate");

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            cameraRecognizerLauncher.launch(new CameraRecognizerOptions.Builder().build());
        } else {
            requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA);
        }
    }
}
