package com.znczQydGkjCs.cpsbsxt;

public class Car {

	// ����ͷip��ַ
	private String ip;
	
	// ��ɫ+ ���ƺ�
	private String sLicense;
	// ��������
	private byte type;
	
	private String typeStr;
	// ������ɫ���
	private byte sLicenseColor;
	// ������ɫ
	private String sLicenseColorStr;
	
	// ͼƬ������
	 byte [] bytes=null;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getTypeStr() {
		return CarTypeEnum.getCarName(this.type);
	}
	
	public String getsLicense() {
		return sLicense;
	}
	public void setsLicense(String sLicense) {
		this.sLicense = sLicense.substring(1, sLicense.length()).trim();
	}
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.setTypeStr(CarTypeEnum.getCarName(type));
	}
	
	public byte getsLicenseColor() {
		return sLicenseColor;
	}
	public void setsLicenseColor(byte sLicenseColor) {
		this.sLicenseColor = sLicenseColor;
		this.setsLicenseColorStr(SLicenseColor.getSLicenseColorName((int)sLicenseColor));
	}
	public String getsLicenseColorStr() {
		int num = sLicenseColor;
		return SLicenseColor.getSLicenseColorName(num);
	}
	public void setsLicenseColorStr(String sLicenseColorStr) {
		this.sLicenseColorStr = sLicenseColorStr;
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	
	
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
//		 FileOutputStream fout = null;
//             try {
//				fout = new FileOutputStream("d://" + this.sLicense +"_plateResult.jpg");
//				fout.write(bytes);
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					fout.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
		
	}
	@Override
	public String toString() {
		return "Car [ip=" + ip + ", sLicense=" + sLicense + ", type=" + type + ", typeStr=" + typeStr
				+ ", sLicenseColor=" + sLicenseColor + ", sLicenseColorStr=" + sLicenseColorStr + "]";
	}
	
	
	public static void main(String[] args) {
		
		String sLicense= "��³N3849";
		
		System.out.println(sLicense.substring(1, sLicense.length()));
	}
}
