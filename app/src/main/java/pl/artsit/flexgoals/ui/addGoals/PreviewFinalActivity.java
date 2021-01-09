package pl.artsit.flexgoals.ui.addGoals;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.model.ModalWidgets;
import pl.artsit.flexgoals.model.goal.finals.FinalGoalFlag;


public class PreviewFinalActivity extends AppCompatActivity {
    private PieChart pieChart;
    private FinalGoalFlag finalGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_final);

        finalGoal = MainActivity.previewFinalGoal;
        pieChart = findViewById(R.id.preview_progressbar);


        // TODO:
        List<Integer> progressData = getProgressData();

        List<PieEntry> pieEntires = new ArrayList<>();
        pieEntires.add(new PieEntry(
            50, getString(R.string.goal_preview_final_chart_progress)
        ));
        pieEntires.add(new PieEntry(
            50, getString(R.string.goal_preview_final_chart_todo)
        ));
        pieEntires.add(new PieEntry(
            50, getString(R.string.goal_preview_final_chart_failed)
        ));

        // /TODO

        final int[] PIE_COLORS = {
                getResources().getColor(R.color.kalendarz),
                getResources().getColor(R.color.celeZaliczeniowe),
                getResources().getColor(R.color.wyloguj),
        };

        PieDataSet dataSet = new PieDataSet(pieEntires,"");
        dataSet.setColors(PIE_COLORS);
        PieData data = new PieData(dataSet);


        //Get the chart
        pieChart.setData(data);
        pieChart.invalidate();
        pieChart.setHoleColor(ModalWidgets.getColorWithAlpha(Color.GREEN, 0.0f));
        pieChart.setDrawEntryLabels(false);
        pieChart.setContentDescription("");
        pieChart.setHoleRadius(75);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawEntryLabels(false);
        pieChart.setDrawMarkers(false);
        pieChart.setDrawSliceText(false);
        pieChart.setDrawSlicesUnderHole(false);
        pieChart.setDrawRoundedSlices(false);
        //legend attributes
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextSize(28);
        legend.setFormSize(28);
        legend.setFormToTextSpace(10);
        legend.setTextColor( getResources().getColor(R.color.colorPrimary));
    }

    List<Integer> getProgressData() {
        List<Integer> data = new ArrayList<>();

        String progressString = finalGoal.getProgress();

        // NEEDED DATE

        return data;
    }
}