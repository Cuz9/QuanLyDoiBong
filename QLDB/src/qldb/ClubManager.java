package qldb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ClubManager {
	List<Player> players;
	private static int nextId = 1;
	private Scanner scanner;

	public ClubManager() {
		this.scanner = new Scanner(System.in);
		String path = "C:\\Users\\ACER\\eclipse-workspace\\QLDB\\src\\qldb\\";
		File file = new File(path + "demo.txt");
		this.players = read(file);
	}

	private void writePlayersToFile(File file) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
			for (Player player : players) {
				writer.println(player.id + "," + player.getHoTen() + "," + player.getTuoi() + "," + player.getGioiTinh()
						+ "," + player.getDiaChi() + "," + player.getBanThang() + "," + player.getSoTran() + ","
						+ player.getQuy());
			}
			System.out.println("Danh sách cầu thủ đã được ghi lại vào tệp " + file);
		} catch (IOException e) {
			System.out.println("Đã xảy ra lỗi khi ghi vào tệp " + file);
			e.printStackTrace();
		}
	}

	public static List<Player> read(File file) {
		List<Player> players = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length == 8) {
					int id = Integer.parseInt(data[0]);
					String hoTen = data[1];
					int tuoi = Integer.parseInt(data[2]);
					String gioiTinh = data[3];
					String diaChi = data[4];
					int banThang = Integer.parseInt(data[5]);
					int soTran = Integer.parseInt(data[6]);
					float quy = Float.parseFloat(data[7]);
					players.add(new Player(id, hoTen, tuoi, gioiTinh, diaChi, banThang, soTran, quy));
					nextId = id + 1;
				}
			}
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return players;
	}

	private static final String[] availableAddresses = { "Xom 1", "Xom 2", "Xom 3" };

	public void addPlayer(File file) {
		System.out.println("Nhập tên cầu thủ:");
		String hoTen = scanner.nextLine();

		System.out.println("Nhập tuổi:");
		int tuoi = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Nhập giới tính:");
		String gioiTinh = scanner.nextLine();

		System.out.println("Chọn địa chỉ từ danh sách sau:");
		for (int i = 0; i < availableAddresses.length; i++) {
			System.out.println((i + 1) + ". " + availableAddresses[i]);
		}
		int diaChiIndex = scanner.nextInt();
		scanner.nextLine();
		String diaChi = availableAddresses[diaChiIndex - 1];

		System.out.println("Nhập số bàn thắng:");
		int banThang = scanner.nextInt();

		System.out.println("Nhập số trận tham gia:");
		int soTran = scanner.nextInt();

		System.out.println("Nhập số quỹ đã đóng:");
		float quy = scanner.nextFloat();
		scanner.nextLine();

		Player player = new Player(nextId, hoTen, tuoi, gioiTinh, diaChi, banThang, soTran, quy);
		players.add(player);
		writePlayersToFile(file);
	}

	public void editPlayer(int id, File file) {
		boolean found = false;
		for (Player player : players) {
			if (player.id == id) {
				System.out.println("Nhập thông tin cầu thủ để sửa:");
				System.out.println("Nhập tên cầu thủ:");
				String hoTen = scanner.nextLine();

				System.out.println("Nhập tuổi:");
				int tuoi = scanner.nextInt();
				scanner.nextLine();

				System.out.println("Nhập giới tính:");
				String gioiTinh = scanner.nextLine();

				System.out.println("Chọn địa chỉ từ danh sách sau:");
				for (int i = 0; i < availableAddresses.length; i++) {
					System.out.println((i + 1) + ". " + availableAddresses[i]);
				}
				int diaChiIndex = scanner.nextInt();
				scanner.nextLine();
				String diaChi = availableAddresses[diaChiIndex - 1];

				System.out.println("Nhập số bàn thắng:");
				int banThang = scanner.nextInt();

				System.out.println("Nhập số trận tham gia:");
				int soTran = scanner.nextInt();

				System.out.println("Nhập số quỹ đã đóng:");
				float quy = scanner.nextFloat();
				scanner.nextLine();

				player.setHoTen(hoTen);
				player.setTuoi(tuoi);
				player.setGioiTinh(gioiTinh);
				player.setDiaChi(diaChi);
				player.setBanThang(banThang);
				player.setSoTran(soTran);
				player.setQuy(quy);

				System.out.println("Thông tin của cầu thủ đã được cập nhật.");
				writePlayersToFile(file);
				found = true;
				break;
			}
		}
		if (!found) {
			System.out.println("Không tìm thấy cầu thủ với ID " + id);
		}
	}

	public void deletePlayer(File file) {
		System.out.println("Nhập ID của cầu thủ mà bạn muốn xóa:");
		int idToDelete = scanner.nextInt();
		scanner.nextLine();

		boolean found = false;
		for (Player player : players) {
			if (player.getId() == idToDelete) {
				found = true;
				players.remove(player);
				System.out.println("Cầu thủ với ID " + idToDelete + " đã được xóa.");
				break;
			}
		}

		if (!found) {
			System.out.println("Không tìm thấy cầu thủ với ID " + idToDelete + ".");
		}

		writePlayersToFile(file);
	}

	public void displayAllPlayers() {
		if (players.isEmpty()) {
			System.out.println("Danh sách cầu thủ trống.");
		} else {
			System.out.println("Danh sách cầu thủ:");
			for (Player player : players) {
				System.out.println(player.toString());
			}
		}
	}

	public void sortByGoals() {
		List<Player> sortedPlayers = new ArrayList<>(players);
		Collections.sort(sortedPlayers, new Comparator<Player>() {
			@Override
			public int compare(Player p1, Player p2) {
				return Integer.compare(p2.getBanThang(), p1.getBanThang());
			}
		});
		System.out.println("Danh sách cầu thủ sau khi sắp xếp theo số bàn thắng:");
		for (Player player : sortedPlayers) {
			System.out.println(player.toString());
		}
	}

	public void sortByMatches() {
		List<Player> sortedPlayers = new ArrayList<>(players);
		Collections.sort(sortedPlayers, new Comparator<Player>() {
			@Override
			public int compare(Player p1, Player p2) {
				return Integer.compare(p2.getSoTran(), p1.getSoTran());
			}
		});
		System.out.println("Danh sách cầu thủ sau khi sắp xếp theo số trận tham gia:");
		for (Player player : sortedPlayers) {
			System.out.println(player.toString());
		}
	}

	public void sort() {
		System.out.println("Ban muon sap xep theo: 1: So tran dau; 2: So ban thang:");
		int choice1 = scanner.nextInt();
		scanner.nextLine();
		if (choice1 == 1) {
			sortByMatches();
		} else if (choice1 == 2) {
			sortByGoals();
		}
	}

	public void searchPlayerByName() {
		System.out.println("Nhập tên cầu thủ cần tìm:");
		String partialName = scanner.nextLine();
		List<Player> result = new ArrayList<>();
		for (Player player : players) {
			if (player.getHoTen().toLowerCase().contains(partialName.toLowerCase())) {
				result.add(player);
			}
		}
		for (Player player : result) {
			System.out.println(player.toString());
		}
	}

	public void searchAndDisplayPlayersByAddress(String address) {
		boolean foundPlayers = false;
		for (Player player : players) {
			if (player.getDiaChi().equalsIgnoreCase(address)) {
				if (!foundPlayers) {
					System.out.println("Danh sách cầu thủ tại địa chỉ " + address + ":");
					foundPlayers = true;
				}
				System.out.println(player.toString());
			}
		}
		if (!foundPlayers) {
			System.out.println("Không có cầu thủ nào tại địa chỉ " + address);
		}
	}

	public void searchByAddress() {
		System.out.println("Chọn một địa chỉ để hiển thị danh sách cầu thủ:");
		System.out.println("1. Xom 1");
		System.out.println("2. Xom 2");
		System.out.println("3. Xom 3");
		System.out.print("Nhập lựa chọn của bạn: ");

		int choice2 = scanner.nextInt();
		scanner.nextLine();

		switch (choice2) {
		case 1:
			searchAndDisplayPlayersByAddress("Xom 1");
			break;
		case 2:
			searchAndDisplayPlayersByAddress("Xom 2");
			break;
		case 3:
			searchAndDisplayPlayersByAddress("Xom 3");
			break;
		default:
			System.out.println("Lựa chọn không hợp lệ.");
		}
	}

	public void displayPlayerWithQuyPaid() {
		System.out.println("Danh sách cầu thủ đã đóng quỹ:");
		boolean found1 = false;
		for (Player player : players) {
			if (player.getQuy() > 0) {
				found1 = true;
				System.out.println(player.toString());
			}
		}
		if (!found1) {
			System.out.println("Không có cầu thủ nào đã đóng quỹ.");
		}
		System.out.println("Danh sách cầu thủ chưa đóng quỹ:");
		boolean found2 = false;
		for (Player player : players) {
			if (player.getQuy() == 0) {
				found2 = true;
				System.out.println(player.toString());
			}
		}
		if (!found2) {
			System.out.println("Không có cầu thủ nào chưa đóng quỹ.");
		}
	}

	public void calculateTotalQuy() {
		float totalQuy = 0;
		for (Player player : players) {
			totalQuy += player.getQuy();
		}
		System.out.println("Tổng quỹ của đội: " + totalQuy);
	}

	public void calculateTotalGoals() {
		int totalGoals = 0;
		for (Player player : players) {
			totalGoals += player.getBanThang();
		}
		System.out.println("Tổng bàn thắng của đội: " + totalGoals);
	}

}
