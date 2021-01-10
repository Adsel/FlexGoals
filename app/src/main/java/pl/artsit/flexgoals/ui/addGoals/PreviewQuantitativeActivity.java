package pl.artsit.flexgoals.ui.addGoals;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoalFlag;


public class PreviewQuantitativeActivity extends AppCompatActivity {
    private QuantitativeGoalFlag quantitativeGoal;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_quantitative);
        quantitativeGoal = MainActivity.previewQuantitativeGoal;

        // DATA
        BarDataSet dataset = new BarDataSet(getEntries(), getResources().getString(R.string.quantitative_activity));

        // CHART
        BarChart chart = (BarChart)findViewById(R.id.testowo);
        BarData data = new BarData(dataset);
        chart.setData(data);

        // CHART DESCRIPTION AND ADDING LABELS
        Description description = new Description();
        description.setText(getResources().getText(R.string.statistic_quantity).toString());
        chart.setDescription(description);
        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(getLabels()));
        chart.setOutlineAmbientShadowColor(getResources().getColor(R.color.colorPrimary));
        chart.setBorderColor(getResources().getColor(R.color.colorPrimary));
        chart.setNoDataTextColor(getResources().getColor(R.color.colorPrimary));
        chart.getXAxis().setTextColor(getResources().getColor(R.color.colorPrimary));
        chart.getBarData().setValueTextColor(getResources().getColor(R.color.colorPrimary));
        chart.getAxisLeft().setZeroLineColor(getResources().getColor(R.color.colorPrimary));
        chart.getLegend().setTextColor(getResources().getColor(R.color.colorPrimary));
        chart.setGridBackgroundColor(getResources().getColor(R.color.colorPrimary));
        chart.getDescription().setEnabled(false);
        // COLORS AND ANIMATION (OF DRAWING CHART)


        dataset.setValueTextColor(getResources().getColor(R.color.colorPrimary));
        dataset.setColors(getResources().getIntArray(R.array.color_group));
        chart.animateY(2500);
    }

    private List<String> getLabels() {
        List<String> labels = new ArrayList<>();

        for (int i = 0; i < quantitativeGoal.getDays(); i++) {
            labels.add(getResources().getText(R.string.day).toString() + " " + (i + 1));
        }

        return labels;
    }

    private ArrayList<BarEntry> getEntries() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        List<Integer> progress = getProgress(quantitativeGoal.getProgress());

        // TODO: read a progress
        for (int i = 0; i < quantitativeGoal.getDays(); i++) {
            entries.add(
                    new BarEntry(i, progress.get(i))
            );
            System.out.println(progress.get(i));
        }

        return entries;
    }

    private List<Integer> getProgress(String progressString){
        String str = "";
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < progressString.length(); i++){
            if(progressString.charAt(i) == ','){
                numbers.add(Integer.parseInt(str));
                str = "";
            }
            else{
                str += progressString.charAt(i);
            }
        }
        numbers.add(Integer.parseInt(str));

        return numbers;
    }
}