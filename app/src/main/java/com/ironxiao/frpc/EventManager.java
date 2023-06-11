package com.ironxiao.frpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

enum NotifyType {
    CameraInfoSetComplete,
}

public class EventManager {
    private static EventManager instance;

    public static EventManager Instance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    public interface OnCallback {
        void Call(Object content);
    }

    public Map<NotifyType, List<OnCallback>> ListenerMap_ = new TreeMap<NotifyType, List<OnCallback>>();

    public void AddEventListener(NotifyType eventType, OnCallback callBack) {
        if (!ListenerMap_.containsKey(eventType)) {
            List<OnCallback> list = new ArrayList<OnCallback>() {};
            list.add(callBack);
            ListenerMap_.put(eventType, list);
        } else {
            List<OnCallback> list = ListenerMap_.get(eventType);
            list.add(callBack);
        }
    }

    public void DeleteEventListener(NotifyType eventType, OnCallback callBack) {
        if (ListenerMap_.containsKey(eventType)) {
            List<OnCallback> list = ListenerMap_.get(eventType);
            list.remove(callBack);
        }
    }

    public void DisPatch(NotifyType eventType, Object data) {
        if(ListenerMap_.containsKey(eventType)){
            List<OnCallback> list = ListenerMap_.get(eventType);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).Call(data);
            }
        }
    }
}
