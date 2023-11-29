package com.example.shrimpfarm.ui.infotambak;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shrimpfarm.R;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;


public class EditTambakActivity extends AppCompatActivity {

  private EditText editTextKeterangan, editTextLuasKolam, editTextJumlahRuasKolam;
  private Button buttonSave;
  private String etKet, etLuas, etJumlah;
  private Tambak tambak;
  String tambakId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.tambak_edit);

	if (getSupportActionBar() != null) {
	  getSupportActionBar().hide();
	}

	initializeUI();

	tambakId = getIntent().getStringExtra("tambakId");
	Log.d("WAWA", "onCreate: "+tambakId);
	String keterangan = getIntent().getStringExtra("keterangan");
	String luasKolam = getIntent().getStringExtra("luas_kolam");
	String jumlahRuasKolam = getIntent().getStringExtra("jumlah_ruas_kolam");

	editTextKeterangan.setText(keterangan);
	editTextLuasKolam.setText(luasKolam); ;
	editTextJumlahRuasKolam.setText(jumlahRuasKolam);

	buttonSave.setOnClickListener(new View.OnClickListener() {
	  @Override
	  public void onClick(View view) {
		saveItemChanges();
		Intent i = new Intent(EditTambakActivity.this, TambakActivity.class);
		startActivity(i);
	  }
	});
  }

  private void initializeUI() {
	editTextKeterangan = findViewById(R.id.editTextKeterangan);
	editTextLuasKolam = findViewById(R.id.editTextLuasKolam);
	editTextJumlahRuasKolam = findViewById(R.id.editTextJumlahRuasKolam);
	buttonSave = findViewById(R.id.buttonSave);
  }

  private void saveItemChanges() {
	// Gather data from EditTexts
	etKet = editTextKeterangan.getText().toString();
	etLuas = editTextLuasKolam.getText().toString();
	etJumlah = editTextJumlahRuasKolam.getText().toString();

	// Validate input
	if (etKet.isEmpty() || etLuas.isEmpty() || etJumlah.isEmpty() ) {
	  Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
	  return;
	}

	updateItem();
  }

  private void updateItem() {
	String url = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-rugju/endpoint/updateBudidaya?id=" + tambakId + "&keterangan=" +etKet +"&luas_kolam="+etLuas+"&jumlah_ruas_kolam="+etJumlah;
	RequestQueue requestQueue = Volley.newRequestQueue(this);
	StringRequest updateData = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
	  @Override
	  public void onResponse(String response) {
		Toast.makeText(EditTambakActivity.this, "Data berhasil diupdate", Toast.LENGTH_SHORT).show();
	  }
	}, new Response.ErrorListener() {
	  @Override
	  public void onErrorResponse(VolleyError error) {
		Toast.makeText(EditTambakActivity.this, "Gagal mengubah data", Toast.LENGTH_SHORT).show();
	  }
	});
	requestQueue.add(updateData);
  }
}
