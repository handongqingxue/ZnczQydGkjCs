package com.znczQydGkjCs.entity;

public class DingDanZhuangTai {

	public static final String DAI_SHEN_HE_TEXT="�����";//1
	public static final String YI_SHEN_HE_TEXT="�����";//2
	public static final String YI_JIAN_PAI_DUI_ZHONG_TEXT="һ���Ŷ���";//3
	public static final String YI_JIAN_SHANG_BANG_TEXT="һ���ϰ�";//4
	public static final String YI_JIAN_DAI_SHEN_HE_TEXT="һ������";//5
	public static final String DAI_RU_KU_TEXT="�����";//6
	public static final String ER_JIAN_PAI_DUI_ZHONG_TEXT="�����Ŷ���";//7
	public static final String ER_JIAN_SHANG_BANG_TEXT="�����ϰ�";//8
	public static final String ER_JIAN_DAI_SHEN_HE_TEXT="��������";//9
	public static final String YI_WAN_CHENG_TEXT="�����";//10

	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public Integer getPx() {
		return px;
	}
	public void setPx(Integer px) {
		this.px = px;
	}
	private String mc;
	private Integer px;
}
