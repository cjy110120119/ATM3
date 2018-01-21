package com.feicuiedu.atm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;



public class Land implements Serializable {
	Scanner sca = new Scanner(System.in);
	public void landWay(){//��Ϊ������ķ����вſ�ʼ��������User,�������ڲ��ô���
		System.out.println("��ѡ���˻�:(1.��ͨ�û�,2,����Ա�˻�)");//ѡ������ͨ�û����ǹ���Ա
		int info = sca.nextInt();
		if(info == 1){//�ж��������,ѡ���Ӧ�Ĳ���
			userland();//��ͨ�û���¼
		}else if(info == 2){
			adminLand();//����Ա��¼
		}
	}
	public void userland(){//�û���¼
		String userkey = null;
		try{
			File file = new File("User" +File.separator+"user.txt");//����File����,��ȡ�ı�
			if(file.length() == 0){//�ı�Ϊ��
				System.out.println("��ǰ����ͨ�û�,���¼����Ա�û�ע��:");//��ʾע��
				landWay();
			}else{
				System.out.println("�������˺Ż����֤��:");//�˺ź����֤�Ŷ����Ե�¼
			}
			String str = sca.next();
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			HashMap<String,Object> hashmap = (HashMap<String, Object>) ois.readObject();
			
			
		
			Set<String> keyset = hashmap.keySet();//����set����,��ȡHashMap���ϵļ�
			for(String  key : keyset){//������
					userkey = key;//��Ϊkey�Ǿֲ�����,�±�Ҫ��,���Խ�key��ֵ����Ա����userkey
				}
			    if(userkey.startsWith(str)|userkey.endsWith(str)){//�ж�HashMap�еļ��Ƿ�����������˺ſ�ͷ,������������֤�Ž�β��
				    Object ob = hashmap.get(userkey);//�����,��ȡ�������Ӧ��ֵ,����ob
				    String str1 = ob.toString();//������obת����Ϊ�ַ���
	    			while(true){
	    				System.out.println("����������(����8λ,���ֺ���ĸ����ͬʱ��,����һ����д��ĸ):");
		    			String password = sca.next();//��������
	    				if((str1.contains(password))){//�ж�ȡ���Ķ����Ƿ�������������
		    				System.out.println("��¼�ɹ�");//�����,��½�ɹ�
		    				User user = (User) ob;//������obת����User��
		    				List list = new List();
		    				list.execute(user);//����List�еķ���ʱ,����user
		    			}else{//û��,��������
		    				System.out.println("�����������,����������:");
		    			}
	    			}
	    		}else{//HashMap��û�м�����������˺ſ�ͷ,������������֤�Ž�β
	    			System.out.println("�û�������");
	    			userland();
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
    public void adminLand(){
    	System.out.println("�������˺�(14λ����)");
    	String stracc = sca.next();
    	if(stracc.equals("370120180104")){//�ж�������˺��Ƿ������Ա�˺���ͬ
    		while(true){//ѭ��,�������������ʱ,�ص��˴�,��������
    			System.out.println("����������(6λ����):");
        		String strpwd = sca.next();
        		if(strpwd.equals("123456")){//�ж������Ƿ���ȷ	
        			System.out.println("��¼�ɹ�!");
        			String strname = "�����";
        			User user = new User(stracc,strpwd,strname);//�����ٹ���Ա�û�
        			user.getAccount();//��ȡ��ǰ�˻�
        			user.getPassword();//��ȡ����
        			user.getName();//��ȡ�û���
        			System.out.println("��ǰ�˻�Ϊ:"+user.getAccount()+"����Ϊ:"+user.getPassword()+"������Ϊ:"+strname);
        			Operate operate = new Operate();//����Operate��Operation()����
        			operate.operation();
        			break;//������ȷʱ,ִ�е��˴�����ѭ��
        		}else{
        			System.out.println("�������,����������");
        		}
    		}
    	}else{
    		System.out.println("�˺Ŵ���,����������");
    		adminLand();//�ص�������,��������
    	}
    }
}
