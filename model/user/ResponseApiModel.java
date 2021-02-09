package com.rizieq.barangku.model.user;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ResponseApiModel {

	@SerializedName("result")
	private List<UserModel> result;

	@SerializedName("kode")
	private String kode;

	@SerializedName("pesan")
	private String pesan;

	public String getPesan() {
		return pesan;
	}

	public void setPesan(String pesan) {
		this.pesan = pesan;
	}

	public void setResult(List<UserModel> result){
		this.result = result;
	}

	public List<UserModel> getResult(){
		return result;
	}

	public void setKode(String kode){
		this.kode = kode;
	}

	public String getKode(){
		return kode;
	}
}