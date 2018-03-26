package app.cddic.com.smarter.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import app.cddic.com.smarter.db.database.AccountDatabase;
import app.cddic.com.smarter.db.database.AlarmDataBase;
import app.cddic.com.smarter.db.database.ChatDataBase;
import app.cddic.com.smarter.db.database.ContactDatabase;
import app.cddic.com.smarter.db.database.DeviceDatabase;
import app.cddic.com.smarter.db.database.FileDatabase;
import app.cddic.com.smarter.db.database.NoticeDatabase;
import app.cddic.com.smarter.db.database.PluginDatabase;
import app.cddic.com.smarter.db.database.SettingDatabase;

/**
 * Created by pantiy on 2017/8/4.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "smart.db";
    private static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AccountDatabase.getSql());
        db.execSQL(DeviceDatabase.getSql());
        db.execSQL(FileDatabase.getSql());
        //db.execSQL(InfoDatabase.getSql());
        db.execSQL(NoticeDatabase.getSql());
        db.execSQL(AlarmDataBase.getSql());
        db.execSQL(ChatDataBase.getSql());
        db.execSQL(SettingDatabase.getSql());
        db.execSQL(ContactDatabase.getSql());
        db.execSQL(PluginDatabase.getSql());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}