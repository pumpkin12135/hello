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
		System.out.print("登录账号: ");
		id = sc.next();
		System.out.print("姓名: ");
		name = sc.next();
		for(int i = 0; i < d.Student.length; i++) {
			if(id.equals(d.administrator_id) && name.equals(d.administrator_key))
				return -1;
			if(d.Student[i].equals(id) && d.name[i].equals(name))  //注意字符串判等
				return 1;
		}
		return 0;
	}
	void police() {          //紧急报警
		System.out.println("校医院电话:028-83202481\n保卫处电话:028-83200110");
	}
	void vocation_leave() {   //假期离校
		Date dNow = new Date( );
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		System.out.println("是否离校(yes/no)");
		String leave = sc.nextLine();
		try {
			FileWriter fw = new FileWriter("d:\\vocation_leave.txt", true);
			if(leave.equals("no")) {
				fw.write(id + " " + name + " 留校");
				fw.write("\r\n"); 
				System.out.println("登记成功");
				fw.flush();
				fw.close();
			}
			else {
				System.out.println("离校时间: ");
				String leave_time = sc.nextLine();
				System.out.println("回校时间: ");
				String back_time = sc.nextLine();
				fw.write(id + " " + name + " " + leave_time + " " + back_time + "\n");
				System.out.println("登记成功");
				fw.flush();
				fw.close();
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	void sign_in() {       //每日签到
		Date dNow = new Date( );
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		try {
			FileWriter fw = new FileWriter("d:\\sign_in.txt", true);
			fw.write(id + " " + name + " " + ft.format(dNow) + "\n");
			System.out.println(name + "签到成功, 签到时间" + ft.format(dNow));
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	void print_sign_in() {    //输入查询日期，打印签到的人和未签到的人
		Scanner sc = new Scanner(System.in);
		date d = new date();
		System.out.println("请输入要查询的日期(yyyy-mm-dd)");
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
			System.out.println(date + "签到的人: " + sign_in);
			System.out.println(date + "未签到的人: " + un_sign_in);
			br.close();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
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
			// TODO 自动生成的 catch 块
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
			System.out.println("登录失败！");
			return;
		}
		System.out.println(sa.name + "登录成功");
		if(log == 1) {       //用户普通功能
			for(;;) {
				System.out.println("输入数字选择功能\n1:紧急报警\n2:假期登记\n3:每日签到\n4:退出");
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
						System.out.println(sa.name + "已经成功退出");
						return;
					default:
						break;
				}
			}
		}
		if(log == -1) {    //系统管理员功能
			for(;;) {
				System.out.println("输入数字选择功能\n1:打印假期登记\n2:打印签到情况\n3:退出");
				int choice = sc.nextInt();
				switch(choice) {
					case 1:
						sa.print_vocation_leave();
						break;
					case 2:
						sa.print_sign_in();
						break;
					case 3:
						System.out.println(sa.name + "已经成功退出");
						return;
					default:
						break;
				}
			}
		}
	}
}
