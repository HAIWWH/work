package com.example.hai.controlscm2.Protocol;

/**
 * Created by Hai on 2017/12/16.
 */


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Protocol {


	private static byte DEVICE_CLASS; //设备类（接收和发送公有的）
	private static byte DEVICE;//设备（接收和发送公有的）
	private static byte DEVICE_ID;//设备编号（接收和发送公有的）
	private static byte DEVICE_CONTROL_ID;//设备控制号
	private static short DATA_LENGTH;//数据长度（接收和发送公有的）
	private static short PARAMETER = 0;//参数（发送独有）

	private static byte LOWER_COMPUTER_ID;//下位机编号(接收独有)
	private static String RECEIVE_DATA;//接收的数据（接收独有）


	private static byte[] DATA;

	public Protocol(int DEVICE_CLASS, int DEVICE, int DEVICE_ID, int DEVICE_CONTROL_ID) {  /*数据段没有参数的报文构造函数*/
		Protocol.DEVICE_CLASS = (byte) DEVICE_CLASS;
		Protocol.DEVICE = (byte) DEVICE;
		Protocol.DEVICE_ID = (byte) DEVICE_ID;
		Protocol.DEVICE_CONTROL_ID = (byte) DEVICE_CONTROL_ID;
		Protocol.DATA_LENGTH = (short)4;

	}

	public Protocol(int DEVICE_CLASS, int DEVICE, int DEVICE_ID, int DEVICE_CONTROL_ID, int PARAMETER){/*数据段有参数的报文构造函数*/
		Protocol.DEVICE_CLASS = (byte)DEVICE_CLASS;
		Protocol.DEVICE = (byte)DEVICE;
		Protocol.DEVICE_ID = (byte)DEVICE_ID;
		Protocol.DEVICE_CONTROL_ID = (byte)DEVICE_CONTROL_ID;
		Protocol.PARAMETER = (short)PARAMETER;
		Protocol.DATA_LENGTH = (short)6;

	}
	public Protocol(byte[] msg){ /*当接收到数据后的处理*/
		Protocol.LOWER_COMPUTER_ID = msg[6];
		Protocol.DEVICE_CLASS = msg[7];
		Protocol.DEVICE = msg[8];
		Protocol.DEVICE_ID = msg[9];
		Protocol.DEVICE_CONTROL_ID = msg[10];

		byte[] b =  null;
		System.arraycopy(DATA,5,b,0,DATA.length-5);
		Protocol.RECEIVE_DATA = b.toString();

	}



	public byte[] sendProtocol()  { /*发送时调用此方法*/
		byte[] Msg = Protocol_head.getHEAD();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream headSB = new DataOutputStream(baos);

		try {
			headSB.write(Msg);
			headSB.writeShort(DATA_LENGTH);
			headSB.write(getDATA());
			Msg = baos.toByteArray();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				headSB.close();
				baos.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}

		return Msg;
	}

	public String receiveProtocol() {/*接收时调用此方法*/
		int deviceClass = DEVICE_CLASS; /*设备大类*/
		int device =  DEVICE;/*具体类*/
		int deviceControlId = DEVICE_CONTROL_ID;/*设备控制号*/
		String msgData = null; /*换回的数据*/
		StringBuffer sb = new StringBuffer(); /*字符串流*/

		/*传感器界面*/
		if(deviceClass == 0){
			sb.append(0);/*在字符串头加入一个数字主要是为了判断哪一个界面来接收（传感器界面）*/
			switch (device){
				case 0:/*测距传感器*/
					break;
				case 1:/*亮度传感器*/
					if(deviceControlId == 0){
						sb.append(4);/*设置message.what的值*/
						sb.append(RECEIVE_DATA);/*传入真实要显示的数据*/
					}else{
						//后续完成
					}
					break;
				case 2:/*烟雾传感器*/
					if(deviceControlId == 0){
						sb.append(5);/*设置message.what的值*/
						sb.append(RECEIVE_DATA);/*传入真实要显示的数据*/
					}else{
						//后续完成
					}
					break;
				case 3:/*温湿度传感器*/
					if(deviceControlId == 0){
						sb.append(6);/*设置message.what的值*/
						sb.append(RECEIVE_DATA);/*传入真实要显示的数据*/
					}else if(deviceControlId == 1)
					{
						sb.append(7);/*设置message.what的值*/
						sb.append(RECEIVE_DATA);/*传入真实要显示的数据*/
					} else{
						//后续完成
					}
					break;
				default:
					break;
			}
			msgData = sb.toString();/*将接收到的数据转换成字符串*/
		}


		/*小车界面(未完成)*/
		else if(deviceClass >0 && deviceClass <= 2 ){
			sb.append(1);/*在字符串头加入一个数字主要是为了判断哪一个界面来接收（小车界面）*/
			switch(device){
				case 0:

					break;
				default:
					break;
			}
			msgData = sb.toString();/*将接收到的数据转换成字符串*/
		}


		/*GPS和电量界面(未完成)*/
		else if(deviceClass >2 && deviceClass <= 5){
			sb.append(2);/*在字符串头加入一个数字主要是为了判断哪一个界面来接收（GPS和电量界面）*/
			switch (device){
				case 0:

					break;
				default:
					break;
			}
			msgData = sb.toString();/*将接收到的数据转换成字符串*/
		}else{

		}

		return msgData;
	}




	public static byte getDEVICE_CLASS() {
		return DEVICE_CLASS;
	}

	public static void setDEVICE_CLASS(byte dEVICE_CLASS) {
		DEVICE_CLASS = dEVICE_CLASS;
	}

	public static byte getDEVICE() {
		return DEVICE;
	}

	public static void setDEVICE(byte dEVICE) {
		DEVICE = dEVICE;
	}

	public static byte getDEVICE_ID() {
		return DEVICE_ID;
	}

	public static void setDEVICE_ID(byte dEVICE_ID) {
		DEVICE_ID = dEVICE_ID;
	}

	public static byte getDEVICE_CONTROL_ID() {
		return DEVICE_CONTROL_ID;
	}

	public static void setDEVICE_CONTROL_ID(byte dEVICE_CONTROL_ID) {
		DEVICE_CONTROL_ID = dEVICE_CONTROL_ID;
	}

	public static short getPARAMETER() {
		return PARAMETER;
	}

	public static void setPARAMETER(short pARAMETER) {
		PARAMETER = pARAMETER;
	}
	public static byte getLOWER_COMPUTER_ID() {
		return LOWER_COMPUTER_ID;
	}

	public static void setLOWER_COMPUTER_ID(byte lOWER_COMPUTER_ID) {
		LOWER_COMPUTER_ID = lOWER_COMPUTER_ID;
	}

	public static byte[] getDATA() { /*得到数据*/
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  //创建一个字节数组的输入流
		DataOutputStream headSB = new DataOutputStream(baos); //字节输入流
		try {
			headSB.writeByte(DEVICE_CLASS);//向流里面追加设备大类
			headSB.writeByte(DEVICE);//向流里面追加具体设备
			headSB.writeByte(DEVICE_ID);//向流里面追加设备ID
			headSB.writeByte(DEVICE_CONTROL_ID);//向流里面追加设备控制号
			headSB.writeShort(PARAMETER);//向流里面追加数据

			DATA = baos.toByteArray();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				headSB.close(); //关闭输入流
				baos.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}

		return DATA;  //返回数据段
	}

	public static void setDATA(byte[] dATA) {
		DATA = dATA;
	}

	public static String getRECEIVE_DATA() {
		return RECEIVE_DATA;
	}

	public static void setRECEIVE_DATA(String rECEIVE_DATA) {
		RECEIVE_DATA = rECEIVE_DATA;
	}

	public static short getDATA_LENGTH() {
		return DATA_LENGTH;
	}

	public static void setDATA_LENGTH(short dATA_LENGTH) {
		DATA_LENGTH = dATA_LENGTH;
	}



	/* 静态内部类报文头部类*/
	static class Protocol_head {
		private static final byte OPVULTEKEN = 0x7E; /* 前导符 */
		private static final byte IOCTL = (byte)33;/*控制码*/
		private static short T_NR = (short)(Math.random() * 100);/*报文序列号（随机生成一个0到100的数）*/
		private static byte[] HEAD;

		public static byte[] getHEAD(){  /*得到报文头部数据*/

			//报文序列号自动增加发送一次增加一
			if (T_NR < Short.MAX_VALUE) {
				T_NR++;
			} else {
				// 报文序列号超出最大值
			}

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream headSB = new DataOutputStream(baos);

			try {
				headSB.writeByte(OPVULTEKEN);//加入前导符
				headSB.writeByte(IOCTL);//加入控制码
				headSB.writeShort(T_NR);//加入报文序列号
				HEAD = baos.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					headSB.close(); //关闭输入流
					baos.close();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}

			return HEAD;
		}
	}
}