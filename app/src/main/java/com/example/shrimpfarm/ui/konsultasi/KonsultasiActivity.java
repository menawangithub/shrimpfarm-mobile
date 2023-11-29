package com.example.shrimpfarm.ui.konsultasi;// KonsultasiActivity.java
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shrimpfarm.R;

public class KonsultasiActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_konsultasi);

	Button linkButton = findViewById(R.id.btn_whatsapp);
	linkButton.setOnClickListener(new View.OnClickListener() {
	  @Override
	  public void onClick(View v) {
		String url = "wa.me/6287872225515"; // Replace with your actual URL
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(url));
		startActivity(intent);
	  }
	});
  }
}
