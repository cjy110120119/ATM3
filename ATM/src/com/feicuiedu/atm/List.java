package com.feicuiedu.atm;

import java.util.Scanner;

public class List {
	public void execute(User user){//��ͨ�û������˵�
		 System.out.println("��ѡ�����");
         System.out.println("1.��ѯ");
         System.out.println("2.ת��");
         System.out.println("3.ȡ��");
         System.out.println("4.���");
         System.out.println("5.�˿�"); 
         getexecute(user);
   }
 public void getexecute(User user){
	 Scanner sca = new Scanner(System.in);
     while(true){
      System.out.println("��������Ҫ���еĲ���");
      int info = sca.nextInt();//�����Ҫ������ֵ,ѡ���Ӧ����
      Money money = new Money();//�ȶ������,����Ҫ��
       if(info == 1){//��ѯ
        Query query = new Query();
        query.queryuser(user);
        System.out.println("(1.������һ��)");//��ѯ�����ʾ������,��������
	       while(true){
	    	   int info2 = sca.nextInt();//��������Ϊ1
		        if (info2 == 1) {
		        	execute(user);//ֱ�ַ��ز˵�
		        }else{
		        	System.out.println("�����������������");
		        }
	        } 
        }else if (info == 2) {
        	money.transfer(user);//ת��
        }else if(info == 3){
            money.withdrawal(user);//ȡ��
        }else if(info == 4){
           money.deposit(user);//���
        }else if(info == 5){
            Exit exit = new Exit();
            exit.returnmenu();//�˳�
        }else{
          System.out.println("�������벻��ȷ,����������");
        }
	 }
     }
}
