package com.example.rgd_monitor.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rgd_monitor.RusRailwaysInfo;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    public void openDB() {
        db = dbHelper.getWritableDatabase();
    }

    public void insertToDB(RusRailwaysInfo item) {

        ContentValues cv = new ContentValues();

        cv.put(DB_CONSTANTS.STATUS, item.getStatus());
        cv.put(DB_CONSTANTS.TICKED_ID, item.getTickedId());
        cv.put(DB_CONSTANTS.REPORTED_BY, item.getReportedBy());
        cv.put(DB_CONSTANTS.CLASS_ID_MAIN, item.getClassIdMain());
        cv.put(DB_CONSTANTS.CRITICAL_LEVEL, item.getCriticLevel());
        cv.put(DB_CONSTANTS.IS_KNOWN_ERROR_DATE, item.getIsKnownErrorDate());
        cv.put(DB_CONSTANTS.TARGET_FINISH, item.getTargetFinish());
        cv.put(DB_CONSTANTS.DESCRIPTION, item.getDescription());
        cv.put(DB_CONSTANTS.EXT_SYS_NAME, item.getExtSysName());
        cv.put(DB_CONSTANTS.NORM, item.getNorm());
        cv.put(DB_CONSTANTS.LNORM, item.getlNorm());

        db.insert(DB_CONSTANTS.TABLE_NAME, null, cv);
    }

    public ArrayList<RusRailwaysInfo> getFromDB(String desc) {
        ArrayList<RusRailwaysInfo> list = new ArrayList<>();
        String selection = DB_CONSTANTS.DESCRIPTION + " like ?";

        Cursor cursor = db.query(DB_CONSTANTS.TABLE_NAME,null,selection,new String[] {"%" + desc + "%"},null,null,null);

        while (cursor.moveToNext()) {
            RusRailwaysInfo rusRailwaysInfo = new RusRailwaysInfo(
                    cursor.getString(cursor.getColumnIndex(DB_CONSTANTS.STATUS)),
                    cursor.getString(cursor.getColumnIndex(DB_CONSTANTS.TICKED_ID)),
                    cursor.getString(cursor.getColumnIndex(DB_CONSTANTS.REPORTED_BY)),
                    cursor.getString(cursor.getColumnIndex(DB_CONSTANTS.CLASS_ID_MAIN)),
                    cursor.getInt(cursor.getColumnIndex(DB_CONSTANTS.CRITICAL_LEVEL)),
                    cursor.getString(cursor.getColumnIndex(DB_CONSTANTS.IS_KNOWN_ERROR_DATE)),
                    cursor.getString(cursor.getColumnIndex(DB_CONSTANTS.TARGET_FINISH)),
                    cursor.getString(cursor.getColumnIndex(DB_CONSTANTS.DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndex(DB_CONSTANTS.EXT_SYS_NAME)),
                    cursor.getDouble(cursor.getColumnIndex(DB_CONSTANTS.NORM)),
                    cursor.getInt(cursor.getColumnIndex(DB_CONSTANTS.LNORM)));
            list.add(rusRailwaysInfo);
        }


        cursor.close();
        return list;
    }

    public void closeDB() {
        dbHelper.close();
    }

    public void removeAll() {
        db.delete(DB_CONSTANTS.TABLE_NAME, null, null);
    }
}
