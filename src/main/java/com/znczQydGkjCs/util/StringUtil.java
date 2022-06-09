package com.znczQydGkjCs.util;

public class StringUtil {

	/**
	 * ʮ�������ַ���ת��Ϊ�ֽ�����
	 * @param hexString
	 * @return
	 */
	public static byte[] hexStringToByteArray(String hexString) {
        hexString = hexString.replaceAll(" ", "");
        int len = hexString.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // ��λһ�飬��ʾһ���ֽ�,��������ʾ��16�����ַ�������ԭ��һ���ֽ�
        	
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character
                    .digit(hexString.charAt(i + 1), 16));
        }
        return bytes;
    }
	
	/**
	 * �ֽ�����ת��Ϊʮ������
	 * @param bytes
	 * @return
	 */
	public static String bytesToHex(byte[] bytes) {
		String r = "";
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            r += hex.toUpperCase();
        }
        return r;
	}
}
