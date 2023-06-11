package com.ironxiao.frpc;

import android.os.Bundle;


import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.hcnetsdk.jna.CameraHelper;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "--zwh-- MainActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");

        CameraHelper.OnInit();
        x.Ext.init(getApplication());
        FrpcHelper.Instance().StartFrpc(getApplicationContext());
        SocketClientHelper.Instance().ReStart(getApplicationContext());
        LocalBaseDataHelper.Instance().SyncDataToServer(getApplicationContext());

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        List<Fragment> fragmentLis = new ArrayList<>();
        fragmentLis.add(new PreviewActivity());
        fragmentLis.add(new PlaybackActivity());
        viewPager.setAdapter(new ViewPageAdapter(this, fragmentLis));
        viewPager.setCurrentItem(0);
    }
}