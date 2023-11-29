package com.example.shrimpfarm.ui.infotambak;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TambahTambakActivity extends AppCompatActivity {

  EditText editTextKeterangan, editTextLuasKolam, editTextJumlahRuasKolam, editTextCustomId;
  Button buttonSubmit;
  String keterangan, luasK,jmlK,customId,userId;
  UserAttribute userAttribute;
//  private final OkHttpClient client = new OkHttpClient();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_tambah_tambak);

	if (getSupportActionBar() != null) {
	  getSupportActionBar().hide();
	}
	userAttribute = (UserAttribute) getIntent().getSerializableExtra("userAttribute");
	editTextKeterangan = findViewById(R.id.editTextKeterangan);
	editTextLuasKolam = findViewById(R.id.editTextLuasKolam);
	editTextJumlahRuasKolam = findViewById(R.id.editTextJumlahRuasKolam);
	editTextCustomId = findViewById(R.id.editTextCustomId);

	buttonSubmit = findViewById(R.id.buttonSubmit2);
	buttonSubmit.setOnClickListener(new View.OnClickListener() {
	  @Override
	  public void onClick(View v) {
		getInputData();

		Intent i = new Intent(TambahTambakActivity.this,TambakActivity.class);
		i.putExtra("userAttribute",userAttribute);
		startActivity(i);
	  }
	});
	Button btnSubmit = findViewById(R.id.buttonSubmit);
	btnSubmit.setOnClickListener(new View.OnClickListener() {
	  @Override
	  public void onClick(View v) {
		Intent i = new Intent(TambahTambakActivity.this,TambakActivity.class);
		i.putExtra("userAttribute",userAttribute);
		startActivity(i);
	  }
	});
  }

  private void getInputData() {
	keterangan = editTextKeterangan.getText().toString();
	luasK = editTextLuasKolam.getText().toString();
	jmlK = editTextJumlahRuasKolam.getText().toString();
	customId = editTextCustomId.getText().toString();
	userId = userAttribute.getUserID();

	Toast.makeText(TambahTambakActivity.this, keterangan + userId + luasK + customId, Toast.LENGTH_SHORT).show();
	submitNewItem();
  }

  private void submitNewItem() {
	String urlEndpoint = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-rugju/endpoint/insertBudidaya";

	RequestQueue requestQueue = Volley.newRequestQueue(this);
	StringRequest stringRequest = new StringRequest(Request.Method.POST, urlEndpoint, new Response.Listener<String>() {
	  @Override
	  public void onResponse(String response) {
		Toast.makeText(TambahTambakActivity.this, "Berhasil Menambahkan Data", Toast.LENGTH_SHORT).show();
	  }
	}, new Response.ErrorListener() {
	  @Override
	  public void onErrorResponse(VolleyError error) {
		Toast.makeText(TambahTambakActivity.this, "Gagal Menambahkan Data", Toast.LENGTH_SHORT).show();
	  }
	}){
	  @Nullable
	  @Override
	  protected Map<String, String> getParams() throws AuthFailureError {
		Map map = new HashMap<>();
		map.put("keterangan", keterangan);
		map.put("luas_kolam", luasK);
		map.put("jumlah_ruas_kolam", jmlK);
		map.put("custom_id", customId);
		map.put("user_id", userId);
		return map;
	  }
	};
	requestQueue.add(stringRequest);
  }
}
