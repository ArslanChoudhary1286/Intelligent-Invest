package com.myapp.intellvest;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MainActivity extends AppCompatActivity {
    public BottomNavigationView navigationView;
    public static Activity fa;
    Fragment temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.bottom_navigation);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        fa = this;

        navigationView.setSelectedItemId(R.id.page_3);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,new MyAccountFragment()).commit();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                temp =null;
                switch (item.getItemId()){
                    case R.id.page_1: {
                        temp = new RecordsFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,temp).commit();
                    }
                        break;
                    case R.id.page_2:{
                        Dexter.withContext(MainActivity.this)
                                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                                .withListener(new PermissionListener() {
                                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {
                                        temp = new ChattingFragment();
                                        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,temp).commit();
                                    }
                                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {
                                        Toast.makeText(MainActivity.this, "Permission denied, please Grant the permission in setting.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                                Uri.fromParts("package", getPackageName(), null));
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                        token.continuePermissionRequest();
                                    }
                                }).check();

                    }
                        break;
                    case R.id.page_3: {
                        temp = new MyAccountFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,temp).commit();
                    }
                        break;
                    case R.id.page_4: {
                        temp = new PlansFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,temp).commit();
                    }
                        break;
                    case R.id.page_5: {
                        temp = new InvestFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,temp).commit();
                    }
                        break;
                }

                return true;
            }
        });


    }
}