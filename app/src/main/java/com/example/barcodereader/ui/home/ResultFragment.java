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

        final TextView itemView = root.findViewById(R.id.item_name);
        itemView.setText(getArguments().getString("itemName"));

        final TextView newsView = root.findViewById(R.id.firm_news);
        String[] newsContents = getArguments().getStringArray("firmNews");

        //newsView.setText(getArguments().getString("firmNews"));

        return root;
    }
}