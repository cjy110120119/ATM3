package com.feicuiedu.atm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class Query {
	public void queryuser(User user) {
		File file = new File("User" +File.separator+"user.txt");//��ȡ�ı�
		try {//���ڴ˲�ѯ������ͨ�û���ѯ,��Ȼ����ͨ�û���¼���ҵ������������,��֤���ı��в�Ϊ��
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));//ֱ�Ӷ�ȡ
			HashMap<String,Object> hashmap =(HashMap<String, Object>) ois.readObject();
				System.out.println(user);//ֱ�Ӵ�ӡ���˺���Ϣ	
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
}
