package com.location_app.popu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.popu.R;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
    Button btnRequest;
    private static final int request_Code = 12;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createRequestPermission();
            }


        });

    }



    private void createRequestPermission() {

    if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return;
    }else{
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Truy cập địa chỉ đã bị từ chối", Toast.LENGTH_SHORT).show();
        }else {
            String [] permissionLog = {Manifest.permission.ACCESS_FINE_LOCATION};
            requestPermissions(permissionLog,request_Code);
        }
    }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == request_Code) {
            if (grantResults.length > 0 && grantResults[0] == getPackageManager().PERMISSION_GRANTED) {
                Toast.makeText(this, "Truy cập địa chỉ đã bật", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Truy cập địa chỉ đã bị từ chối", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void init(){
        btnRequest = findViewById(R.id.request);
    }

}