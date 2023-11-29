//package com.example.shrimpfarm.ui.home;
//
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.ViewModelProvider;
//
//import com.example.shrimpfarm.R;
//import com.example.shrimpfarm.databinding.FragmentHomeBinding;
//import com.example.shrimpfarm.databinding.PartialActionBinding;
//import com.example.shrimpfarm.ui.infotambak.TambakActivity;
//import com.example.shrimpfarm.ui.rencanapanen.RencanaActivity;
//
//import java.util.Objects;
//
//public class HomeFragment extends Fragment {
//
//  HomeViewModel homeViewModel;
//  FragmentHomeBinding binding;
//  PartialActionBinding partialActionBinding;
//
//
//
//
//  @Override
//  public View onCreateView(@NonNull LayoutInflater inflater,
//						   ViewGroup container, Bundle savedInstanceState) {
//	binding = FragmentHomeBinding.inflate(inflater, container, false);
//	View root = binding.getRoot();
//
//	if (getActivity() instanceof AppCompatActivity) {
//	  Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).hide();
//	}
//
//	partialActionBinding = PartialActionBinding.bind(binding.getRoot());
//	setupButtonListeners();
//
////	TextView textView = root.findViewById(R.id.txtusername);
////	textView.setText("Direct Update Test");
//
//	// Initialize homeViewModel
//	homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
////	String username = homeViewModel.getUserAttribute().toString();
////	TextView textView = root.findViewById(R.id.txtusername);
////	textView.setText(username);
//
//	Log.d("SVM", "onCreateView: "+ homeViewModel);
//
//	homeViewModel.getUserAttribute().observe(getViewLifecycleOwner(), attribute -> {
//	  Log.d("HomeFragment", "User attribute observed: " + attribute);
//	  if (attribute != null) {
//		TextView textView = root.findViewById(R.id.txtusername);
//		textView.setText(attribute.getNama()); // Make sure getNama() is the correct method
//	  }
//	});
//
//	return root;
//  }
//
//  private void setupButtonListeners() {
//	  partialActionBinding.btnInfoTambak.setOnClickListener(v -> onButtonClicked());
//	  partialActionBinding.btnRencanapanen.setOnClickListener(v -> onRencanaPanenClicked());
//	}
//
//	private void observeData() {
//	  ListView listView = binding.lvRencanapanen; // Replace with your ListView ID in binding
//
//	  homeViewModel.getData().observe(getViewLifecycleOwner(), listData -> {
//		ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listData);
//		listView.setAdapter(adapter);
//	  });
//
//	  homeViewModel.fetchData();
//	}
//
//	private void onRencanaPanenClicked() {
//	  Intent intent = new Intent(getActivity(), RencanaActivity.class);
//	  startActivity(intent);
//	}
//
//	private void onButtonClicked() {
//	  Intent intent = new Intent(getActivity(), TambakActivity.class);
//	  startActivity(intent);
//	}
//  //	final TextView textView = binding.textHome;
//  //	homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//  //	return root;
//  //  }
//
//	@Override
//	public void onDestroyView() {
//	  super.onDestroyView();
//	  binding = null;
//	}
//}