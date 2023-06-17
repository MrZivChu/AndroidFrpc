package com.ironxiao.frpc.helper;

import java.sql.Struct;

public class DataDefines {
    public enum NotifyType {
        CameraInfoSetComplete,
        UpdateRealtimeGasData
    }

    public enum EWarningLevel {
        SecondAlarm,
        FirstAlarm,
        Normal,
        NoResponse
    }

    public static class SGasInfo {
        public String gasName;
        public int timeStamp;
        public int gasValue;
        public int firstValue;
        public int secondValue;
        public EWarningLevel level;
    }
}
