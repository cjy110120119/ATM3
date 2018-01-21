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
	private String userkey=null;//全局变量
	Scanner sca = new Scanner(System.in);
	public void transfer(User user){//转账业务
		System.out.println("请输入对方账号:");
		String otheraccount = sca.next();//接受输入的对方账户
		File file = new File("User" +File.separator+"user.txt");//获取文本
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			HashMap<String,Object> hm = (HashMap<String, Object>) ois.readObject();//将文本信息读成集合
			Set<String> keyset = hm.keySet();//获取集合中的键
			for(String  key : keyset){//遍历键
				userkey = key;//为使key这个局部变量在下面能用,将其赋值给成员变量userkey
			}
			if(userkey.startsWith(otheraccount)){//判断键有没有以输入的账号开头的
				Object obj = hm.get(userkey);//如果有,以此键取出对应的值
				User tuser = (User)obj;//获取被转账用户
				while(true){
					System.out.println("请输入转账金额:");
					double tamount = sca.nextDouble();//获取输入的转账金额
					if(tamount<0 | tamount %100 !=0){//判断是否小于0或者不是100的倍数
						System.out.println("金额必须大于0且是100的整数倍,请重新输入");
					}else if(tamount > user.getBalance()){//判断输入的金额是否大于当前账户余额
						System.out.println("余额不足,请重新输入:");//如果大于,重新输入
					}else{//输入的金额符合
					user.setBalance(user.getBalance() - tamount);//当前用户余额减去转账金额
					tuser.setBalance(tuser.getBalance() + tamount);//对方账户余额加上转账金额
					System.out.println("转账成功,当前余额为:"+ user.getBalance());//输出当前账户当前余额
					}
				}
			}else{//没有以输入的账号开头的
				System.out.println("无此用户");
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
	public void deposit(User user){//存款
		System.out.println("请输入存款金额");
		double balance = sca.nextDouble();//获取输入的存款金额
		System.out.println("1.确认      2.重新输入");
		int info = sca.nextInt();
		if(info == 1){//输入的为1,确认存款
			if(balance > 0 & balance %100 == 0){//判断输入的金额是否符合规则
				double initialbalance = user.getBalance();//符合,获取当前用户的余额
				user.setBalance(initialbalance + balance);//当前用户余额加上存入的金额
				System.out.println("存入成功,当前余额为:"+user.getBalance());//显示当前余额
				System.out.println("1.继续存款           2.退出");
				while(true){
					int info2 = sca.nextInt();//判断接下来的操作
					if(info2 == 1){//继续存款
						deposit(user);//调用自己
					}else if(info2 == 2){
						List list = new List();
						list.execute(user);//返回菜单界面
					}else{
						System.out.println("输入错误,请重新输入:");	
					}
				}
			}else{
				System.out.println("存入的金额必须大于0,并且只能是100的整数倍");
			}
		}else if(info == 2){//如果是重新输入,调用方法,从头开始
			deposit(user);
		}else{
			deposit(user);
		}
		
	}
	public void withdrawal(User user){//取款
		System.out.println("请输入取款金额:");
		double  amount = sca.nextDouble();//获取取出的金额
		if(amount<0 | amount %100 !=0){//判断输入的是否符合规则
			System.out.println("金额必须大于0且是100的整数倍,请重新输入");
			withdrawal(user);//不符合,重新开始
		}else if(amount > user.getBalance()){//符合规则,再看是否小于当前用户的当前余额
			System.out.println("余额不足,请重新输入:");//比余额大
			withdrawal(user);//重新输入
		}else{//都符合
			double balance  = user.getBalance();//获取当前用户的当前金额
			user.setBalance(balance - amount);//当前用户的余额减去取出的金额
			System.out.println("取款成功,当前余额为:"+user.getBalance());//获取当前用户当前余额	
			while(true){
				System.out.println("1.继续取款           2.退出");
				int info = sca.nextInt();
				if(info == 1){//判断取款之后的操作
					withdrawal(user);//继续取款
				}else if(info == 2){
					List list = new List();
					list.execute(user);//返回菜单
				}else{
					System.out.println("输入错误,请重新输入:");	
				}
			}
		}
		
	}
}
