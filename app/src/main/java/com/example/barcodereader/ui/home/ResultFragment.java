package com.example.barcodereader.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.barcodereader.R;

public class ResultFragment extends Fragment {

    public static ResultFragment newInstance() {
        return new ResultFragment();
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_result, container, false);

        final TextView nameView = root.findViewById(R.id.firm_name);
        nameView.setText(getArguments().getString("firmName"));

        final TextView nationView = root.findViewById(R.id.firm_nation);
        nationView.setText(getArguments().getString("firmNation"));

        final TextView infoView = root.findViewById(R.id.firm_info);
        infoView.setText(getArguments().getString("firmInfo"));

        return root;
    }
}