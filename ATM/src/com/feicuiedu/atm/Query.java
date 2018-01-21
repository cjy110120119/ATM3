package com.feicuiedu.atm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class Query {
	public void queryuser(User user) {
		File file = new File("User" +File.separator+"user.txt");//获取文本
		try {//由于此查询用于普通用户查询,既然有普通用户登录并且调用了这个方法,就证明文本中不为空
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));//直接读取
			HashMap<String,Object> hashmap =(HashMap<String, Object>) ois.readObject();
				System.out.println(user);//直接打印此账号信息	
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
