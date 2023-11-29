package com.example.shrimpfarm.ui.signuplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.shrimpfarm.R;
import com.example.shrimpfarm.UserAttribute;
import com.example.shrimpfarm.ui.home.HomeActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//

public class LoginActivity extends AppCompatActivity {

  EditText email, password;
  Button login;

  UserAttribute userAttribute;
  UserDataListener listener;

  public interface UserDataListener {
	void onUserDataReceiver(UserAttribute userAttribute);
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_login);

	if (getSupportActionBar() != null) {
	  getSupportActionBar().hide();
	}


	email = findViewById(R.id.email);
	password = findViewById(R.id.password);
	login = findViewById(R.id.btn_login);

	login.setOnClickListener(new View.OnClickListener() {
	  @Override
	  public void onClick(View v) {
		getDataUser();
	  }
	});

	Log.d("LoginActivity", "Received userAttribute: " + userAttribute);

//	// In LoginActivity
//	HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
//	homeViewModel.setUserAttribute(userAttribute);


	listener = new UserDataListener() {
	  @Override
	  public void onUserDataReceiver(UserAttribute userAttribute) {
		LoginActivity.this.userAttribute = userAttribute;
		if (dataReceived()) {
		  Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
		  intent.putExtra("userAttribute", userAttribute);
		  startActivity(intent);
		} else {
		  Toast.makeText(LoginActivity.this, "Akun tidak ditemukan", Toast.LENGTH_SHORT).show();
		}
	  }
	};
  }

  private void getDataUser() {
	String em = email.getText().toString();
	String pw = password.getText().toString();

	validateUser(em, pw);
  }

  private void validateUser(String em, String pw) {
	String urlEndpoint = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-rugju/endpoint/getUserLogin?email=" + em + "&password=" + pw;
	StringRequest stringRequest = new StringRequest(Request.Method.GET, urlEndpoint, new Response.Listener<String>() {
	  @Override
	  public void onResponse(String response) {
		try {
		  JSONArray jArray = new JSONArray(response);
		  JSONObject jResponse = jArray.getJSONObject(0);
		  if (jArray.length() == 0) {
			Toast.makeText(LoginActivity.this, "Akun belum terdaftar", Toast.LENGTH_SHORT).show();
			return;  // Return early, as there is no user data to process
		  }

		  // Extracting user details and creating UserAttribute object
		  userAttribute = new UserAttribute(
				  jResponse.getString("_id"),
				  jResponse.getString("nama"),
				  jResponse.getString("email"),
				  jResponse.getInt("phone"),
				  jResponse.getString("address"),
				  jResponse.getString("kota"),
				  jResponse.getString("provinsi"),
				  jResponse.getString("zipcode"),
				  jResponse.getString("password"),
				  jResponse.getString("kategori")
		  );



		  listener.onUserDataReceiver(userAttribute);
		  Log.d("MCHALWN", "onResponse: " + userAttribute);

		} catch (JSONException e) {
		  e.printStackTrace();
		}
	  }
	}, new Response.ErrorListener() {
	  @Override
	  public void onErrorResponse(VolleyError error) {
		// Handle network error
		Toast.makeText(LoginActivity.this, "Network error", Toast.LENGTH_SHORT).show();
	  }
	});
	RequestQueue requ = Volley.newRequestQueue(getApplicationContext());
	requ.add(stringRequest);
  }


  private boolean dataReceived() {
	return userAttribute != null;
  }
}

//public class LoginActivity extends AppCompatActivity {
//
//  EditText email, password;
//  Button login;
//
//  String em, pw;
//
//  UserAttribute userAttribute;
//
//  UserDataListener listener;
//
//  public interface UserDataListener{
//	void onUserDataReceiver(UserAttribute userAttribute);
//  }
//
//
//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//	super.onCreate(savedInstanceState);
//	setContentView(R.layout.activity_login);
//
//	email = findViewById(R.id.email);
//	password = findViewById(R.id.password);
//	login = findViewById(R.id.btn_login);
//
//	login.setOnClickListener(new View.OnClickListener() {
//	  @Override
//	  public void onClick(View v) {
//		getDataUser();
//	  }
//	});
//	listener = new UserDataListener() {
//	  @Override
//	  public void onUserDataReceiver(UserAttribute userAttribute) {
//		LoginActivity.this.userAttribute = userAttribute;
//	  }
//	};
//  }
//
//  private void getDataUser() {
//	em = email.getText().toString();
//	pw = password.getText().toString();
//
//	validateUser();
//  }
//
//  private void validateUser() {
//	String urlEndpoint = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-rugju/endpoint/getUserLogin?email=" + em + "&password=" + pw;
//	StringRequest stringRequest = new StringRequest(Request.Method.GET, urlEndpoint, new Response.Listener<String>() {
//	  @Override
//	  public void onResponse(String response) {
//		try {
//		  JSONArray jArray = new JSONArray(response);
//
//		  JSONObject jResponse = jArray.getJSONObject(0);
//
//		  String userID = jResponse.getString("_id");
//		  String nama = jResponse.getString("nama");
//		  String email = jResponse.getString("email");
//		  int phone = jResponse.getInt("phone");
//		  String address = jResponse.getString("address");
//		  String kota = jResponse.getString("kota");
//		  String provinsi = jResponse.getString("provinsi");
//		  String zipcode = jResponse.getString("zipcode");
//		  String password = jResponse.getString("password");
//		  String kategori = jResponse.getString("kategori");
//
//		  userAttribute = new UserAttribute(userID,nama,email,phone,address,kota,provinsi,zipcode,password,kategori);
//
//		  listener.onUserDataReceiver(userAttribute);
//
//		  dataReceived();
//		  if(dataReceived()){
//			Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//			intent.putExtra("userAttribute",userAttribute);
//			startActivity(intent);
//		  }else {
//			Toast.makeText(LoginActivity.this, "Akun tidak ditemukan", Toast.LENGTH_SHORT).show();
//		  }
//
//		} catch (JSONException e) {
//		  throw new RuntimeException(e);
//		}
//	  }
//	}, new Response.ErrorListener() {
//	  @Override
//	  public void onErrorResponse(VolleyError error) {
//
//	  }
//	});
//  }
//
//  private boolean dataReceived() {
//	return userAttribute !=null;
//  }
//
//
////  public void onSignupClicked(View view) {
////	String email = emailEditText.getText().toString();
////	String password = passwordEditText.getText().toString();
////
////	// Add code to insert new user into MongoDB
////  }
//}


