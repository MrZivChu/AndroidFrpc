package com.hcnetsdk.jna;

import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.hikvision.netsdk.HCNetSDK;
import com.hikvision.netsdk.INT_PTR;
import com.hikvision.netsdk.NET_DVR_PREVIEWINFO;
import com.hikvision.netsdk.NET_DVR_VOD_PARA;
import com.hikvision.netsdk.PTZCommand;
import com.hikvision.netsdk.PlaybackControlCommand;
import com.sun.jna.Pointer;

public class CameraHelper {
    private static int userID_ = -1;
    private static int previewHandle_ = -1;
    private static int playBackID_ = -1;

    public static int GetUserID() {
        return userID_;
    }

    public static boolean OnInit() {
        return HCNetSDK.getInstance().NET_DVR_Init();
    }

    public static boolean OnLogout() {
        if (userID_ >= 0) {
            HCNetSDKJNAInstance.getInstance().NET_DVR_Logout(userID_);
            userID_ = -1;
        }
        return true;
    }

    // 请求登录次数太多，IP会被锁！！！
    public static boolean OnLogin(String userip, String username, String userpassward, int port) {
        HCNetSDKByJNA.NET_DVR_USER_LOGIN_INFO loginInfo = new HCNetSDKByJNA.NET_DVR_USER_LOGIN_INFO();
        System.arraycopy(userip.getBytes(), 0, loginInfo.sDeviceAddress, 0, userip.length());
        System.arraycopy(username.getBytes(), 0, loginInfo.sUserName, 0, username.length());
        System.arraycopy(userpassward.getBytes(), 0, loginInfo.sPassword, 0, userpassward.length());
        loginInfo.wPort = (short) port;
        HCNetSDKByJNA.NET_DVR_DEVICEINFO_V40 deviceInfo = new HCNetSDKByJNA.NET_DVR_DEVICEINFO_V40();
        loginInfo.write();
        userID_ = HCNetSDKJNAInstance.getInstance().NET_DVR_Login_V40(loginInfo.getPointer(), deviceInfo.getPointer());
        return userID_ != -1;
    }

    public static boolean OnRealPlay(SurfaceView surfaceView) {
        if (userID_ < 0) {
            return false;
        }
        NET_DVR_PREVIEWINFO playInfo = new NET_DVR_PREVIEWINFO();
        playInfo.lChannel = 1;
        playInfo.dwStreamType = 0;
        playInfo.bBlocked = 1;
        playInfo.hHwnd = surfaceView.getHolder();
        previewHandle_ = HCNetSDK.getInstance().NET_DVR_RealPlay_V40(userID_, playInfo, null);
        if (previewHandle_ < 0) {
            Log.e("DeviceSystem", "NET_DVR_RealPlay_V40 error!");
            return false;
        }
        HCNetSDKJNAInstance.getInstance().NET_DVR_OpenSound((short) previewHandle_);
        return true;
    }

    public static boolean OnStopRealPlay() {
        if (previewHandle_ < 0) {
            Log.e("DeviceSystem", "RealPlay_Stop_jni failed with error param");
            return false;
        }
        if (!HCNetSDK.getInstance().NET_DVR_StopRealPlay(previewHandle_)) {
            Log.e("DeviceSystem", "RealPlay_Stop_jni failed");
            return false;
        }
        return true;
    }

    public static void OnPTZControl(int cmd) {
        if (previewHandle_ < 0) {
            return;
        }
        if (!HCNetSDK.getInstance().NET_DVR_PTZControl(previewHandle_, cmd, 0)) {
            System.out.println("PTZControlWithSpeed  PAN_RIGHT 0 faild!" + " err: " + HCNetSDK.getInstance().NET_DVR_GetLastError());
        } else {
            System.out.println("PTZControlWithSpeed  PAN_RIGHT 0 succ");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!HCNetSDK.getInstance().NET_DVR_PTZControl(previewHandle_, cmd, 1)) {
            System.out.println("PTZControlWithSpeed  PAN_RIGHT 1 faild!" + " err: " + HCNetSDK.getInstance().NET_DVR_GetLastError());
        } else {
            System.out.println("PTZControlWithSpeed  PAN_RIGHT 1 succ");
        }
    }

    public static boolean OnPlayBackByTime(NET_DVR_VOD_PARA vodParma) {
        if (userID_ < 0 || vodParma == null) {
            Log.e("SimpleDemo", "PlayBackByTime_v40_jni failed with error param");
            return false;
        }
        playBackID_ = HCNetSDK.getInstance().NET_DVR_PlayBackByTime_V40(userID_, vodParma);
        if (playBackID_ < 0) {
            Log.e("SimpleDemo", "NET_DVR_PlayBackByTime_V40 is failed!Err:" + HCNetSDK.getInstance().NET_DVR_GetLastError());
            return false;
        }
        if (!HCNetSDK.getInstance().NET_DVR_PlayBackControl_V40(playBackID_, PlaybackControlCommand.NET_DVR_PLAYSTART, null, 0, null)) {
            Log.e("SimpleDemo", "NET_DVR_PlayBackControl_V40 is failed!Err:" + HCNetSDK.getInstance().NET_DVR_GetLastError());
            HCNetSDK.getInstance().NET_DVR_StopPlayBack(playBackID_);
            return false;
        }
        if (!HCNetSDK.getInstance().NET_DVR_PlayBackControl_V40(playBackID_, PlaybackControlCommand.NET_DVR_PLAYSTARTAUDIO, null, 0, null)) {
            Log.e("SimpleDemo", "NET_DVR_PlayBackControl_V40 is failed!Err:" + HCNetSDK.getInstance().NET_DVR_GetLastError());
            HCNetSDK.getInstance().NET_DVR_StopPlayBack(playBackID_);
            return false;
        }
        return true;
    }

    public static boolean OnStopPlayBack() {
        if (playBackID_ < 0) {
            Log.e("SimpleDemo", "StopPlayBack_jni failed with error param");
            return false;
        }
        return HCNetSDK.getInstance().NET_DVR_StopPlayBack(playBackID_);
    }

    public static int OnGetPlayBackPos() {
        if (playBackID_ < 0) {
            return 0;
        }
        return HCNetSDK.getInstance().NET_DVR_GetPlayBackPos(playBackID_);
    }

    public static String GetLastErrorMsg() {
        INT_PTR ff = new INT_PTR();
        ff.iValue = GetLastError();
        return HCNetSDK.getInstance().NET_DVR_GetErrorMsg(ff);
    }

    static int GetLastError() {
        return HCNetSDK.getInstance().NET_DVR_GetLastError();
    }
}
