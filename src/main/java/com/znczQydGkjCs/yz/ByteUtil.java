package com.znczQydGkjCs.yz;

public class ByteUtil {
	/**
	 * �ֽ�����ת16�����ַ���
	 * 
	 * @param b
	 *            �ֽ�����
	 * @return 16�����ַ���
	 */
	public static String byte2hex(byte[] b) {
		StringBuffer hs = new StringBuffer();
		String stmp;
		for (int i = 0; i < b.length; i++) {
			stmp = Integer.toHexString(b[i] & 0xFF);
			if (stmp.length() == 1) {
				hs.append("0").append(stmp);
			} else {
				hs.append(stmp);
			}
		}
		return hs.toString();
	}

	/**
	 * 16�����ַ���ת�ֽ�����
	 * 
	 * @param hex
	 *            16�����ַ���
	 * @return �ֽ�����
	 */
	public static byte[] hex2byte(String hex) {
		if (!isHexString(hex)) {
			return null;
		}
		char[] arr = hex.toCharArray();
		byte[] b = new byte[hex.length() / 2];
		for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
			String swap = "" + arr[i++] + arr[i];
			int byteint = Integer.parseInt(swap, 16) & 0xFF;
			b[j] = new Integer(byteint).byteValue();
		}
		return b;
	}

	/**
	 * У���Ƿ���16�����ַ���
	 * 
	 * @param hex
	 * @return
	 */
	public static boolean isHexString(String hex) {
		if (hex == null || hex.length() % 2 != 0) {
			return false;
		}
		for (int i = 0; i < hex.length(); i++) {
			char c = hex.charAt(i);
			if (!isHexChar(c)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * У���Ƿ���16�����ַ�
	 * 
	 * @param c
	 * @return
	 */
	private static boolean isHexChar(char c) {
		return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
	}
}
