package com.example.barcodereader.ui.github;

import android.content.Intent;
import android.net.Uri;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String address = "https://github.com/Haejoon0313/2020_Fall_Barcode_Reader";
        Intent OpenGitPage = new Intent(Intent.ACTION_VIEW, Uri.parse(address));
        startActivity(OpenGitPage);
    }

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