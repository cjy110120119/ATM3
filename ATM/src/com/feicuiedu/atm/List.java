package com.feicuiedu.atm;

import java.util.Scanner;

public class List {
	public void execute(User user) {// 普通用户操作菜单
		System.out.println("请选择操作");
		System.out.println("1.查询");
		System.out.println("2.转账");
		System.out.println("3.取款");
		System.out.println("4.存款");
		System.out.println("5.退卡");
		getexecute(user);
	}

	public void getexecute(User user) {
		Scanner sca = new Scanner(System.in);
		while (true) {
			System.out.println("请输入您要进行的操作");
			String input = sca.next();// 输入得要操作的值,选择对应方法
			Money money = new Money();// 先定义对象,下面要用
			if (input.equals("1")) {// 查询
				Query query = new Query();
				query.queryuser(user);
				System.out.println("(1.返回上一级)");// 查询结果显示出来后,输出此语句
				while (true) {
					String input2 = sca.next();// 如果输入的为1
					if (input.equals("1")) {
						execute(user);// 直街返回菜单
					} else {
						System.out.println("输入错误请重新输入");
					}
				}
			} else if (input.equals("2")) {
				money.transfer(user);// 转账
			} else if (input.equals("3")) {
				money.withdrawal(user);// 取款
			} else if (input.equals("4")) {
				money.deposit(user);// 存款
			} else if (input.equals("5")) {
				Exit exit = new Exit();
				exit.returnmenu();// 退出
			} else {
				System.out.println("您的输入不正确,请重新输入");
			}
		}
	}
}
