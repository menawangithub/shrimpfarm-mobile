package com.example.shrimpfarm.ui.rencanapanen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shrimpfarm.R;
import com.example.shrimpfarm.UserAttribute;
import com.mongodb.lang.Nullable;

import com.example.shrimpfarm.R;
import com.example.shrimpfarm.UserAttribute;

import java.util.HashMap;
import java.util.Map;

public class TambahRencanaActivity extends AppCompatActivity {

  EditText etJenisPanen, etPerkiraanPanen, etUkuranPanen, etTonasePanen, etHargaHarapan, etUsiaBudidaya, etLokasiBudidaya, etCustomId;

  Button btnSubmit;

  String jPanen, pPanen, uPanen, tPanen, hHarapan, uBudidaya, lBudidaya, cId, uId;

  UserAttribute userAttribute;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_tambah_rencana);

	if (getSupportActionBar() != null) {
	  getSupportActionBar().hide();
	}

	userAttribute = (UserAttribute) getIntent().getSerializableExtra("userAttribute");
	etJenisPanen = findViewById(R.id.etJenisPanen);
	etPerkiraanPanen = findViewById(R.id.etPerkiraanPanen);
	etUkuranPanen = findViewById(R.id.etUkuranPanen);
	etTonasePanen = findViewById(R.id.etTonasePanen);
	etHargaHarapan = findViewById(R.id.etHargaHarapan);
	etUsiaBudidaya = findViewById(R.id.etUsiaBudidaya);
	etLokasiBudidaya = findViewById(R.id.etLokasiBudidaya);
	etCustomId = findViewById(R.id.etCustomId);

	btnSubmit = findViewById(R.id.buttonSubmitRencana);
	btnSubmit.setOnClickListener(new View.OnClickListener() {
	  @Override
	  public void onClick(View v) {
		getInputData();

		Intent i = new Intent(TambahRencanaActivity.this, RencanaActivity.class);
		i.putExtra("userAttribute", userAttribute);
		startActivity(i);
	  }
	});
	Button btnKembali = findViewById(R.id.buttonKembaliRencana);
	btnKembali.setOnClickListener(new View.OnClickListener() {
	  @Override
	  public void onClick(View v) {
		Intent i = new Intent(TambahRencanaActivity.this, RencanaActivity.class);
		i.putExtra("userAttribute",userAttribute);
		startActivity(i);
	  }
	});
  }

  private void getInputData (){
	jPanen = etJenisPanen.getText().toString();
	pPanen = etPerkiraanPanen.getText().toString();
	uPanen = etJenisPanen.getText().toString();
	tPanen = etTonasePanen.getText().toString();
	hHarapan = etHargaHarapan.getText().toString();
	uBudidaya = etUsiaBudidaya.getText().toString();
	lBudidaya = etLokasiBudidaya.getText().toString();
	cId = etCustomId.getText().toString();
	uId = userAttribute.getUserID();

	Toast.makeText(TambahRencanaActivity.this, jPanen + pPanen + uPanen + tPanen + uBudidaya + lBudidaya + cId, Toast.LENGTH_SHORT).show();
	submitNewItem();
  }

  private void submitNewItem() {
	String urlEndpoint = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-rugju/endpoint/insertPanen";

	RequestQueue requestQueue = Volley.newRequestQueue(this);
	StringRequest stringRequest = new StringRequest(Request.Method.POST, urlEndpoint, new Response.Listener<String>() {
	  @Override
	  public void onResponse(String response) {
		Toast.makeText(TambahRencanaActivity.this, "Berhasil Membuat Rencana", Toast.LENGTH_SHORT).show();
	  }
	}, new Response.ErrorListener() {
	  @Override
	  public void onErrorResponse(VolleyError error) {
		Toast.makeText(TambahRencanaActivity.this, "Gagal Membuat Rencana", Toast.LENGTH_SHORT).show();
	  }
	}){
	  @Nullable
	  @Override
	  protected Map<String, String> getParams() throws  AuthFailureError {
		Map map = new HashMap<>();
		map.put("jenis_panen", jPanen);
		map.put("perkiraan_panen", pPanen);
		map.put("ukuran_panen", uPanen);
		map.put("tonase_panen", tPanen);
		map.put("usia_budidaya", uBudidaya);
		map.put("harga_harapan", hHarapan);
		map.put("lokasi_budidaya", lBudidaya);
		map.put("custom_id", cId);
		map.put("user_id", uId);
		return map;
	  }
	};
	requestQueue.add(stringRequest);
  }
}