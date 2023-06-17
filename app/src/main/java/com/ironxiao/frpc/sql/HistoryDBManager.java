package com.ironxiao.frpc.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ironxiao.frpc.helper.AndroidUtils;

import java.util.ArrayList;
import java.util.List;

public class HistoryDBManager {
    private static final String TAG = "--zwh-- HistoryDBManager";
    static Context context_;
    static SQLiteDatabase database;

    public static void initDB(Context context) {
        context_ = context;
        DBHelper dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public static long AddInfo(int timeStamp, String gasValues) {
        ContentValues values = new ContentValues();
        values.put("AndroidID", AndroidUtils.GetAndroidID(context_));
        values.put("TimeStamp", timeStamp);
        values.put("GasValues", gasValues);
        return database.insert("CameraHistory", null, values);
    }

    public static List<CameraHistoryModel> QueryAllInfo(int pageSize, int pageIndex, int startTimeStamp, int endTimeStamp) {
        String sql = "Select * From CameraHistory where TimeStamp >= " + startTimeStamp + " and TimeStamp <= " + endTimeStamp +
                " order by ID desc Limit " + pageSize + " Offset " + ((pageIndex - 1) * pageSize);
        Log.d(TAG, "QueryAllInfo: " + sql);
        Cursor cursor = database.rawQuery(sql, new String[]{});
        List<CameraHistoryModel> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            CameraHistoryModel model = new CameraHistoryModel();
            model.ID = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
            model.AndroidID = cursor.getString(cursor.getColumnIndexOrThrow("AndroidID"));
            model.TimeStamp = cursor.getInt(cursor.getColumnIndexOrThrow("TimeStamp"));
            model.GasValues = cursor.getString(cursor.getColumnIndexOrThrow("GasValues"));
            list.add(model);
        }
        return list;
    }

    public static int QueryAllCount(int startTimeStamp, int endTimeStamp) {
        String sql = "Select count(ID) as count From CameraHistory where TimeStamp >= " + startTimeStamp + " and TimeStamp <= " + endTimeStamp;
        Log.d(TAG, "QueryPageCount: " + sql);
        Cursor cursor = database.rawQuery(sql, new String[]{});
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public static void DeleteExpireInfo(int timeStamp) {
        String sql = "delete from CameraHistory where TimeStamp<= " + timeStamp;
        database.execSQL(sql);
    }

    public static List<CameraHistoryModel> GetDataByTimestamp(int timeStamp) {
        String sql = "select * from CameraHistory where TimeStamp >= " + timeStamp;
        Log.d(TAG, "GetDataByTimestamp: " + sql);
        Cursor cursor = database.rawQuery(sql, new String[]{});
        List<CameraHistoryModel> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            CameraHistoryModel model = new CameraHistoryModel();
            model.ID = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
            model.AndroidID = cursor.getString(cursor.getColumnIndexOrThrow("AndroidID"));
            model.TimeStamp = cursor.getInt(cursor.getColumnIndexOrThrow("TimeStamp"));
            model.GasValues = cursor.getString(cursor.getColumnIndexOrThrow("GasValues"));
            list.add(model);
        }
        return list;
    }
}
