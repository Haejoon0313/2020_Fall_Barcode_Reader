package com.example.barcodereader.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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

        if(newsBundle != null){
            Integer[] news_title = {R.id.news_title0, R.id.news_title1, R.id.news_title2, R.id.news_title3, R.id.news_title4};
            Integer[] news_date = {R.id.news_date0, R.id.news_date1, R.id.news_date2, R.id.news_date3, R.id.news_date4};

            for(int i = 0; i < newsBundle.size(); i++){
                String[] newsContents = newsBundle.getStringArray("firmNews"+i);
                final TextView newsTitleView = root.findViewById(news_title[i]);
                final TextView newsDateView = root.findViewById(news_date[i]);
                newsTitleView.setText(newsContents[0]);
                newsDateView.setText(betterDateFormat(newsContents[1]));
                newsTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        newsTitleView.setTextColor(Color.argb(255, 156, 39, 176));
                        Intent newsPageIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsContents[2]));
                        startActivity(newsPageIntent);
                    }
                });
            }
        }

        return root;
    }

    private String betterDateFormat(String rawDate){
        String[] parseDate = rawDate.split(" ");

        String newDate = parseDate[2]+" "+parseDate[1]+", "+parseDate[3];

        return newDate;
    }
}