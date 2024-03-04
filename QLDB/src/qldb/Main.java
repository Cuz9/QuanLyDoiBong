package qldb;

import java.io.File;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		ClubManager clubManager = new ClubManager();
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;
		String path = "C:\\Users\\ACER\\eclipse-workspace\\QLDB\\src\\qldb\\";
		File file = new File(path + "demo.txt");
		while (!exit) {
			System.out.println("1. Add Player");
			System.out.println("2. Edit Player");
			System.out.println("3. Delete Player");
			System.out.println("4. Display All Players");
			System.out.println("5. Sort Players");
			System.out.println("6. Search Player");
			System.out.println("7. Display Players by Address");
			System.out.println("8. Display Players by Quota Status");
			System.out.println("9. Calculate Total Quota");
			System.out.println("10. Calculate Total Goals");
			System.out.println("11. Exit");
			System.out.println("Enter your choice:");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline character

			switch (choice) {
			case 1:
				clubManager.addPlayer(file);
				break;
			case 2:
				System.out.println("Nhap id cau thu can sua: ");
				int idset = scanner.nextInt();
				clubManager.editPlayer(idset, file);
				break;
			case 3:
				clubManager.deletePlayer(file);
				break;
			case 4:
				clubManager.displayAllPlayers();
				break;
			case 5:
				clubManager.sort();
				break;
			case 6:
				clubManager.searchPlayerByName();
				break;
			case 7:
				clubManager.searchByAddress();
				break;
			case 8:
				clubManager.displayPlayerWithQuyPaid();
				break;
			case 9:
				clubManager.calculateTotalQuy();
				break;
			case 10:
				clubManager.calculateTotalGoals();
				break;
			case 11:
				exit = true;
				break;
			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 11.");
			}
		}
		scanner.close();
	}
}
