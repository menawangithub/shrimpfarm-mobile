package com.example.shrimpfarm.ui.liked;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LikedViewModel extends ViewModel {

  private final MutableLiveData<String> mText;

  public LikedViewModel() {
	mText = new MutableLiveData<>();
	mText.setValue("This is dashboard fragment");
  }

  public LiveData<String> getText() {
	return mText;
  }
}