package app.cddic.com.smarter.entity;


import app.cddic.com.smarter.utils.StaticClass;

public class MyInformationMSG extends MsgObject {

    private String mName;

    private String mUserType;

    private String mSex;

    private String mUserId;

    private String mRegistrationTime;

    private String mCurrentState;

    private String mMailBox;


    public String getName() {return mName;}

    public void setName(String mName) {this.mName = mName;}

    public String getUserType() {return mUserType;}

    public void setUserType(String mUserType) {this.mUserType = mUserType;}

    public String getSex() {return mSex;}

    public void setSex(String mSex) {this.mSex = mSex;}

    public String getUserId() {return mUserId;}

    public void setUserAccount(String mUserAccount) {this.mUserId = mUserAccount;}

    public String getRegistrationTime() {return mRegistrationTime;}

    public void setRegistrationTime(String mRegistrationTime) {this.mRegistrationTime = mRegistrationTime;}

    public String getCurrentState() {return mCurrentState;}

    public void setCurrentState(String mCurrentState) {this.mCurrentState = mCurrentState;}

    public String getMailBox() {return mMailBox;}

    public void setMailBox(String mMailBox) {this.mMailBox = mMailBox;}

    public MyInformationMSG(String name, String userType, String sex, String userAccount, String registrationTime, String currentState, String mailBox){
        mName = name;
        mUserType = userType;
        mSex = sex;
        mUserId = userAccount;
        mRegistrationTime = registrationTime;
        mCurrentState = currentState;
        mMailBox = mailBox;
        setType(StaticClass.MSG_MY_INFORMATION);
    }

}
