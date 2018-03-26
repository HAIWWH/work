package app.cddic.com.smarter.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import app.cddic.com.smarter.db.database.DeviceDatabase.DeviceTable;
import app.cddic.com.smarter.entity.DeviceMSG;

/**
 * Created by pantiy on 2017/8/4.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public class MSGCursorWrapper extends CursorWrapper {

    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public MSGCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public DeviceMSG getDeviceMSG() {

        String deviceName = getString(getColumnIndex(DeviceTable.DEVICE_NAME));
        int state = getInt(getColumnIndex(DeviceTable.STATE));

        return new DeviceMSG(deviceName, state);
    }


}

