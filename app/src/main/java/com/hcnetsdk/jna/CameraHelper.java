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

    public static int GetUserID() {
        return userID_;
    }

    public static boolean OnInit() {
        return HCNetSDK.getInstance().NET_DVR_Init();
    }

    public static boolean OnLogout(int userID) {
        userID_ = -1;
        return HCNetSDKJNAInstance.getInstance().NET_DVR_Logout(userID);
    }

    public static int OnLogin(String userip, String username, String userpassward, int port) {
        HCNetSDKByJNA.NET_DVR_USER_LOGIN_INFO loginInfo = new HCNetSDKByJNA.NET_DVR_USER_LOGIN_INFO();
        System.arraycopy(userip.getBytes(), 0, loginInfo.sDeviceAddress, 0, userip.length());
        System.arraycopy(username.getBytes(), 0, loginInfo.sUserName, 0, username.length());
        System.arraycopy(userpassward.getBytes(), 0, loginInfo.sPassword, 0, userpassward.length());
        loginInfo.wPort = (short) port;
        HCNetSDKByJNA.NET_DVR_DEVICEINFO_V40 deviceInfo = new HCNetSDKByJNA.NET_DVR_DEVICEINFO_V40();
        loginInfo.write();
        userID_ = HCNetSDKJNAInstance.getInstance().NET_DVR_Login_V40(loginInfo.getPointer(), deviceInfo.getPointer());
        return userID_;
    }

    public static int OnRealPlay(int userID, SurfaceView surfaceView) {
        NET_DVR_PREVIEWINFO playInfo = new NET_DVR_PREVIEWINFO();
        playInfo.lChannel = 1;
        playInfo.dwStreamType = 0;
        playInfo.bBlocked = 1;
        playInfo.hHwnd = surfaceView.getHolder();
        int iRet = HCNetSDK.getInstance().NET_DVR_RealPlay_V40(userID, playInfo, null);
        if (iRet < 0) {
            Log.e("DeviceSystem", "NET_DVR_RealPlay_V40 error!");
            return -1;
        }
        boolean bRet = HCNetSDKJNAInstance.getInstance().NET_DVR_OpenSound((short) iRet);
        if (bRet) {
            Log.e("DeviceSystem", "NET_DVR_OpenSound Succ!");
        }
        return iRet;
    }

    public static boolean OnStopRealPlay(int previewHandle) {
        if (previewHandle < 0) {
            Log.e("DeviceSystem", "RealPlay_Stop_jni failed with error param");
            return false;
        }
        if (!HCNetSDK.getInstance().NET_DVR_StopRealPlay(previewHandle)) {
            Log.e("DeviceSystem", "RealPlay_Stop_jni failed");
            return false;
        }
        return true;
    }

    public static void OnPTZControl(int previewHandle, int cmd) {
        if (!HCNetSDK.getInstance().NET_DVR_PTZControl(previewHandle, cmd, 0)) {
            System.out.println("PTZControlWithSpeed  PAN_RIGHT 0 faild!" + " err: " + HCNetSDK.getInstance().NET_DVR_GetLastError());
        } else {
            System.out.println("PTZControlWithSpeed  PAN_RIGHT 0 succ");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!HCNetSDK.getInstance().NET_DVR_PTZControl(previewHandle, cmd, 1)) {
            System.out.println("PTZControlWithSpeed  PAN_RIGHT 1 faild!" + " err: " + HCNetSDK.getInstance().NET_DVR_GetLastError());
        } else {
            System.out.println("PTZControlWithSpeed  PAN_RIGHT 1 succ");
        }
    }

    public static int OnPlayBackByTime(int userID, NET_DVR_VOD_PARA vodParma) {
        if (userID < 0 || vodParma == null) {
            Log.e("SimpleDemo", "PlayBackByTime_v40_jni failed with error param");
            return -1;
        }
        int iPlaybackID = HCNetSDK.getInstance().NET_DVR_PlayBackByTime_V40(userID, vodParma);
        if (iPlaybackID < 0) {
            Log.e("SimpleDemo", "NET_DVR_PlayBackByTime_V40 is failed!Err:" + HCNetSDK.getInstance().NET_DVR_GetLastError());
            return -2;
        }
        if (!HCNetSDK.getInstance().NET_DVR_PlayBackControl_V40(iPlaybackID, PlaybackControlCommand.NET_DVR_PLAYSTART, null, 0, null)) {
            Log.e("SimpleDemo", "NET_DVR_PlayBackControl_V40 is failed!Err:" + HCNetSDK.getInstance().NET_DVR_GetLastError());
            HCNetSDK.getInstance().NET_DVR_StopPlayBack(iPlaybackID);
            return -3;
        }
        if (!HCNetSDK.getInstance().NET_DVR_PlayBackControl_V40(iPlaybackID, PlaybackControlCommand.NET_DVR_PLAYSTARTAUDIO, null, 0, null)) {
            Log.e("SimpleDemo", "NET_DVR_PlayBackControl_V40 is failed!Err:" + HCNetSDK.getInstance().NET_DVR_GetLastError());
            HCNetSDK.getInstance().NET_DVR_StopPlayBack(iPlaybackID);
            return -4;
        }
        return iPlaybackID;
    }

    public static boolean OnStopPlayBack(int playbackID) {
        if (playbackID < 0) {
            Log.e("SimpleDemo", "StopPlayBack_jni failed with error param");
            return false;
        }
        return HCNetSDK.getInstance().NET_DVR_StopPlayBack(playbackID);
    }

    public static int OnGetPlayBackPos(int playbackID) {
        return HCNetSDK.getInstance().NET_DVR_GetPlayBackPos(playbackID);
    }

    public static int OnPlayBackSurfaceChanged(int previewHandle, int nRegionNum, SurfaceView
            surfaceView) {
        if (previewHandle < 0 || nRegionNum < 0) {
            Log.e("SimpleDemo", "RealPlaySurfaceChanged_jni failed with error param");
            return -1;
        }
        return HCNetSDK.getInstance().NET_DVR_PlayBackSurfaceChanged(previewHandle, nRegionNum, surfaceView.getHolder());
    }

    public static int OnRealPlaySurfaceChanged(int previewHandle, int nRegionNum, SurfaceView
            surfaceView) {
        if (previewHandle < 0 || nRegionNum < 0) {
            Log.e("SimpleDemo", "RealPlaySurfaceChanged_jni failed with error param");
            return -1;
        }
        return HCNetSDK.getInstance().NET_DVR_RealPlaySurfaceChanged(previewHandle, nRegionNum, surfaceView.getHolder());
    }

    public static int GetLastError() {
        return HCNetSDK.getInstance().NET_DVR_GetLastError();
    }

    public static String GetLastErrorMsg(INT_PTR errorID) {
        return HCNetSDK.getInstance().NET_DVR_GetErrorMsg(errorID);
    }
}
