//package com.example.shrimpfarm.ui.home;
//
//import android.os.Handler;
//import android.os.Looper;
//import android.util.Log;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.ViewModel;
//
//import com.example.shrimpfarm.UserAttribute;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class HomeViewModel extends ViewModel {
//  private MutableLiveData<List<String>> data = new MutableLiveData<>();
//
//  public MutableLiveData<List<String>> getData() {
//	return data;
//  }
//
//  private final MutableLiveData<UserAttribute> userAttribute = new MutableLiveData<>();
//
//  // Method to set the userAttribute data
//  public void setUserAttribute(UserAttribute attribute) {
//	Log.d("HomeViewModel", "Setting user attribute: " + attribute);
//	userAttribute.setValue(attribute);
//  }
//
//  // Method to get the userAttribute LiveData
//  public LiveData<UserAttribute> getUserAttribute() {
//	return userAttribute;
//  }
//
//  public void fetchData() {
//	new Thread(() -> {
//	  OkHttpClient client = new OkHttpClient();
//	  String urlEndpoint = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-rugju/endpoint/getAllBudidaya"; // Replace with your URL
//	  Request request = new Request.Builder().url(urlEndpoint).build();
//
//	  try (Response response = client.newCall(request).execute()) {
//		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//		JSONArray jsonArray = new JSONArray(response.body().string());
//		List<String> dataList = new ArrayList<>();
//		for (int i = 0; i < jsonArray.length(); i++) {
//		  JSONObject jsonObject = jsonArray.getJSONObject(i);
//		  String displayText = "Keterangan: " + jsonObject.getString("keterangan") + "\n"
//				  + "Luas Kolam: " + jsonObject.getInt("luas_kolam") + "\n"
//				  + "Jumlah Ruas Kolam: " + jsonObject.getInt("jumlah_ruas_kolam") + "\n"
//				  + "Custom ID: " + jsonObject.getString("custom_id");
//		  dataList.add(displayText);
//		}
//
//		// Post the data to LiveData on the main thread
//		new Handler(Looper.getMainLooper()).post(() -> data.setValue(dataList));
//	  } catch (Exception e) {
//		e.printStackTrace();
//	  }
//	}).start();
//  }
//
//}