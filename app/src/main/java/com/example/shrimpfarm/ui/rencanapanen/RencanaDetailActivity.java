package com.example.shrimpfarm.ui.rencanapanen;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shrimpfarm.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RencanaDetailActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_rencana_detail);

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
		TextView jenisPanen = findViewById(R.id.etxtJenisPanen);
		TextView perkiraanPanen = findViewById(R.id.etxtPerkiraanPanen);
		TextView ukuranPanen = findViewById(R.id.etxtUkuranPanen);
		TextView tonasePanen = findViewById(R.id.etxtTonasePanen);

		// Set individual text for each TextView
		jenisPanen.setText(detail.getString("jenis_panen"));
		perkiraanPanen.setText(detail.getInt("perkiraan_panen"));
		ukuranPanen.setText(detail.getInt("ukuran_panen"));
		tonasePanen.setText(detail.getString("tonase_panen"));

	  } catch (JSONException e) {
		e.printStackTrace();
	  }
	}
  }
}
