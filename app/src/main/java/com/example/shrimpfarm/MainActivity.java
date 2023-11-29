  package com.example.shrimpfarm;
//
//  import android.os.Bundle;
//  import android.util.Log;
//  import android.widget.TextView;
//
//  import com.google.android.material.bottomnavigation.BottomNavigationView;
//
  import androidx.appcompat.app.AppCompatActivity;
//  import androidx.lifecycle.ViewModelProvider;
//  import androidx.navigation.NavController;
//  import androidx.navigation.Navigation;
//  import androidx.navigation.ui.AppBarConfiguration;
//  import androidx.navigation.ui.NavigationUI;
//
//  import com.example.shrimpfarm.databinding.ActivityMainBinding;
//
  public class MainActivity extends AppCompatActivity {
//	private UserAttribute userAttribute;
//
//  ActivityMainBinding binding;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//	  super.onCreate(savedInstanceState);
//
//	  if (getSupportActionBar() != null) {
//		getSupportActionBar().hide();
//	  }
//
////	  homeViewModel = new ViewModelProvider(requireActivity()).get(HomeActivity.class);
////
////	  homeViewModel.getUserAttribute().observe(getViewLifecycleOwner(), attribute -> {
////		Log.d("HomeFragment", "User attribute observed: " + attribute);
////		if (attribute != null) {
////		  TextView textView = root.findViewById(R.id.txtusername);
////		  textView.setText(attribute.getNama()); // Make sure getNama() is the correct method
////		}
////	  });
//
//	  //data user
//  //	userAttribute = (UserAttribute) getIntent().getSerializableExtra("userAttribute");
//  //	if (userAttribute != null){
//  //	  String username = findViewById(R.id.txtusername);
//  //	}
//
//	  binding = ActivityMainBinding.inflate(getLayoutInflater());
//	  setContentView(binding.getRoot());
//
//	  BottomNavigationView navView = findViewById(R.id.nav_view);
//	  // Passing each menu ID as a set of Ids because each
//	  // menu should be considered as top level destinations.
//  //	AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//  //			R.id.navigation_home, R.id.navigation_menu, R.id.navigation_notifications, R.id.navigation_liked)
//  //			.build();
//	  NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//  //	NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//	  NavigationUI.setupWithNavController(binding.navView, navController);
//
//
//
	}
//
//  }