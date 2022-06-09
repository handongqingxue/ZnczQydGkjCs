package com.znczQydGkjCs.xpPrint;

import java.awt.print.*;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.standard.PrinterName;

import org.json.JSONObject;

import com.znczQydGkjCs.entity.*;
import com.znczQydGkjCs.util.APIUtil;

public class XPPrinter {

	private BangDanPrint bangDanPrint;

	public XPPrinter() {
		
	}
	
	public XPPrinter(BangDanPrint bangDanPrint){
		this.bangDanPrint=bangDanPrint;
	}

	public void printer() {  
        try {  
            //Book ���ṩ�ĵ��ı�ʾ��ʽ�����ĵ���ҳ�����ʹ�ò�ͬ��ҳ���ʽ��ҳ�� painter
            Book book = new Book(); //Ҫ��ӡ���ĵ�
            
            //PageFormat������Ҫ��ӡ��ҳ���С�ͷ���  
            PageFormat pf = new PageFormat();  //��ʼ��һ��ҳ���ӡ����
            pf.setOrientation(PageFormat.PORTRAIT); //����ҳ���ӡ���򣬴������£���������
          
            //���ô�ӡֽҳ����Ϣ��ͨ��Paper����ҳ��Ŀհױ߾�Ϳɴ�ӡ���򡣱�����ʵ�ʴ�ӡֽ�Ŵ�С�����  
            Paper paper = new Paper(); 
            paper.setSize(158,30000);// ֽ�Ŵ�С
            paper.setImageableArea(0,0,158,30000);// A4(595 X 842)���ô�ӡ������ʵ0��0Ӧ����72��72����ΪA4ֽ��Ĭ��X,Y�߾���72  
            pf.setPaper(paper);  
  
            book.append(bangDanPrint,pf);
           
            PrinterJob job = PrinterJob.getPrinterJob();   //��ȡ��ӡ�������  
            
            job.setPageable(book);  //���ô�ӡ��  
  
            job.print(); //��ʼ��ӡ 
        } catch (PrinterException e) {  
            e.printStackTrace();  
        }  
    }  
	
	public void findPrinter(String printerName) {
	    HashAttributeSet hash = new HashAttributeSet();    // �洢��ӡ�����Եļ���
	    hash.add(new PrinterName(printerName, null));    // ��Ӵ�ӡ���������ԣ�����������������ô�ӡ��Ӳ��ʱָ��������
	    PrintService[] pss = PrintServiceLookup.lookupPrintServices(null, hash);    // ���ҿ��õĴ�ӡ�����񲢲�ָ���Զ��Ĵ�ӡ��ʽ
	    if (pss.length == 0) {
	     System.out.println("û�������ӡ��");
	    } else {
	     System.out.println("�ҵ���ӡ��");
	    }
	}
    
    public static void main(String[] args) {
    	APIUtil.printGbjl(GuoBangJiLu.RU_CHANG_GUO_BANG);
    	/*
    	XPPrinter xpp=new XPPrinter();
    	xpp.findPrinter("DL-801PN");
    	xpp.findPrinter("DL-801P(NEW)");
        */
	}
}
