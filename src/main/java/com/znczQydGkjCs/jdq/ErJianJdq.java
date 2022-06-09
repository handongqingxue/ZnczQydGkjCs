package com.znczQydGkjCs.jdq;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.znczQydGkjCs.util.*;

public class ErJianJdq {

    private Socket client;
    private Thread t_read; 
    private boolean kgl1Open;
    private boolean kgl2Open;
    
	public boolean isKgl2Open() {
		return kgl2Open;
	}

	public void setKgl2Open(boolean kgl2Open) {
		this.kgl2Open = kgl2Open;
	}

	public boolean isKgl1Open() {
		return kgl1Open;
	}

	public void setKgl1Open(boolean kgl1Open) {
		this.kgl1Open = kgl1Open;
	}

	public void open() {
		try {
			String erJianJdqIp = LoadProperties.getErJianJdqIp();
			int erJianJdqPort = LoadProperties.getErJianJdqPort();
			client=new Socket(erJianJdqIp,erJianJdqPort);
			t_read= new Thread(new ReadErJianJdqSocket(client,ErJianJdq.this));
			t_read.start();
			System.out.println("���Ӷ���̵���");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			client.close();
			System.out.println("�Ͽ�����̵�������");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendData(String data) {
		try {
			if(client!=null) {
				if(!client.isClosed()) {
					/***************��������*****************/
					OutputStream out = client.getOutputStream();
					/***************��ʮ�������ַ���ת��Ϊ�ֽ����鷢��*****************/
					out.write (StringUtil.hexStringToByteArray(data));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
