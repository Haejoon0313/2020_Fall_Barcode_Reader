package com.example.barcodereader.ui.github;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GithubViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GithubViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Move to github");
    }

    public LiveData<String> getText() {
        return mText;
    }
}