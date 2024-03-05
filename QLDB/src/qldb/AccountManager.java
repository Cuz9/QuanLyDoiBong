package qldb;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountManager {
	private List<Account> accounts;
	private File file;
	Scanner scanner = new Scanner(System.in);

	public AccountManager() {
		String path = "C:\\Users\\ACER\\eclipse-workspace\\QLDB\\src\\qldb\\";
		File file = new File(path + "account.txt");
		this.accounts = readAccountsFromFile();
	}

	private List<Account> readAccountsFromFile() {
		List<Account> accountList = new ArrayList<>();
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] data = line.split(",");
				String taiKhoan = data[0];
				String matKhau = data[1];
				accountList.add(new Account(taiKhoan, matKhau));
			}
		} catch (IOException e) {
			System.err.println("Lỗi đọc tài khoản từ tệp: " + e.getMessage());
		}
		return accountList;
	}

	public boolean dangKy() {
		System.out.println("Nhập tên tài khoản để đăng ký: ");
		String taiKhoan = scanner.nextLine();
		System.out.println("Nhập mật khẩu để đăng ký: ");
		String matKhau = scanner.nextLine();
		for (Account account : accounts) {
			if (account.getTaiKhoan().equals(taiKhoan)) {
				System.out.println("Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác.");
				return false;
			}
		}
		accounts.add(new Account(taiKhoan, matKhau));
		luuTaiKhoanVaoTep();
		System.out.println("Đăng ký thành công.");
		return true;
	}

	public boolean dangNhap() {
		System.out.println("Nhập tên tài khoản để đăng nhập: ");
		String taiKhoan = scanner.nextLine();
		System.out.println("Nhập mật khẩu để đăng nhập: ");
		String matKhau = scanner.nextLine();
		for (Account account : accounts) {
			if (account.getTaiKhoan().equals(taiKhoan) && account.getMatKhau().equals(matKhau)) {
				System.out.println("Đăng nhập thành công.");
				return true;
			}
		}
		System.out.println("Tên đăng nhập hoặc mật khẩu không đúng. Vui lòng thử lại.");
		return false;
	}

	private void luuTaiKhoanVaoTep() {
		try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
			for (Account account : accounts) {
				writer.println(account.getTaiKhoan() + "," + account.getMatKhau());
			}
		} catch (IOException e) {
			System.err.println("Lỗi lưu tài khoản vào tệp: " + e.getMessage());
		}
	}
}
