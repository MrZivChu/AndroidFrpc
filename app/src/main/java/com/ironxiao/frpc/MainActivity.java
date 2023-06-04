package com.ironxiao.frpc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import org.xutils.x;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.Ext.init(getApplication());

        StartFrpc();

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        List<Fragment> fragmentLis = new ArrayList<>();
        PreviewActivity previewActivity = new PreviewActivity();
        PlaybackActivity playbackActivity = new PlaybackActivity();
        fragmentLis.add(previewActivity);
        fragmentLis.add(playbackActivity);
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(this, fragmentLis);
        Toast.makeText(this, "4", Toast.LENGTH_LONG);
        viewPager.setAdapter(viewPageAdapter);
        Toast.makeText(this, "5", Toast.LENGTH_LONG);
        viewPager.setCurrentItem(0);
    }

    private void StartFrpc() {
        Intent intent = new Intent(getApplicationContext(), FrpcService.class);
        getApplicationContext().startService(intent);
    }
}