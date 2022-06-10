package com.znczQydGkjCs.util;

import org.json.JSONObject;

import com.znczQydGkjCs.cpsbsxt.Car;
import com.znczQydGkjCs.entity.*;
import com.znczQydGkjCs.jdq.*;
import com.znczQydGkjCs.task.*;
import com.znczQydGkjCs.yz.YzZlUtil;

/**
 * ��������1�Ű�����������
 * */
public class BangFang1Util {

	
	/**
	 * ����һ�쳵��ʶ�𶩵���Ϣ
	 * @param car
	 */
	public static void updateYJCPSBDDXX(Car car) {
		try {
			System.out.println("��ѯ����״̬Ϊһ���Ŷ��еĶ���");
			JSONObject resultJO=APIUtil.getDingDan(car.getsLicense(),DingDanZhuangTai.YI_JIAN_PAI_DUI_ZHONG_TEXT);
	        if("ok".equals(resultJO.getString("status"))) {
	        	System.out.println("���ڸö���");
	        	System.out.println("������������״̬��֤�Ƿ������������");
	        	Integer bfh = LoadProperties.getBangFangHao();
	        	JSONObject ddExistResult = APIUtil.checkDingDanIfExistByZt(bfh,0,DingDanZhuangTai.YI_JIAN_SHANG_BANG_TEXT,DingDan.DAI_SHANG_BANG,DingDan.DAI_SHANG_BANG);
	        	if("ok".equals(ddExistResult.getString("status"))) {
	            	System.out.println("������������������״̬������������");
	        	}
	        	else {
	            	System.out.println("����һ��̵���");
	            	/**
	            	 * ��ʱû���豸�������ε���δ���
		    		JdqZlUtil.openYiJianJdq();
	            	 */
		        	System.out.println("̧��һ���ϰ���բ");
	            	/**
	            	 * ��ʱû���豸�������ε���δ���
		        	JdqBf1Util.openYiJianShangBangDz();
	            	 */
		        	
		        	System.out.println("�ı䶩��״̬Ϊһ���ϰ�");
					JSONObject ddJO=resultJO.getJSONObject("dingDan");
		        	DingDan dd=new DingDan();
		        	dd.setId(ddJO.getInt("id"));
		        	dd.setDdztMc(DingDanZhuangTai.YI_JIAN_SHANG_BANG_TEXT);
		        	dd.setYjzt(DingDan.DAI_SHANG_BANG);
		        	dd.setYjbfh(bfh);
		        	APIUtil.editDingDan(dd);

	            	/**
	            	 * ��ʱû���豸�������ε���δ���
		    		YinZhuTask.sendMsg(YzZlUtil.get87().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
	            	 */
		        	
		    		checkYJSBHWGSState();
	        	}
	        }
	        else {
	        	System.out.println("message==="+resultJO.getString("message"));
	        	System.out.println("����������û���ҵ�ƥ�䶩��");
	    		YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
				Thread.sleep(3000);
	    		YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
	        }
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ���һ���ϰ������դ״̬
	 */
	public static void checkYJSBHWGSState() {
		try {
			/*
			 * 
			YiJianJdq yjjdq = JdqZlUtil.getYjjdq();
			System.out.println("ǰopen1==="+yjjdq.isKgl1Open());
			 */
			Integer bfh = LoadProperties.getBangFangHao();
			int waitTime=0;
			while (true) {
            	/**
            	 * ��ʱû���豸�������ε���δ���
				yjjdq.sendData(WriteZhiLingConst.DU_QU_KAI_GUAN_LIANG_ZHUANG_TAI);
				*/
				Thread.sleep(1000);
				waitTime+=1000;
					
				//System.out.println("��open1==="+yjjdq.isKgl1Open());
				if(waitTime>30*1000) {
					System.out.println("�ϰ�ʧ�ܣ������³���ʶ��");
					System.out.println("���Ҷ���״̬Ϊһ���ϰ��Ķ�������һ���ϰ�״̬�Ӵ��ϰ�����Ϊһ���Ŷ���");
					DingDan dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_SHANG_BANG_TEXT);
					dd.setXddztMc(DingDanZhuangTai.YI_JIAN_PAI_DUI_ZHONG_TEXT);
					APIUtil.editDingDanByZt(dd);
					
					waitTime+=1000;
		    		YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
		    		JdqBf1Util.openYiJianXiaBangDz();
		        	JdqZlUtil.closeYiJianJdq();
					Thread.sleep(2000);
		    		YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
					break;
				}
				else if(waitTime%(5*1000)==0) {
					waitTime+=1000;
		    		YinZhuTask.sendMsg(YzZlUtil.get87().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
				}
				
				
				//if(yjjdq.isKgl1Open()) {
					System.out.println("���Ҷ���״̬Ϊһ���ϰ��Ķ�������һ���ϰ�״̬�Ӵ��ϰ�����Ϊ�ϰ���");
					DingDan dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_SHANG_BANG_TEXT);
					dd.setYjzt(DingDan.DAI_SHANG_BANG);
					dd.setXyjzt(DingDan.SHANG_BANG_ZHONG);
					APIUtil.editDingDanByZt(dd);
					
					checkYJSBHXBHWGSState();
					break;
				//}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ���һ���ϰ����°������դ״̬
	 */
	public static void checkYJSBHXBHWGSState() {
		try {
			/**
			YiJianJdq yjjdq = JdqZlUtil.getYjjdq();
			System.out.println("ǰopen1==="+yjjdq.isKgl1Open());
			System.out.println("ǰopen2==="+yjjdq.isKgl2Open());
			**/
			Integer bfh = LoadProperties.getBangFangHao();
			int waitTime=0;
			while (true) {
				/*
				 * 
				yjjdq.sendData(WriteZhiLingConst.DU_QU_KAI_GUAN_LIANG_ZHUANG_TAI);
				 */
				Thread.sleep(1000);
				waitTime+=1000;
				//System.out.println("��open1==="+yjjdq.isKgl1Open());
				//System.out.println("��open2==="+yjjdq.isKgl2Open());
				System.out.println("waitTime==="+waitTime);
				if(waitTime>30*1000) {
					System.out.println("����ʧ�ܣ������³���ʶ��");
					System.out.println("���Ҷ���״̬Ϊһ���ϰ��Ķ�������һ���ϰ�״̬�Ӵ��ϰ�����Ϊһ���Ŷ���");
					DingDan dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_SHANG_BANG_TEXT);
					dd.setXddztMc(DingDanZhuangTai.YI_JIAN_PAI_DUI_ZHONG_TEXT);
					dd.setYjzt(DingDan.SHANG_BANG_ZHONG);
					dd.setXyjzt(DingDan.DAI_SHANG_BANG);
					APIUtil.editDingDanByZt(dd);
					
					waitTime+=1000;
		    		YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
		    		JdqBf1Util.openYiJianXiaBangDz();
		        	JdqZlUtil.closeYiJianJdq();
					Thread.sleep(2000);
		    		YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
					break;
				}
				else if(waitTime%(5*1000)==0) {
					waitTime+=1000;
		    		YinZhuTask.sendMsg(YzZlUtil.get87().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
				}
				
				//if(!yjjdq.isKgl1Open()&&!yjjdq.isKgl2Open()) {
					System.out.println("���Ҷ���״̬Ϊһ���ϰ��Ķ�������һ���ϰ�״̬���ϰ��и���Ϊ������");
					DingDan dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_SHANG_BANG_TEXT);
					dd.setYjzt(DingDan.SHANG_BANG_ZHONG);
					dd.setXyjzt(DingDan.DAI_CHENG_ZHONG);
					APIUtil.editDingDanByZt(dd);
					
					yiJianChengZhongZhong();
					break;
				//}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * һ�������
	 */
	public static void yiJianChengZhongZhong() {
		try {
			System.out.println("���Ҷ���״̬Ϊһ���ϰ��Ķ�������һ���ϰ�״̬�Ӵ����ظ���Ϊ������");
			Integer bfh = LoadProperties.getBangFangHao();
			DingDan dd=new DingDan();
			dd.setYjbfh(bfh);
			dd.setDdztMc(DingDanZhuangTai.YI_JIAN_SHANG_BANG_TEXT);
			dd.setYjzt(DingDan.DAI_CHENG_ZHONG);
			dd.setXyjzt(DingDan.CHENG_ZHONG_ZHONG);
			APIUtil.editDingDanByZt(dd);
			
			System.out.println("��ѯ����״̬Ϊһ���ϰ���һ��״̬Ϊ�����еĶ���");
			JSONObject resultJO=APIUtil.getDingDanByZt(bfh,0,DingDanZhuangTai.YI_JIAN_SHANG_BANG_TEXT,DingDan.CHENG_ZHONG_ZHONG,DingDan.DAI_SHANG_BANG);
			String status = resultJO.getString("status");
			if("ok".equals(status)) {
				DingDan dd1=(DingDan)net.sf.json.JSONObject.toBean(net.sf.json.JSONObject.fromObject(resultJO.get("dingDan").toString()), DingDan.class);
				
				System.out.println("��ȷ�������ȶ���15���ʼһ�����");
				/*
				 * 
	    		YinZhuTask.sendMsg(YzZlUtil.get88().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
				Thread.sleep(6000);
	    		YinZhuTask.sendMsg(YzZlUtil.get88().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
				Thread.sleep(15000);
	    		YinZhuTask.sendMsg(YzZlUtil.get97().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
				 */
				
				Float mz=null;
				Float pz=null;
				Float jz=null;
				float djczl=0;
				if(dd1.getLxlx()==DingDan.SONG_YUN) {
					mz=(float)5000;
					//mz=(float)DiBangTask3124.getWeight(GuoBangJiLu.RU_CHANG_GUO_BANG);
					djczl=mz;
				}
				else {
					pz=(float)1000;
					//pz=(float)DiBangTask3124.getWeight(GuoBangJiLu.RU_CHANG_GUO_BANG);
					djczl=pz;
				}

				if(djczl>0) {
					/*
					 * 
					APIUtil.playWeight(djczl,YinZhuTask.YI_JIAN);
					Thread.sleep(2000);
					APIUtil.playWeight(djczl,YinZhuTask.YI_JIAN);
					Thread.sleep(2000);
					 */
					
					JSONObject ddJO=resultJO.getJSONObject("dingDan");
			    	int ddId = ddJO.getInt("id");
			    	System.out.println("ddId==="+ddId);
			    	System.out.println("���ɰ�����¼");
					System.out.println("���ݳ��س�������������Ӷ�����Ӧ�İ�����¼");
					APIUtil.newBangDanJiLu(mz, pz, jz, ddId);
	
			    	if(dd1.getLxlx()==DingDan.SONG_YUN) {
						System.out.println("���Ķ�����ʵ����������������");
				    	DingDan dd2=new DingDan();
				    	dd2.setYjbfh(bfh);
				    	dd2.setDdztMc(DingDanZhuangTai.YI_JIAN_SHANG_BANG_TEXT);
				    	dd2.setYjzt(DingDan.CHENG_ZHONG_ZHONG);
				    	dd2.setSjzl(mz);
				    	//dd2.setZlceb(dd1.getYzxzl()/mz);
				    	APIUtil.editDingDanByZt(dd2);
			    	}
	
					//float zhongLiang=2998;
					System.out.println("����һ�������¼");
					GuoBangJiLu gbjl=new GuoBangJiLu();
					if(dd1.getLxlx()==DingDan.SONG_YUN)
						gbjl.setGbzl(mz);
					else
						gbjl.setGbzl(pz);
					//gbjl.setZp1(zp1);
					//gbjl.setZp2(zp2);
					//gbjl.setZp3(zp3);
					gbjl.setGbzt(GuoBangJiLu.ZHENG_CHANG);
					gbjl.setGblx(GuoBangJiLu.RU_CHANG_GUO_BANG);
					gbjl.setDdId(dd1.getId());
					//gbjl.setDdId(16);
					APIUtil.newGuoBangJiLu(gbjl);
				
					System.out.println("���Ҷ���״̬Ϊһ���ϰ��Ķ�������һ���ϰ�״̬�ӳ����и���Ϊ���°�");
					dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_SHANG_BANG_TEXT);
					dd.setYjzt(DingDan.CHENG_ZHONG_ZHONG);
					dd.setXyjzt(DingDan.DAI_XIA_BANG);
					APIUtil.editDingDanByZt(dd);
					
					/*
		    		//��ӡһ�������¼(���ڸ�Ϊ���һ�ζ���һ���ӡģʽ��������ʱ����Ҫ��ӡ��)
					APIUtil.printGbjl(GuoBangJiLu.RU_CHANG_GUO_BANG);
					
		    		YinZhuTask.sendMsg(YzZlUtil.get99().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
					Thread.sleep(2000);
		    		YinZhuTask.sendMsg(YzZlUtil.get99().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
					Thread.sleep(2000);
		    		YinZhuTask.sendMsg(YzZlUtil.get89().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
					*/
					
		        	System.out.println("̧��һ���°���բ");
		        	/*
		        	 * 
		        	JdqBf1Util.openYiJianXiaBangDz();
		        	 */
					
					checkYJXBHWGSState();
				}
				else if(djczl==0) {//�ذ���û�г���
					System.out.println("���Ҷ���״̬Ϊһ���ϰ��Ķ�������һ���ϰ�״̬�ӳ����и���Ϊ���ϰ�");
					dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_SHANG_BANG_TEXT);
					dd.setYjzt(DingDan.CHENG_ZHONG_ZHONG);
					dd.setXyjzt(DingDan.DAI_SHANG_BANG);
					APIUtil.editDingDanByZt(dd);
					
		    		//YinZhuTask.sendMsg(YzZlUtil.get87().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
					checkYJSBHWGSState();
				}
				else if(djczl==-1) {//����ʧ��
					System.out.println("���Ҷ���״̬Ϊһ���ϰ��Ķ�������һ���ϰ�״̬�ӳ����и���Ϊ���ϰ�");
					dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_SHANG_BANG_TEXT);
					dd.setXddztMc(DingDanZhuangTai.YI_JIAN_PAI_DUI_ZHONG_TEXT);
					dd.setYjzt(DingDan.CHENG_ZHONG_ZHONG);
					dd.setXyjzt(DingDan.DAI_SHANG_BANG);
					APIUtil.editDingDanByZt(dd);
					
					/*
					 * 
					JdqBf1Util.openYiJianXiaBangDz();
		        	JdqZlUtil.closeYiJianJdq();
					
		    		YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
					Thread.sleep(2000);
		    		YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
					 */
				}
			}
			else {
				String message = resultJO.getString("message");
				System.out.println("message==="+message+",��������");
	    		YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
				Thread.sleep(3000);
	    		YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ���һ���°������դ״̬
	 */
	public static void checkYJXBHWGSState() {
		try {
			/*
			 * 
			YiJianJdq yjjdq = JdqZlUtil.getYjjdq();
			System.out.println("ǰopen2==="+yjjdq.isKgl2Open());
			 */
			Integer bfh = LoadProperties.getBangFangHao();
			while (true) {
				/*
				 * 
				yjjdq.sendData(WriteZhiLingConst.DU_QU_KAI_GUAN_LIANG_ZHUANG_TAI);
				 */
				Thread.sleep(1000);
				//System.out.println("��open2==="+yjjdq.isKgl2Open());
				//if(yjjdq.isKgl2Open()) {
		        	
					System.out.println("���Ҷ���״̬Ϊһ���ϰ��Ķ�������һ���ϰ�״̬�Ӵ��°�����Ϊ�°���");
					DingDan dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_SHANG_BANG_TEXT);
					dd.setYjzt(DingDan.DAI_XIA_BANG);
					dd.setXyjzt(DingDan.XIA_BANG_ZHONG);
					APIUtil.editDingDanByZt(dd);
					
					checkIfYJXBYwc();
					break;
				//}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ���һ���°��Ƿ����
	 */
	public static void checkIfYJXBYwc() {
		try {
			/*
			 * 
			YiJianJdq yjjdq = JdqZlUtil.getYjjdq();
			System.out.println("ǰopen2==="+yjjdq.isKgl2Open());
			 */
			Integer bfh = LoadProperties.getBangFangHao();
			while (true) {
				/*
				 * 
				yjjdq.sendData(WriteZhiLingConst.DU_QU_KAI_GUAN_LIANG_ZHUANG_TAI);
				 */
				Thread.sleep(1000);
				//System.out.println("��open2==="+yjjdq.isKgl2Open());
				//if(!yjjdq.isKgl2Open()) {
					System.out.println("���Ҷ���״̬Ϊһ���ϰ��Ķ��������Ķ���״̬Ϊһ�����ˡ�һ��״̬���°��и���Ϊ�����");
					DingDan dd=new DingDan();
					dd.setYjbfh(bfh);
			    	dd.setDdztMc(DingDanZhuangTai.YI_JIAN_SHANG_BANG_TEXT);
			    	dd.setXddztMc(DingDanZhuangTai.YI_JIAN_DAI_SHEN_HE_TEXT);
			    	dd.setYjzt(DingDan.XIA_BANG_ZHONG);
			    	dd.setXyjzt(DingDan.YI_WAN_CHENG);
			    	APIUtil.editDingDanByZt(dd);
			    	
	            	System.out.println("�ر�һ��̵���");
	            	/*
	            	 * 
		    		JdqZlUtil.closeYiJianJdq();
	            	 */
					break;
				//}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ���¶��쳵��ʶ�𶩵���Ϣ
	 * @param car
	 */
	public static void updateEJCPSBDDXX(Car car) {
		try {
			System.out.println("��ѯ����״̬Ϊ�����Ŷ��еĶ���");
        	Integer bfh = LoadProperties.getBangFangHao();
			JSONObject resultJO=APIUtil.getDingDan(car.getsLicense(),DingDanZhuangTai.ER_JIAN_PAI_DUI_ZHONG_TEXT);
	        if("ok".equals(resultJO.getString("status"))) {
	        	System.out.println("���ڸö���");
	        	System.out.println("������������״̬��֤�Ƿ������������");
	        	JSONObject ddExistResult = APIUtil.checkDingDanIfExistByZt(0,bfh,DingDanZhuangTai.ER_JIAN_SHANG_BANG_TEXT,DingDan.YI_WAN_CHENG,DingDan.DAI_SHANG_BANG);
	        	if("ok".equals(ddExistResult.getString("status"))) {
	            	System.out.println("������������������״̬������������");
	        	}
	        	else {
	            	System.out.println("��������̵���");
		    		JdqZlUtil.openErJianJdq();
		        	System.out.println("̧������ϰ���բ");
		        	JdqBf1Util.openErJianShangBangDz();
		        	
		        	System.out.println("�ı䶩��״̬Ϊ�����ϰ�");
					JSONObject ddJO=resultJO.getJSONObject("dingDan");
		        	DingDan dd=new DingDan();
		        	dd.setId(ddJO.getInt("id"));
		        	dd.setDdztMc(DingDanZhuangTai.ER_JIAN_SHANG_BANG_TEXT);
		        	dd.setEjzt(DingDan.DAI_SHANG_BANG);
		        	dd.setEjbfh(bfh);
		        	APIUtil.editDingDan(dd);
		        	
		    		YinZhuTask.sendMsg(YzZlUtil.get87().replaceAll(" ", ""), 1500,YinZhuTask.ER_JIAN);
		        	
		    		checkEJSBHWGSState();
	        	}
	        }
	        else {
	        	System.out.println("message==="+resultJO.getString("message"));
	        	System.out.println("����������û���ҵ�ƥ�䶩��");
	    		YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.ER_JIAN);
	    		Thread.sleep(3000);
	    		YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.ER_JIAN);
	        }
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * �������ϰ������դ״̬
	 */
	public static void checkEJSBHWGSState() {
		try {
			//ErJianJdq ejjdq=new ErJianJdq();
			//ejjdq.open();
			ErJianJdq ejjdq = JdqZlUtil.getEjjdq();
			System.out.println("ǰopen1==="+ejjdq.isKgl1Open());
			Integer bfh = LoadProperties.getBangFangHao();
			int waitTime=0;
			while (true) {
				ejjdq.sendData(WriteZhiLingConst.DU_QU_KAI_GUAN_LIANG_ZHUANG_TAI);
				Thread.sleep(1000);
				waitTime+=1000;
				
				System.out.println("��open1==="+ejjdq.isKgl1Open());
				if(waitTime>30*1000) {
					System.out.println("�ϰ�ʧ�ܣ������³���ʶ��");
					System.out.println("���Ҷ���״̬Ϊ�����ϰ��Ķ������������ϰ�״̬�Ӵ��ϰ�����Ϊ�����Ŷ���");
					DingDan dd=new DingDan();
					dd.setEjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.ER_JIAN_SHANG_BANG_TEXT);
					dd.setXddztMc(DingDanZhuangTai.ER_JIAN_PAI_DUI_ZHONG_TEXT);
					APIUtil.editDingDanByZt(dd);
					
					waitTime+=1000;
		    		YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.ER_JIAN);
		    		JdqBf1Util.openErJianXiaBangDz();
		        	JdqZlUtil.closeErJianJdq();
					Thread.sleep(2000);
		    		YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.ER_JIAN);
					break;
				}
				else if(waitTime%(5*1000)==0) {
					waitTime+=1000;
		    		YinZhuTask.sendMsg(YzZlUtil.get87().replaceAll(" ", ""), 1000,YinZhuTask.ER_JIAN);
				}
				
				if(ejjdq.isKgl1Open()) {
					System.out.println("���Ҷ���״̬Ϊ�����ϰ��Ķ������������ϰ�״̬�Ӵ��ϰ�����Ϊ�ϰ���");
					DingDan dd=new DingDan();
					dd.setEjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.ER_JIAN_SHANG_BANG_TEXT);
					dd.setYjzt(DingDan.YI_WAN_CHENG);
					dd.setEjzt(DingDan.DAI_SHANG_BANG);
					dd.setXejzt(DingDan.SHANG_BANG_ZHONG);
					APIUtil.editDingDanByZt(dd);

					checkEJSBHXBHWGSState();
					break;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * �������ϰ����°������դ״̬
	 */
	public static void checkEJSBHXBHWGSState() {
		try {
			ErJianJdq ejjdq = JdqZlUtil.getEjjdq();
			System.out.println("ǰopen1==="+ejjdq.isKgl1Open());
			System.out.println("ǰopen2==="+ejjdq.isKgl2Open());
			Integer bfh = LoadProperties.getBangFangHao();
			int waitTime=0;
			while (true) {
				ejjdq.sendData(WriteZhiLingConst.DU_QU_KAI_GUAN_LIANG_ZHUANG_TAI);
				Thread.sleep(1000);
				waitTime+=1000;
				System.out.println("��open1==="+ejjdq.isKgl1Open());
				System.out.println("��open2==="+ejjdq.isKgl2Open());
				System.out.println("waitTime==="+waitTime);
				if(waitTime>30*1000) {
					System.out.println("����ʧ�ܣ������³���ʶ��");
					System.out.println("���Ҷ���״̬Ϊ�����ϰ��Ķ������������ϰ�״̬�Ӵ��ϰ�����Ϊ�����Ŷ���");
					DingDan dd=new DingDan();
					dd.setEjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.ER_JIAN_SHANG_BANG_TEXT);
					dd.setXddztMc(DingDanZhuangTai.ER_JIAN_PAI_DUI_ZHONG_TEXT);
					dd.setEjzt(DingDan.SHANG_BANG_ZHONG);
					dd.setXejzt(DingDan.DAI_SHANG_BANG);
					APIUtil.editDingDanByZt(dd);
					
					waitTime+=1000;
		    		YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.ER_JIAN);
		    		JdqBf1Util.openErJianXiaBangDz();
		        	JdqZlUtil.closeErJianJdq();
					Thread.sleep(2000);
		    		YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.ER_JIAN);
					break;
				}
				else if(waitTime%(5*1000)==0) {
					waitTime+=1000;
		    		YinZhuTask.sendMsg(YzZlUtil.get87().replaceAll(" ", ""), 1000,YinZhuTask.ER_JIAN);
				}
				
				if(!ejjdq.isKgl1Open()&&!ejjdq.isKgl2Open()) {
					System.out.println("���Ҷ���״̬Ϊ�����ϰ��Ķ������������ϰ�״̬���ϰ��и���Ϊ������");
					DingDan dd=new DingDan();
					dd.setEjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.ER_JIAN_SHANG_BANG_TEXT);
					dd.setEjzt(DingDan.SHANG_BANG_ZHONG);
					dd.setXejzt(DingDan.DAI_CHENG_ZHONG);
					APIUtil.editDingDanByZt(dd);
					
					erJianChengZhongZhong();
					break;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ���������
	 */
	public static void erJianChengZhongZhong() {
		try {
			System.out.println("���Ҷ���״̬Ϊ�����ϰ��Ķ������������ϰ�״̬�Ӵ����ظ���Ϊ������");
			Integer bfh = LoadProperties.getBangFangHao();
			DingDan dd=new DingDan();
			dd.setEjbfh(bfh);
			dd.setDdztMc(DingDanZhuangTai.ER_JIAN_SHANG_BANG_TEXT);
			dd.setEjzt(DingDan.DAI_CHENG_ZHONG);
			dd.setXejzt(DingDan.CHENG_ZHONG_ZHONG);
			APIUtil.editDingDanByZt(dd);
			
			System.out.println("��ѯ����״̬Ϊ�����ϰ�������״̬Ϊ�����еĶ���");
			JSONObject resultJO=APIUtil.getDingDanByZt(0,bfh,DingDanZhuangTai.ER_JIAN_SHANG_BANG_TEXT,DingDan.YI_WAN_CHENG,DingDan.CHENG_ZHONG_ZHONG);
			String status = resultJO.getString("status");
			if("ok".equals(status)) {
				DingDan dd1=(DingDan)net.sf.json.JSONObject.toBean(net.sf.json.JSONObject.fromObject(resultJO.get("dingDan").toString()), DingDan.class);
	
				System.out.println("��ȷ�������ȶ���15���ʼ�������");
				YinZhuTask.sendMsg(YzZlUtil.get88().replaceAll(" ", ""), 1500,YinZhuTask.ER_JIAN);
				Thread.sleep(6000);
				YinZhuTask.sendMsg(YzZlUtil.get88().replaceAll(" ", ""), 1500,YinZhuTask.ER_JIAN);
				Thread.sleep(15000);
	    		YinZhuTask.sendMsg(YzZlUtil.get97().replaceAll(" ", ""), 1500,YinZhuTask.ER_JIAN);
				
				Float mz=null;
				Float dxgmz=null;
				Float pz=null;
				Float dxgpz=null;
				Float jz=null;
				float djczl=0;
				JSONObject gbjlJO=APIUtil.selectBangDanJiLuByDdId(dd1.getId());
				JSONObject bdJO=gbjlJO.getJSONObject("bdjl");
				int bdId = bdJO.getInt("id");
				if(dd1.getLxlx()==DingDan.SONG_YUN) {
					mz=(float)bdJO.getDouble("mz");
					//pz=(float)1000;
					dxgpz=(float)DiBangTask3124.getWeight(GuoBangJiLu.CHU_CHANG_GUO_BANG);
					djczl=dxgpz;
					jz=mz-dxgpz;
				}
				else {
					//mz=(float)5000;
					dxgmz=(float)DiBangTask3124.getWeight(GuoBangJiLu.CHU_CHANG_GUO_BANG);
					djczl=dxgmz;
					pz=(float)bdJO.getDouble("pz");
					jz=dxgmz-pz;
				}
	    		
				if(djczl>0) {
					APIUtil.playWeight(djczl,YinZhuTask.ER_JIAN);
					Thread.sleep(2000);
					APIUtil.playWeight(djczl,YinZhuTask.ER_JIAN);
					Thread.sleep(2000);
				
					System.out.println("���ݳ��س������������޸Ķ�����Ӧ�İ�����¼");
					APIUtil.editBangDanJiLu(bdId,dxgmz,dxgpz,jz);
				
					if(dd1.getLxlx()==DingDan.QU_YUN) {
						System.out.println("���Ķ�����ʵ����������������");
				    	DingDan dd2=new DingDan();
				    	dd2.setEjbfh(bfh);
				    	dd2.setDdztMc(DingDanZhuangTai.ER_JIAN_SHANG_BANG_TEXT);
				    	dd2.setYjzt(DingDan.YI_WAN_CHENG);
				    	dd2.setEjzt(DingDan.SHANG_BANG_ZHONG);
				    	dd2.setSjzl(mz);
				    	//dd2.setZlceb(dd1.getYzxzl()/mz);
				    	APIUtil.editDingDanByZt(dd2);
			    	}
				
					System.out.println("���ɶ��������¼");
					GuoBangJiLu gbjl=new GuoBangJiLu();
					if(dd1.getLxlx()==DingDan.SONG_YUN)
						gbjl.setGbzl(dxgpz);
					else
						gbjl.setGbzl(dxgmz);
					//gbjl.setZp1(zp1);
					//gbjl.setZp2(zp2);
					//gbjl.setZp3(zp3);
					gbjl.setGbzt(GuoBangJiLu.ZHENG_CHANG);
					gbjl.setGblx(GuoBangJiLu.CHU_CHANG_GUO_BANG);
					gbjl.setDdId(dd1.getId());
					APIUtil.newGuoBangJiLu(gbjl);
				
					System.out.println("���Ҷ���״̬Ϊ�����ϰ��Ķ������������ϰ�״̬�ӳ����и���Ϊ���°�");
					dd=new DingDan();
					dd.setEjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.ER_JIAN_SHANG_BANG_TEXT);
					dd.setEjzt(DingDan.CHENG_ZHONG_ZHONG);
					dd.setXejzt(DingDan.DAI_XIA_BANG);
					APIUtil.editDingDanByZt(dd);

					/*
		    		//��ӡ���������¼(���ڸ�Ϊ���һ�ζ���һ���ӡģʽ��������ʱ����Ҫ��ӡ��)
					APIUtil.printGbjl(GuoBangJiLu.CHU_CHANG_GUO_BANG);
					*/
					//��ӡ������¼
					APIUtil.printBdjl();
					
		    		YinZhuTask.sendMsg(YzZlUtil.get99().replaceAll(" ", ""), 1500,YinZhuTask.ER_JIAN);
					Thread.sleep(2000);
		    		YinZhuTask.sendMsg(YzZlUtil.get99().replaceAll(" ", ""), 1500,YinZhuTask.ER_JIAN);
					Thread.sleep(2000);
		    		YinZhuTask.sendMsg(YzZlUtil.get89().replaceAll(" ", ""), 1500,YinZhuTask.ER_JIAN);
					
		        	System.out.println("̧������°���բ");
		        	JdqBf1Util.openErJianXiaBangDz();
					
					checkEJXBHWGSState();
				}
				else if(djczl==0) {//�ذ���û�г���
					System.out.println("���Ҷ���״̬Ϊ�����ϰ��Ķ������������ϰ�״̬�ӳ����и���Ϊ���ϰ�");
					dd=new DingDan();
					dd.setEjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.ER_JIAN_SHANG_BANG_TEXT);
					dd.setEjzt(DingDan.CHENG_ZHONG_ZHONG);
					dd.setXejzt(DingDan.DAI_SHANG_BANG);
					APIUtil.editDingDanByZt(dd);

		    		YinZhuTask.sendMsg(YzZlUtil.get87().replaceAll(" ", ""), 1500,YinZhuTask.ER_JIAN);
					checkEJSBHWGSState();
				}
				else if(djczl==-1) {//����ʧ��
					System.out.println("���Ҷ���״̬Ϊ�����ϰ��Ķ������������ϰ�״̬�ӳ����и���Ϊ���ϰ�");
					dd=new DingDan();
					dd.setEjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.ER_JIAN_SHANG_BANG_TEXT);
					dd.setXddztMc(DingDanZhuangTai.ER_JIAN_PAI_DUI_ZHONG_TEXT);
					dd.setEjzt(DingDan.CHENG_ZHONG_ZHONG);
					dd.setXejzt(DingDan.DAI_SHANG_BANG);
					APIUtil.editDingDanByZt(dd);
					
					JdqBf1Util.openErJianXiaBangDz();
		        	JdqZlUtil.closeErJianJdq();
					
		    		YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1500,YinZhuTask.ER_JIAN);
					Thread.sleep(2000);
		    		YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.ER_JIAN);
				}
			}
			else {
				String message = resultJO.getString("message");
				System.out.println("message==="+message+",��������");
	    		YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.ER_JIAN);
				Thread.sleep(3000);
	    		YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.ER_JIAN);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * �������°������դ״̬
	 */
	public static void checkEJXBHWGSState() {
		try {
			ErJianJdq ejjdq = JdqZlUtil.getEjjdq();
			System.out.println("ǰopen2==="+ejjdq.isKgl2Open());
			Integer bfh = LoadProperties.getBangFangHao();
			while (true) {
				ejjdq.sendData(WriteZhiLingConst.DU_QU_KAI_GUAN_LIANG_ZHUANG_TAI);
				Thread.sleep(1000);
				System.out.println("��open2==="+ejjdq.isKgl2Open());
				if(ejjdq.isKgl2Open()) {
		        	
					System.out.println("���Ҷ���״̬Ϊ�����ϰ��Ķ������������ϰ�״̬�Ӵ��°�����Ϊ�°���");
					DingDan dd=new DingDan();
					dd.setEjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.ER_JIAN_SHANG_BANG_TEXT);
					dd.setEjzt(DingDan.DAI_XIA_BANG);
					dd.setXejzt(DingDan.XIA_BANG_ZHONG);
					APIUtil.editDingDanByZt(dd);
					
					checkIfEJXBYwc();
					break;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * �������°��Ƿ����
	 */
	public static void checkIfEJXBYwc() {
		try {
			ErJianJdq ejjdq = JdqZlUtil.getEjjdq();
			System.out.println("ǰopen2=="+ejjdq.isKgl2Open());
			Integer bfh = LoadProperties.getBangFangHao();
			while (true) {
				ejjdq.sendData(WriteZhiLingConst.DU_QU_KAI_GUAN_LIANG_ZHUANG_TAI);
				Thread.sleep(1000);
				System.out.println("��open2==="+ejjdq.isKgl2Open());
				if(!ejjdq.isKgl2Open()) {
			    	System.out.println("���Ҷ���״̬Ϊ�����ϰ��Ķ��������Ķ���״̬Ϊ�������ˡ�����״̬���°��и���Ϊ�����");
			    	DingDan dd=new DingDan();
			    	dd.setEjbfh(bfh);
			    	dd.setDdztMc(DingDanZhuangTai.ER_JIAN_SHANG_BANG_TEXT);
			    	dd.setXddztMc(DingDanZhuangTai.ER_JIAN_DAI_SHEN_HE_TEXT);
			    	dd.setEjzt(DingDan.XIA_BANG_ZHONG);
			    	dd.setXejzt(DingDan.YI_WAN_CHENG);
			    	APIUtil.editDingDanByZt(dd);
			    	
	            	System.out.println("�رն���̵���");
		    		JdqZlUtil.closeErJianJdq();
					break;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
    	Car car = new Car();
    	car.setsLicense(" ³B123456");
		updateYJCPSBDDXX(car);
	}
}
