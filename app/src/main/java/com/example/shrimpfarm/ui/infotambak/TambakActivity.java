package com.example.shrimpfarm.ui.infotambak;

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

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TambakActivity extends AppCompatActivity {

  private ListView listView;
  private TambakAdapter adapter;
  private final OkHttpClient client = new OkHttpClient();
  private JSONArray jsonArray = new JSONArray();
  private ArrayList<Tambak> tambakList = new ArrayList<>();
  String TambakId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_tambak);

	if (getSupportActionBar() != null) {
	  getSupportActionBar().hide();
	}

	listView = findViewById(R.id.listview_tambak);
	adapter = new TambakAdapter(this, tambakList);
	listView.setAdapter(adapter);
	listView.setOnItemClickListener((parent, view, position, id) -> {
	  Intent intent = new Intent(TambakActivity.this, TambakDetailActivity.class);
	  try {
		JSONObject selectedTambak = jsonArray.getJSONObject(position);
		intent.putExtra("detail", selectedTambak.toString());
		startActivity(intent);
	  } catch (JSONException e) {
		e.printStackTrace();
	  }
	});

	fetchTambakData();
	setupListViewListener();
  }

  private void fetchTambakData() {
	new Thread(() -> {
	  String urlEndpoint = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-rugju/endpoint/getAllBudidaya";
	  Request request = new Request.Builder().url(urlEndpoint).build();

	  try (Response response = client.newCall(request).execute()) {
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

		final String responseData = response.body().string();
		jsonArray = new JSONArray(responseData);

		tambakList.clear(); // Clear existing data
		for (int i = 0; i < jsonArray.length(); i++) {
		  JSONObject jsonObject = jsonArray.getJSONObject(i);
		  Tambak tambak = new Tambak(jsonObject.getString("custom_id"),
				  jsonObject.getString("keterangan"),
				  jsonObject.getInt("luas_kolam"),
				  jsonObject.getInt("jumlah_ruas_kolam"),
				  jsonObject.getString("_id"));
		  tambakList.add(tambak);
		}

		runOnUiThread(() -> {
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
		final Dialog dialog = new Dialog(TambakActivity.this);
		dialog.setContentView(R.layout.tambak_dialog);
		dialog.setTitle("Pilih Operasi");
		dialog.show();
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(64, 0, 0, 0)));
		Window window = dialog.getWindow();
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

		final String selectedTambakId = getOrderInfoAtPosition(position); // Implement this method

		Button editButton = dialog.findViewById(R.id.btn_update);
		Button deleteButton = dialog.findViewById(R.id.btn_delete);

		editButton.setOnClickListener(v -> {
		  Intent i = new Intent(TambakActivity.this, EditTambakActivity.class);
		  UserAttribute userAttribute = (UserAttribute) getIntent().getSerializableExtra("userAttribute");
		  i.putExtra("userAttribute", userAttribute);
		  try {
			JSONObject selectedTambak = jsonArray.getJSONObject(position);
			String tambakId = selectedTambak.getString("_id");
			i.putExtra("keterangan", selectedTambak.getString("keterangan"));
			i.putExtra("luas_kolam", selectedTambak.getString("luas_kolam"));
			i.putExtra("jumlah_ruas_kolam", selectedTambak.getString("jumlah_ruas_kolam"));
			i.putExtra("tambakId", tambakId);
		  } catch (JSONException e) {
			e.printStackTrace();
		  }

		  dialog.dismiss();
		  startActivity(i);
		});
		deleteButton.setOnClickListener(v -> {
		  deleteItem(selectedTambakId); // Implement deleteItem method
		  dialog.dismiss();

		});

		return true;
	  }

	  private String getOrderInfoAtPosition(int position) {
		TambakId = ""; // Ganti dengan logika sesuai kebutuhan Anda
		try {
		  JSONObject jsonObject = jsonArray.getJSONObject(position);
		  TambakId = jsonObject.getString("_id");
		} catch (JSONException e) {
		  e.printStackTrace();
		}
		return TambakId;
	  }

	  private void deleteItem(String TambakId) {
		new Thread(() -> {
		  String urlEndpoint = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-rugju/endpoint/deleteBudidaya?id=" + TambakId; // Replace with your actual DELETE endpoint

		  Request request = new Request.Builder()
				  .url(urlEndpoint)
				  .delete() // Using the delete method
				  .build();

		  try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful()) {
			  throw new IOException("Unexpected code " + response);
			}
			reloadData();

			// Handle the successful deletion
			runOnUiThread(() -> {
			  Toast.makeText(TambakActivity.this, "Tambak deleted successfully", Toast.LENGTH_SHORT).show();
			  // Refresh the list here if necessary
			  tambakList.removeIf(tambak -> tambak.getId().equals(TambakId)); // Contoh untuk menghapus item dari list
			  adapter.notifyDataSetChanged(); // Gunakan adapter yang sama yang terhubung dengan listView
			});
		  }  catch (IOException e) {
			e.printStackTrace();
			runOnUiThread(() -> Toast.makeText(TambakActivity.this, "Failed to delete tambak", Toast.LENGTH_SHORT).show());
		  }
		}).start();
	  }
	});

	Button addButton = findViewById(R.id.btn_tambahtambak); // Replace with your actual button ID
	addButton.setOnClickListener(view -> openTambahTambakPage());
  }

  private void reloadData(){
	fetchTambakData();
  }

  private void openTambahTambakPage() {
	UserAttribute userAttribute = (UserAttribute) getIntent().getSerializableExtra("userAttribute");
	Intent intent = new Intent(this, TambahTambakActivity.class);
	intent.putExtra("userAttribute", userAttribute);
	startActivity(intent);
  }
}
