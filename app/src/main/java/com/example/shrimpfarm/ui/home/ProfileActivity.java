package com.example.shrimpfarm.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.shrimpfarm.R;
import com.example.shrimpfarm.UserAttribute;
import com.example.shrimpfarm.ui.infotambak.TambakActivity;
import com.example.shrimpfarm.ui.infotambak.TambakAdapter;
import com.example.shrimpfarm.ui.infotambak.TambakDetailActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity {

  UserAttribute userAttribute;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_profile);

	if (getSupportActionBar() != null) {
	  getSupportActionBar().hide();
	}

	userAttribute = (UserAttribute) getIntent().getSerializableExtra("userAttribute");

	Log.d("ALWN", "onCreate: "+userAttribute);

	TextView username = findViewById(R.id.tvNamaPengguna);
	String uname = userAttribute.getNama();
	username.setText(uname);

	TextView email = findViewById(R.id.tvEmail);
	String em = userAttribute.getEmail();
	email.setText(em);
//
//	TextView notelepon = findViewById(R.id.tvNoTelp);
	int notelp = userAttribute.getPhone();
//	notelepon.setText(notelp);
	Log.d("ALWN", "onCreate: "+notelp);
//
	TextView alamat = findViewById(R.id.tvAlamat);
	String almt = userAttribute.getaddress();
	alamat.setText(almt);

	TextView kodepos = findViewById(R.id.tvKodepos);
	String kp = userAttribute.getZipcode();
	kodepos.setText(kp);

	TextView provinsi = findViewById(R.id.tvProvinsi);
	String pv = userAttribute.getProvinsi()	;
	provinsi.setText(pv);

 //	listView = findViewById(R.id.listview_tambak);
//	adapter = new TambakAdapter(this, tambakList);
//	listView.setAdapter(adapter);
//	listView.setOnItemClickListener((parent, view, position, id) -> {
//	  Intent intent = new Intent(TambakActivity.this, TambakDetailActivity.class);
//	  try {
//		JSONObject selectedTambak = jsonArray.getJSONObject(position);
//		intent.putExtra("detail", selectedTambak.toString());
//		startActivity(intent);
//	  } catch (JSONException e) {
//		e.printStackTrace();
//	  }
//	});
  }
}