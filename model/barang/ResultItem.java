package com.rizieq.barangku.model.barang;


import com.google.gson.annotations.SerializedName;


public class ResultItem {

	@SerializedName("kondisi")
	private String kondisi;

	@SerializedName("id_tukar")
	private String idTukar;

	@SerializedName("gambar_barang")
	private String gambarBarang;

	@SerializedName("nama_barang")
	private String namaBarang;

	@SerializedName("harapan_barang")
	private String harapanBarang;

	@SerializedName("desc_barang")
	private String descBarang;

	@SerializedName("status")
	private String status;

	@SerializedName("status_harapan")
	private String status_harapan;

	@SerializedName("respon_kurir")
	private String respon_kurir;

	@SerializedName("alamat")
	private String alamat;

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getStatusHarapan() {
		return status_harapan;
	}

	public void setStatusHarapan(String status_harapan) {
		this.status_harapan = status_harapan;
	}

	public String getResponKurir() {
		return respon_kurir;
	}

	public void setResponKurir(String respon_kurir) {
		this.respon_kurir = respon_kurir;
	}

	public void setKondisi(String kondisi) {
		this.kondisi = kondisi;
	}

	public String getKondisi() {
		return kondisi;
	}

	public void setIdTukar(String idTukar) {
		this.idTukar = idTukar;
	}

	public String getIdTukar() {
		return idTukar;
	}

	public void setGambarBarang(String gambarBarang) {
		this.gambarBarang = gambarBarang;
	}

	public String getGambarBarang() {
		return gambarBarang;
	}

	public void setNamaBarang(String namaBarang) {
		this.namaBarang = namaBarang;
	}

	public String getNamaBarang() {
		return namaBarang;
	}

	public void setHarapanBarang(String harapanBarang) {
		this.harapanBarang = harapanBarang;
	}

	public String getHarapanBarang() {
		return harapanBarang;
	}

	public void setDescBarang(String descBarang) {
		this.descBarang = descBarang;
	}

	public String getDescBarang() {
		return descBarang;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}


}