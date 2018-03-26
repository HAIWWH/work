package app.cddic.com.smarter.entity;


public class DeviceShareMSG extends MsgObject {

    private String mDeviceName;

    private String mDevicealias;

    private String mCurrentAssociation;

    private String mMaxAssociations;

    public String getDeviceName() {return mDeviceName;}

    public String getDevicealias() {return mDevicealias;}

    public String getCurrentAssociation() {return mCurrentAssociation;}

    public String getMaxAssociations() {return mMaxAssociations;}


    public DeviceShareMSG(String devicename, String devicealias, String currentAssociation, String maxAssociations){
        mDeviceName = devicename;
        mDevicealias = devicealias;
        mCurrentAssociation = currentAssociation;
        mMaxAssociations = maxAssociations;
    }
}