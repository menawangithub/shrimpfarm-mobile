package com.example.shrimpfarm.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import com.example.shrimpfarm.databinding.FragmentLikedBinding;


public class MenuFragment extends Fragment {

  FragmentLikedBinding binding;


  public View onCreateView(@NonNull LayoutInflater inflater,
						   ViewGroup container, Bundle savedInstanceState) {
	MenuViewModel menuViewModel =
			new ViewModelProvider(this).get(MenuViewModel.class);

	binding = FragmentLikedBinding.inflate(inflater, container, false);
	View root = binding.getRoot();

	final TextView textView = binding.textLiked;
	menuViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
	return root;
  }


//  public static class MongoDBConnector {
//	/**
//	 * Connects to MongoDB Atlas and returns a MongoDatabase instance.
//	 *
//	 * @return MongoDatabase instance or null if an error occurs
//	 */
//	public static MongoDatabase connectToDB() {
//	  String connectionString = "mongodb+srv://menawanproject:kowan123@cluster0/test?retryWrites=true&w=majority";
//
//	  try (MongoClient mongoClient = MongoClients.create(connectionString)) {
//		// Replace 'test' with your actual database name
//		return mongoClient.getDatabase("db_shrimpfarm");
//	  } catch (Exception e) {
//		e.printStackTrace();
//		return null;
//	  }
//	}
//  }


  @Override
  public void onDestroyView() {
	super.onDestroyView();
	binding = null;
  }
}