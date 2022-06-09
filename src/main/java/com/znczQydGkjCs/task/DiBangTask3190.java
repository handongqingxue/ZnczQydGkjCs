package com.znczQydGkjCs.task;

import java.util.ArrayList;
import java.util.List;

import com.znczQydGkjCs.entity.*;
import com.znczQydGkjCs.jdq.*;
import com.znczQydGkjCs.util.*;

import gnu.io.SerialPort;

public class DiBangTask3190 {

    public static int getWeight(int jyFlag) throws InterruptedException {
        SerialPort serialPortTest = null;
        byte[] bytes = null;
        int preWeight=0;
        int weight=0;
        int steadyCount=0;//�ȶ�����
        int waitTime=0;
        int i = 1;
        //ѭ����������
        List<String> serialPorts = MachineTool.uartPortUseAblefind();
        System.out.println("size==="+serialPorts.size());
        for (String name : serialPorts) {
        	System.out.println("name==="+name);
			serialPortTest = MachineTool.portParameterOpen(name, 9600);
			
			while (true) {
				Thread.sleep(600);
	            //��������
	            bytes = MachineTool.uartReceiveDatafromSingleChipMachine(serialPortTest);
	            System.out.println("b==="+bytes);
	            if (bytes != null && bytes.length > 0) {
	                String str=ByteUtil.byte2hex(bytes);
	                //�ڴ˴����Զ����ݽ����жϴ���ʶ�����
	    			//String str="022B30303030303030314403";
	    			//String str="022B30303030313030314403";
	    			//str=str.substring(str.indexOf("022b"), 32);
	                System.out.println((i++) + ". �Ӵ���" + name + "���յ����ݣ�" + str);
	                System.out.println("str==="+str);
	                if(!str.startsWith("022b"))
	                	continue;
	    			
	    			String str5 = str.substring(4, 6);
	    			String str6 = str.substring(6, 8);
	    			String str7 = str.substring(8, 10);
	    			String str8 = str.substring(10, 12);
	    			String str9 = str.substring(12, 14);
	    			String str10 = str.substring(14, 16);
	    			
	    			System.out.println("str5==="+str5);
	    			System.out.println("str6==="+str6);
	    			System.out.println("str7==="+str7);
	    			System.out.println("str8==="+str8);
	    			System.out.println("str9==="+str9);
	    			System.out.println("str10==="+str10);
	    			
	    			List<String> list=new ArrayList<String>();
	    			list.add(ByteUtil.hex2Zf(str5));
	    			list.add(ByteUtil.hex2Zf(str6));
	    			list.add(ByteUtil.hex2Zf(str7));
	    			list.add(ByteUtil.hex2Zf(str8));
	    			list.add(ByteUtil.hex2Zf(str9));
	    			list.add(ByteUtil.hex2Zf(str10));
	    			weight=ByteUtil.connectZf2Weight(list);
	    			System.out.println("����==="+weight);
					//if(weight>0) {
						if(steadyCount>5) {
							break;
						}
						if(waitTime>10) {
							weight=-1;
							break;
						}
						
						boolean kgl1Open=false;
						boolean kgl2Open=false;
						if(jyFlag==GuoBangJiLu.RU_CHANG_GUO_BANG) {
							YiJianJdq yjjdq = JdqZlUtil.getYjjdq();
							yjjdq.sendData(WriteZhiLingConst.DU_QU_KAI_GUAN_LIANG_ZHUANG_TAI);
							kgl1Open=yjjdq.isKgl1Open();
							kgl2Open=yjjdq.isKgl2Open();
							System.out.println("������open1==="+kgl1Open);
							System.out.println("������open2==="+kgl2Open);
						}
						else if(jyFlag==GuoBangJiLu.CHU_CHANG_GUO_BANG) {
							ErJianJdq ejjdq = JdqZlUtil.getEjjdq();
							ejjdq.sendData(WriteZhiLingConst.DU_QU_KAI_GUAN_LIANG_ZHUANG_TAI);
							kgl1Open=ejjdq.isKgl1Open();
							kgl2Open=ejjdq.isKgl2Open();
							System.out.println("������open1==="+kgl1Open);
							System.out.println("������open2==="+kgl2Open);
						}
						
						if(kgl1Open||kgl2Open) {
							System.out.println("��դ���ڵ�");
							steadyCount=0;
							waitTime++;
						}
						else {
							waitTime=0;
							if(weight<=preWeight+preWeight*0.3||weight>=preWeight-preWeight*0.3)
								steadyCount++;
							else
								steadyCount=0;
							preWeight=weight;
						}
					//}
				} 
	            else {
	            	System.out.println("���ںţ�" + name + "���յ�������Ϊ�գ�");
					if(waitTime>10) {
						weight=-1;
						break;
					}
					steadyCount=0;
					waitTime++;
	            }
			}
            
            serialPortTest.close();
        }
        return weight;
    }
    
    public static void main(String[] args) {
		try {
			getWeight(GuoBangJiLu.RU_CHANG_GUO_BANG);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
