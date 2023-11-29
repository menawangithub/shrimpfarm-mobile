package com.example.shrimpfarm.ui.infotambak;

public class Tambak {
  String id, namaTambak, customId;
  int luasKolam, jumlahRuas;

  public Tambak(String id, String namaTambak, int luasKolam, int jumlahRuas, String customId) {
	this.id = id;
	this.namaTambak = namaTambak;
	this.luasKolam = luasKolam;
	this.jumlahRuas = jumlahRuas;
	this.customId = customId;
  }

  public String getId() {
	return id;
  }

  public String getNamaTambak() {
	return namaTambak;
  }

  public String getCustomId() {
	return customId;
  }

  public int getLuasKolam() {
	return luasKolam;
  }

  public int getJumlahRuas() {
	return jumlahRuas;
  }

  public void setId(String id) {
	this.id = id;
  }

  public void setNamaTambak(String namaTambak) {
	this.namaTambak = namaTambak;
  }

  public void setCustomId(String customId) {
	this.customId = customId;
  }

  public void setLuasKolam(int luasKolam) {
	this.luasKolam = luasKolam;
  }

  public void setJumlahRuas(int jumlahRuas) {
	this.jumlahRuas = jumlahRuas;
  }
}
