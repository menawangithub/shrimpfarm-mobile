package com.example.shrimpfarm.ui.home;

import static android.app.PendingIntent.getActivity;

import static com.mongodb.BasicDBObjectBuilder.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shrimpfarm.R;
import com.example.shrimpfarm.UserAttribute;
import com.example.shrimpfarm.databinding.PartialActionBinding;
import com.example.shrimpfarm.ui.infotambak.TambakActivity;
import com.example.shrimpfarm.ui.konsultasi.KonsultasiActivity;
import com.example.shrimpfarm.ui.kontenbudidaya.KontenActivity;
import com.example.shrimpfarm.ui.rencanapanen.RencanaActivity;
import com.example.shrimpfarm.ui.signuplogin.LoginActivity;
import com.example.shrimpfarm.ui.todolist.TodoList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity {

  private ListView listView;
  private ArrayAdapter<String> adapter;
  private final List<String> dataList = new ArrayList<>();
  private final OkHttpClient client = new OkHttpClient();

  private JSONArray jsonArray;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_home);

	if (getSupportActionBar() != null) {
	  getSupportActionBar().hide();
	}

	listView = findViewById(R.id.lv_rencanapanen); // Replace with your ListView ID
	adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
	listView.setAdapter(adapter);

	fetchTambakData();

	//Data User
	UserAttribute userAttribute = (UserAttribute) getIntent().getSerializableExtra("userAttribute");
	String username = userAttribute.getNama();
	TextView uname = findViewById(R.id.txtusername);
	uname.setText(username);

	ImageView btnProfile = findViewById(R.id.imageView);
	ImageButton btnTambak = findViewById(R.id.btn_infotambak);
	ImageButton btnRencana = findViewById(R.id.btn_rencanapanen);
	ImageButton btnKonsultasi = findViewById(R.id.btn_konsultasi);
	ImageButton btnKonten = findViewById(R.id.btn_konten);
	ImageButton btnTodo = findViewById(R.id.btn_todo);


	btnProfile.setOnClickListener(new View.OnClickListener() {
	  @Override
	  public void onClick(View v) {
		Intent intent = new Intent(HomeActivity.this, ProfileActivity.class); // Replace ProfileActivity with the actual activity class
		UserAttribute userAttribute = (UserAttribute) getIntent().getSerializableExtra("userAttribute");
		intent.putExtra("userAttribute", userAttribute);
		startActivity(intent);
	  }
	});

	btnTambak.setOnClickListener(new View.OnClickListener() {
	  @Override
	  public void onClick(View v) {
		Intent intent = new Intent(HomeActivity.this, TambakActivity.class);
		UserAttribute userAttribute = (UserAttribute) getIntent().getSerializableExtra("userAttribute");
		intent.putExtra("userAttribute", userAttribute);
		startActivity(intent);// Replace TambakActivity with the actual activity class
	  }
	});

	btnRencana.setOnClickListener(new View.OnClickListener() {
	  @Override
	  public void onClick(View v) {
		Intent intent = new Intent(HomeActivity.this, RencanaActivity.class); // Replace RencanaPanenActivity with the actual activity class
		intent.putExtra("userAttribute", userAttribute);
		startActivity(intent);
	  }
	});

	btnKonsultasi.setOnClickListener(new View.OnClickListener() {
	  @Override
	  public void onClick(View v) {
		Intent intent = new Intent(HomeActivity.this, KonsultasiActivity.class); // Replace KonsultasiActivity with the actual activity class
		intent.putExtra("userAttribute", userAttribute);
		startActivity(intent);
	  }
	});

	btnKonten.setOnClickListener(new View.OnClickListener() {
	  @Override
	  public void onClick(View v) {
		Intent intent = new Intent(HomeActivity.this, KontenActivity.class); // Replace KontenActivity with the actual activity class
		intent.putExtra("userAttribute", userAttribute);
		startActivity(intent);
	  }
	});

	btnTodo.setOnClickListener(new View.OnClickListener() {
	  @Override
	  public void onClick(View v) {
		Intent intent = new Intent(HomeActivity.this, TodoList.class); // Replace TodoActivity with the actual activity class
		intent.putExtra("userAttribute", userAttribute);
		startActivity(intent);
	  }
	});
  }

  private void fetchTambakData() {
	new Thread(() -> {
	  String urlEndpoint = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-rugju/endpoint/getAllDataPanen"; //URL ENDPOINTNYA MBAK
	  Request request = new Request.Builder().url(urlEndpoint).build();

	  try (Response response = client.newCall(request).execute()) {
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

		assert response.body() != null;

		JSONArray jsonArray = new JSONArray(response.body().string());

		for (int i = 0; i < jsonArray.length(); i++) {
		  JSONObject jsonObject = jsonArray.getJSONObject(i);
		  String displayText = "Jenis Panen: " + jsonObject.getString("jenis_panen") + "\n"
				  + "Perkiraan Panen: " + jsonObject.getString("perkiraan_panen") + "\n"
				  + "Ukuran Panen: " + jsonObject.getInt("ukuran_panen") + "\n"
				  + "Tonase Panen: " + jsonObject.getInt("tonase_panen") + "\n"
				  + "Usia Budidaya: " + jsonObject.getInt("usia_budidaya") + "\n"
				  + "Harga Harapan: " + jsonObject.getString("harga_harapan") + "\n"
				  + "Lokasi Budidaya: " + jsonObject.getString("lokasi_budidaya") + "\n"
				  + "Custom ID: " + jsonObject.getString("custom_id");

		  runOnUiThread(() -> {
			dataList.add(displayText);
			adapter.notifyDataSetChanged();
		  });
		}

	  } catch (Exception e) {
		e.printStackTrace();
	  }

	}).start();
  }

}

