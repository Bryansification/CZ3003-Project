package com.Minions.relaxia.fragments;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.Minions.relaxia.R;
import com.Minions.relaxia.common.ScoreDatabaseHandler;
import com.Minions.relaxia.common.Shared;
import com.Minions.relaxia.ui.History;
import com.Minions.relaxia.ui.HistoryAdapter;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


/**
 * Created by JJ on 9/4/2017.
 */

public class DataAnalysisFragment extends Fragment {

    private ImageButton mImageButton;
    private GraphView mGraphView;
    private LinearLayout mLinearLayout;
    private DataPoint[] numbersValues;
    private DataPoint[] alphabetsValues;
    private ScoreDatabaseHandler mScoreDataBaseHandler = ScoreDatabaseHandler.getInstance(Shared.context);

    private Context mContext;
    private PopupWindow mPopupWindow;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_analysis_fragment, container, false);

        mContext = getContext();

        numbersValues = mScoreDataBaseHandler.numbersThemeCoords();
        alphabetsValues = mScoreDataBaseHandler.alphabetsThemeCoords();

        mLinearLayout = (LinearLayout)  view.findViewById(R.id.linearLayout_data);
        mGraphView = (GraphView) view.findViewById(R.id.graphView);

        plotGraphs(numbersValues, alphabetsValues);

        mImageButton = (ImageButton) view.findViewById(R.id.button_history);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.popup_history_view,null);

                mPopupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                mPopupWindow.showAtLocation(mLinearLayout, Gravity.CENTER,0,0);

                ArrayList<History> gameplayHistory = mScoreDataBaseHandler.tableToArrayList();

                HistoryAdapter adapter = new HistoryAdapter(mContext, gameplayHistory);
                ListView mListView = (ListView) customView.findViewById(R.id.listView_history);
                mListView.setAdapter(adapter);


                ImageButton closeButton = (ImageButton) customView.findViewById(R.id.imageButton_close);


                // Set a click listener for the popup window close button
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismiss();

                    }
                });
            }
        });

        return view;

    }

    private void plotGraphs (DataPoint[] numbersValues, DataPoint[] alphabetsValues) {

        LineGraphSeries<DataPoint> numberslineGraphSeries = new LineGraphSeries<>(numbersValues);
        LineGraphSeries<DataPoint> alphabetslineGraphSeries = new LineGraphSeries<>(alphabetsValues);


        numberslineGraphSeries.setTitle("Numbers");
        numberslineGraphSeries.setColor(Color.BLUE);
        numberslineGraphSeries.setThickness(16);
        mGraphView.addSeries(numberslineGraphSeries);


        alphabetslineGraphSeries.setTitle("Alphabets");
        alphabetslineGraphSeries.setColor(Color.RED);
        alphabetslineGraphSeries.setThickness(16);
        mGraphView.addSeries(alphabetslineGraphSeries);

        mGraphView.getLegendRenderer().setVisible(true);
        mGraphView.getLegendRenderer().setBackgroundColor(Color.TRANSPARENT);
        mGraphView.getLegendRenderer().setSpacing(24);
        mGraphView.getLegendRenderer().setTextSize(48);

        mGraphView.getViewport().setXAxisBoundsManual(true);
        mGraphView.getViewport().setMinX(0);
        mGraphView.getViewport().setMaxX(6);


        mGraphView.getViewport().setScrollable(true);
        mGraphView.getViewport().setScrollableY(true);
        mGraphView.getViewport().setScalable(true);
        mGraphView.getViewport().setScalableY(true);

        mGraphView.setTitle("Child's Progress Report");
        mGraphView.setTitleTextSize(72);

        mGraphView.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);

        mGraphView.getGridLabelRenderer().setHorizontalAxisTitle("Attempts");
        mGraphView.getGridLabelRenderer().setHorizontalAxisTitleTextSize(48);


        mGraphView.getGridLabelRenderer().setVerticalAxisTitle("Stars Earned");
        mGraphView.getGridLabelRenderer().setVerticalAxisTitleTextSize(48);


    }

}
