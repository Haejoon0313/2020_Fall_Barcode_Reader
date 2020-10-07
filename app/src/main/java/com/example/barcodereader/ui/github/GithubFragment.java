package com.example.barcodereader.ui.github;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.barcodereader.R;

public class GithubFragment extends Fragment {

    private GithubViewModel githubViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        githubViewModel =
                ViewModelProviders.of(this).get(GithubViewModel.class);
        View root = inflater.inflate(R.layout.fragment_github, container, false);
        final TextView textView = root.findViewById(R.id.text_github);
        githubViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}