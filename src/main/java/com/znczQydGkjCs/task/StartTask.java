package com.znczQydGkjCs.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.znczQydGkjCs.jdq.*;
import com.znczQydGkjCs.socket.ClientSocket;

public class StartTask {
	
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		// ����ʶ����߳�����
		CpsbsxtTask cpsbsxtTask = new CpsbsxtTask();
		cpsbsxtTask.start();
		
		YiJianJdq yjjdq=new YiJianJdq();
		JdqZlUtil.setYjjdq(yjjdq);
		
		ErJianJdq ejjdq=new ErJianJdq();
		JdqZlUtil.setEjjdq(ejjdq);

		//testLiuCheng();
		
		ClientSocket cs = new ClientSocket();
		cs.connectServer();
		
		// �����߳�����
		while (true) {
			// main����һֱ����
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!cs.isServerOpend()) {
				System.out.println("���������ͨѶ�Ͽ��ˣ��������½�������,ʱ��:"+sdf.format(new Date()));
				cs.connectServer();
			}
		}
	}
	
	public static void testLiuCheng() {
		try {
			///////////һ���ϰ���ʼ
			//Car car1=new Car();
			//car1.setsLicense(" ³A9031");
			//APIUtil.updateYJCPSBDDXX(car1);
			
			//APIUtil.checkYJSBHWGSState();

			//Thread.sleep(10000);
			//APIUtil.checkYJSBHXBHWGSState();
			
			//APIUtil.yiJianChengZhongZhong();
			
			//APIUtil.checkYJXBHWGSState();
			
			//Thread.sleep(10000);
			//APIUtil.checkIfYJXBYwc();
			///////////һ���ϰ�����

			///////////�����ϰ���ʼ
			//Car car2=new Car();
			//car2.setsLicense(" ³A9031");
			//APIUtil.updateEJCPSBDDXX(car2);
			
			//APIUtil.checkEJSBHWGSState();
			
			//Thread.sleep(10000);
			//APIUtil.checkEJSBHXBHWGSState();
			
			//APIUtil.erJianChengZhongZhong();
			
			//APIUtil.checkEJXBHWGSState();

			//Thread.sleep(10000);
			//APIUtil.checkIfEJXBYwc();
			///////////�����ϰ�����
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
