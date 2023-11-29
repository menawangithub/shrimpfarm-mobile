package com.example.shrimpfarm.ui.signuplogin;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.shrimpfarm.R;

public class SignupActivity extends AppCompatActivity {

  private EditText usernameEditText;
  private EditText passwordEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_signup);

	usernameEditText = findViewById(R.id.username);
	passwordEditText = findViewById(R.id.password);
  }

  public void onSignupClicked(View view) {
	String username = usernameEditText.getText().toString();
	String password = passwordEditText.getText().toString();

	// Add code to insert new user into MongoDB
  }
}
