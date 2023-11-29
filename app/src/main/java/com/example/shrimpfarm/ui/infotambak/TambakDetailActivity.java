package com.example.shrimpfarm.ui.infotambak;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shrimpfarm.R;

import org.json.JSONException;
import org.json.JSONObject;

public class TambakDetailActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_tambak_detail);

	if (getSupportActionBar() != null) {
	  getSupportActionBar().hide();
	}

	// Get the detail string from the intent
	String detailString = getIntent().getStringExtra("detail");
	Log.d("<Data>", "onCreate: "+ detailString);
	if (detailString != null) {
	  try {
		JSONObject detail = new JSONObject(detailString);

		// Extract data from the JSONObject and display it in separate TextViews
		TextView tvKeterangan = findViewById(R.id.tvKeterangan);
		TextView tvLuasKolam = findViewById(R.id.tvLuasKolam);
		TextView tvJumlahRuasKolam = findViewById(R.id.tvJumlahRuasKolam);
		TextView tvCustomId = findViewById(R.id.tvCustomId);

		// Set individual text for each TextView
		tvKeterangan.setText(detail.getString("keterangan"));
		tvLuasKolam.setText(detail.getInt("luas_kolam") + "m²");
		tvJumlahRuasKolam.setText(detail.getInt("jumlah_ruas_kolam") + " Kolam");
		tvCustomId.setText(detail.getString("custom_id"));

	  } catch (JSONException e) {
		e.printStackTrace();
	  }
	}
  }
}
