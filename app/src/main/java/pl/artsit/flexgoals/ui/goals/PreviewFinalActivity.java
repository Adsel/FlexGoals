package pl.artsit.flexgoals.ui.goals;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;


public class PreviewFinalActivity extends AppCompatActivity {

    float time[] = {55, 95, 30 , 360 - (55+95+30)};
    String activity[] ={"Jan","Feb","March",""};
    PieChart pieChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_final);

        List<PieEntry> pieEntires = new ArrayList<>();
        for( int i = 0 ; i<time.length;i++){
            pieEntires.add(new PieEntry(time[i],activity[i]));
        }
        PieDataSet dataSet = new PieDataSet(pieEntires,"");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData data = new PieData(dataSet);
        //Get the chart
        pieChart.setData(data);
        pieChart.invalidate();
        pieChart.setCenterText("50% \n ");
        pieChart.setDrawEntryLabels(false);
        pieChart.setContentDescription("");
        //pieChart.setDrawMarkers(true);
        //pieChart.setMaxHighlightDistance(34);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setHoleRadius(75);

        //legend attributes
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextSize(12);
        legend.setFormSize(20);
        legend.setFormToTextSpace(2);


    }

}