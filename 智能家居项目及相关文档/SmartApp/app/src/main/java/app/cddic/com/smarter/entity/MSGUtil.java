package app.cddic.com.smarter.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.cddic.com.smarter.db.DatabaseHelper;
import app.cddic.com.smarter.db.MSGCursorWrapper;
import app.cddic.com.smarter.db.database.DeviceDatabase;
import app.cddic.com.smarter.db.database.DeviceDatabase.DeviceTable;
import app.cddic.com.smarter.db.database.InfoDatabase;
import app.cddic.com.smarter.db.database.NoticeDatabase;
import app.cddic.com.smarter.utils.StaticClass;

/**
 * Created by pantiy on 2017/8/4.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public final class MSGUtil {

    private static final String TAG = "MSGUtil";

    private static MSGUtil sMSGUtil;

    private SQLiteDatabase mSQLiteDatabase;

    public static MSGUtil touch(Context context) {
        if (sMSGUtil == null) {
            sMSGUtil = new MSGUtil(context);
        }
        return sMSGUtil;
    }

    private MSGUtil(Context context) {
        mSQLiteDatabase = new DatabaseHelper(context).getWritableDatabase();
    }

    @Nullable
    public <T extends MsgObject> List<T> getMSGList(int MSGType) {
        Log.i(TAG, "getMSGList() MSGType = " + MSGType);
        switch (MSGType) {
            case StaticClass.MSG_DEVICE:
                return getDeviceMSGList(getCursorWrapper(MSGType));
            default:
                return null;
        }
    }

    public void insertMSG(MsgObject msg) {
        switch (msg.getType()) {
            case StaticClass.MSG_DEVICE:
                ContentValues contentValues = getDeviceContentValues((DeviceMSG)msg);
                mSQLiteDatabase.insert(DeviceDatabase.TABLE_NAME, null, contentValues);
                break;
        }
    }


    @SuppressWarnings("unchecked")
    private <T extends MsgObject> List<T> getDeviceMSGList(MSGCursorWrapper cursorWrapper) {
        Log.i(TAG, "getDeviceMSGList()");
        List<T> deviceMSGList = new ArrayList<>();
        if (cursorWrapper.getCount() == 0) {
            Log.i(TAG, "deviceMSGList is null");
            return deviceMSGList;
        }
        cursorWrapper.moveToFirst();
        while (!cursorWrapper.isAfterLast()){
            deviceMSGList.add((T)cursorWrapper.getDeviceMSG());
            cursorWrapper.moveToNext();
        }
        cursorWrapper.close();
        return deviceMSGList;
    }


    private ContentValues getDeviceContentValues(DeviceMSG deviceMSG) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DeviceTable.DEVICE_NAME, deviceMSG.getName());
        contentValues.put(DeviceTable.STATE, deviceMSG.getStateInt());
        return contentValues;
    }

    private ContentValues getInfoContentValues(InfoMSG infoMSG) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(InfoDatabase.InfoTable.ID, infoMSG.getOfferid());
        contentValues.put(InfoDatabase.InfoTable.TIME, infoMSG.getTime());
        contentValues.put(InfoDatabase.InfoTable.MESSAGE,infoMSG.getMessage());
        contentValues.put(InfoDatabase.InfoTable.TITLE,infoMSG.getTitle());
        contentValues.put(InfoDatabase.InfoTable.STATE,infoMSG.getState());
        return contentValues;
    }

    private ContentValues getNoticeContentValues(NoticeMSG noticeMSG) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NoticeDatabase.NoticeTable.REQUEST, noticeMSG.getRequserid());
        contentValues.put(NoticeDatabase.NoticeTable.SORT, noticeMSG.getSort());
        contentValues.put(NoticeDatabase.NoticeTable.STATE, noticeMSG.getState());
        contentValues.put(NoticeDatabase.NoticeTable.TIME,noticeMSG.getTime());
        contentValues.put(NoticeDatabase.NoticeTable.KIND, noticeMSG.getKind());
        contentValues.put(NoticeDatabase.NoticeTable.MESSAGE, noticeMSG.getMessage());
        contentValues.put(NoticeDatabase.NoticeTable.MID, noticeMSG.getDev());
        contentValues.put(NoticeDatabase.NoticeTable.PASS, noticeMSG.getPass());
        return contentValues;
    }

    private MSGCursorWrapper getCursorWrapper(int MSGType) {
        Cursor cursor;
        switch (MSGType) {
            case StaticClass.MSG_DEVICE:
                cursor = mSQLiteDatabase.query(
                        DeviceDatabase.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
                break;
            case StaticClass.MSG_INFO:
                cursor = mSQLiteDatabase.query(
                        InfoDatabase.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
                break;
            case StaticClass.MSG_NOTICE:
                cursor = mSQLiteDatabase.query(
                        NoticeDatabase.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
                break;
            default:
                cursor = null;
        }
        return new MSGCursorWrapper(cursor);
    }
}