package com.example.shrimpfarm;

//import com.example.shrimpfarm.ui.home.MainAc;

import java.io.Serializable;

public class UserAttribute implements Serializable {

  private String userID;
  private String nama;
  private String email;
  private int phone;
  private String address;
  private String kota;
  private String provinsi;
  private String zipcode;
  private String password;
  private String kategori;

  public UserAttribute(String userID, String nama, String email, int phone, String address, String kota, String provinsi, String zipcode, String password, String kategori) {
	this.userID = userID;
	this.nama = nama;
	this.email = email;
	this.phone = phone;
	this.address = address;
	this.kota = kota;
	this.provinsi = provinsi;
	this.zipcode = zipcode;
	this.password = password;
	this.kategori = kategori;
  }

  public String getUserID() {
	return userID;
  }

  public void setUserID(String userID) {
	this.userID = userID;
  }

  public String getNama() {
	return nama;
  }

  public void setNama(String nama) {
	this.nama = nama;
  }

  public String getEmail() {
	return email;
  }

  public void setEmail(String email) {
	this.email = email;
  }

  public int getPhone() {
	return phone;
  }

  public void setPhone(int phone) {
	this.phone = phone;
  }

  public String getaddress() {
	return address;
  }

  public void setaddress(String address) {
	this.address = address;
  }

  public String getKota() {
	return kota;
  }

  public void setKota(String kota) {
	this.kota = kota;
  }

  public String getProvinsi() {
	return provinsi;
  }

  public void setProvinsi(String provinsi) {
	this.provinsi = provinsi;
  }

  public String getZipcode() {
	return zipcode;
  }

  public void setZipcode(String zipcode) {
	this.zipcode = zipcode;
  }

  public String getPassword() {
	return password;
  }

  public void setPassword(String password) {
	this.password = password;
  }

  public String getKategori() {
	return kategori;
  }

  public void setKategori(String kategori) {
	this.kategori = kategori;
  }

//  public void observe(HomeActivity homeActivity, Object o) {
//  }
}

