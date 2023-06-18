package com.ironxiao.frpc;

import android.graphics.Color;
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
import com.ironxiao.frpc.helper.CameraDataHelper;
import com.hikvision.netsdk.PTZCommand;
import com.ironxiao.frpc.helper.DataDefines;
import com.ironxiao.frpc.helper.EventManager;
import com.ironxiao.frpc.helper.FrpcHelper;
import com.ironxiao.frpc.helper.ProjectUtils;

import java.util.ArrayList;
import java.util.HashMap;

class HandlerMsgID {
    static final int ENUM_Recover_Camera = 1;
    static final int ENUM_Update_Realtime_data = 2;
}

public class PreviewActivity extends Fragment {
    private static final String TAG = "--zwh-- PreviewActivity";
    private SurfaceView surfaceView_ = null;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView1Name;
    TextView textView2Name;
    TextView textView3Name;
    TextView textView4Name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.activity_preview, container, false);

        textView1 = view.findViewById(R.id.textView1);
        textView2 = view.findViewById(R.id.textView2);
        textView3 = view.findViewById(R.id.textView3);
        textView4 = view.findViewById(R.id.textView4);
        textView1Name = view.findViewById(R.id.textView1Name);
        textView2Name = view.findViewById(R.id.textView2Name);
        textView3Name = view.findViewById(R.id.textView3Name);
        textView4Name = view.findViewById(R.id.textView4Name);
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
        EventManager.Instance().AddEventListener(DataDefines.NotifyType.CameraInfoSetComplete, new EventManager.OnCallback() {
            @Override
            public void Call(Object content) {
                Log.d(TAG, "设置相机信息回调");
                OnStopPreview();
                boolean isSuccess = OnReLogin();
                if (isSuccess) {
                    OnPreview();
                }
            }
        });

        EventManager.Instance().AddEventListener(DataDefines.NotifyType.UpdateRealtimeGasData, new EventManager.OnCallback() {
            @Override
            public void Call(Object content) {
                Log.d(TAG, "气体数据实时更新回调");
                Message msg = new Message();
                msg.what = HandlerMsgID.ENUM_Update_Realtime_data;
                msg.obj = content;
                hander.sendMessage(msg);
            }
        });
        OnReLogin();
        return view;
    }

    void UpdateRealtimeData(Object content) {
        ArrayList<DataDefines.SGasInfo> list = (ArrayList<DataDefines.SGasInfo>) content;
        if (list != null && list.size() >= 4) {
            textView1Name.setText(list.get(0).gasName + "：");
            textView2Name.setText(list.get(1).gasName + "：");
            textView3Name.setText(list.get(2).gasName + "：");
            textView4Name.setText(list.get(3).gasName + "：");
            textView1.setText(String.valueOf(list.get(0).gasValue));
            textView2.setText(String.valueOf(list.get(1).gasValue));
            textView3.setText(String.valueOf(list.get(2).gasValue));
            textView4.setText(String.valueOf(list.get(3).gasValue));
            textView1.setTextColor(ProjectUtils.GetWarnColor(list.get(0).level));
            textView2.setTextColor(ProjectUtils.GetWarnColor(list.get(1).level));
            textView3.setTextColor(ProjectUtils.GetWarnColor(list.get(2).level));
            textView4.setTextColor(ProjectUtils.GetWarnColor(list.get(3).level));
        }
    }

    boolean OnReLogin() {
        CameraHelper.OnLogout();
        if (CameraDataHelper.Instance().GetCameraIP(getContext()) == null || CameraDataHelper.Instance().GetCameraPwd(getContext()) == null) {
            return false;
        }
        String ip = CameraDataHelper.Instance().GetCameraIP(getContext());
        String pwd = CameraDataHelper.Instance().GetCameraPwd(getContext());
        String userName = CameraDataHelper.Instance().GetCameraUserName();
        int cameraPort = CameraDataHelper.Instance().GetCameraPort();
        boolean isSuccess = CameraHelper.OnLogin(ip, userName, pwd, cameraPort);
        Log.d(TAG, "请求登录：" + ip + "=" + pwd + "=" + userName + "=" + cameraPort + "=" + CameraHelper.GetLastErrorMsg());
        if (!isSuccess) {
            Log.d(TAG, "登录失败" + CameraHelper.GetLastErrorMsg());
            Toast.makeText(getContext(), "登录失败:" + CameraHelper.GetLastErrorMsg(), Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Log.d(TAG, "登录成功");
            return true;
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

    private final Handler hander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HandlerMsgID.ENUM_Recover_Camera:
                    Log.d(TAG, "恢复画面");
                    OnPreview();
                    break;
                case HandlerMsgID.ENUM_Update_Realtime_data:
                    UpdateRealtimeData(msg.obj);
                    break;
                default:
                    break;
            }
        }
    };

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
        // 由于这些回调函数不能直接涉及UI，所以只能使用hander
        Message msg = new Message();
        msg.what = HandlerMsgID.ENUM_Recover_Camera;
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
