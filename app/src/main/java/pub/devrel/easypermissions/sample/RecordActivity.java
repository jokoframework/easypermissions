package pub.devrel.easypermissions.sample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class RecordActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks,
                                                                EasyPermissions.RationaleCallbacks{
    private TextView message;
    private static final int RC_RECORD_PERM = 125;
    private static final String TAG = "RecordActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        message = findViewById(R.id.textPermission);
        recordTask();
        recordMessage();
    }

    @Override
    protected void onResume() {
        super.onResume();
        recordMessage();
    }

    //Show the message about the permission on the screen
    public void recordMessage(){
        if(hasRecordPermission()){
            message.setText(getString(R.string.yesMsg));
        }else{
            message.setText(getString(R.string.noMsg));
        }
    }

    private boolean hasRecordPermission() {
        return EasyPermissions.hasPermissions(this, Manifest.permission.RECORD_AUDIO);
    }

    @AfterPermissionGranted(RC_RECORD_PERM)
    public void recordTask() {
        if (hasRecordPermission()) {
            // Have permission, do the thing!
            Toast.makeText(this, "TODO: Record things", Toast.LENGTH_LONG).show();
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.rationale_record),
                    RC_RECORD_PERM,
                    Manifest.permission.RECORD_AUDIO);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size());

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());

        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onRationaleAccepted(int requestCode) {
        Log.d(TAG, "onRationaleAccepted:" + requestCode);
    }

    @Override
    public void onRationaleDenied(int requestCode) {
        Log.d(TAG, "onRationaleDenied:" + requestCode);
    }

}