package com.feicuiedu.atm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Money {
	Scanner sca = new Scanner(System.in);

	public void transfer(User user) {// ת��ҵ��
		while (true) {
			System.out.println("������Է��˺�:");
			String otheraccount = sca.next();// ��������ĶԷ��˻�
			File file = new File("User" + File.separator + "user.txt");// ��ȡ�ı�
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				HashMap<String, Object> hm = (HashMap<String, Object>) ois.readObject();// ���ı���Ϣ���ɼ���
				Set<String> keyset = hm.keySet();// ��ȡ�����еļ�

				for (String key : keyset) {// ������
					Object obj = hm.get(key);// �����,�Դ˼�ȡ����Ӧ��ֵ
					User tuser = (User) obj;// ��ȡ��ת���û�
					String taccount = tuser.getAccount();
					if (taccount.equals(otheraccount)) {// �жϼ���û����������˺ſ�ͷ��
						while (true) {
							System.out.println("������ת�˽��:");
							double tamount = sca.nextDouble();// ��ȡ�����ת�˽��
							if (tamount < 0 | tamount % 100 != 0) {// �ж��Ƿ�С��0���߲���100�ı���
								System.out.println("���������0����100��������,����������");
							} else if (tamount > user.getBalance()) {// �ж�����Ľ���Ƿ���ڵ�ǰ�˻����
								System.out.println("����,����������:");// �������,��������
							} else {// ����Ľ�����
								System.out.println("ת�˳ɹ�!");
								user.setBalance(user.getBalance() - tamount);// ��ǰ�û�����ȥת�˽��
								tuser.setBalance(tuser.getBalance() + tamount);// �Է��˻�������ת�˽��
								String flow = "����" + otheraccount + "ת��" + tamount + "Ԫ";
								record(user, flow);
								flow = user.getName() + "����ת��" + tamount + "Ԫ";
								record(tuser, flow);
								break;
							}

						}
						System.out.println("1.����ת��    2.����");
						String str = sca.next();
						if (str.equals("1")) {

						} else {
							List list = new List();
							list.execute(user);
						}
					} else {// û����������˺ſ�ͷ��
						System.out.println("�޴��û�");
					}
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void deposit(User user) {// ���
		boolean bln = false;
		while (true) {
			System.out.println("����������");
			double balance = sca.nextDouble();// ��ȡ����Ĵ����
			System.out.println("1.ȷ��      2.��������");
			String input = sca.next();
			if (input.equals("1")) {// �����Ϊ1,ȷ�ϴ��
				if (balance > 0 & balance % 100 == 0) {// �ж�����Ľ���Ƿ���Ϲ���
					double initialbalance = user.getBalance();// ����,��ȡ��ǰ�û������
					user.setBalance(initialbalance + balance);// ��ǰ�û������ϴ���Ľ��
					System.out.println("����ɹ�,��ǰ���Ϊ:" + user.getBalance());// ��ʾ��ǰ���
					String flow = "��������" + balance + "Ԫ";
					record(user, flow);
					System.out.println("1.�������           2.�˳�");
					while (true) {
						String input2 = sca.next();// �жϽ������Ĳ���
						if (input2.equals("1")) {// �������
							deposit(user);// �����Լ�
						} else if (input2.equals("2")) {
							Operate operate = new Operate();
							operate.modify(user);
							List list = new List();
							list.execute(user);// ���ز˵�����
						} else {
							System.out.println("�������,����������:");
						}
					}
				} else {
					System.out.println("����Ľ��������0,����ֻ����100��������");
				}
			} else if (input.equals("2")) {// �������������,���÷���,��ͷ��ʼ
				bln = true;
			} else {
				bln = true;
			}
		}

	}

	public void withdrawal(User user) {// ȡ��
		boolean bln = false;
		while (true) {
			System.out.println("������ȡ����:");
			double amount = sca.nextDouble();// ��ȡȡ���Ľ��
			if (amount < 0 | amount % 100 != 0) {// �ж�������Ƿ���Ϲ���
				System.out.println("���������0����100��������,����������");
				bln = true;
			} else if (amount > user.getBalance()) {// ���Ϲ���,�ٿ��Ƿ�С�ڵ�ǰ�û��ĵ�ǰ���
				System.out.println("����,����������:");// ������
				bln = true;
			} else {// ������
				double balance = user.getBalance();// ��ȡ��ǰ�û��ĵ�ǰ���
				user.setBalance(balance - amount);// ��ǰ�û�������ȥȡ���Ľ��
				System.out.println("ȡ��ɹ�,��ǰ���Ϊ:" + user.getBalance());// ��ȡ��ǰ�û���ǰ���
				String flow = "��ȡ����" + balance + "Ԫ";
				record(user, flow);
				while (true) {
					System.out.println("1.����ȡ��           2.�˳�");
					String input = sca.next();
					if (input.equals("1")) {// �ж�ȡ��֮��Ĳ���
						break;

					} else if (input.equals("2")) {
						Operate operate = new Operate();
						operate.modify(user);
						List list = new List();
						list.execute(user);// ���ز˵�
					} else {
						System.out.println("�������,����������:");
					}
				}
			}
		}

	}

	public void record(User user, String flow) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��HHʱmm��ss��");// ����ִ�е�ʱ��
		ArrayList<String> reco = user.getRecode();
		String str = sdf.format(new Date());
		reco.add(flow + str);
		user.setRecode(reco);

		// oos.write

	}
}
