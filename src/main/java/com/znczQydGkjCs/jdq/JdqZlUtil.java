package com.znczQydGkjCs.jdq;

import com.znczQydGkjCs.util.LoadProperties;

/**
 * �̵���ָ�����
 * */
public class JdqZlUtil {
	
	private static YiJianJdq yjjdq;
	private static ErJianJdq ejjdq;
	
	public static ErJianJdq getEjjdq() {
		return ejjdq;
	}

	public static void setEjjdq(ErJianJdq ejjdq) {
		JdqZlUtil.ejjdq = ejjdq;
	}

	public static YiJianJdq getYjjdq() {
		return yjjdq;
	}

	public static void setYjjdq(YiJianJdq yjjdq) {
		JdqZlUtil.yjjdq = yjjdq;
	}

	/**
	 * ����һ��̵���
	 */
	public static void openYiJianJdq() {
		JdqZlUtil.yjjdq.open();
	}

	/**
	 * �ر�һ��̵���
	 */
	public static void closeYiJianJdq() {
		JdqZlUtil.yjjdq.close();
	}

	/**
	 * ��������̵���
	 */
	public static void openErJianJdq() {
    	System.out.println("��������̵���");
		JdqZlUtil.ejjdq.open();
	}

	/**
	 * �رն���̵���
	 */
	public static void closeErJianJdq() {
		JdqZlUtil.ejjdq.close();
	}


	
	public static void main(String[] args) {
		//YiJianJdq yjjdq=new YiJianJdq();
		//JdqZlUtil.setYjjdq(yjjdq);
		//JdqZlUtil.openYiJianJdq();
    	//JdqZlUtil.openYiJianShangBangDz();
		
		ErJianJdq ejjdq=new ErJianJdq();
		JdqZlUtil.setEjjdq(ejjdq);
		JdqZlUtil.openErJianJdq();
		JdqBf2Util.openErJianXiaBangDz();
		JdqZlUtil.closeErJianJdq();
	}
}
