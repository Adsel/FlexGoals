package pl.artsit.flexgoals.ui.addGoals;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
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

        PieDataSet dataSet = new PieDataSet(pieEntires,"");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData data = new PieData(dataSet);

        //Get the chart
        pieChart.setData(data);
        pieChart.invalidate();
        pieChart.setCenterText(getString(R.string.goal_preview_final_chart_title));
        pieChart.setCenterTextSize(45);
        pieChart.setDrawEntryLabels(false);
        pieChart.setContentDescription("");
        //pieChart.setDrawMarkers(true);
        //pieChart.setMaxHighlightDistance(34);
        pieChart.setEntryLabelTextSize(75);
        pieChart.setHoleRadius(75);
        pieChart.setDescription(new Description());

        //legend attributes
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextSize(30);
        legend.setFormSize(15);
        legend.setFormToTextSpace(2);
    }

    List<Integer> getProgressData() {
        List<Integer> data = new ArrayList<>();

        String progressString = finalGoal.getProgress();

        // NEEDED DATE

        return data;
    }
}