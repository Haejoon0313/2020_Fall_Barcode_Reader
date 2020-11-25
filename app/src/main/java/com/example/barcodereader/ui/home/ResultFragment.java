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

        Bundle newsBundle = getArguments().getBundle("firmNews");
        String[] newsContents;

        newsContents = newsBundle.getStringArray("firmNews0");
        final TextView newsTitle0View = root.findViewById(R.id.news_title0);
        final TextView newsDate0View = root.findViewById(R.id.news_date0);
        newsTitle0View.setText(newsContents[0]);
        newsDate0View.setText(newsContents[1]);


        return root;
    }
}