package com.feicuiedu.atm;

import java.util.Scanner;

public class List {
	public void execute(User user) {// ��ͨ�û������˵�
		System.out.println("��ѡ�����");
		System.out.println("1.��ѯ");
		System.out.println("2.ת��");
		System.out.println("3.ȡ��");
		System.out.println("4.���");
		System.out.println("5.�˿�");
		getexecute(user);
	}

	public void getexecute(User user) {
		Scanner sca = new Scanner(System.in);
		while (true) {
			System.out.println("��������Ҫ���еĲ���");
			String input = sca.next();// �����Ҫ������ֵ,ѡ���Ӧ����
			Money money = new Money();// �ȶ������,����Ҫ��
			if (input.equals("1")) {// ��ѯ
				Query query = new Query();
				query.queryuser(user);
				System.out.println("(1.������һ��)");// ��ѯ�����ʾ������,��������
				while (true) {
					String input2 = sca.next();// ��������Ϊ1
					if (input.equals("1")) {
						execute(user);// ֱ�ַ��ز˵�
					} else {
						System.out.println("�����������������");
					}
				}
			} else if (input.equals("2")) {
				money.transfer(user);// ת��
			} else if (input.equals("3")) {
				money.withdrawal(user);// ȡ��
			} else if (input.equals("4")) {
				money.deposit(user);// ���
			} else if (input.equals("5")) {
				Exit exit = new Exit();
				exit.returnmenu();// �˳�
			} else {
				System.out.println("�������벻��ȷ,����������");
			}
		}
	}
}
