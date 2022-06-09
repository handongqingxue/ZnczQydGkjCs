package com.znczQydGkjCs.cpsbsxt;

/**
 * ��������ö��
 * @author lhb
 *
 */
public class CarTypeEnum {
	
	/**
	 * ����num ����������
	 * @param num
	 * @return
	 */
	public static String getCarName(byte num) {
		
		switch (num) {
		case 0x0:
			return "δ֪";
		case 0x1:
			return "�ͳ�";
		case 0x2:
			return "����";
		case 0x3:
			return "�γ�";
		case 0x4:
			return "�����";
		case 0x5:
			return "С����";
		case 0x6:
			return "����";
		case 0x7:
			return "���ֳ�";
		case 0x8:
			return "���ֳ�";
		case 0x9:
			return "SUV/MPV";
		case 10:
			return "���Ϳͳ�";
		case 11:
			return "������";
		case 12:
			return "�ǻ�����";
		case 13:
			return "С�ͽγ�";
		case 14:
			return "΢�ͽγ�";
		case 15:
			return "Ƥ����";
		case 16:
			return "��װ�俨��";
		case 17:
			return "΢�������忨";
		case 18:
			return "΢�������忨";
		case 19:
			return "���������̳�";
		case 20:
			return "�͹޳�";
		case 21:
			return "���������賵";
		case 22:
			return "ƽ���ϳ�";
		case 23:
			return "����γ�";
		case 24:
			return "����γ�";
		case 25:
			return "����";
		case 26:
			return "С�Ϳͳ�";
			
		}
		return "δ֪";
	}


}