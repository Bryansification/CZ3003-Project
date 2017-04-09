package com.snatik.matches.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.snatik.matches.R;
import com.snatik.matches.common.ScoreDatabaseHandler;
import com.snatik.matches.common.Shared;


/**
 * Created by JJ on 9/4/2017.
 */

public class DataAnalysisFragment extends Fragment {

    private GraphView mGraphView;
    private DataPoint[] numbersValues;
    private DataPoint[] alphabetsValues;
    private ScoreDatabaseHandler mScoreDataBaseHandler = ScoreDatabaseHandler.getInstance(Shared.context);


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_analysis_fragment, container, false);

        numbersValues = mScoreDataBaseHandler.numbersThemeCoords();
        alphabetsValues = mScoreDataBaseHandler.alphabetsThemeCoords();

        mGraphView = (GraphView) view.findViewById(R.id.graphView);

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

        mGraphView.setTitle("Child's Progress Report");
        mGraphView.setTitleTextSize(72);

        mGraphView.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);

        mGraphView.getGridLabelRenderer().setHorizontalAxisTitle("Attempts");
        mGraphView.getGridLabelRenderer().setHorizontalAxisTitleTextSize(48);


        mGraphView.getGridLabelRenderer().setVerticalAxisTitle("Stars Earned");
        mGraphView.getGridLabelRenderer().setVerticalAxisTitleTextSize(48);

        mGraphView.getViewport().setScrollable(true);



        return view;

    }
}
