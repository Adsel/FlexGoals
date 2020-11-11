package pl.artsit.flexgoals;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;


public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        // DATA
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 0));
        entries.add(new BarEntry(1, 1));
        entries.add(new BarEntry(2, 2));
        entries.add(new BarEntry(3, 3));
        entries.add(new BarEntry(4, 4));
        entries.add(new BarEntry(5, 5));
        BarDataSet dataset = new BarDataSet(entries, "ilość");

        // LABELS
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("11-11-2020");
        labels.add("12-11-2020");
        labels.add("13-11-2020");
        labels.add("14-11-2020");
        labels.add("15-11-2020");
        labels.add("16-11-2020");

        // CHART
        BarChart chart = new BarChart(getApplicationContext());
        setContentView(chart);
        BarData data = new BarData(dataset);
        chart.setData(data);

        // CHART DESCRIPTION AND ADDING LABELS
        Description description = new Description();
        description.setText("Cel ilościowy");
        chart.setDescription(description);
        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));

        // COLORS AND ANIMATION (OF DRAWING CHART)
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.animateY(5000);

    }
}