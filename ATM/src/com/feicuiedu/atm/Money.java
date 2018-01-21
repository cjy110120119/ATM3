package com.feicuiedu.atm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Money {
	private String userkey=null;//ȫ�ֱ���
	Scanner sca = new Scanner(System.in);
	public void transfer(User user){//ת��ҵ��
		System.out.println("������Է��˺�:");
		String otheraccount = sca.next();//��������ĶԷ��˻�
		File file = new File("User" +File.separator+"user.txt");//��ȡ�ı�
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			HashMap<String,Object> hm = (HashMap<String, Object>) ois.readObject();//���ı���Ϣ���ɼ���
			Set<String> keyset = hm.keySet();//��ȡ�����еļ�
			for(String  key : keyset){//������
				userkey = key;//Ϊʹkey����ֲ���������������,���丳ֵ����Ա����userkey
			}
			if(userkey.startsWith(otheraccount)){//�жϼ���û����������˺ſ�ͷ��
				Object obj = hm.get(userkey);//�����,�Դ˼�ȡ����Ӧ��ֵ
				User tuser = (User)obj;//��ȡ��ת���û�
				while(true){
					System.out.println("������ת�˽��:");
					double tamount = sca.nextDouble();//��ȡ�����ת�˽��
					if(tamount<0 | tamount %100 !=0){//�ж��Ƿ�С��0���߲���100�ı���
						System.out.println("���������0����100��������,����������");
					}else if(tamount > user.getBalance()){//�ж�����Ľ���Ƿ���ڵ�ǰ�˻����
						System.out.println("����,����������:");//�������,��������
					}else{//����Ľ�����
					user.setBalance(user.getBalance() - tamount);//��ǰ�û�����ȥת�˽��
					tuser.setBalance(tuser.getBalance() + tamount);//�Է��˻�������ת�˽��
					System.out.println("ת�˳ɹ�,��ǰ���Ϊ:"+ user.getBalance());//�����ǰ�˻���ǰ���
					}
				}
			}else{//û����������˺ſ�ͷ��
				System.out.println("�޴��û�");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deposit(User user){//���
		System.out.println("����������");
		double balance = sca.nextDouble();//��ȡ����Ĵ����
		System.out.println("1.ȷ��      2.��������");
		int info = sca.nextInt();
		if(info == 1){//�����Ϊ1,ȷ�ϴ��
			if(balance > 0 & balance %100 == 0){//�ж�����Ľ���Ƿ���Ϲ���
				double initialbalance = user.getBalance();//����,��ȡ��ǰ�û������
				user.setBalance(initialbalance + balance);//��ǰ�û������ϴ���Ľ��
				System.out.println("����ɹ�,��ǰ���Ϊ:"+user.getBalance());//��ʾ��ǰ���
				System.out.println("1.�������           2.�˳�");
				while(true){
					int info2 = sca.nextInt();//�жϽ������Ĳ���
					if(info2 == 1){//�������
						deposit(user);//�����Լ�
					}else if(info2 == 2){
						List list = new List();
						list.execute(user);//���ز˵�����
					}else{
						System.out.println("�������,����������:");	
					}
				}
			}else{
				System.out.println("����Ľ��������0,����ֻ����100��������");
			}
		}else if(info == 2){//�������������,���÷���,��ͷ��ʼ
			deposit(user);
		}else{
			deposit(user);
		}
		
	}
	public void withdrawal(User user){//ȡ��
		System.out.println("������ȡ����:");
		double  amount = sca.nextDouble();//��ȡȡ���Ľ��
		if(amount<0 | amount %100 !=0){//�ж�������Ƿ���Ϲ���
			System.out.println("���������0����100��������,����������");
			withdrawal(user);//������,���¿�ʼ
		}else if(amount > user.getBalance()){//���Ϲ���,�ٿ��Ƿ�С�ڵ�ǰ�û��ĵ�ǰ���
			System.out.println("����,����������:");//������
			withdrawal(user);//��������
		}else{//������
			double balance  = user.getBalance();//��ȡ��ǰ�û��ĵ�ǰ���
			user.setBalance(balance - amount);//��ǰ�û�������ȥȡ���Ľ��
			System.out.println("ȡ��ɹ�,��ǰ���Ϊ:"+user.getBalance());//��ȡ��ǰ�û���ǰ���	
			while(true){
				System.out.println("1.����ȡ��           2.�˳�");
				int info = sca.nextInt();
				if(info == 1){//�ж�ȡ��֮��Ĳ���
					withdrawal(user);//����ȡ��
				}else if(info == 2){
					List list = new List();
					list.execute(user);//���ز˵�
				}else{
					System.out.println("�������,����������:");	
				}
			}
		}
		
	}
}
