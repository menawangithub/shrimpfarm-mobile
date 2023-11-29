//package com.example.shrimpfarm.ui.rencanapanen;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.ViewModelProvider;
//
//import com.example.shrimpfarm.databinding.FragmentMenuBinding;
//
//public class RencanaFragment extends Fragment {
//
//  private FragmentMenuBinding binding;
//
//  public View onCreateView(@NonNull LayoutInflater inflater,
//						   ViewGroup container, Bundle savedInstanceState) {
//	RencanaViewModel rencanaViewModel =
//			new ViewModelProvider(this).get(RencanaViewModel.class);
//
//	binding = FragmentMenuBinding.inflate(inflater, container, false);
//	View root = binding.getRoot();
//
//	final TextView textView = binding.textMenu;
//	rencanaViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//	return root;
//  }
//
//  @Override
//  public void onDestroyView() {
//	super.onDestroyView();
//	binding = null;
//  }
//}