package com.example.barcodereader.ui.search;

import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.barcodereader.R;

public class SearchFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        final EditText editText = root.findViewById(R.id.edit_text);
        final ImageButton searchButton = root.findViewById(R.id.image_button);

        searchButton.setOnClickListener(v -> {
            String number = editText.getText().toString();
            if (number.length() != 13) {
                Toast.makeText(getActivity(), "Input should be a 13-digit number", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "바코드 정보:" + number, Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

}