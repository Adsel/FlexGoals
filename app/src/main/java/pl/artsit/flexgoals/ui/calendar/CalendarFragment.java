package pl.artsit.flexgoals.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.savvi.rangedatepicker.CalendarPickerView;
import com.savvi.rangedatepicker.SubTitle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import pl.artsit.flexgoals.R;

public class CalendarFragment extends Fragment {

    private CalendarViewModel homeViewModel;

    CalendarPickerView calendar;
    Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(CalendarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calender, container, false);

        final TextView textView = root.findViewById(R.id.text_home);

        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.MONTH, 1);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.MONTH, - 1);

        calendar = root.findViewById(R.id.calendar_view);
        button = root.findViewById(R.id.get_selected_dates);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);

        calendar.deactivateDates(list);
        ArrayList<Date> arrayList = new ArrayList<>();
        try {
            SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");

            String strdate = "22-12-2020";
            String strdate2 = "26-12-2020";

            Date newdate = dateformat.parse(strdate);
            Date newdate2 = dateformat.parse(strdate2);
            arrayList.add(newdate);
            arrayList.add(newdate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendar.init(lastYear.getTime(), nextYear.getTime(), new SimpleDateFormat("MMMM, YYYY", Locale.getDefault())) //
                .inMode(CalendarPickerView.SelectionMode.RANGE) //
                .withDeactivateDates(list)
                .withSubTitles(getSubTitles())
                .withSelectedDates(arrayList)
                .displayOnly();

        calendar.scrollToDate(new Date());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "list " + calendar.getSelectedDates().toString(), Toast.LENGTH_LONG).show();
            }
        });
        return root;
    }

    private ArrayList<SubTitle> getSubTitles() {
        final ArrayList<SubTitle> subTitles = new ArrayList<>();
        final Calendar tmrw = Calendar.getInstance();
        tmrw.add(Calendar.DAY_OF_MONTH, 1);
        subTitles.add(new SubTitle(tmrw.getTime(), "₹1000"));
        return subTitles;
    }
}