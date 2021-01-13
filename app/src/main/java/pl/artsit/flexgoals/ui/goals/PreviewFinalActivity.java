package pl.artsit.flexgoals.ui.goals;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
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
import pl.artsit.flexgoals.shared.Helper;


public class PreviewFinalActivity extends AppCompatActivity {
    private static final char PROGRESS_DONE = '1';
    private PieChart pieChart;
    private FinalGoalFlag finalGoal;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @SuppressLint("ResourceType")
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
                progressData.get(0), getString(R.string.goal_preview_final_chart_progress)
        ));
        pieEntires.add(new PieEntry(
                progressData.get(2), getString(R.string.goal_preview_final_chart_todo)
        ));
        pieEntires.add(new PieEntry(
                progressData.get(1), getString(R.string.goal_preview_final_chart_failed)
        ));


        PieDataSet dataSet = new PieDataSet(pieEntires,"");
        dataSet.setColors(getResources().getIntArray(R.array.color_group));
        dataSet.setValueTextSize(getResources().getInteger(R.integer.final_chart_size));
        PieData data = new PieData(dataSet);


        //Get the chart
        pieChart.setData(data);
        pieChart.invalidate();
        pieChart.setHoleColor(ModalWidgets.getColorWithAlpha(Color.GREEN, 0.0f));
        pieChart.setDrawEntryLabels(false);
        pieChart.setContentDescription("");
        pieChart.setHoleRadius(getResources().getInteger(R.integer.hole_radius));
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawEntryLabels(false);
        pieChart.setDrawMarkers(false);
        pieChart.setDrawSliceText(false);
        pieChart.setDrawSlicesUnderHole(false);
        pieChart.setDrawRoundedSlices(false);
        pieChart.setHoleColor(getResources().getColor(R.color.colorPrimary));
        //legend attributes
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextSize(getResources().getInteger(R.integer.primary_text_size));
        legend.setFormSize(getResources().getInteger(R.integer.primary_text_size));
        legend.setTextColor( getResources().getColor(R.color.colorPrimary));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    List<Integer> getProgressData() {
        List<Integer> data = new ArrayList<>();

        int progressCount = getProgressCount(finalGoal.getProgress()).get(0);
        int failedCount = getProgressCount(finalGoal.getProgress()).get(1);

        int leftDays = (int) Helper.getLeftDays(finalGoal.getDate(), finalGoal.getDays());
        int todoPoints = leftDays > 0 ? leftDays : 0;

        data.add(progressCount);
        data.add(failedCount);
        data.add(todoPoints);

        return data;
    }

    private List<Integer> getProgressCount(String progress) {
        int progressCount = 0;
        int failedCount = 0;
        for (int i = 0; i < progress.length(); i++){
            if (progress.charAt(i) == PROGRESS_DONE){
                progressCount += 1;
            } else {
                failedCount += 1;
            }
        }

        List<Integer> result = new ArrayList<>();
        result.add(progressCount);
        result.add(failedCount);

        return result;
    }
}