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
		System.out.println("请选择业务(1.注册,2.销户,3.查询,4.修改)");//根据输入的数字选择对应的业务
		int integer = sca.nextInt();
		if(integer == 1){//判断输入的数,选择业务
			register();//注册
		}else if(integer == 2){
			cancellation();//销户
		}else if(integer == 3){
			information();//查询
		}else if(integer == 4){
			updateUser();//修改
		}else{
			System.out.println("输入错误,重新输入");
			operation();//调用自己,重新输入
		}
	}
	
	public void register(){//注册
		//系统自动生成  规则  37+(如果是男01,如果是女02)+当前时间(年月日时分秒毫秒)
		System.out.println("请输入姓名:");
		String name = sca.next();
		System.out.println("请输入性别(1.男,2.女)(请务必输入正确,之后无法修改):");
		String gender = sca.next();
		if(gender.equals(1)){//1.男
			gender = "0"+"1";//因为在生成账户时会用到gender,所以重新赋值
		}else if(gender.equals(2)){//2.女
			gender = "0"+"2";
		}else{
			
		}
		while(true){
			System.out.println("请输入身份证号(18位)(请务必输入正确,之后无法修改):");
			cardid = sca.next();//输入的身份证号
			if(cardid.matches("^([0-9]{1,18})")){//判断是否符合正则表达式,不符合重新输入
				System.out.println("身份证号输入错误,应为18位数字,请重新输入:");
			}else{
				break;
			}
		}
		while(true){
			System.out.println("请输入学历(1.小学,2.中学,3.大学,4.其他):");//1代表  小数  2代表 中学  3代表大学 4代表其他
		    education = sca.next();
			if(education.equals(1)){//根据所选学历的不同,将对应值赋给education
				education = "小学";
				break;
			}else if(education.equals(2)){
				education = "中学";
				break;
			}else if(education.equals(3)){
				education = "大学";
				break;
			}else if(education.equals(4)){
				education = "其他";
				break;
			}else{
				System.out.println("输入错误,请重新输入");
			}
		}
		while(true){
			System.out.println("请输入家庭住址:");
		    address = sca.next();
			if(address.length()>50){//判断输入的长度是否大于50
				System.out.println("地址长度不能大于50,请重新输入");
				address = sca.next();
			}else{
				break;
			}
		}
        double balance = 0.0;//直接初始化余额为0.0
		while(true){
			System.out.println("请输入初始密码(至少8位,数字和字母必须同时又,至少一个大写字母):");
			password = sca.next();
			if(password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$")){//不符合正则表达式,
				System.out.println("密码输入错误,请重新输入");//重新输入
			}else{
				break;//符合,跳出
			}
		}
        
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");//设置获取的时间日期格式
        String time = sdf.format(new Date());//获取系统当前时间,用作生成账户
        String account = "37"+ gender +time;//将需要的参数连接起来,组成账户
        
        
        User user = new User(account, password, name, gender, cardid, education, address, balance);
        System.out.println(user);
        //将新注册的用户赋给user
       /* arrays = new ArrayList<>();//用集合先将用户存储在内存中
        arrays.add(user);*/
        
        
        System.out.println("开户成功!");
        
      
        while(true){
         	System.out.println("请选择接下来的操作(1.继续注册,2.返回登录):");//注册完成,选择接下来的操作
         	int info = sca.nextInt();
           	if(info == 1){
           		register();//继续注册
           	}else if(info == 2){
           		write(account,cardid,user);
           		Land land = new Land();//登录界面
           		land.landWay();//返回登录
           	}else{
           		System.out.println("输入错误请重新输入:");
           	}
        }
      	
	}
	public void cancellation() {//销户
		System.out.println("请输入要删除的账户:");
		String account = sca.next();
		System.out.println("请输入身份证号:");
		String cardid = sca.next();
		String str = account + cardid;//将输入的账号和身份证号组成一个字符串
		try {
			File file = new File("User" +File.separator+"user.txt");//创建File对象,获取文本
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));//转为对象流
			HashMap<String, Object> hm = (HashMap<String,Object>)ois.readObject();//读取文本中的对象
			//System.out.println(hm);
			if (hm.containsKey(str)) {//判断集合hm中的键是否有输入的账号和身份证号组成的键,如果有,继续执行
				//Object obj = hm.get(str);
				System.out.println("确认删除吗?(1.确认,2.取消)");
				int info = sca.nextInt();
				if(info == 1){
					hm.remove(str);//删除
					System.out.println("删除成功!");
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(hm);//将删除之后的集合,重新写入文本
					ois.close();//关闭流
				}else if(info == 2){//取消删除,退回管理员登录后的界面
					operation();
				}
			}else{//hm中不包含输入的账号和身份证号组成的键
				System.out.println("账号不存在,请重新输入");
				cancellation();//返回方法,重新输入
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
	public void information(){//查询用户信息
		File file = new File("User" +File.separator+"user.txt");//创建File对象,获取文本
			
			if(file.length() == 0){//对象长度为零,说明文本中没有对象
				System.out.println("无用户");
			}else{//有对象
				try {
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					HashMap<String,Object> hm = (HashMap<String, Object>) ois.readObject();//读取对象
					System.out.println(hm.values());//显示出所有对象信息
				} catch( ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		
	}
	public void updateUser(){//修改用户信息
		
		File file = new File("User" +File.separator+"user.txt");//创建File对象,获取文本
		if(file.length() == 0){//文本中无用户
			System.out.println("无用户");
			
		}else{//有用户
			System.out.println("请输入要修改的账户:");
		}
		String account = sca.next();//输入的账户
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			HashMap<String, Object> hashmap = (HashMap<String, Object>) ois.readObject();
			
			Set<String> keyset = hashmap.keySet();//获取集合中的键
			for(String  key : keyset){//遍历键
				userkey = key;//将键赋给userkey
			}
			if(userkey.startsWith(account)){//如果集合中的键有以输入的账号开头的
    				Object ob = hashmap.get(userkey);//根据这个键取出对应的值
    				User temp = (User) ob;//转换成User对象
    				update(temp);//调用update方法
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
	public void update(User temp){//将用户信息传进来
		while(true){
			System.out.println("请选择要修改的属性:(1.密码,2.姓名,3.学历,4.联系地址)");//判断要改的属性
			int info = sca.nextInt();
			if(info == 1){
				System.out.println("请输入新密码(6位数字):");//改密码
				String newpassword = sca.next();
				temp.setPassword(newpassword);//调用User的set方法
			}else if(info == 2){
				System.out.println("请输入修改后的姓名:");//改开户名
				String newname = sca.next();
				temp.setName(newname);//调用set方法
			}else if(info == 3){
				while(true){
					System.out.println("请输入修改后的学历(1.小学,2.中学,3.大学,4.其他):");//修改学历
					String neweducation = sca.next();
					if(neweducation.equals(1)){
						neweducation = "小学";
						temp.setEducation(neweducation);//调用方法
					}else if(neweducation.equals(2)){
						neweducation = "中学";
						temp.setEducation(neweducation);
					}else if(neweducation.equals(3)){
						neweducation = "大学";
						temp.setEducation(neweducation);
					}else if(neweducation.equals(4)){
						neweducation = "其他";
						temp.setEducation(neweducation);
					}else {
			             System.out.println("输入错误,只能是1-4,重新输入");
					}
				}
			}else if(info == 4){
				System.out.println("请输入修改后的地址:");//修改地址
				String newaddress = sca.next();
				temp.setAddress(newaddress);
			}
			else{
				System.out.println("账户不存在!请重新输入");
				updateUser();
				}
		}
	}
	public void write (String account,String cardid,User user){//用于注册后统一写入,传入注册时输入的账号和身份证号,还有注册时生成的用户
		
		File file = new File("User" +File.separator+"user.txt");//先获取文本
		
		try {
			
			if(file.equals("") || file.length()==0){//判断是否为空
				HashMap<String,Object> hm = new HashMap<String,Object>();//为空时,直接创建新的集合,不再读取
				String str = account + cardid;//将注册时输入的账号和身份证号作为键
				hm.put(str, user);//以注册时输入的账号和身份证号作为键,生成的用户作为值,添加到集合中
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
				oos.writeObject(hm);//用对象流写入
				System.out.println("存入成功");
				oos.close();//关闭流
			}else{//不为空,即文本中有用户
				   ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));//先读取
					HashMap<String,Object> hm = (HashMap<String, Object>) ois.readObject();//读取为集合
					String str = account + cardid;//将注册时输入的账号和身份证号作为键
					hm.put(str, user);//以注册时输入的账号和身份证号作为键,生成的用户作为值,添加到集合中
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(hm);//想文本中写入这个集合
					System.out.println("存入成功!");
					ois.close();//关闭流
					oos.close();//关闭流
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
