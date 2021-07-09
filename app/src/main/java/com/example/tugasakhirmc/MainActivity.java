package com.example.tugasakhirmc;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int MY_PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 1;

    Button buttonLihatData, buttonInputData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // jika tidak diizinkan
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // diizinkan
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Minta Izin");
                builder.setMessage("Kamu harus Mengaktifkan Izin ini untuk membaca kontak");
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_ACCESS_FINE_LOCATION);

                    }
                });
                builder.setPositiveButton("Diizinkan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_ACCESS_FINE_LOCATION);

                    }
                });
                builder.show();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_ACCESS_FINE_LOCATION);
            }
        } else {
            // sudah diizinkan
        }

        buttonLihatData = findViewById(R.id.button_lihat_data);
        buttonInputData = findViewById(R.id.button_input_data);

        buttonLihatData.setOnClickListener(this);
        buttonInputData.setOnClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_ACCESS_FINE_LOCATION: {
                // jika izin dibatalkan, maka array nya kosong
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "MANTAPP, terimakasih udah diizinkan", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Yahh gak diizinin..", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == buttonLihatData) {
            Intent intentLihatData = new Intent(MainActivity.this, DataMahasiswaActivity.class);
            startActivity(intentLihatData);
        } else if (v == buttonInputData) {
            Intent intentInputData = new Intent(MainActivity.this, InputDataActivity.class);
            startActivity(intentInputData);
        }
    }
}