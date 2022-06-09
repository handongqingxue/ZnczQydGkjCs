package com.znczQydGkjCs.jdq;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;

import com.znczQydGkjCs.util.StringUtil;

public class ThreadReadSocket implements Runnable {
	
	private Socket client;
	private int BUFFER_SIZE = 50;
	
	public ThreadReadSocket(Socket i) {
		// TODO Auto-generated constructor stub
		client = i;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//����һ�������������ͻ�������
		try {
			InputStream in = client.getInputStream();
			OutputStream out =client.getOutputStream();
			byte[] recData = null;
			int munber =0;
			while(true) {
				if(client.isClosed()) {
					break;
				}
				else {
					try {
						recData = new byte[1024];
						//���������ж�ȡ�ͻ�������
						int size = in.read(recData);
						System.out.println("size="+size);
						if(size>-1) {
							byte[] new_data=new byte[size]	;
							//��ȡ�������ݷŵ�������
							new_data=Arrays.copyOf(recData,size);					
							String data = StringUtil.bytesToHex(new_data);	
							System.out.println("data==="+data);
						}
						else {
							System.out.println("���ݶ�ȡ��ϣ�");
							client.close();
							break;
						}
					} catch (SocketException err) {
						// TODO Auto-generated catch block
						//err.printStackTrace();
						System.out.println("�ѶϿ�����");
						client.close();
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}
}
