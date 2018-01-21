package com.feicuiedu.atm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.sun.javafx.collections.MappingChange.Map;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Operate implements Serializable {
	private String password = null;
	private String cardid = null;
	private String education = null;
	private String address = null;
	private String userkey =null;
	Scanner sca = new Scanner(System.in);
	ArrayList<User> arrays = null;
	public void operation(){
		System.out.println("��ѡ��ҵ��(1.ע��,2.����,3.��ѯ,4.�޸�)");//�������������ѡ���Ӧ��ҵ��
		int integer = sca.nextInt();
		if(integer == 1){//�ж��������,ѡ��ҵ��
			register();//ע��
		}else if(integer == 2){
			cancellation();//����
		}else if(integer == 3){
			information();//��ѯ
		}else if(integer == 4){
			updateUser();//�޸�
		}else{
			System.out.println("�������,��������");
			operation();//�����Լ�,��������
		}
	}
	
	public void register(){//ע��
		//ϵͳ�Զ�����  ����  37+(�������01,�����Ů02)+��ǰʱ��(������ʱ�������)
		System.out.println("����������:");
		String name = sca.next();
		System.out.println("�������Ա�(1.��,2.Ů)(�����������ȷ,֮���޷��޸�):");
		String gender = sca.next();
		if(gender.equals(1)){//1.��
			gender = "0"+"1";//��Ϊ�������˻�ʱ���õ�gender,�������¸�ֵ
		}else if(gender.equals(2)){//2.Ů
			gender = "0"+"2";
		}else{
			
		}
		while(true){
			System.out.println("���������֤��(18λ)(�����������ȷ,֮���޷��޸�):");
			cardid = sca.next();//��������֤��
			if(cardid.matches("^([0-9]{1,18})")){//�ж��Ƿ����������ʽ,��������������
				System.out.println("���֤���������,ӦΪ18λ����,����������:");
			}else{
				break;
			}
		}
		while(true){
			System.out.println("������ѧ��(1.Сѧ,2.��ѧ,3.��ѧ,4.����):");//1����  С��  2���� ��ѧ  3�����ѧ 4��������
		    education = sca.next();
			if(education.equals(1)){//������ѡѧ���Ĳ�ͬ,����Ӧֵ����education
				education = "Сѧ";
				break;
			}else if(education.equals(2)){
				education = "��ѧ";
				break;
			}else if(education.equals(3)){
				education = "��ѧ";
				break;
			}else if(education.equals(4)){
				education = "����";
				break;
			}else{
				System.out.println("�������,����������");
			}
		}
		while(true){
			System.out.println("�������ͥסַ:");
		    address = sca.next();
			if(address.length()>50){//�ж�����ĳ����Ƿ����50
				System.out.println("��ַ���Ȳ��ܴ���50,����������");
				address = sca.next();
			}else{
				break;
			}
		}
        double balance = 0.0;//ֱ�ӳ�ʼ�����Ϊ0.0
		while(true){
			System.out.println("�������ʼ����(����8λ,���ֺ���ĸ����ͬʱ��,����һ����д��ĸ):");
			password = sca.next();
			if(password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$")){//������������ʽ,
				System.out.println("�����������,����������");//��������
			}else{
				break;//����,����
			}
		}
        
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");//���û�ȡ��ʱ�����ڸ�ʽ
        String time = sdf.format(new Date());//��ȡϵͳ��ǰʱ��,���������˻�
        String account = "37"+ gender +time;//����Ҫ�Ĳ�����������,����˻�
        
        
        User user = new User(account, password, name, gender, cardid, education, address, balance);
        System.out.println(user);
        //����ע����û�����user
       /* arrays = new ArrayList<>();//�ü����Ƚ��û��洢���ڴ���
        arrays.add(user);*/
        
        
        System.out.println("�����ɹ�!");
        
      
        while(true){
         	System.out.println("��ѡ��������Ĳ���(1.����ע��,2.���ص�¼):");//ע�����,ѡ��������Ĳ���
         	int info = sca.nextInt();
           	if(info == 1){
           		register();//����ע��
           	}else if(info == 2){
           		write(account,cardid,user);
           		Land land = new Land();//��¼����
           		land.landWay();//���ص�¼
           	}else{
           		System.out.println("�����������������:");
           	}
        }
      	
	}
	public void cancellation() {//����
		System.out.println("������Ҫɾ�����˻�:");
		String account = sca.next();
		System.out.println("���������֤��:");
		String cardid = sca.next();
		String str = account + cardid;//��������˺ź����֤�����һ���ַ���
		try {
			File file = new File("User" +File.separator+"user.txt");//����File����,��ȡ�ı�
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));//תΪ������
			HashMap<String, Object> hm = (HashMap<String,Object>)ois.readObject();//��ȡ�ı��еĶ���
			//System.out.println(hm);
			if (hm.containsKey(str)) {//�жϼ���hm�еļ��Ƿ���������˺ź����֤����ɵļ�,�����,����ִ��
				//Object obj = hm.get(str);
				System.out.println("ȷ��ɾ����?(1.ȷ��,2.ȡ��)");
				int info = sca.nextInt();
				if(info == 1){
					hm.remove(str);//ɾ��
					System.out.println("ɾ���ɹ�!");
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(hm);//��ɾ��֮��ļ���,����д���ı�
					ois.close();//�ر���
				}else if(info == 2){//ȡ��ɾ��,�˻ع���Ա��¼��Ľ���
					operation();
				}
			}else{//hm�в�����������˺ź����֤����ɵļ�
				System.out.println("�˺Ų�����,����������");
				cancellation();//���ط���,��������
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
	public void information(){//��ѯ�û���Ϣ
		File file = new File("User" +File.separator+"user.txt");//����File����,��ȡ�ı�
			
			if(file.length() == 0){//���󳤶�Ϊ��,˵���ı���û�ж���
				System.out.println("���û�");
			}else{//�ж���
				try {
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					HashMap<String,Object> hm = (HashMap<String, Object>) ois.readObject();//��ȡ����
					System.out.println(hm.values());//��ʾ�����ж�����Ϣ
				} catch( ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		
	}
	public void updateUser(){//�޸��û���Ϣ
		
		File file = new File("User" +File.separator+"user.txt");//����File����,��ȡ�ı�
		if(file.length() == 0){//�ı������û�
			System.out.println("���û�");
			
		}else{//���û�
			System.out.println("������Ҫ�޸ĵ��˻�:");
		}
		String account = sca.next();//������˻�
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			HashMap<String, Object> hashmap = (HashMap<String, Object>) ois.readObject();
			
			Set<String> keyset = hashmap.keySet();//��ȡ�����еļ�
			for(String  key : keyset){//������
				userkey = key;//��������userkey
			}
			if(userkey.startsWith(account)){//��������еļ�����������˺ſ�ͷ��
    				Object ob = hashmap.get(userkey);//���������ȡ����Ӧ��ֵ
    				User temp = (User) ob;//ת����User����
    				update(temp);//����update����
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
	public void update(User temp){//���û���Ϣ������
		while(true){
			System.out.println("��ѡ��Ҫ�޸ĵ�����:(1.����,2.����,3.ѧ��,4.��ϵ��ַ)");//�ж�Ҫ�ĵ�����
			int info = sca.nextInt();
			if(info == 1){
				System.out.println("������������(6λ����):");//������
				String newpassword = sca.next();
				temp.setPassword(newpassword);//����User��set����
			}else if(info == 2){
				System.out.println("�������޸ĺ������:");//�Ŀ�����
				String newname = sca.next();
				temp.setName(newname);//����set����
			}else if(info == 3){
				while(true){
					System.out.println("�������޸ĺ��ѧ��(1.Сѧ,2.��ѧ,3.��ѧ,4.����):");//�޸�ѧ��
					String neweducation = sca.next();
					if(neweducation.equals(1)){
						neweducation = "Сѧ";
						temp.setEducation(neweducation);//���÷���
					}else if(neweducation.equals(2)){
						neweducation = "��ѧ";
						temp.setEducation(neweducation);
					}else if(neweducation.equals(3)){
						neweducation = "��ѧ";
						temp.setEducation(neweducation);
					}else if(neweducation.equals(4)){
						neweducation = "����";
						temp.setEducation(neweducation);
					}else {
			             System.out.println("�������,ֻ����1-4,��������");
					}
				}
			}else if(info == 4){
				System.out.println("�������޸ĺ�ĵ�ַ:");//�޸ĵ�ַ
				String newaddress = sca.next();
				temp.setAddress(newaddress);
			}
			else{
				System.out.println("�˻�������!����������");
				updateUser();
				}
		}
	}
	public void write (String account,String cardid,User user){//����ע���ͳһд��,����ע��ʱ������˺ź����֤��,����ע��ʱ���ɵ��û�
		
		File file = new File("User" +File.separator+"user.txt");//�Ȼ�ȡ�ı�
		
		try {
			
			if(file.equals("") || file.length()==0){//�ж��Ƿ�Ϊ��
				HashMap<String,Object> hm = new HashMap<String,Object>();//Ϊ��ʱ,ֱ�Ӵ����µļ���,���ٶ�ȡ
				String str = account + cardid;//��ע��ʱ������˺ź����֤����Ϊ��
				hm.put(str, user);//��ע��ʱ������˺ź����֤����Ϊ��,���ɵ��û���Ϊֵ,��ӵ�������
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
				oos.writeObject(hm);//�ö�����д��
				System.out.println("����ɹ�");
				oos.close();//�ر���
			}else{//��Ϊ��,���ı������û�
				   ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));//�ȶ�ȡ
					HashMap<String,Object> hm = (HashMap<String, Object>) ois.readObject();//��ȡΪ����
					String str = account + cardid;//��ע��ʱ������˺ź����֤����Ϊ��
					hm.put(str, user);//��ע��ʱ������˺ź����֤����Ϊ��,���ɵ��û���Ϊֵ,��ӵ�������
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(hm);//���ı���д���������
					System.out.println("����ɹ�!");
					ois.close();//�ر���
					oos.close();//�ر���
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}
}
