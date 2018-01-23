package com.feicuiedu.atm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Land implements Serializable {
	Scanner sca = new Scanner(System.in);

	public void landWay() {// ��Ϊ������ķ����вſ�ʼ��������User,�������ڲ��ô���
		while (true) {
			System.out.println("��ѡ���˻�:(1.��ͨ�û�,2,����Ա�˻�)");// ѡ������ͨ�û����ǹ���Ա
			String input = sca.next();
			if (input.equals("1")) {// �ж��������,ѡ���Ӧ�Ĳ���
				userland();// ��ͨ�û���¼
			} else if (input.equals("2")) {
				adminLand();// ����Ա��¼
			} else {
				System.out.println("�������,����������");
			}
		}

	}

	public void userland(){//�û���¼
		while(true){
			try{
				File file = new File("User" +File.separator+"user.txt");//����File����,��ȡ�ı�
				
				if(file.length() == 0){//�ı�Ϊ��
					System.out.println("��ǰ����ͨ�û�,���¼����Ա�û�ע��:");//��ʾע��
					landWay();
				}else{
				
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				HashMap<String,Object> hashmap = (HashMap<String, Object>) ois.readObject();
				if(hashmap == null || hashmap.isEmpty()){
					System.out.println("��ǰ����ͨ�û�,���¼����Ա�û�ע��:");//��ʾע��
					landWay();
				}else {
					System.out.println("�������˺Ż����֤��:");//�˺ź����֤�Ŷ����Ե�¼
				}
				Set<String> keyset = hashmap.keySet();//����set����,��ȡHashMap���ϵļ�
				String str = sca.next();
				
				for(String  key : keyset){//������
					if(key.startsWith(str)||key.endsWith(str)){//�ж�HashMap�еļ��Ƿ�����������˺ſ�ͷ,������������֤�Ž�β��
					    Object ob = hashmap.get(key);//�����,��ȡ�������Ӧ��ֵ,����ob
					  User user = (User) ob;
					  String userpassword = user.getPassword();
		    			while(true){
		    				System.out.println("����������(����8λ,���ֺ���ĸ����ͬʱ��,����һ����д��ĸ):");
			    			String password = sca.next();//��������
		    				if((userpassword.equals(password))){//�ж�ȡ���Ķ����Ƿ�������������
			    				System.out.println("��¼�ɹ�");//�����,��½�ɹ�
			    				List list = new List();
			    				list.execute(user);//����List�еķ���ʱ,����user
			    			}else{//û��,��������
			    				System.out.println("�����������,����������:");
			    			}
		    			}
		    		}else{//HashMap��û�м�����������˺ſ�ͷ,������������֤�Ž�β
		    			System.out.println("�û�������");
		    		}
				}
			} 
		}catch (FileNotFoundException e) {
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
		
 }

	public void adminLand() {
		
		while(true){
			System.out.println("�������˺�(14λ����)");
			String stracc = sca.next();
			if (stracc.equals("370120180104")) {// �ж�������˺��Ƿ������Ա�˺���ͬ
				while (true) {// ѭ��,�������������ʱ,�ص��˴�,��������
					System.out.println("����������(6λ����):");
					String strpwd = sca.next();
					if (strpwd.equals("123456")) {// �ж������Ƿ���ȷ
						System.out.println("��¼�ɹ�!");
						String strname = "�����";
						User user = new User(stracc, strpwd, strname);// �����ٹ���Ա�û�
						user.getAccount();// ��ȡ��ǰ�˻�
						user.getPassword();// ��ȡ����
						user.getName();// ��ȡ�û���
						System.out.println("��ǰ�˻�Ϊ:" + user.getAccount() + "����Ϊ:" + user.getPassword() + "������Ϊ:" + strname);
						Operate operate = new Operate();// ����Operate��Operation()����
						operate.operation();// ������ȷʱ,ִ�е��˴�����ѭ��
					} else {
						System.out.println("�������,����������");
					}
				}
			} else {
				System.out.println("�˺Ŵ���,����������");
			}
		}
		
	}

	public void date(User user) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��HHʱmm��ss��");
		String str = sdf.format(date);
		String landdate = user.getAccount() + "��½ʱ��:" + sdf;
		ArrayList<String> arrays = new ArrayList<>();
		arrays.add(landdate);
		File file = new File("User" + File.separator + "landdate.txt");// ��ȡ�ı�
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(arrays);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
