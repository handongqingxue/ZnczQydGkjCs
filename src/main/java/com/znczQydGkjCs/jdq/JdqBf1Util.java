package com.znczQydGkjCs.jdq;

import com.znczQydGkjCs.util.LoadProperties;

public class JdqBf1Util {

	/**
	 * ̧��һ���ϰ���բ
	 */
	public static void openYiJianShangBangDz() {
		try {
			YiJianJdq yjjdq = JdqZlUtil.getYjjdq();
			yjjdq.sendData(WriteZhiLingConst.KAI_JI_DIAN_QI1);
			int yiJianJdqMaiChong = LoadProperties.getYiJianJdqMaiChong();
			Thread.sleep(yiJianJdqMaiChong);
			yjjdq.sendData(WriteZhiLingConst.GUAN_JI_DIAN_QI1);//����ʱ�����ִ�и�λ����
			//Thread.sleep(1000);
			//yjjdq.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ̧��һ���°���բ
	 */
	public static void openYiJianXiaBangDz() {
		try {
			YiJianJdq yjjdq = JdqZlUtil.getYjjdq();
			yjjdq.sendData(WriteZhiLingConst.KAI_JI_DIAN_QI2);
			int yiJianJdqMaiChong = LoadProperties.getYiJianJdqMaiChong();
			Thread.sleep(yiJianJdqMaiChong);
			yjjdq.sendData(WriteZhiLingConst.GUAN_JI_DIAN_QI2);//����ʱ�����ִ�и�λ����
			//Thread.sleep(1000);
			//yjjdq.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ̧������ϰ���բ
	 */
	public static void openErJianShangBangDz() {
		try {
			ErJianJdq ejjdq = JdqZlUtil.getEjjdq();
			ejjdq.sendData(WriteZhiLingConst.KAI_JI_DIAN_QI1);
			int erJianJdqMaiChong = LoadProperties.getErJianJdqMaiChong();
			Thread.sleep(erJianJdqMaiChong);
			ejjdq.sendData(WriteZhiLingConst.GUAN_JI_DIAN_QI1);//����ʱ�����ִ�и�λ����
			//Thread.sleep(1000);
			//yjjdq.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ̧������°���բ
	 */
	public static void openErJianXiaBangDz() {
		try {
			ErJianJdq ejjdq = JdqZlUtil.getEjjdq();
			ejjdq.sendData(WriteZhiLingConst.KAI_JI_DIAN_QI2);
			int erJianJdqMaiChong = LoadProperties.getErJianJdqMaiChong();
			Thread.sleep(erJianJdqMaiChong);
			ejjdq.sendData(WriteZhiLingConst.GUAN_JI_DIAN_QI2);//����ʱ�����ִ�и�λ����
			//Thread.sleep(1000);
			//yjjdq.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
