package app.cddic.com.smarter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import app.cddic.com.smarter.db.MSGUtil;
import app.cddic.com.smarter.entity.DeviceMSG;
import app.cddic.com.smarter.global.SmartApplication;

/**
 * Created by pantiy on 2017/8/4.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public class WriteData {

    public static final String KEY_INIT_DATA = "initData";

    private MSGUtil mMSGUtil;

    public static void begin(Context context) {
        WriteData writeData = new WriteData(context);
        writeData.write();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_INIT_DATA, true).apply();
    }

    private WriteData(Context context) {
        mMSGUtil = MSGUtil.touch(context);
    }

    private void write() {
        mMSGUtil = MSGUtil.touch(SmartApplication.getContext());
        for (int i = 0; i < 5; i++) {
            DeviceMSG item;
            if (i % 2 == 0) {
                item = new DeviceMSG("厨房设备", 1);
            } else {
                item = new DeviceMSG("照明设备", 0);
            }
            mMSGUtil.insertMSG(item);
        }


    }
}
