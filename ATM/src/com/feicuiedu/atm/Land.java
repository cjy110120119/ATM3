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
	public void landWay(){//因为在下面的方法中才开始真正调用User,所以现在不用传参
		System.out.println("请选择账户:(1.普通用户,2,管理员账户)");//选择是普通用户还是管理员
		int info = sca.nextInt();
		if(info == 1){//判断输入的数,选择对应的操作
			userland();//普通用户登录
		}else if(info == 2){
			adminLand();//管理员登录
		}
	}
	public void userland(){//用户登录
		String userkey = null;
		try{
			File file = new File("User" +File.separator+"user.txt");//创建File对象,获取文本
			if(file.length() == 0){//文本为空
				System.out.println("当前无普通用户,请登录管理员用户注册:");//提示注册
				landWay();
			}else{
				System.out.println("请输入账号或身份证号:");//账号和身份证号都可以登录
			}
			String str = sca.next();
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			HashMap<String,Object> hashmap = (HashMap<String, Object>) ois.readObject();
			
			
		
			Set<String> keyset = hashmap.keySet();//定义set集合,获取HashMap集合的键
			for(String  key : keyset){//遍历键
					userkey = key;//因为key是局部变量,下边要用,所以将key赋值给成员变量userkey
				}
			    if(userkey.startsWith(str)|userkey.endsWith(str)){//判断HashMap中的键是否有以输入的账号开头,或以输入的身份证号结尾的
				    Object ob = hashmap.get(userkey);//如果有,获取这个键对应的值,赋给ob
				    String str1 = ob.toString();//将对象ob转换成为字符串
	    			while(true){
	    				System.out.println("请输入密码(至少8位,数字和字母必须同时有,至少一个大写字母):");
		    			String password = sca.next();//输入密码
	    				if((str1.contains(password))){//判断取出的对象是否包含输入的密码
		    				System.out.println("登录成功");//如果有,登陆成功
		    				User user = (User) ob;//将对象ob转换成User型
		    				List list = new List();
		    				list.execute(user);//调用List中的方法时,传入user
		    			}else{//没有,重新输入
		    				System.out.println("密码输入错误,请重新输入:");
		    			}
	    			}
	    		}else{//HashMap中没有键是以输入的账号开头,或以输入的身份证号结尾
	    			System.out.println("用户不存在");
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
    	System.out.println("请输入账号(14位数字)");
    	String stracc = sca.next();
    	if(stracc.equals("370120180104")){//判断输入的账号是否与管理员账号相同
    		while(true){//循环,当密码输入错误时,回到此处,重新输入
    			System.out.println("请输入密码(6位数字):");
        		String strpwd = sca.next();
        		if(strpwd.equals("123456")){//判断密码是否正确	
        			System.out.println("登录成功!");
        			String strname = "翡翠侠";
        			User user = new User(stracc,strpwd,strname);//创建假管理员用户
        			user.getAccount();//获取当前账户
        			user.getPassword();//获取密码
        			user.getName();//获取用户名
        			System.out.println("当前账户为:"+user.getAccount()+"密码为:"+user.getPassword()+"开户名为:"+strname);
        			Operate operate = new Operate();//调用Operate的Operation()方法
        			operate.operation();
        			break;//密码正确时,执行到此处跳出循环
        		}else{
        			System.out.println("密码错误,请重新输入");
        		}
    		}
    	}else{
    		System.out.println("账号错误,请重新输入");
    		adminLand();//回到方法体,重新输入
    	}
    }
}
