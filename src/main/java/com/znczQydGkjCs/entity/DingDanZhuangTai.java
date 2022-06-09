package com.znczQydGkjCs.entity;

public class DingDanZhuangTai {

	public static final String DAI_SHEN_HE_TEXT="待审核";//1
	public static final String YI_SHEN_HE_TEXT="已审核";//2
	public static final String YI_JIAN_PAI_DUI_ZHONG_TEXT="一检排队中";//3
	public static final String YI_JIAN_SHANG_BANG_TEXT="一检上磅";//4
	public static final String YI_JIAN_DAI_SHEN_HE_TEXT="一检待审核";//5
	public static final String DAI_RU_KU_TEXT="待入库";//6
	public static final String ER_JIAN_PAI_DUI_ZHONG_TEXT="二检排队中";//7
	public static final String ER_JIAN_SHANG_BANG_TEXT="二检上磅";//8
	public static final String ER_JIAN_DAI_SHEN_HE_TEXT="二检待审核";//9
	public static final String YI_WAN_CHENG_TEXT="已完成";//10

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
