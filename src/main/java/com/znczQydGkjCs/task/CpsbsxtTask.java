package com.znczQydGkjCs.task;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.znczQydGkjCs.cpsbsxt.*;
import com.znczQydGkjCs.cpsbsxt.HCNetSDK.NET_DVR_DEVICEINFO_V30;
import com.znczQydGkjCs.util.LoadProperties;

public class CpsbsxtTask extends Thread {
	static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean initSuc = hCNetSDK.NET_DVR_Init();
		
		System.out.println("initSuc: " + initSuc);
		//  ע��ص�
	 	FMSGCallBack fMSFCallBack = new FMSGCallBack();  //�����ص�����ʵ��
		Pointer pUser = null;
		hCNetSDK.NET_DVR_SetDVRMessageCallBack_V30(fMSFCallBack, pUser );
		
		//ע��1
        String  yiJianDeviceIP = LoadProperties.getHikvisionYiJianIP();//�豸ip��ַ
        System.out.println("yiJianDeviceIP==="+yiJianDeviceIP);
        //String  yiJianDeviceIP = "192.168.1.11";
        int yiJianPort = Integer.parseInt(LoadProperties.getHikvisionYiJianPort());
        //int yiJianPort = 8000;
        String yiJianUserName =LoadProperties.getHikvisionYiJianUserName();
        //String yiJianUserName ="admin";
        String yiJianPassword = LoadProperties.getHikvisionYiJianPassword();
        //String yiJianPassword = "lanfan2022";
        NET_DVR_DEVICEINFO_V30 yiJianDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
        NativeLong lUserID = hCNetSDK.NET_DVR_Login_V30(yiJianDeviceIP, (short) yiJianPort, yiJianUserName, yiJianPassword, yiJianDeviceInfo);

        long userID = lUserID.longValue();
        if (userID == -1){
           System.out.println("ע��ʧ�ܣ�ipΪ�� " + yiJianDeviceIP);
           return;
        }
        System.out.println(yiJianDeviceIP + "  userID: " + userID);
        
        NativeLong yiJianAlarmHandle = hCNetSDK.NET_DVR_SetupAlarmChan_V30(lUserID);
        if (yiJianAlarmHandle.intValue() == -1){
        	System.out.println(yiJianDeviceInfo + "����ʧ�ܣ�");
            return;
        }
        
        /*
        //ע��2
	    String  erJianDeviceIP = LoadProperties.getHikvisionErJianIP();//�豸ip��ַ
	    int erJianPort = Integer.parseInt(LoadProperties.getHikvisionErJianPort());
	    String erJianUserName =LoadProperties.getHikvisionErJianUserName();
	    String erJianPassword = LoadProperties.getHikvisionErJianPassword();
	    NET_DVR_DEVICEINFO_V30 erJianDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
	    NativeLong lUserID2 = hCNetSDK.NET_DVR_Login_V30(erJianDeviceIP, (short) erJianPort, erJianUserName, erJianPassword, erJianDeviceInfo);

        long userID2 = lUserID.longValue();
        if (userID2 == -1){
        	System.out.println("ע��ʧ�ܣ�ipΪ�� " + erJianDeviceIP);
            return;
        }
        System.out.println(erJianDeviceIP + "  userID2: " + userID2);

        NativeLong erJianAlarmHandle = hCNetSDK.NET_DVR_SetupAlarmChan_V30(lUserID2);
        if (erJianAlarmHandle.intValue() == -1){
        	System.out.println(erJianDeviceIP + "����ʧ�ܣ�");
            return;
        }
        */
	}
}
