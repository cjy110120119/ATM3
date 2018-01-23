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

	public void landWay() {// 因为在下面的方法中才开始真正调用User,所以现在不用传参
		while (true) {
			System.out.println("请选择账户:(1.普通用户,2,管理员账户)");// 选择是普通用户还是管理员
			String input = sca.next();
			if (input.equals("1")) {// 判断输入的数,选择对应的操作
				userland();// 普通用户登录
			} else if (input.equals("2")) {
				adminLand();// 管理员登录
			} else {
				System.out.println("输入错误,请重新输入");
			}
		}

	}

	public void userland(){//用户登录
		while(true){
			try{
				File file = new File("User" +File.separator+"user.txt");//创建File对象,获取文本
				
				if(file.length() == 0){//文本为空
					System.out.println("当前无普通用户,请登录管理员用户注册:");//提示注册
					landWay();
				}else{
				
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				HashMap<String,Object> hashmap = (HashMap<String, Object>) ois.readObject();
				if(hashmap == null || hashmap.isEmpty()){
					System.out.println("当前无普通用户,请登录管理员用户注册:");//提示注册
					landWay();
				}else {
					System.out.println("请输入账号或身份证号:");//账号和身份证号都可以登录
				}
				Set<String> keyset = hashmap.keySet();//定义set集合,获取HashMap集合的键
				String str = sca.next();
				
				for(String  key : keyset){//遍历键
					if(key.startsWith(str)||key.endsWith(str)){//判断HashMap中的键是否有以输入的账号开头,或以输入的身份证号结尾的
					    Object ob = hashmap.get(key);//如果有,获取这个键对应的值,赋给ob
					  User user = (User) ob;
					  String userpassword = user.getPassword();
		    			while(true){
		    				System.out.println("请输入密码(至少8位,数字和字母必须同时有,至少一个大写字母):");
			    			String password = sca.next();//输入密码
		    				if((userpassword.equals(password))){//判断取出的对象是否包含输入的密码
			    				System.out.println("登录成功");//如果有,登陆成功
			    				List list = new List();
			    				list.execute(user);//调用List中的方法时,传入user
			    			}else{//没有,重新输入
			    				System.out.println("密码输入错误,请重新输入:");
			    			}
		    			}
		    		}else{//HashMap中没有键是以输入的账号开头,或以输入的身份证号结尾
		    			System.out.println("用户不存在");
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
			System.out.println("请输入账号(14位数字)");
			String stracc = sca.next();
			if (stracc.equals("370120180104")) {// 判断输入的账号是否与管理员账号相同
				while (true) {// 循环,当密码输入错误时,回到此处,重新输入
					System.out.println("请输入密码(6位数字):");
					String strpwd = sca.next();
					if (strpwd.equals("123456")) {// 判断密码是否正确
						System.out.println("登录成功!");
						String strname = "翡翠侠";
						User user = new User(stracc, strpwd, strname);// 创建假管理员用户
						user.getAccount();// 获取当前账户
						user.getPassword();// 获取密码
						user.getName();// 获取用户名
						System.out.println("当前账户为:" + user.getAccount() + "密码为:" + user.getPassword() + "开户名为:" + strname);
						Operate operate = new Operate();// 调用Operate的Operation()方法
						operate.operation();// 密码正确时,执行到此处跳出循环
					} else {
						System.out.println("密码错误,请重新输入");
					}
				}
			} else {
				System.out.println("账号错误,请重新输入");
			}
		}
		
	}

	public void date(User user) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		String str = sdf.format(date);
		String landdate = user.getAccount() + "登陆时间:" + sdf;
		ArrayList<String> arrays = new ArrayList<>();
		arrays.add(landdate);
		File file = new File("User" + File.separator + "landdate.txt");// 获取文本
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
