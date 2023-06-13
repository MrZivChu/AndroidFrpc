package com.ironxiao.frpc.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ironxiao.frpc.helper.AndroidUtils;

import java.util.ArrayList;
import java.util.List;

public class HistoryDBManager {
    static Context context_;
    static SQLiteDatabase database;

    public static void initDB(Context context) {
        context_ = context;
        DBHelper dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public static long AddInfo(int gasIndex, String timeStamp, float gasValue) {
        ContentValues values = new ContentValues();
        values.put("AndroidID", AndroidUtils.GetAndroidID(context_));
        values.put("GasIndex", gasIndex);
        values.put("TimeStamp", timeStamp);
        values.put("GasValue", gasValue);
        return database.insert("CameraHistory", null, values);
    }

    public static int GetAllCount() {
        Cursor cursor = database.query("CameraHistory", null, null, null, null, null, null);
        return cursor.getCount();
    }

    public static List<CameraHistoryModel> QueryAllInfo(int pageSize, int pageIndex) {
        String sql = "Select * From CameraHistory order by ID desc " + " Limit '" + pageSize + "' Offset '" + ((pageIndex - 1) * pageSize) + "'";
        Cursor cursor = database.rawQuery(sql, new String[]{});
        List<CameraHistoryModel> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            CameraHistoryModel model = new CameraHistoryModel();
            model.ID = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
            model.AndroidID = cursor.getString(cursor.getColumnIndexOrThrow("AndroidID"));
            model.GasIndex = cursor.getInt(cursor.getColumnIndexOrThrow("GasIndex"));
            model.TimeStamp = cursor.getString(cursor.getColumnIndexOrThrow("TimeStamp"));
            model.GasValue = cursor.getFloat(cursor.getColumnIndexOrThrow("GasValue"));
            list.add(model);
        }
        return list;
    }

    public static void DeleteAllInfo() {
        String sql = "delete from CameraHistory";
        database.execSQL(sql);
    }
}
