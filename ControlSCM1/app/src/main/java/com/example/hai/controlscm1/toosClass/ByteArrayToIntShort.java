package com.example.hai.controlscm1.toosClass;

/**
 * Created by Hai on 2017/12/21.
 */

public class ByteArrayToIntShort {

    //int 转 byte[]类型
    public static byte[] intToByte(int value)
    {
        byte[] byte_src = new byte[4];
        byte_src[0] = (byte) ((value & 0xFF000000)>>24);
        byte_src[1] = (byte) ((value & 0x00FF0000)>>16);
        byte_src[2] = (byte) ((value & 0x0000FF00)>>8);
        byte_src[3] = (byte) ((value & 0x000000FF));
        return byte_src;
    }


    //byte[]转int类型
    public static int byteToInt(byte[] ary, int offset) {
        int value;
        value = (int) ((ary[offset+3]&0xFF)
                | ((ary[offset+2]<<8) & 0xFF00)
                | ((ary[offset+1]<<16)& 0xFF0000)
                | ((ary[offset]<<24) & 0xFF000000));
        return value;
    }

    //short转byte[]类型
    public static byte[] shortToByte(short value) {
        byte[] byte_src = new byte[2];
        byte_src[0] = (byte) ((value & 0x0000FF00)>>8);
        byte_src[1] = (byte) ((value & 0x000000FF));
        return byte_src;
    }


    //byte[]转short类型
    public static short byteToShort(byte[] ary, int offset) {
        short value;
        value = (short) ((ary[offset+1]&0xFF)
                | ((ary[offset]<<8) & 0xFF00)
        );
        return value;
    }
}