package qldb;

public class Player extends Person {
	private static int nextId = 1;
	public int id;
	public int banThang;
	public int soTran;
	public float quy;

	public Player(int id, String hoTen, int tuoi, String gioiTinh, String diaChi, int banThang, int soTran, float quy) {
		super(hoTen, tuoi, gioiTinh, diaChi);
		this.id = nextId++;
		this.banThang = banThang;
		this.soTran = soTran;
		this.quy = quy;
	}

	public int getId() {
		return id;
	}

	public int getBanThang() {
		return banThang;
	}

	public void setBanThang(int banThang) {
		this.banThang = banThang;
	}

	public int getSoTran() {
		return soTran;
	}

	public void setSoTran(int soTran) {
		this.soTran = soTran;
	}

	public float getQuy() {
		return quy;
	}

	public void setQuy(float quy) {
		this.quy = quy;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ",hoTen=" + hoTen + ",tuoi=" + tuoi + ",gioiTinh=" + gioiTinh + ", diaChi=" + diaChi
				+ " banThang=" + banThang + ", soTran=" + soTran + ", quy=" + quy + ",]";
	}

}
