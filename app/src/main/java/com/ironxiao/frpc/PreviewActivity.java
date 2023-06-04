package com.ironxiao.frpc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hcnetsdk.jna.CameraHelper;
import com.hikvision.netsdk.INT_PTR;
import com.hikvision.netsdk.PTZCommand;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PreviewActivity extends Fragment {
    private static final String TAG = "--zwh-- PreviewActivity";
    private static final int ENUM_TICK = 1;
    private static final int ENUM_Login_Failed = 2;
    private static final int ENUM_Login_Success = 3;
    private static final int ENUM_Recover_Camera = 4;

    private SurfaceView surfaceView_ = null;
    private int previewHandle_ = -1;
    boolean isExecThread_ = true;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.activity_preview, container, false);
        CameraHelper.OnInit();

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
                CameraHelper.OnPTZControl(previewHandle_, PTZCommand.PAN_LEFT);
            }
        });
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CameraHelper.OnPTZControl(previewHandle_, PTZCommand.PAN_RIGHT);
            }
        });
        upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CameraHelper.OnPTZControl(previewHandle_, PTZCommand.TILT_UP);
            }
        });
        downBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CameraHelper.OnPTZControl(previewHandle_, PTZCommand.TILT_DOWN);
            }
        });
        EventManager.Instance().AddEventListener(NotifyType.CameraInfoSetComplete, new EventManager.OnCallback() {
            @Override
            public void Call(Object content) {
                Log.d(TAG, "设置相机信息回调");
                OnStopPreview();
                int userID = CameraHelper.GetUserID();
                if (userID != -1) {
                    Log.d(TAG, "登出");
                    CameraHelper.OnLogout(userID);
                }
            }
        });
        CreateTimer();
        return view;
    }

    void CreateTimer() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        Runnable command = new Runnable() {
            @Override
            public void run() {
                if (isExecThread_) {
                    OnLoginInThread();
                    Message msg = new Message();
                    msg.what = ENUM_TICK;
                    hander.sendMessage(msg);
                }
            }
        };
        executorService.scheduleAtFixedRate(command, 0, 1, TimeUnit.SECONDS);
    }

    private final Handler hander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ENUM_TICK:
                    RequestGasValue();
                    break;
                case ENUM_Login_Failed:
                    OnLoginFailed();
                    break;
                case ENUM_Login_Success:
                    OnLoginSuccess();
                    break;
                case ENUM_Recover_Camera:
                    OnRecoverCamera();
                default:
                    break;
            }
        }
    };

    void RequestGasValue() {
        RequestParams params = new RequestParams("http://www.huaiantegang.com/Handler/User.ashx");
        params.addBodyParameter("requestType", "SelectAllUser");
        x.http().post(params, new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                textView1.setText(result);
                textView2.setText(result);
                textView3.setText(result);
                textView4.setText(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d(TAG, "onError:" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    //只能在子线程运行，避免主线程UI界面卡死
    void OnLoginInThread() {
        int userID = CameraHelper.GetUserID();
        if (userID == -1) {
            previewHandle_ = -1;
            Log.d(TAG, "请求登录");
            SharedPreferences ref = getContext().getSharedPreferences("cameraInfo", Context.MODE_PRIVATE);
            String ip = ref.getString("ip", "192.168.0.103");
            String pwd = ref.getString("pwd", "HikCDJDNF");
            Integer port = ref.getInt("port", 8000);
            userID = CameraHelper.OnLogin(ip, "admin", pwd, port);
            Log.d(TAG, "登录信息: " + ip + "=" + port + "=" + userID);
            if (userID == -1) {
                Message msg = new Message();
                msg.what = ENUM_Login_Failed;
                hander.sendMessage(msg);
            } else {
                Message msg = new Message();
                msg.what = ENUM_Login_Success;
                hander.sendMessage(msg);
            }
        }
    }

    void OnLoginFailed() {
        Log.d(TAG, "登录失败" + CameraHelper.GetLastError());
        Toast.makeText(this.getContext(), "登录失败:" + CameraHelper.GetLastError(), Toast.LENGTH_SHORT).show();
    }

    void OnLoginSuccess() {
        Log.d(TAG, "登录成功");
        OnPreview();
    }

    void OnRecoverCamera() {
        Log.d(TAG, "恢复画面");
        OnPreview();
    }

    void OnPreview() {
        int userID = CameraHelper.GetUserID();
        if (previewHandle_ != -1 || userID == -1) {
            return;
        }
        Log.d(TAG, "请求预览");
        previewHandle_ = CameraHelper.OnRealPlay(userID, surfaceView_);
        if (previewHandle_ < 0) {
            INT_PTR ff = new INT_PTR();
            ff.iValue = CameraHelper.GetLastError();
            String ss = CameraHelper.GetLastErrorMsg(ff);
            Log.d(TAG, "播放失败:" + ss);
            Toast.makeText(this.getContext(), "播放失败:" + CameraHelper.GetLastError(), Toast.LENGTH_SHORT).show();
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
        isExecThread_ = false;
        OnStopPreview();
    }

    void OnStopPreview() {
        if (previewHandle_ != -1) {
            Log.d(TAG, "退出预览");
            CameraHelper.OnStopRealPlay(previewHandle_);
        }
        previewHandle_ = -1;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        isExecThread_ = true;

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
