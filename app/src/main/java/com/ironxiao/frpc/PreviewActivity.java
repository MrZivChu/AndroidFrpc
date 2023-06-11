package com.ironxiao.frpc;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hcnetsdk.jna.CameraHelper;
import com.hikvision.netsdk.INT_PTR;
import com.hikvision.netsdk.PTZCommand;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PreviewActivity extends Fragment {
    private static final String TAG = "--zwh-- PreviewActivity";
    private static final int ENUM_Recover_Camera = 4;
    private SurfaceView surfaceView_ = null;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.activity_preview, container, false);

        textView1 = view.findViewById(R.id.textView1);
        textView2 = view.findViewById(R.id.textView2);
        textView3 = view.findViewById(R.id.textView3);
        textView4 = view.findViewById(R.id.textView4);
        Button settingBtn = view.findViewById(R.id.settingBtn);
        Button leftBtn = view.findViewById(R.id.leftBtn);
        Button rightBtn = view.findViewById(R.id.rightBtn);
        Button upBtn = view.findViewById(R.id.upBtn);
        Button downBtn = view.findViewById(R.id.downBtn);
        surfaceView_ = view.findViewById(R.id.surfaceView);

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputPwdDialog inputPwdDialog = new InputPwdDialog(getContext());
                inputPwdDialog.show();
            }
        });
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CameraHelper.OnPTZControl(PTZCommand.PAN_LEFT);
            }
        });
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CameraHelper.OnPTZControl(PTZCommand.PAN_RIGHT);
            }
        });
        upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CameraHelper.OnPTZControl(PTZCommand.TILT_UP);
            }
        });
        downBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CameraHelper.OnPTZControl(PTZCommand.TILT_DOWN);
            }
        });
        EventManager.Instance().AddEventListener(NotifyType.CameraInfoSetComplete, new EventManager.OnCallback() {
            @Override
            public void Call(Object content) {
                Log.d(TAG, "设置相机信息回调");
                OnStopPreview();
                OnReLogin();
            }
        });
        OnReLogin();
        return view;
    }

    private final Handler hander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ENUM_Recover_Camera:
                    Log.d(TAG, "恢复画面");
                    OnPreview();
                default:
                    break;
            }
        }
    };

    void OnReLogin() {
        CameraHelper.OnLogout();
        if (LocalBaseDataHelper.Instance().GetCameraIP(getContext()) == null || LocalBaseDataHelper.Instance().GetCameraPwd(getContext()) == null) {
            return;
        }
        String ip = LocalBaseDataHelper.Instance().GetCameraIP(getContext());
        String pwd = LocalBaseDataHelper.Instance().GetCameraPwd(getContext());
        String userName = LocalBaseDataHelper.Instance().GetCameraUserName();
        int cameraPort = LocalBaseDataHelper.Instance().GetCameraPort();
        boolean isSuccess = CameraHelper.OnLogin(ip, userName, pwd, cameraPort);
        Log.d(TAG, "请求登录：" + ip + "=" + pwd + "=" + userName + "=" + cameraPort + "=" + CameraHelper.GetLastErrorMsg());
        if (!isSuccess) {
            Log.d(TAG, "登录失败" + CameraHelper.GetLastErrorMsg());
            Toast.makeText(getContext(), "登录失败:" + CameraHelper.GetLastErrorMsg(), Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "登录成功");
            OnPreview();
        }
    }

    void OnPreview() {
        Log.d(TAG, "请求预览");
        boolean isSuccess = CameraHelper.OnRealPlay(surfaceView_);
        if (!isSuccess) {
            Log.d(TAG, "播放失败:" + CameraHelper.GetLastErrorMsg());
            Toast.makeText(this.getContext(), "播放失败:" + CameraHelper.GetLastErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        OnStopPreview();
    }

    void OnStopPreview() {
        Log.d(TAG, "退出预览");
        CameraHelper.OnStopRealPlay();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        //由于这些回调函数不能直接涉及UI，所以只能使用hander
        Message msg = new Message();
        msg.what = ENUM_Recover_Camera;
        hander.sendMessage(msg);
    }

    //这些回调函数不能执行涉及UI的东西！！！

    //1、第一次进入此页面：onCreateView -> onResume
    //2、从其他页面进入此页面：onResume
    //3、翻到其他页面：onPause

    //4、从后台进入此页面：onResume
    //5、从此页面进入后台：onPause -> onStop

    //6、从其他页面进入后台：onStop
    //7、从后台进入其他页面：不会执行任何回调

}
