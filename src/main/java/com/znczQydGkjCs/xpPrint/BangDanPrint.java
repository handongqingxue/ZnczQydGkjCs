package com.znczQydGkjCs.xpPrint;

import java.awt.*;
import java.awt.print.*;

import org.json.JSONObject;

import com.znczQydGkjCs.entity.*;

/**
 *СƱ��ӡ����
 */
public class BangDanPrint implements Printable {
	
	private GuoBangJiLu gbjl;
	private BangDanJiLu bdjl;
	private int dyFlag;//��ӡ��־ 1.������¼ 2.������¼
	public static final int GUO_BANG_JI_LU=1;
	public static final int BANG_DAN_JI_LU=2;
	
	public BangDanPrint(GuoBangJiLu gbjl,int dyFlag) {
		this.gbjl=gbjl;
		this.dyFlag=dyFlag;
	}
	
	public BangDanPrint(BangDanJiLu bdjl,int dyFlag) {
		this.bdjl=bdjl;
		this.dyFlag=dyFlag;
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		//�� Graphics2D ����չ Graphics �࣬���ṩ�Լ�����״������ת������ɫ������ı����ָ�Ϊ���ӵĿ��ơ�
		//���������� Java(tm) ƽ̨�ϳ��ֶ�ά��״���ı���ͼ��Ļ����ࡣ 
		Graphics2D g2 = (Graphics2D) graphics;  
		g2.setColor(Color.black);//���ô�ӡ��ɫΪ��ɫ

		//��ӡ�������  
		double x= pageFormat.getImageableX();  //������� PageFormat��ص� Paper����Ŀɳ����������Ϸ���� x���ꡣ  
		double y= pageFormat.getImageableY();  //������� PageFormat��ص� Paper����Ŀɳ����������Ϸ���� y���ꡣ

		//Font.PLAIN�� ��ͨ��ʽ����  	Font.ITALIC б����ʽ����	Font.BOLD ������ʽ������
		Font font = new Font("����",Font.BOLD,10); //����ָ�����ơ���ʽ�Ͱ�ֵ��С������һ���� Font��
		
		g2.setFont(font);//���ñ����ӡ����     
		
		float heigth = font.getSize2D();//��ȡ����ĸ߶�  
		float line = 0;
		
		switch (dyFlag) {
		case GUO_BANG_JI_LU:
			//����СƱ�ı������  
			g2.drawString("ɽ�������������޹�˾",(float)x+25,(float)y+heigth);

			line = 2*heigth; //��һ�п�ʼ��ӡ�ĸ߶�
			g2.setFont(new Font("����", Font.PLAIN,8));//������������  
			heigth = font.getSize2D();// ����߶�  

			//���ö�����  
			g2.drawString("������:"+gbjl.getDdh(), (float)x+20,(float)y+line);
			line+=heigth+2;

			//���ó��ƺ�  
			g2.drawString("���ƺ�:"+gbjl.getCph(), (float)x+20,(float)y+line);
			line+=heigth+2;

			//����˾������  
			g2.drawString("˾������:"+gbjl.getSjxm(), (float)x+20,(float)y+line);
			line+=heigth+2;

			//����˾�����֤��  
			g2.drawString("˾�����֤��:"+gbjl.getSjsfzh(), (float)x+20,(float)y+line);
			line+=heigth+2;

			//���ù�������  
			g2.drawString("��������:"+gbjl.getGbzl() , (float)x+20,(float)y+line);
			line+=heigth+2;
			
			line+=2;
			//���ù������� 
			g2.drawString("��������:"+gbjl.getGblxName(),(float)x+20,(float)y+line);
			line+=heigth;

			//���ù���ʱ��  
			g2.drawString("����ʱ��:"+gbjl.getGbsj(), (float)x+20,(float)y+line);
			line+=heigth+100;
			g2.drawString(".", (float)x+20,(float)y+line);
			break;
		case BANG_DAN_JI_LU:
			//����СƱ�ı������  
			g2.drawString("ɽ�����������Ƽ�������",(float)x+25,(float)y+heigth);

			line = 2*heigth; //��һ�п�ʼ��ӡ�ĸ߶�
			g2.setFont(new Font("����", Font.PLAIN,8));//������������  
			heigth = font.getSize2D();// ����߶�  

			//���ö�����  
			g2.drawString("������:"+bdjl.getDdh(), (float)x+20,(float)y+line);
			line+=heigth+2;

			//���ó��ƺ�  
			g2.drawString("���ƺ�:"+bdjl.getCph(), (float)x+20,(float)y+line);
			line+=heigth+2;

			//����ë��  
			g2.drawString("ë��:"+bdjl.getMz() , (float)x+20,(float)y+line);
			line+=heigth+2;

			//����Ƥ��  
			g2.drawString("Ƥ��:"+bdjl.getPz() , (float)x+20,(float)y+line);
			line+=heigth+2;

			//���þ���  
			g2.drawString("����:"+bdjl.getJz() , (float)x+20,(float)y+line);
			line+=heigth+2;
			
			line+=2;
			//����ʱ��(��һ�ι���ʱ��)
			g2.drawString("ʱ��:"+bdjl.getRq(), (float)x+20,(float)y+line);
			line+=heigth+100;
			g2.drawString(".", (float)x+20,(float)y+line);
			break;
		}
		
		switch (pageIndex) {  
			case 0:  
				return PAGE_EXISTS;//0 
			default:  
				return NO_SUCH_PAGE;//1
		}  
	}

}
