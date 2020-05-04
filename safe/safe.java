package safe;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class safe {
	String id;
	String name;
	int log_in() {
		date d = new date();
		Scanner sc = new Scanner(System.in);  
		System.out.print("��¼�˺�: ");
		id = sc.next();
		System.out.print("����: ");
		name = sc.next();
		for(int i = 0; i < d.Student.length; i++) {
			if(id.equals(d.administrator_id) && name.equals(d.administrator_key))
				return -1;
			if(d.Student[i].equals(id) && d.name[i].equals(name))  //ע���ַ����е�
				return 1;
		}
		return 0;
	}
	void police() {          //��������
		System.out.println("УҽԺ�绰:028-83202481\n�������绰:028-83200110");
	}
	void vocation_leave() {   //������У
		Date dNow = new Date( );
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		System.out.println("�Ƿ���У(yes/no)");
		String leave = sc.nextLine();
		try {
			FileWriter fw = new FileWriter("d:\\vocation_leave.txt", true);
			if(leave.equals("no")) {
				fw.write(id + " " + name + " ��У");
				fw.write("\r\n"); 
				System.out.println("�Ǽǳɹ�");
				fw.flush();
				fw.close();
			}
			else {
				System.out.println("��Уʱ��: ");
				String leave_time = sc.nextLine();
				System.out.println("��Уʱ��: ");
				String back_time = sc.nextLine();
				fw.write(id + " " + name + " " + leave_time + " " + back_time + "\n");
				System.out.println("�Ǽǳɹ�");
				fw.flush();
				fw.close();
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	void sign_in() {       //ÿ��ǩ��
		Date dNow = new Date( );
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		try {
			FileWriter fw = new FileWriter("d:\\sign_in.txt", true);
			fw.write(id + " " + name + " " + ft.format(dNow) + "\n");
			System.out.println(name + "ǩ���ɹ�, ǩ��ʱ��" + ft.format(dNow));
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	void print_sign_in() {    //�����ѯ���ڣ���ӡǩ�����˺�δǩ������
		Scanner sc = new Scanner(System.in);
		date d = new date();
		System.out.println("������Ҫ��ѯ������(yyyy-mm-dd)");
		String date = sc.nextLine();
		int[] flag = new int[d.Student.length];
		try {
			BufferedReader br = new BufferedReader(new FileReader("d:\\sign_in.txt"));
			String line;
			while((line=br.readLine())!=null) {
				if(line.indexOf(date)!=-1) {
					System.out.println(line);
					for(int i = 0; i < d.Student.length; i++) {
						if(line.indexOf(d.Student[i])!=-1)
							flag[i] = 1;
					}
				}
			}
			String sign_in = "";
			String un_sign_in = "";
			for(int i = 0; i < d.Student.length; i++) {
				if(flag[i] == 1) 
					sign_in = sign_in + d.name[i] + " ";
				else
					un_sign_in = un_sign_in + d.name[i] + " ";
			}
			System.out.println(date + "ǩ������: " + sign_in);
			System.out.println(date + "δǩ������: " + un_sign_in);
			br.close();
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	void print_vocation_leave() {     
		try {
			BufferedReader br = new BufferedReader(new FileReader("d:\\vocation_leave.txt"));
			String line;
			while((line=br.readLine())!=null) {
				System.out.println(line);
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);  
		safe sa = new safe();
		int log = sa.log_in();
		if(log == 0) {
			System.out.println("��¼ʧ�ܣ�");
			return;
		}
		System.out.println(sa.name + "��¼�ɹ�");
		if(log == 1) {       //�û���ͨ����
			for(;;) {
				System.out.println("��������ѡ����\n1:��������\n2:���ڵǼ�\n3:ÿ��ǩ��\n4:�˳�");
				int choice = sc.nextInt();
				switch(choice) {
					case 1:
						sa.police();
						break;
					case 2:
						sa.vocation_leave();
						break;
					case 3:
						sa.sign_in();
						break;
					case 4:
						System.out.println(sa.name + "�Ѿ��ɹ��˳�");
						return;
					default:
						break;
				}
			}
		}
		if(log == -1) {    //ϵͳ����Ա����
			for(;;) {
				System.out.println("��������ѡ����\n1:��ӡ���ڵǼ�\n2:��ӡǩ�����\n3:�˳�");
				int choice = sc.nextInt();
				switch(choice) {
					case 1:
						sa.print_vocation_leave();
						break;
					case 2:
						sa.print_sign_in();
						break;
					case 3:
						System.out.println(sa.name + "�Ѿ��ɹ��˳�");
						return;
					default:
						break;
				}
			}
		}
	}
}
