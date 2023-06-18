package com.ironxiao.frpc.helper;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProjectUtils {
    static HashMap<DataDefines.EWarningLevel, Integer> warningColorDic = new HashMap<DataDefines.EWarningLevel, Integer>() {{
        put(DataDefines.EWarningLevel.SecondAlarm, Color.argb(1f, 1f, 0f, 0f));
        put(DataDefines.EWarningLevel.FirstAlarm, Color.argb(1f, 1f, 1f, 0f));
        put(DataDefines.EWarningLevel.Normal, Color.argb(0.5f, 0.2f, 0.6f, 0.2f));
        put(DataDefines.EWarningLevel.NoResponse, Color.argb(1f, 0.75f, 0.75f, 0.75f));
    }};

    public static Integer GetWarnColor(DataDefines.EWarningLevel level) {
        return warningColorDic.get(level);
    }

    static ArrayList<DataDefines.SGasInfo> gasInfoList_ = new ArrayList<>();

    public static void AnalysisGasBaseInfoList(Context context) {
        gasInfoList_.clear();
        String content = GasInfoHelper.Instance().GetGases(context);
        if (!TextUtils.isEmpty(content)) {
            String[] gasArray = content.split(";");
            if (gasArray.length >= 4) {
                for (int i = 0; i < 4; i++) {
                    String[] itemArray = gasArray[i].split(",");
                    if (itemArray.length >= 4) {
                        DataDefines.SGasInfo item = new DataDefines.SGasInfo();
                        item.firstValue = Integer.parseInt(itemArray[2]);
                        item.secondValue = Integer.parseInt(itemArray[3]);
                        item.gasName = itemArray[1];
                        gasInfoList_.add(item);
                    }
                }
            }
        }
    }

    public static ArrayList<DataDefines.SGasInfo> GetGasBaseInfoList() {
        return gasInfoList_;
    }

    public static ArrayList<DataDefines.SGasInfo> GasValuesToSGasInfoList(String gasValues, int timeStamp) {
        ArrayList<DataDefines.SGasInfo> gasBaseInfoList = GetGasBaseInfoList();
        ArrayList<DataDefines.SGasInfo> list = new ArrayList<>();
        String[] values = gasValues.split(",");
        if (values.length >= 4 && gasBaseInfoList.size() > 0) {
            for (int j = 0; j < 4; j++) {
                DataDefines.SGasInfo model = new DataDefines.SGasInfo();
                model.firstValue = gasBaseInfoList.get(j).firstValue;
                model.secondValue = gasBaseInfoList.get(j).secondValue;
                model.gasName = gasBaseInfoList.get(j).gasName;
                model.timeStamp = timeStamp;
                model.gasValue = Integer.valueOf(values[j]);
                model.level = model.gasValue >= gasBaseInfoList.get(j).secondValue ? DataDefines.EWarningLevel.SecondAlarm : (
                        model.gasValue >= gasBaseInfoList.get(j).firstValue ? DataDefines.EWarningLevel.FirstAlarm : DataDefines.EWarningLevel.Normal);
                list.add(model);
            }
        }
        return list;
    }
}
