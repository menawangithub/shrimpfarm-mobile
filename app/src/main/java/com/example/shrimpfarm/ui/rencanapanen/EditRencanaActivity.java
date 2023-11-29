package com.example.shrimpfarm.ui.rencanapanen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shrimpfarm.R;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.shrimpfarm.R;
import com.example.shrimpfarm.ui.infotambak.EditTambakActivity;
import com.example.shrimpfarm.ui.infotambak.TambakActivity;

public class EditRencanaActivity extends AppCompatActivity {

  private EditText etxtJenisPanen, etxtPerkiraanPanen, etxtUkuranPanen, etxtTonasePanen, etxtHargaHarapan, etxtUsiaBudidaya, etxtLokasiBudidaya, etxtCustomId;

  private Button btnEditRencana;

  private String etJp, etPp, etUp, etTp, etHh, etUb, etLb, etCi;

  private Rencana rencana;
  String rencanaId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_edit_rencana);

	if (getSupportActionBar() != null) {
	  getSupportActionBar().hide();
	}

	initializeUI();

	rencanaId = getIntent().getStringExtra("rencanaId");
	String jenisPanen = getIntent().getStringExtra("jenis_panen");
	String perkiraanPanen = getIntent().getStringExtra("perkiraan_panen");
	String ukuranPanen= getIntent().getStringExtra("ukuran_panen");
	String tonasePanen = getIntent().getStringExtra("tonase_panen");
	String usiaBudidaya = getIntent().getStringExtra("usia_budidaya");
	String hargaHarapan = getIntent().getStringExtra("harga_harapan");
	String lokasiBudidaya = getIntent().getStringExtra("lokasi_budidaya");
	String customId = getIntent().getStringExtra("custom_id");

	etxtJenisPanen.setText(jenisPanen);
	etxtPerkiraanPanen.setText(perkiraanPanen);
	etxtUkuranPanen.setText(ukuranPanen);
	etxtTonasePanen.setText(tonasePanen);
	etxtUsiaBudidaya.setText(usiaBudidaya);
	etxtHargaHarapan.setText(hargaHarapan);
	etxtLokasiBudidaya.setText(lokasiBudidaya);
	etxtCustomId.setText(customId);

	btnEditRencana.setOnClickListener(new View.OnClickListener() {
	  @Override
	  public void onClick(View view) {
		saveItemChanges();
		Intent i = new Intent(EditRencanaActivity.this, TambakActivity.class);
		startActivity(i);
	  }
	});
  }

  private void initializeUI(){
	etxtJenisPanen.findViewById(R.id.etxtJenisPanen);
	etxtPerkiraanPanen.findViewById(R.id.etxtPerkiraanPanen);
	etxtUkuranPanen.findViewById(R.id.etxtUkuranPanen);
	etxtTonasePanen.findViewById(R.id.etxtTonasePanen);
	etxtUsiaBudidaya.findViewById(R.id.etxtUsiaBudidaya);
	etxtHargaHarapan.findViewById(R.id.etxtHargaHarapan);
	etxtLokasiBudidaya.findViewById(R.id.etxtLokasiBudidaya);
	etxtCustomId.findViewById(R.id.etxtCustomId);
  }

  private void saveItemChanges(){
	etJp = etxtJenisPanen.getText().toString();
	etPp = etxtPerkiraanPanen.getText().toString();
	etUp = etxtUkuranPanen.getText().toString();
	etTp = etxtTonasePanen.getText().toString();
	etUb = etxtUsiaBudidaya.getText().toString();
	etHh = etxtHargaHarapan.getText().toString();
	etLb = etxtLokasiBudidaya.getText().toString();
	etCi = etxtCustomId.getText().toString();

	if (etJp.isEmpty() || etPp.isEmpty() || etUp.isEmpty() || etTp.isEmpty() || etUb.isEmpty() || etHh.isEmpty() || etLb.isEmpty() || etCi.isEmpty() ) {
	  Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
	  return;
	}
	updateRencana();
  }

  private void updateRencana() {
	String url = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-rugju/endpoint/insertPanen?=" + rencanaId + "&jenis_panen=" + etJp + "&perkiraan_panen?=" + etPp + "&ukuran_panen?=" + etUp + "&tonase_panen?=" + etTp + "&usia_budidaya?=" + etUb + "&harga_harapan?=" + etHh + "&lokasi_budidaya?=" + etLb + "&custom_id?=" + etCi;
	RequestQueue requestQueue = Volley.newRequestQueue(this);
	StringRequest updateData = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
	  @Override
	  public void onResponse(String response) {
		Toast.makeText(EditRencanaActivity.this, "Rencana berhasil diupdate", Toast.LENGTH_SHORT).show();
	  }
	}, new Response.ErrorListener() {
	  @Override
	  public void onErrorResponse(VolleyError error) {
		Toast.makeText(EditRencanaActivity.this, "Gagal mengubah data", Toast.LENGTH_SHORT).show();
	  }
	});
	requestQueue.add(updateData);
  }
}