package com.example.shrimpfarm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shrimpfarm.UserAttribute;

public class SharedViewModel extends ViewModel {
  private final MutableLiveData<UserAttribute> userAttribute = new MutableLiveData<>();

  // Method to set the userAttribute data
  public void setUserAttribute(UserAttribute attribute) {
	userAttribute.setValue(attribute);
  }

  // Method to get the userAttribute LiveData
  public LiveData<UserAttribute> getUserAttribute() {
	return userAttribute;
  }
}
