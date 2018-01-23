package com.feicuiedu.atm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

public class User implements Serializable {// ˽�л�����
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
		return "[�˺�=" + account + ", ����=" + password + ", ������=" + name + ", �Ա�=" + gender + ", ���֤��=" + cardid + ", ѧ��="
				+ education + ", ��ַ=" + address + ", ���=" + balance + "���׼�¼" + recode + "]\n";
	}

	// ������
	public User(String account, String cardid) {// ����ʱ�ж��˺ź����֤��ʱ��
		super();
		this.account = account;
		this.cardid = cardid;
	}

	public User(String account) {// �޸��˻���Ϣ��ʱ��ʹ��
		super();
		this.account = account;

	}

	public User(String account, String password, String name) {// ��ʾ����Ա�˻���Ϣʹ��
		super();
		this.account = account;
		this.password = password;
		this.name = name;
	}

	public User() {// �޲ι�����

	}

	public User(String account, String password, String name, String gender, String cardid, String education,
			String address, double balance) {// ����ע���û�
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

	@Override // ��дequals����
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj == this) {// obj �뵱ǰ�����ַ��ͬ
			return true;
		}

		if (!(obj instanceof User)) {
			return false;
		}

		if (((User) obj).account.equals(account)) {// �˻���ͬ
			return true;
		}

		if (((User) obj).cardid.equals(cardid)) {// ���֤����ͬ
			return true;
		}

		return false;
	}
}
