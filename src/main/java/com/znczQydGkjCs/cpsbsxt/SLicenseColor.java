package com.znczQydGkjCs.cpsbsxt;

/**
 * 	������ɫö��
 * @param num
 */
public enum SLicenseColor {
	
	VCA_BLUE_PLATE("��ɫ����", 0x0),
	VCA_YELLOW_PLATE("��ɫ����", 0x1),
	VCA_WHITE_PLATE("��ɫ����", 0x2),
	VCA_BLACK_PLATE("��ɫ����", 0x3),
	VCA_GREEN_PLATE("��ɫ����", 0x4),
	VCA_BKAIR_PLATE("�񺽺�ɫ����",  0x5);
	
	private String name;
	private int num;
	
	private SLicenseColor(String name, int num) {
		this.name = name;
		this.num = num;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	

	/**
	 * ���ݱ�ţ� ���س�����ɫ
	 * @param num
	 * @return
	 */
	public static String getSLicenseColorName(int num) {
		switch (num) {
		case 0x0:
			return VCA_BLUE_PLATE.getName();
		case 0x1:
			return VCA_YELLOW_PLATE.getName();
		case 0x2:
			return VCA_WHITE_PLATE.getName();
		case 0x3:
			return VCA_BLACK_PLATE.getName();
		case 0x4:
			return VCA_GREEN_PLATE.getName();
		case 0x5:
			return VCA_BKAIR_PLATE .getName();
		}
		
		return "����";
	}
	
}