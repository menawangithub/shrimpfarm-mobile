package com.example.shrimpfarm.ui.rencanapanen;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shrimpfarm.R;
import com.example.shrimpfarm.UserAttribute;
import com.example.shrimpfarm.ui.infotambak.Tambak;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RencanaActivity extends AppCompatActivity {

  private ListView listView;
  private RencanaAdapter adapter;
  private final OkHttpClient client = new OkHttpClient();
  private JSONArray jsonArray = new JSONArray();
  private ArrayList<Rencana> rencanaList = new ArrayList<>();

  String RencanaId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_rencana);

	if (getSupportActionBar() != null) {
	  getSupportActionBar().hide();
	}

	listView = findViewById(R.id.lv_rencanapanen); // Replace with your ListView ID
	adapter = new RencanaAdapter(this, rencanaList);
	listView.setAdapter(adapter);

	fetchTambakData();
	setupListViewListener();
  }

  private void fetchTambakData() {
	new Thread(() -> {
	  String urlEndpoint = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-rugju/endpoint/getAllDataPanen"; //URL ENDPOINTNYA MBAK
	  Request request = new Request.Builder().url(urlEndpoint).build();

	  try (Response response = client.newCall(request).execute()) {
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

		final String responseData = response.body().string();
		jsonArray = new JSONArray(responseData);

		rencanaList.clear();
		for (int i = 0; i < jsonArray.length(); i++) {
		  JSONObject jsonObject = jsonArray.getJSONObject(i);
		  Rencana rencana = new Rencana(jsonObject.getString("_id"),
				  jsonObject.getString("jenis_panen"),
				  jsonObject.getString("perkiraan_panen"),
				  jsonObject.getString("ukuran_panen"),
				  jsonObject.getString("tonase_panen"),
				  jsonObject.getString("usia_budidaya"),
				  jsonObject.getString("harga_harapan"),
				  jsonObject.getString("lokasi_budidaya"),
				  jsonObject.getString("custom_id"));
		  rencanaList.add(rencana);
		  }
		runOnUiThread(() ->{
		  adapter.notifyDataSetChanged();
		});
	  } catch (Exception e) {
		e.printStackTrace();
	  }
	}).start();
  }

  private void setupListViewListener() {
	listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
	  @Override
	  public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		final Dialog dialog = new Dialog(RencanaActivity.this);
		dialog.setContentView(R.layout.rencana_dialog);
		dialog.setTitle("Pilih Operasi");
		dialog.show();
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(64,0,0,0)));
		Window	window = dialog.getWindow();

		if (window != null) {
		  window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
		}
		View backgroundView = dialog.findViewById(android.R.id.content);
		backgroundView.setOnClickListener(new View.OnClickListener() {
		  @Override
		  public void onClick(View v) {
			dialog.dismiss();
		  }
		});

		final String selectedRencanaId = getOrderInfoAtPosition(position);

		Button editButtonRencana = dialog.findViewById(R.id.btn_updaterencana);
		Button deleteButtonRencana = dialog.findViewById(R.id.btn_deleterencana);

		editButtonRencana.setOnClickListener(v ->{
		  Intent i = new Intent(RencanaActivity.this, EditRencanaActivity.class);
		  UserAttribute userAttribute = (UserAttribute) getIntent().getSerializableExtra("userAttribute");
		  i.putExtra("userAttribute", userAttribute);
		  try{
			JSONObject selectedRencana = jsonArray.getJSONObject(position);
			String rencanaId = selectedRencana.getString("_id");
			i.putExtra("jenis_panen", selectedRencana.getString("jenis_panen"));
			i.putExtra("perkiraan_panen", selectedRencana.getString("perkiraan_panen"));
			i.putExtra("ukuran_panen", selectedRencana.getString("ukuran_panen"));
			i.putExtra("tonase_panen", selectedRencana.getString("tonase_panen"));
			i.putExtra("usia_budidaya", selectedRencana.getString("usia_budidaya"));
			i.putExtra("harga_harapan", selectedRencana.getString("harga_harapan"));
			i.putExtra("lokasi_budidaya", selectedRencana.getString("lokasi_budidaya"));
			i.putExtra("custom_id", selectedRencana.getString("custom_id"));
			i.putExtra("rencanaId", rencanaId);
		  } catch (JSONException e) {
			e.printStackTrace();
		  }
		  dialog.dismiss();
		  startActivity(i);
		});

		deleteButtonRencana.setOnClickListener( v->{
		  deleteItem(selectedRencanaId);
		  dialog.dismiss();
		});
		deleteButtonRencana.setOnClickListener(v -> {
		  deleteItem(selectedRencanaId);
		  dialog.dismiss();
		});
		return true;
	  }

	  private String getOrderInfoAtPosition(int position) {
		String RencanaId = "";
		try{
		  JSONObject jsonObject = jsonArray.getJSONObject(position);
		  RencanaId = jsonObject.getString("_id");
		} catch (JSONException e){
		  e.printStackTrace();
		}
		return RencanaId;
	  }

	  private void deleteItem(String RencanaId) {
		new Thread(() -> {
		  String urlEndpoint = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-rugju/endpoint/deleteInfoPanen?id=" + RencanaId;

		  Request request = new Request.Builder()
				  .url(urlEndpoint)
				  .delete()
				  .build();

		  try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful()) {
			  throw new IOException("Unexpected code " + response);
			}
			reloadData();

			runOnUiThread(() -> {
			  Toast.makeText(RencanaActivity.this, "Rencana deleted succesfully", Toast.LENGTH_SHORT).show();

			  rencanaList.removeIf(tambak -> tambak.getId().equals(RencanaId));
			  adapter.notifyDataSetChanged();
			});
		  } catch (IOException e) {
			e.printStackTrace();
			runOnUiThread(() -> Toast.makeText(RencanaActivity.this, "Failed to delete rencana", Toast.LENGTH_SHORT).show());
		  }
		}).start();
	  }
	});

	Button addButton = findViewById(R.id.btn_tambahrencana);
	addButton.setOnClickListener(view -> openTambahRencanaPage());
  }

  private void reloadData() {fetchTambakData();}

  private void openTambahRencanaPage() {
	UserAttribute userAttribute = (UserAttribute) getIntent().getSerializableExtra("userAttribute");
	Intent intent = new Intent(this, TambahRencanaActivity.class);
	intent.putExtra("userAttribute", userAttribute);
	startActivity(intent);
  }
}
