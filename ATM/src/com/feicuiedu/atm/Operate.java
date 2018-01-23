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
	private String gender = null;
	Scanner sca = new Scanner(System.in);
	HashMap<String, Object> hm = new HashMap<>();

	public void operation() {
		while (true) {
			System.out.println("��ѡ��ҵ��(1.ע��,2.����,3.��ѯ,4.�޸�)");// �������������ѡ���Ӧ��ҵ��
			String input = sca.next();
			if (input.equals("1")) {// �ж��������,ѡ��ҵ��
				register();// ע��
				break;
			} else if (input.equals("2")) {
				cancellation();// ����
				break;
			} else if (input.equals("3")) {
				information();// ��ѯ
				break;
			} else if (input.equals("4")) {
				updateuser();// �޸�
				break;
			} else {
				System.out.println("�������,��������");
			}
		}

	}

	public void register() {// ע��
		// ϵͳ�Զ����� ���� 37+(�������01,�����Ů02)+��ǰʱ��(������ʱ�������)
		System.out.println("����������:");
		String name = sca.next();
		while (true) {
			System.out.println("�������Ա�(1.��     2.Ů)(�뱣֤�������ȷ,֮���޷��޸�)");
			String input = sca.next();
			if (input.equals("1")) {
				gender = "01";
				break;
			} else if (input.equals("2")) {
				gender = "02";
				break;
			} else {
				System.out.println("������������������:");

			}
		}
		while (true) {
			System.out.println("���������֤��(18λ)(�����������ȷ,֮���޷��޸�):");
			cardid = sca.next();// ��������֤��
			if (!cardid.matches("([0-9]{1,18})")) {// �ж��Ƿ����������ʽ,��������������
				System.out.println("���֤���������,ӦΪ18λ����,����������:");
			} else {
				break;
			}
		}
		while (true) {
			System.out.println("������ѧ��(1.Сѧ,2.��ѧ,3.��ѧ,4.����):");// 1���� С�� 2���� ��ѧ
																// 3�����ѧ 4��������
			String input = sca.next();
			if (input.equals("1")) {// ������ѡѧ���Ĳ�ͬ,����Ӧֵ����education
				education = "Сѧ";
				break;
			} else if (input.equals("2")) {
				education = "��ѧ";
				break;
			} else if (input.equals("3")) {
				education = "��ѧ";
				break;
			} else if (input.equals("4")) {
				education = "����";
				break;
			} else {
				System.out.println("�������,����������");
			}
		}
		while (true) {
			System.out.println("�������ͥסַ:");
			address = sca.next();
			if (address.length() > 50) {// �ж�����ĳ����Ƿ����50
				System.out.println("��ַ���Ȳ��ܴ���50,����������");
				address = sca.next();
			} else {
				break;
			}
		}
		double balance = 0.0;// ֱ�ӳ�ʼ�����Ϊ0.0
		while (true) {
			System.out.println("�������ʼ����(����8λ,���ֺ���ĸ����ͬʱ��,����һ����д��ĸ):");
			password = sca.next();
			if (!password.matches("(?![0-9A-Z]+$)(?![0-9a-z]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,}$")) {// ������������ʽ,
				System.out.println("�����������,����������");// ��������
			} else {
				break;// ����,����
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");// ���û�ȡ��ʱ�����ڸ�ʽ
		String time = sdf.format(new Date());// ��ȡϵͳ��ǰʱ��,���������˻�
		String account = "37" + gender + time;// ����Ҫ�Ĳ�����������,����˻�

		User user = new User(account, password, name, gender, cardid, education, address, balance);
		System.out.println(user);
		// ����ע����û�����user
		/*
		 * arrays = new ArrayList<>();//�ü����Ƚ��û��洢���ڴ��� arrays.add(user);
		 */

		System.out.println("�����ɹ�!");
		String key = account + cardid;
		hm.put(key, user);
		while (true) {
			System.out.println("��ѡ��������Ĳ���(1.����ע��,2.���ص�¼):");// ע�����,ѡ��������Ĳ���
			String input = sca.next();

			if (input.equals("1")) {

				register();// ����ע��

			} else if (input.equals("2")) {
				write(hm);
				System.out.println("����ɹ�");
				Land land = new Land();// ��¼����
				land.landWay();// ���ص�¼
			} else {
				System.out.println("�����������������:");
			}
		}

	}

	public void cancellation() {// ����
		System.out.println("������Ҫɾ�����˻�:");
		String account = sca.next();
		System.out.println("���������֤��:");
		String cardid = sca.next();
		String str = account + cardid;// ��������˺ź����֤�����һ���ַ���
		try {
			File file = new File("User" + File.separator + "user.txt");// ����File����,��ȡ�ı�
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));// תΪ������
			HashMap<String, Object> hm = (HashMap<String, Object>) ois.readObject();// ��ȡ�ı��еĶ���
			// System.out.println(hm);
			if (hm.containsKey(str)) {// �жϼ���hm�еļ��Ƿ���������˺ź����֤����ɵļ�,�����,����ִ��
				// Object obj = hm.get(str);
				while (true) {
					System.out.println("ȷ��ɾ����?(1.ȷ��,2.ȡ��)");
					String input = sca.next();
					if (input.equals("1")) {
						hm.remove(str);// ɾ��
						System.out.println("ɾ���ɹ�!");
						ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(hm);// ��ɾ��֮��ļ���,����д���ı�
						ois.close();// �ر���
						break;
					} else if (input.equals("2")) {// ȡ��ɾ��,�˻ع���Ա��¼��Ľ���
						operation();
						break;
					} else {
						System.out.println("�����������������");
					}
				}

			} else {// hm�в�����������˺ź����֤����ɵļ�
				System.out.println("�˺Ų�����,����������");
				cancellation();// ���ط���,��������
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

	public void information() {// ��ѯ�û���Ϣ
		File file = new File("User" + File.separator + "user.txt");// ����File����,��ȡ�ı�
		if (file.equals(null) || file.length() == 0) {// ���󳤶�Ϊ��,˵���ı���û�ж���
			HashMap<String, Object> hashmap = new HashMap<>();
			System.out.println("���û�");
			operation();
		} else {// �ж���
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				HashMap<String, Object> hm = (HashMap<String, Object>) ois.readObject();// ��ȡ����
				System.out.println(hm.values());// ��ʾ�����ж�����Ϣ
				ois.close();
				while (true) {
					System.out.println("(����1.���ز˵�   2.���ص�¼)");
					String str = sca.next();
					if (str.equals("1")) {
						operation();
					} else if (str.equals("2")) {
						Land land = new Land();
						land.landWay();
					} else {
						System.out.println("�������,��������:");
					}
				}
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void updateuser() {// �޸��û���Ϣ

		File file = new File("User" + File.separator + "user.txt");// ����File����,��ȡ�ı�
		if (file == null || file.length() == 0) {// �ı������û�
			System.out.println("���û�");

		} else {// ���û�
			while (true) {
				System.out.println("������Ҫ�޸ĵ��˻�:");
				String account = sca.next();// ������˻�
				try {
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					HashMap<String, Object> hashmap = (HashMap<String, Object>) ois.readObject();
				    System.out.println(hashmap);
					Set<String> keyset = hashmap.keySet();// ��ȡ�����еļ�
					boolean bln = false;// �ȶ���һ��Boolean �ı���Ϊfalse
					for (String key : keyset) {// ������//��������userkey
						if (key.startsWith(account)) {// ��������еļ�����������˺ſ�ͷ��
							Object ob = hashmap.get(key);// ���������ȡ����Ӧ��ֵ
							User temp = (User) ob;// ת����User����
							update(temp, file, hashmap);// ����update����
							bln = true;// �������¸�ֵ
							break;// ����
						}
					}
					if (!bln) {
						System.out.println("�˻�������");
					}
					break;
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

	}

	public void update(User temp, File file, HashMap<String, Object> hashmap) {// ���û���Ϣ������
		while (true) {
			System.out.println("��ѡ��Ҫ�޸ĵ�����:(1.����,2.����,3.ѧ��,4.��ϵ��ַ,5.�˳�)");// �ж�Ҫ�ĵ�����
			String input = sca.next();
			if (input.equals("1")) {
				System.out.println("������������(6λ����):");// ������
				String newpassword = sca.next();
				temp.setPassword(newpassword);// ����User��set����
			} else if (input.equals("2")) {
				System.out.println("�������޸ĺ������:");// �Ŀ�����
				String newname = sca.next();
				temp.setName(newname);// ����set����
			} else if (input.equals("3")) {
				while (true) {
					System.out.println("�������޸ĺ��ѧ��(1.Сѧ,2.��ѧ,3.��ѧ,4.����):");// �޸�ѧ��
					String neweducation = sca.next();
					if (neweducation.equals("1")) {
						education = "Сѧ";
						temp.setEducation(education);// ���÷���
						break;
					} else if (neweducation.equals("2")) {
						education = "��ѧ";
						temp.setEducation(education);
						break;
					} else if (neweducation.equals("3")) {
						education = "��ѧ";
						temp.setEducation(education);
						break;
					} else if (neweducation.equals("4")) {
						education = "����";
						temp.setEducation(education);
						break;
					} else {
						System.out.println("�������,ֻ����1-4,��������");
					}
				}
			} else if (input.equals("4")) {
				System.out.println("�������޸ĺ�ĵ�ַ:");// �޸ĵ�ַ
				String newaddress = sca.next();
				temp.setAddress(newaddress);
			}else if(input.equals("5")){
				Land land = new Land();
				land.landWay();
			}
			else {
				System.out.println("�������,��������");
			}
			
			System.out.println("1.ȷ��     2.�����޸�");
			String str = sca.next();
			if (str.equals("1")) {
				String account = temp.getAccount();
				String userCard = temp.getCardid();
				String accountCard = account + userCard;
				hashmap.put(accountCard, temp);
				try {
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(hashmap);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (str.equals("2")) {

			} else {
				System.out.println("�������,����������");
			}
		}
	}

	public void write(HashMap<String, Object> hm) {// ����ע���ͳһд��,����ע��ʱ������˺ź����֤��,����ע��ʱ���ɵ��û�
		File file = new File("User" + File.separator + "user.txt");// ����File����,��ȡ�ı�
		if (file == null || file.equals("") || file.length() == 0) {
			HashMap<String, Object> hashmap = new HashMap<>();
			ObjectOutputStream oos;
			try {
				oos = new ObjectOutputStream(new FileOutputStream(file));
				hashmap.putAll(hm);
				oos.writeObject(hashmap);
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				HashMap<String, Object> hashmap = (HashMap<String, Object>) ois.readObject();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
				hashmap.putAll(hm);
				oos.writeObject(hashmap);
				ois.close();
				oos.close();
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// ��ͨ�û���.ȡ.ת������
	public void modify(User user) {
		File file = new File("User" + File.separator + "user.txt");// �Ȼ�ȡ�ı�

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			HashMap<String, Object> hm = (HashMap<String, Object>) ois.readObject();
			// ��ȡΪ����
			hm.put(user.getAccount() + user.getCardid(), user);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(hm);// ���ı���д���������
			System.out.println("����ɹ�!");
			ois.close();// �ر���
			oos.close();// �ر���
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // �ȶ�ȡ

	}
}
