package com.example.barcodereader.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.barcodereader.MainActivity;
import com.example.barcodereader.R;
import com.example.barcodereader.http.ServerConnection;

import java.io.IOException;

public class SearchFragment extends Fragment {

    MainActivity mainActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        final EditText editText = root.findViewById(R.id.edit_text);
        final ImageButton searchButton = root.findViewById(R.id.image_button);

        searchButton.setOnClickListener(v -> {
            String number = editText.getText().toString();
            if (number.length() != 13) {
                Toast.makeText(getActivity(), "Input should be a 13-digit number", Toast.LENGTH_SHORT).show();
            } else {
                Bundle bundle = null;
                try {
                    bundle = ServerConnection.requestInfo(number);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mainActivity.replaceFragment(false, bundle);
            }
        });

        return root;
    }
}