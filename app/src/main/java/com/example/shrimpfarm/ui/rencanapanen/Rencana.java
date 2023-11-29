package com.example.shrimpfarm.ui.rencanapanen;

public class Rencana {

  String id, jenisPanen, perkiraanPanen, ukuranPanen, tonasePanen, usiaBudidaya, hargaHarapan, lokasiBudidaya, customId;
  public Rencana(String id, String jenisPanen, String perkiraanPanen, String ukuranPanen, String tonasePanen, String usiaBudidaya, String hargaHarapan, String lokasiBudidaya, String customId) {
	this.id = id;
	this.jenisPanen = jenisPanen;
	this.perkiraanPanen = perkiraanPanen;
	this.ukuranPanen = ukuranPanen;
	this.tonasePanen = tonasePanen;
	this.usiaBudidaya = usiaBudidaya;
	this.hargaHarapan = hargaHarapan;
	this.lokasiBudidaya = lokasiBudidaya;
	this.customId = customId;
  }

  public String getId() {
	return id;
  }

  public String getJenisPanen() {
	return jenisPanen;
  }

  public String getPerkiraanPanen() {
	return perkiraanPanen;
  }

  public String getUkuranPanen() {
	return ukuranPanen;
  }

  public String getTonasePanen() {
	return tonasePanen;
  }

  public String getUsiaBudidaya() {
	return usiaBudidaya;
  }

  public String getHargaHarapan() {
	return hargaHarapan;
  }

  public String getLokasiBudidaya() {
	return lokasiBudidaya;
  }

  public String getCustomId() {
	return customId;
  }
}
