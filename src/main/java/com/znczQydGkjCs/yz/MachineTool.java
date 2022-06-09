package com.znczQydGkjCs.yz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

public class MachineTool {

	public static final String USED_PORT_NAME="COM4";
	
	/**
     * �෽�� ���ɸı� �����ܼ̳�
     * ɨ���ȡ���õĴ���
     * �����ô��������list��������list
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static final ArrayList<String> uartPortUseAblefind() {
        //��ȡ��ǰ���п��ô���
        //��CommPortIdentifier���ṩ����
        Enumeration<CommPortIdentifier> portList=CommPortIdentifier.getPortIdentifiers();
        ArrayList<String> portNameList=new ArrayList();
        //��Ӳ�����ArrayList
        while(portList.hasMoreElements()) {
            String portName=portList.nextElement().getName();
            System.out.println("portName1==="+portName);
            if(USED_PORT_NAME.equals(portName)) {
	            portNameList.add(portName);
            }
        }
        return portNameList;
    }

    /**
     * ���ڳ�������
     * 1)�򿪴���
     * 2)���ò����� ���ݵ�����������������Ϊ57600 ...
     * 3)�ж϶˿��豸�Ƿ�Ϊ�����豸
     * 4)�˿��Ƿ�ռ��
     * 5)��������������check�Ժ󷵻�һ���������ö���new UARTParameterSetup()
     * 6)return:����һ��SerialPortһ��ʵ���������ж���com���Ǵ�������в�������
     *   �������򷵻�SerialPort����Ϊnull
     */
    public static final SerialPort portParameterOpen(String portName,int baudrate) {
        System.out.println("portName==="+portName);
    	SerialPort serialPort=null;
        try {  //ͨ���˿���ʶ�𴮿�
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            //�򿪶˿ڲ����ö˿����� serialPort�ͳ�ʱʱ�� 2000ms
            CommPort commPort=portIdentifier.open(portName,1000);
            //��һ���ж�comm�˿��Ƿ��Ǵ��� instanceof
            if(commPort instanceof SerialPort) {
                System.out.println("��COM�˿��Ǵ��ڣ����������ǣ�" + portName);
                //portNameLabel.setText("��COM�˿��Ǵ��ڣ����������ǣ�" + portName);
                //��һ��ǿ������ת��
                serialPort=(SerialPort)commPort;
                //����baudrate �˴���Ҫע��:������ֻ��������int�� ����57600�㹻
                //8λ����λ
                //1λֹͣλ
                //����żУ��
                serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8,SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                //����������� log
                System.out.println("���ڲ�����������ɣ�������Ϊ"+baudrate+",����λ8bits,ֹͣλ1λ,����żУ��");
        		//baudrateLabel.setText("���ڲ�����������ɣ�������Ϊ"+baudrate+",����λ8bits,ֹͣλ1λ,����żУ��");
                
            } else {    //���Ǵ���
                System.out.println("��com�˿ڲ��Ǵ���,�����豸!");
                //��com�˿�����Ϊnull Ĭ����null����Ҫ����
            }

        } catch (NoSuchPortException e) {
            e.printStackTrace();
        } catch (PortInUseException e) {
            e.printStackTrace();
        } catch (UnsupportedCommOperationException e) {
            e.printStackTrace();
        }

        return serialPort;
    }

    /**
     * �������ݷ����Լ����ݴ�����Ϊһ����
     * ��������Ҫʵ�ֶ����ݰ��Ĵ������µ����
     */

    /**
     * ��λ���������ͨ�����ڷ�������
     * ���ڶ��� seriesPort
     * ����֡:dataPackage
     * ���͵ı�־:����δ���ͳɹ��׳�һ���쳣
     */
    public static void uartSendDatatoSerialPort(SerialPort serialPort,byte[] dataPackage) {
        OutputStream out=null;
        try {
            out=serialPort.getOutputStream();
            out.write(dataPackage);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //�ر������
            if(out!=null) {
                try {
                    out.close();
                    out=null;
                    //System.out.println("�����ѷ������!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * ��λ����������
     * ���ڶ���seriesPort
     * ��������buffer
     * ����һ��byte����
     */
    public static byte[] uartReceiveDatafromSingleChipMachine(SerialPort serialPort) {
        byte[] receiveDataPackage=null;
        InputStream in=null;
        try {
            in=serialPort.getInputStream();
            // ��ȡdata buffer���ݳ���
            int bufferLength=in.available();
            while(bufferLength!=0) {
                receiveDataPackage=new byte[bufferLength];
                in.read(receiveDataPackage);
                bufferLength=in.available();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receiveDataPackage;
    }

}
