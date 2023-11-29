//package com.example.shrimpfarm.ui.infotambak;
////
////import androidx.lifecycle.LiveData;
////import androidx.lifecycle.MutableLiveData;
////import androidx.lifecycle.ViewModel;
////
////public class TambakViewModel extends ViewModel {
////
////  private final MutableLiveData<String> mText;
////
////  public TambakViewModel() {
////	mText = new MutableLiveData<>();
////	mText.setValue("This is dashboard fragment");
////  }
////
////  public LiveData<String> getText() {
////	return mText;
////  }
////}
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.ViewModel;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TambakViewModel extends ViewModel {
//  private MutableLiveData<List<String>> pesananList = new MutableLiveData<>();
//  private JSONArray jsonArrayRes;
//
//  public LiveData<List<String>> getPesananList() {
//	return pesananList;
//  }
//
//  public void fetchPesananData() {
//	// Implement API call using Volley and update pesananList
//	// Similar to getAPIdata() method in TambakActivity
//  }
//
//  public String getOrderInfoAtPosition(int position) {
//	String orderId = "";
//	try {
//	  JSONObject jsonObject = jsonArrayRes.getJSONObject(position);
//	  orderId = jsonObject.getString("_id");
//	} catch (JSONException e) {
//	  e.printStackTrace();
//	}
//	return orderId;
//  }
//
//  // Additional methods for data processing as required
//}
