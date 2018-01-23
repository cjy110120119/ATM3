package com.feicuiedu.atm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

public class User implements Serializable {// 私有化变量
	private String account;
	private String password;
	private String name;
	private String gender;
	private String cardid;
	private String education;
	private String address;
	private double balance;
	private ArrayList<String> recode = new ArrayList<>();

	@Override
	public String toString() {
		return "[账号=" + account + ", 密码=" + password + ", 开户名=" + name + ", 性别=" + gender + ", 身份证号=" + cardid + ", 学历="
				+ education + ", 地址=" + address + ", 余额=" + balance + "交易记录" + recode + "]\n";
	}

	// 构造器
	public User(String account, String cardid) {// 销户时判断账号和身份证号时用
		super();
		this.account = account;
		this.cardid = cardid;
	}

	public User(String account) {// 修改账户信息的时候使用
		super();
		this.account = account;

	}

	public User(String account, String password, String name) {// 显示管理员账户信息使用
		super();
		this.account = account;
		this.password = password;
		this.name = name;
	}

	public User() {// 无参构造器

	}

	public User(String account, String password, String name, String gender, String cardid, String education,
			String address, double balance) {// 用以注册用户
		this.account = account;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.cardid = cardid;
		this.education = education;
		this.address = address;
		this.balance = balance;
	}

	public String getAccount() {
		return account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public String getCardid() {
		return cardid;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;

	}

	public ArrayList<String> getRecode() {
		return recode;
	}

	public void setRecode(ArrayList<String> recode) {
		this.recode = recode;
	}

	@Override // 重写equals方法
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj == this) {// obj 与当前对象地址相同
			return true;
		}

		if (!(obj instanceof User)) {
			return false;
		}

		if (((User) obj).account.equals(account)) {// 账户相同
			return true;
		}

		if (((User) obj).cardid.equals(cardid)) {// 身份证号相同
			return true;
		}

		return false;
	}
}
