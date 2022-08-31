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

public class RecordActivity extends AppCompatActivity{
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        message = findViewById(R.id.textPermission);
        recordMessage();
    }

    public void recordMessage(){
        if(EasyPermissions.hasPermissions(getBaseContext(), Manifest.permission.RECORD_AUDIO)){
            message.setText(getString(R.string.yesMsg));
        }else{
            message.setText(getString(R.string.noMsg));
        }
    }

}