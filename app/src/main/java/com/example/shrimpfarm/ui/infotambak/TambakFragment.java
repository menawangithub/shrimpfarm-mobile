//package com.example.shrimpfarm.ui.infotambak;
////
////import android.os.Bundle;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.ViewGroup;
////import android.widget.TextView;
////
////import androidx.annotation.NonNull;
////import androidx.appcompat.app.AppCompatActivity;
////import androidx.fragment.app.Fragment;
////import androidx.lifecycle.ViewModel;
////import androidx.lifecycle.ViewModelProvider;
////
////import com.example.shrimpfarm.databinding.FragmentTambakBinding;
////
////
////public class TambakFragment extends Fragment {
////
////  FragmentTambakBinding binding;
////
////
////  public View onCreateView(@NonNull LayoutInflater inflater,
////						   ViewGroup container, Bundle savedInstanceState) {
////	TambakViewModel tambakViewModel =
////			new ViewModelProvider(this).get(TambakViewModel.class);
////
////	binding = FragmentTambakBinding.inflate(inflater, container, false);
////
////
//////	final TextView textView = binding.textTambak;
//////	tambakViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
////	return binding.getRoot();
////  }
////
////
//////  public static class MongoDBConnector {
//////	/**
//////	 * Connects to MongoDB Atlas and returns a MongoDatabase instance.
//////	 *
//////	 * @return MongoDatabase instance or null if an error occurs
//////	 */
//////	public static MongoDatabase connectToDB() {
//////	  String connectionString = "mongodb+srv://menawanproject:kowan123@cluster0/test?retryWrites=true&w=majority";
//////
//////	  try (MongoClient mongoClient = MongoClients.create(connectionString)) {
//////		// Replace 'test' with your actual database name
//////		return mongoClient.getDatabase("db_shrimpfarm");
//////	  } catch (Exception e) {
//////		e.printStackTrace();
//////		return null;
//////	  }
//////	}
//////  }
////
////
////  @Override
////  public void onDestroyView() {
////	super.onDestroyView();
////	binding = null;
////  }
////}
//
//import static android.os.Build.VERSION_CODES.R;
//
//import android.app.Dialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.Toast;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.ViewModelProvider;
//import com.example.shrimpfarm.R; // Replace with your actual R class import
//
//public class TambakFragment<ViewGroup> extends Fragment {
//
//  TambakViewModel tambakViewModel;
//  private Button addButton;
//  private ListView listView;
//
//  @Override
//  public View onCreateView(LayoutInflater inflater, ViewGroup container,
//						   Bundle savedInstanceState) {
//	View view = inflater.inflate(R.layout.fragment_tambak, container, false);
//
//	tambakViewModel = new ViewModelProvider(this).get(TambakViewModel.class);
//	tambakViewModel.fetchPesananData();
//
//	addButton = view.findViewById(R.id.btn_tambahpesanan);
//	addButton.setOnClickListener(v -> {
//	  Intent in = new Intent(getActivity(), TambahTambak.class);
//	  startActivity(in);
//	});
//
//	listView = view.findViewById(R.id.lv_pesanan);
//	listView.setOnItemLongClickListener((parent, view, position, id) -> {
//	  // Similar to the OnItemLongClickListener in TambakActivity
//	  // You might need to adjust Dialog related code as per Fragment
//	  return true;
//	});
//
//	tambakViewModel.getPesananList().observe(getViewLifecycleOwner(), this::populateListView);
//
//	return view;
//  }
//
//  private void populateListView(List<String> pesanan) {
//	ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, pesanan);
//	listView.setAdapter(adapter);
//  }
//
//  // Additional methods as required, similar to those in TambakActivity
//}
