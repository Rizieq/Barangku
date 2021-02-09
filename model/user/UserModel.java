package com.rizieq.barangku.model.user;


import com.google.gson.annotations.SerializedName;


public class UserModel {

	@SerializedName("password")
	private String password;

	@SerializedName("foto_signup")
	private String fotoSignup;

	@SerializedName("nama")
	private String nama;

	@SerializedName("authenfikasi")
	private String authenfikasi;

	@SerializedName("no_handphone")
	private String noHandphone;

	@SerializedName("id")
	private String id;

	@SerializedName("jenis_kelamin")
	private String jenisKelamin;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	@SerializedName("alamat")
	private String alamat;



	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setFotoSignup(String fotoSignup){
		this.fotoSignup = fotoSignup;
	}

	public String getFotoSignup(){
		return fotoSignup;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setAuthenfikasi(String authenfikasi){
		this.authenfikasi = authenfikasi;
	}

	public String getAuthenfikasi(){
		return authenfikasi;
	}

	public void setNoHandphone(String noHandphone){
		this.noHandphone = noHandphone;
	}

	public String getNoHandphone(){
		return noHandphone;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setJenisKelamin(String jenisKelamin){
		this.jenisKelamin = jenisKelamin;
	}

	public String getJenisKelamin(){
		return jenisKelamin;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}
}