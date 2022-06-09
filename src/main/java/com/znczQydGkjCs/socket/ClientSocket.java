package com.znczQydGkjCs.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import com.znczQydGkjCs.cpsbsxt.Car;
import com.znczQydGkjCs.util.*;

import net.sf.json.JSONObject;

public class ClientSocket implements Runnable {

	public static final int YI_JIAN=1;
	public static final int ER_JIAN=2;
	public static final String PUSH_CPH="pushCph";
	private OutputStreamWriter out;
	private BufferedReader in;
	private Socket socket;
	private boolean serverOpend;

	public boolean isServerOpend() {
		return serverOpend;
	}

	public void setServerOpend(boolean serverOpend) {
		this.serverOpend = serverOpend;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				String line = in.readLine();
				System.out.println("line==="+line);
				if(line==null) {
					//�ر���\socket
					break;
				}
				this.readMessage(line);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Connection reset");
				serverOpend=false;
				break;
			}
			
		}
	}

	private void readMessage(String mesJOStr) {
		System.out.println("mesJOStr==="+mesJOStr);
		JSONObject mesJO = net.sf.json.JSONObject.fromObject(mesJOStr);
		String action = mesJO.getString("action");
		System.out.println("action==="+action);
		int bfh = LoadProperties.getBangFangHao();
		switch (action) {
		case PUSH_CPH:
			Car car1=new Car();
			String cph = mesJO.getString("cph");
			car1.setsLicense(" "+cph);
			int jyFlag = mesJO.getInt("jyFlag");
			System.out.println("jyFlag==="+jyFlag);
			System.out.println("bfh==="+bfh);
			switch (jyFlag) {
			case YI_JIAN:
				switch (bfh) {
				case APIUtil.YI_HAO_BANG_FANG:
					BangFang1Util.updateYJCPSBDDXX(car1);
					break;
				case APIUtil.ER_HAO_BANG_FANG:
					BangFang2Util.updateYJCPSBDDXX(car1);
					break;
				}
				break;
			case ER_JIAN:
				switch (bfh) {
				case APIUtil.YI_HAO_BANG_FANG:
					BangFang1Util.updateEJCPSBDDXX(car1);
					break;
				case APIUtil.ER_HAO_BANG_FANG:
					BangFang2Util.updateEJCPSBDDXX(car1);
					break;
				}
				break;
			}
			break;
		}
	}
	
	private void sendName(){
		int bfNoFlag= 0;
		//name = JOptionPane.showInputDialog(f, "����������:");
		bfNoFlag=LoadProperties.getBangFangHao();
		this.sendMessageToServer(bfNoFlag+"");
	}
	
	private void sendMessageToServer(String mes){
		try {
			out.write(mes+"\n");
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void connectServer() {
		try {
			String serverIp=LoadProperties.getServerIp();
			socket = new Socket(serverIp,8000);//����������
			System.out.println("���ӳɹ�!");
			out = new OutputStreamWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.sendName();//��һʱ�䷢��һ�������־
			Thread th = new Thread(this);
			th.start();		
			serverOpend=true;
		} catch (UnknownHostException e) {
			System.out.println("������������...");
			serverOpend=false;
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("����������������쳣...");
			serverOpend=false;
			//e.printStackTrace();
		}

	}

	/**
	* �ж��Ƿ�Ͽ����ӣ��Ͽ�����true,û�з���false(����ж���������Ƿ�Ͽ����ӵķ���ִ�д������˻ᱨConnection reset�쳣����ʱ������)
	* https://www.cnblogs.com/wisdo/p/5859857.html
	* @param socket
	* @return
	*/ 
	/*
	public Boolean isServerClose(){ 
	   try{ 
		   socket.sendUrgentData(0xFF);//����1���ֽڵĽ������ݣ�Ĭ������£���������û�п����������ݴ�����Ӱ������ͨ�� 
		   return false; 
	   } catch(Exception se){ 
		   return true; 
	   }
	}
	*/
}
