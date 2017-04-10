package com.Minions.relaxia.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.Minions.relaxia.R;

import java.util.ArrayList;

/**
 * Created by JJ on 10/4/2017.
 */

public class HistoryAdapter extends ArrayAdapter<History> {

    public HistoryAdapter(Context context, ArrayList<History> words) {
        super(context, 0, words);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_history, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        History currentHistory = getItem(position);


        TextView attempt = (TextView) listItemView.findViewById(R.id.textView_attempt);
        attempt.setText(currentHistory.getAttempt());

        TextView dateTime = (TextView) listItemView.findViewById(R.id.textView_dateTime);
        dateTime.setText(currentHistory.getDateTime());

        TextView difficulty = (TextView) listItemView.findViewById(R.id.textView_difficulty);
        difficulty.setText(currentHistory.getDifficulty());

        TextView stars = (TextView) listItemView.findViewById(R.id.textView_stars);
        stars.setText(currentHistory.getStars());

        TextView theme = (TextView) listItemView.findViewById(R.id.textView_theme);
        theme.setText(currentHistory.getTheme());


        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}
