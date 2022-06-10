package com.znczQydGkjCs.cpsbsxt;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import org.json.JSONObject;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.znczQydGkjCs.cpsbsxt.HCNetSDK.NET_DVR_ALARMER;
import com.znczQydGkjCs.cpsbsxt.HCNetSDK.NET_DVR_PLATE_INFO;
import com.znczQydGkjCs.cpsbsxt.HCNetSDK.RECV_ALARM;
import com.znczQydGkjCs.entity.*;
import com.znczQydGkjCs.task.YinZhuTask;
import com.znczQydGkjCs.util.APIUtil;
import com.znczQydGkjCs.util.BangFang1Util;
import com.znczQydGkjCs.util.BangFang2Util;
import com.znczQydGkjCs.util.LoadProperties;
import com.znczQydGkjCs.yz.ByteUtil;
import com.znczQydGkjCs.yz.GiftTool;
import com.znczQydGkjCs.yz.YzZlUtil;

/**
 * 	����ʶ��ص�����
 * @author lhb
 *
 */
public class FMSGCallBack implements HCNetSDK.FMSGCallBack
{
	javax.swing.JTable jTableAlarm = new javax.swing.JTable();
	
    //������Ϣ�ص�����
    public void invoke(NativeLong lCommand, HCNetSDK.NET_DVR_ALARMER pAlarmer, HCNetSDK.RECV_ALARM pAlarmInfo, int dwBufLen, Pointer pUser)
    {
    	
    	System.out.println("����ʶ�𱨾��ص���������");
    	
    	
//    	����ʶ��ı�����Ϣ����ΪCOMM_ITS_PLATE_RESULT���±�����Ϣ����COMM_UPLOAD_PLATE_RESULT���ϱ�����Ϣ����
//    	�ֱ��Ӧ�ӿ�NET_DVR_SetupAlarmChan_V41�в�������byAlarmInfoType=1��byAlarmInfoType=0��
//    			1���豸�Ƿ�֧���±�����Ϣ�ɴ�ע�᷵�ص�������֪�����NET_DVR_DEVICEINFO_V30�ṹ
//    			��bySupport1&0x80����ʾ�Ƿ�֧�ֳ����±�����Ϣ�������ע�᷵��������֧�֣��豸��֧���ϱ�����Ϣ�ϴ���
//        String sAlarmType = new String();
////        DefaultTableModel alarmTableModel = ((DefaultTableModel) jTableAlarm.getModel());//��ȡ���ģ��
//        String[] newRow = new String[3];
//        //����ʱ��
//        Date today = new Date();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        String[] sIP = new String[2];
    	
    	
    	 String sAlarmType = new String();
         DefaultTableModel alarmTableModel = ((DefaultTableModel) jTableAlarm.getModel());//��ȡ���ģ��
         String[] newRow = new String[3];
         //����ʱ��
         Date today = new Date();
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
         String[] sIP = new String[2];

         sAlarmType = new String("lCommand=0x") +  Integer.toHexString(lCommand.intValue());

         System.out.println("lCommand.intValue(): " + lCommand.intValue());
//        COMM_ITS_PLATE_RESULT	0x3050	 12368 (ʮ����) ��ͨץ�Ľ��(�±�����Ϣ)	
//        COMM_UPLOAD_PLATE_RESULT	0x2800   10240 (ʮ����)	��ͨץ�Ľ��
        //lCommand�Ǵ��ı�������
        switch (lCommand.intValue())
        {
        	
        case HCNetSDK.COMM_UPLOAD_PLATE_RESULT:
        	// ������������
        	Car car = new Car();
        	
            HCNetSDK.NET_DVR_PLATE_RESULT strPlateResult = new HCNetSDK.NET_DVR_PLATE_RESULT();
            
            //
            System.out.println("�ṹ���С : " + strPlateResult.dwSize);
            System.out.println("ͼƬ���ȣ�����ͼ�� : " + strPlateResult.dwPicLen);
            
            byte byVehicleType = strPlateResult.byVehicleType;
            
            car.setType(byVehicleType);
            
            System.out.println("��������: " + byVehicleType);
            strPlateResult.write();
            Pointer pPlateInfo = strPlateResult.getPointer();
			byte[] buf = new byte[1024];
			
//			            pPlateInfo.write(0, pAlarmInfo.getByteArray(0, strPlateResult.size()), 0, strPlateResult.size());
            pPlateInfo.write(0, pAlarmInfo.RecvBuffer, 0, strPlateResult.size());
            strPlateResult.read();
            String srt3 = "";
            try {
            	
            	NET_DVR_PLATE_INFO struPlateInfo = strPlateResult.struPlateInfo;
            	byte[] sLicenseByte = struPlateInfo.sLicense;
            	
            	String sLicense = new String(sLicenseByte, "GBK");
            	
                srt3=new String(strPlateResult.struPlateInfo.sLicense,"GBK");
                System.out.println("���ƺ�: " + sLicense);
                car.setsLicense(srt3);
                sAlarmType = sAlarmType + "����ͨץ���ϴ������ƣ�"+ srt3.toString();
                
                // ������ɫ
                byte byColor = struPlateInfo.byColor;
                System.out.println("������ɫ�� " + byColor);
                car.setsLicenseColor(byColor);
            }
            catch (UnsupportedEncodingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            newRow[0] = dateFormat.format(today);
            //��������
            newRow[1] = sAlarmType;
            //�����豸IP��ַ
            sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
            System.out.println("�����豸ip��ַsIP �� " + new String(pAlarmer.sDeviceIP).trim());
            // ����ip��ַ
            car.setIp(new String(pAlarmer.sDeviceIP).trim());
            newRow[2] = sIP[0];
            alarmTableModel.insertRow(0, newRow);

            System.out.println("ͼƬ���ȣ� strPlateResult.dwPicLen: " + strPlateResult.dwPicLen);
            
            /*
            if(strPlateResult.dwPicLen>0)
            {
                SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                String newName = sf.format(new Date());
                FileOutputStream fout;
                try {
                	
                	String photoPath = LoadProperties.getPhotoPath();
                	
                    fout = new FileOutputStream(photoPath+ File.separator + new String(pAlarmer.sDeviceIP).trim() + "_"
                            + newName+"_plateResult.jpg");
                    //���ֽ�д���ļ�
                    long offset = 0;
                    ByteBuffer buffers = strPlateResult.pBuffer1.getByteBuffer(offset, strPlateResult.dwPicLen);
                    byte [] bytes = new byte[strPlateResult.dwPicLen];
                    buffers.rewind();
                    buffers.get(bytes);
                    fout.write(bytes);
                    
                    car.setBytes(bytes);
                    fout.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            */
            
            // ���ƵĶ���
            System.out.println(car.toString());
            
            // �õ����ƶ���� car
            // ���ݳ��ƺţ� ��ѯ��ƥ��Ĵ��볧�Ķ���, �������̧�ˣ� �޸Ķ���״̬Ϊ�����볧�����ŶӺŵ�״̬��Ϊ�������С�
            // ����̨����Ϣ�� ̨����Ϣ�����������볡ʱ�䡿���������볡��Ƭ������̨���붩��������������ϵ��
            
            try {
            	int bfh = LoadProperties.getBangFangHao();
            	String ip = car.getIp().trim();
            	if(LoadProperties.getHikvisionYiJianIP().equals(ip)) {
            		
            		JSONObject resultJO=null;
            		resultJO=APIUtil.getDingDan(car.getsLicense(),DingDanZhuangTai.YI_JIAN_PAI_DUI_ZHONG_TEXT);
            		if(resultJO!=null) {
	                    if("ok".equals(resultJO.getString("status"))) {
	                		//һ�쳵��ʶ������ͷ
	                    	switch (bfh) {
							case APIUtil.YI_HAO_BANG_FANG:
								BangFang1Util.updateYJCPSBDDXX(car);
								break;
							case APIUtil.ER_HAO_BANG_FANG:
								BangFang2Util.updateYJCPSBDDXX(car);
								break;
							}
	                    }
	                    else {
	                		//Ŀǰ�ذ���ֻ��һ������ͷ�������������ͷ�������
	                		resultJO=APIUtil.getDingDan(car.getsLicense(),DingDanZhuangTai.ER_JIAN_PAI_DUI_ZHONG_TEXT);
	                		if(resultJO!=null) {
	    	                    if("ok".equals(resultJO.getString("status"))) {
	    	                		//���쳵��ʶ������ͷ
	    	                    	switch (bfh) {
									case APIUtil.YI_HAO_BANG_FANG:
		    	                    	BangFang1Util.updateEJCPSBDDXX(car);
										break;
									case APIUtil.ER_HAO_BANG_FANG:
										BangFang2Util.updateEJCPSBDDXX(car);
										break;
									}
	    	                    }
	    	                    else {
		                        	System.out.println("message==="+resultJO.getString("message"));
		                        	System.out.println("����������û���ҵ�ƥ�䶩��");
		                    		YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
	    	                    }
	                		}
	                    }
            		}
            	} else if (LoadProperties.getHikvisionErJianIP().equals(ip)) {
            		//���쳵��ʶ������ͷ
            		//uploadingCarInfoLiChang(car);
            		//APIUtil.updateEJCPSBDDXX(car);
            		
            		JSONObject resultJO=null;
            		resultJO=APIUtil.getDingDan(car.getsLicense(),DingDanZhuangTai.ER_JIAN_PAI_DUI_ZHONG_TEXT);
            		if(resultJO!=null) {
	                    if("ok".equals(resultJO.getString("status"))) {
	                		//���쳵��ʶ������ͷ
	                    	switch (bfh) {
							case APIUtil.YI_HAO_BANG_FANG:
		                    	BangFang1Util.updateEJCPSBDDXX(car);
								break;
							}
	                    }
            		}
            	} else {
            		System.out.println("����ʶ������ͷip��ַ���ô���");
            	}
			} catch (Exception e) {
				System.out.println(e + "");
				e.printStackTrace();
			}
            break;
        case HCNetSDK.COMM_ITS_PLATE_RESULT:
            HCNetSDK.NET_ITS_PLATE_RESULT strItsPlateResult = new HCNetSDK.NET_ITS_PLATE_RESULT();
            strItsPlateResult.write();
            Pointer pItsPlateInfo = strItsPlateResult.getPointer();
//            pItsPlateInfo.write(0, pAlarmInfo.getByteArray(0, strItsPlateResult.size()), 0, strItsPlateResult.size());
//            pPlateInfo.write(0, buf, 0, strPlateResult.size());
            strItsPlateResult.read();
            try {
                 srt3=new String(strItsPlateResult.struPlateInfo.sLicense,"GBK");
                sAlarmType = sAlarmType + ",�������ͣ�"+strItsPlateResult.byVehicleType + ",��ͨץ���ϴ������ƣ�"+ srt3;
            }
            catch (UnsupportedEncodingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            newRow[0] = dateFormat.format(today);
            //��������
            newRow[1] = sAlarmType;
            //�����豸IP��ַ
            sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
            newRow[2] = sIP[0];
            alarmTableModel.insertRow(0, newRow);

            for(int i=0;i<strItsPlateResult.dwPicNum;i++)
            {
                if(strItsPlateResult.struPicInfo[i].dwDataLen>0)
                {
                    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String newName = sf.format(new Date());
                    FileOutputStream fout;
                    try {
                        String filename = ".\\pic\\"+ new String(pAlarmer.sDeviceIP).trim() + "_"
                                + newName+"_type["+strItsPlateResult.struPicInfo[i].byType+"]_ItsPlate.jpg";
                        fout = new FileOutputStream(filename);
                        //���ֽ�д���ļ�
                        long offset = 0;
                        ByteBuffer buffers = strItsPlateResult.struPicInfo[i].pBuffer.getByteBuffer(offset, strItsPlateResult.struPicInfo[i].dwDataLen);
                        byte [] bytes = new byte[strItsPlateResult.struPicInfo[i].dwDataLen];
                        buffers.rewind();
                        buffers.get(bytes);
                        fout.write(bytes);
                        fout.close();
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            break;
        	
            //9000����
            case HCNetSDK.COMM_ALARM_V30:
               HCNetSDK.NET_DVR_ALARMINFO_V30 strAlarmInfoV30 = new HCNetSDK.NET_DVR_ALARMINFO_V30();
               strAlarmInfoV30.write();
               Pointer pInfoV30 = strAlarmInfoV30.getPointer();
               pInfoV30.write(0, pAlarmInfo.RecvBuffer, 0, strAlarmInfoV30.size());
               strAlarmInfoV30.read();

                switch (strAlarmInfoV30.dwAlarmType)
                {
                    case 0:
                        sAlarmType = new String("�ź�������");
                        break;
                    case 1:
                        sAlarmType = new String("Ӳ����");
                        break;
                    case 2:
                        sAlarmType = new String("�źŶ�ʧ");
                        break;
                    case 3:
                        sAlarmType = new String("�ƶ����");
                        break;
                    case 4:
                        sAlarmType = new String("Ӳ��δ��ʽ��");
                        break;
                    case 5:
                        sAlarmType = new String("��дӲ�̳���");
                        break;
                    case 6:
                        sAlarmType = new String("�ڵ�����");
                        break;
                    case 7:
                        sAlarmType = new String("��ʽ��ƥ��");
                        break;
                    case 8:
                        sAlarmType = new String("�Ƿ�����");
                        break;
                }

                newRow[0] = dateFormat.format(today);
                //��������
                newRow[1] = sAlarmType;
                //�����豸IP��ַ
                sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                newRow[2] = sIP[0];
                alarmTableModel.insertRow(0, newRow);

                break;

            //8000����
            case HCNetSDK.COMM_ALARM:
                HCNetSDK.NET_DVR_ALARMINFO strAlarmInfo = new HCNetSDK.NET_DVR_ALARMINFO();
                strAlarmInfo.write();
                Pointer pInfo = strAlarmInfo.getPointer();
                pInfo.write(0, pAlarmInfo.RecvBuffer, 0, strAlarmInfo.size());
                strAlarmInfo.read();


                switch (strAlarmInfo.dwAlarmType)
                {
                    case 0:
                        sAlarmType = new String("�ź�������");
                        break;
                    case 1:
                        sAlarmType = new String("Ӳ����");
                        break;
                    case 2:
                        sAlarmType = new String("�źŶ�ʧ");
                        break;
                    case 3:
                        sAlarmType = new String("�ƶ����");
                        break;
                    case 4:
                        sAlarmType = new String("Ӳ��δ��ʽ��");
                        break;
                    case 5:
                        sAlarmType = new String("��дӲ�̳���");
                        break;
                    case 6:
                        sAlarmType = new String("�ڵ�����");
                        break;
                    case 7:
                        sAlarmType = new String("��ʽ��ƥ��");
                        break;
                    case 8:
                        sAlarmType = new String("�Ƿ�����");
                        break;
                }

                newRow[0] = dateFormat.format(today);
                //��������
                newRow[1] = sAlarmType;
                //�����豸IP��ַ
                sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                newRow[2] = sIP[0];
                alarmTableModel.insertRow(0, newRow);

                break;

            //ATM DVR transaction information
            case HCNetSDK.COMM_TRADEINFO:
                //��������Ϣ����
                break;

            //IPC�������øı䱨��
            case HCNetSDK.COMM_IPCCFG:
                // ����IPC����
                break;

            default:
                System.out.println("δ֪��������");
                break;
        }
    }

	
	
	
	
	/**
	 * 
	 * @param tradeFile
	 * @return
	 */
	public static byte[] FileToByte(File tradeFile){
	    byte[] buffer = null;
	    try
	    {
	        FileInputStream fis = new FileInputStream(tradeFile);
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        byte[] b = new byte[1024];
	        int n;
	        while ((n = fis.read(b)) != -1)
	        {
	            bos.write(b, 0, n);
	        }
	        fis.close();
	        bos.close();
	        buffer = bos.toByteArray();
	    }catch (FileNotFoundException e){
	        e.printStackTrace();
	    }catch (IOException e){
	        e.printStackTrace();
	    }
	    return buffer;
	}

	
	/** 
     * 2����ת16�����ַ��� 
     * @param bytes 
     * @return 
     */  
    public static String byteToHexString(byte[] bytes){  
        if(bytes==null){  
            return null;  
        }  
        StringBuffer sb = new StringBuffer();     
        for (int i = 0; i < bytes.length; i++) {     
             String strHex=Integer.toHexString(bytes[i]);     
             if(strHex.length() > 3){     
                    sb.append(strHex.substring(6));     
             } else {     
                  if(strHex.length() < 2){     
                     sb.append("0" + strHex);     
                  } else {     
                     sb.append(strHex);     
                  }     
             }     
        }     
       return  sb.toString();     
   }  

//	public void invoke(NativeLong lCommand, NET_DVR_ALARMER pAlarmer, RECV_ALARM pAlarmInfo, int dwBufLen,
//			Pointer pUser) {
//		// TODO Auto-generated method stub
//		
//	}
    
    
    
}