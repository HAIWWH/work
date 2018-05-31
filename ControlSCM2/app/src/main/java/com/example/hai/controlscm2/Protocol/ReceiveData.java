package com.example.hai.controlscm2.Protocol;

import java.io.Serializable;

public class ReceiveData implements Serializable {
    private byte R_LOWER_COMPUTER_ID;
    private byte R_DEVICE_CLASS;
    private byte R_DEVICE;
    private byte R_DEVICE_ID;
    private byte DEVICE_CONTROL_ID;
    private String RECEIVE_DATA;

    public byte getR_LOWER_COMPUTER_ID() {
        return R_LOWER_COMPUTER_ID;
    }

    public void setR_LOWER_COMPUTER_ID(byte r_LOWER_COMPUTER_ID) {
        R_LOWER_COMPUTER_ID = r_LOWER_COMPUTER_ID;
    }

    public byte getR_DEVICE_CLASS() {
        return R_DEVICE_CLASS;
    }

    public void setR_DEVICE_CLASS(byte r_DEVICE_CLASS) {
        R_DEVICE_CLASS = r_DEVICE_CLASS;
    }

    public byte getR_DEVICE() {
        return R_DEVICE;
    }

    public void setR_DEVICE(byte r_DEVICE) {
        R_DEVICE = r_DEVICE;
    }

    public byte getR_DEVICE_ID() {
        return R_DEVICE_ID;
    }

    public void setR_DEVICE_ID(byte r_DEVICE_ID) {
        R_DEVICE_ID = r_DEVICE_ID;
    }

    public byte getDEVICE_CONTROL_ID() {
        return DEVICE_CONTROL_ID;
    }

    public void setDEVICE_CONTROL_ID(byte DEVICE_CONTROL_ID) {
        this.DEVICE_CONTROL_ID = DEVICE_CONTROL_ID;
    }

    public String getRECEIVE_DATA() {
        return RECEIVE_DATA;
    }

    public void setRECEIVE_DATA(String RECEIVE_DATA) {
        this.RECEIVE_DATA = RECEIVE_DATA;
    }


}
