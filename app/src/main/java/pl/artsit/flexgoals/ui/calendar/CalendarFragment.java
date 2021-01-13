package pl.artsit.flexgoals.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.savvi.rangedatepicker.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.http.goals.GoalGetCallback;
import pl.artsit.flexgoals.http.services.FinalGoalService;
import pl.artsit.flexgoals.http.services.QuantitativeGoalService;
import pl.artsit.flexgoals.model.ModalWidgets;
import pl.artsit.flexgoals.model.goal.finals.FinalGoalFlag;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoalFlag;

public class CalendarFragment<T> extends Fragment implements GoalGetCallback {

    CalendarPickerView calendar;
    List<FinalGoalFlag> finalGoals =new ArrayList<>();
    List<QuantitativeGoalFlag> quantitativeGoals =new ArrayList<>();


    Button button;
    int typeGoal = 0;
    Button buttonPrev;
    Button buttonNext;
    int indexGoal;
    TextView textViewCalendarTitle;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CalendarViewModel homeViewModel = new ViewModelProvider(this).get(CalendarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calender, container, false);

        ModalWidgets modalWidgets = new ModalWidgets(getContext());

        calendar = root.findViewById(R.id.calendar_view);
        button = root.findViewById(R.id.get_selected_dates);
        buttonPrev = root.findViewById(R.id.buttonCalendarPrev);
        buttonNext = root.findViewById(R.id.buttonCalendarNext);
        textViewCalendarTitle = root.findViewById(R.id.textViewCalendarTitle);

        new FinalGoalService().getFinalGoals(this, MainActivity.currentUser);
        new QuantitativeGoalService().getQuantitativeGoals(this, MainActivity.currentUser);

        setCalendarDate(new Date(),new Date());




        buttonNext.setOnClickListener(view ->{
            if (typeGoal == 1){
                if(!finalGoals.isEmpty()){
                    if( finalGoals.size() -1 > indexGoal ){
                        indexGoal++;
                        setCalendarDate(
                                finalGoals.get(indexGoal).getDate(),
                                addDays(finalGoals.get(indexGoal).getDate(),
                                        finalGoals.get(indexGoal).getDays()));
                        textViewCalendarTitle.setText(finalGoals.get(indexGoal).getName());
                    }

                }
            } else if(typeGoal == 2) {
                if(!quantitativeGoals.isEmpty()){
                    if( quantitativeGoals.size() -1 > indexGoal ){
                        indexGoal++;
                        setCalendarDate(
                                quantitativeGoals.get(indexGoal).getDate(),
                                addDays(quantitativeGoals.get(indexGoal).getDate(),
                                        quantitativeGoals.get(indexGoal).getDays()));
                        textViewCalendarTitle.setText(quantitativeGoals.get(indexGoal).getName());
                    }
                }
            }
        });

        buttonPrev.setOnClickListener(view ->{
            if (typeGoal == 1){
                typeGoal=1;
                if(!finalGoals.isEmpty()){
                    if( 0 < indexGoal ){
                        indexGoal--;
                        setCalendarDate(
                                finalGoals.get(indexGoal).getDate(),
                                addDays(finalGoals.get(indexGoal).getDate(),
                                        finalGoals.get(indexGoal).getDays())
                        );
                        textViewCalendarTitle.setText(finalGoals.get(indexGoal).getName());
                    }
                }
            } else if(typeGoal == 2){
                if(!quantitativeGoals.isEmpty()){
                    typeGoal=2;
                    if( 0 < indexGoal ){
                        indexGoal--;
                        setCalendarDate(
                                quantitativeGoals.get(indexGoal).getDate(),
                                addDays(quantitativeGoals.get(indexGoal).getDate(),
                                        quantitativeGoals.get(indexGoal).getDays())
                        );
                        textViewCalendarTitle.setText(quantitativeGoals.get(indexGoal).getName());
                    }
                }
            }
        });

        button.setOnClickListener(view -> {
            if (typeGoal == 2 || typeGoal == 0){
                if(!finalGoals.isEmpty()){
                    button.setText(getResources().getText(R.string.statistic_final));
                    typeGoal=1;
                    indexGoal = 0;
                    setCalendarDate(
                            finalGoals.get(0).getDate(),
                            addDays(finalGoals.get(0).getDate(),
                                    finalGoals.get(0).getDays())
                    );
                    textViewCalendarTitle.setText(finalGoals.get(indexGoal).getName());


                } else {
                    modalWidgets.showToast(getResources().getText(R.string.calendar_final_empty).toString());
                }

            } else if(typeGoal == 1){
                if(!quantitativeGoals.isEmpty()) {
                    button.setText(getResources().getText(R.string.statistic_quantity));
                    typeGoal=2;
                    indexGoal = 0;
                    setCalendarDate(
                            quantitativeGoals.get(0).getDate(),
                            addDays(quantitativeGoals.get(0).getDate(),
                                    quantitativeGoals.get(0).getDays())
                    );
                    textViewCalendarTitle.setText(quantitativeGoals.get(indexGoal).getName());

                } else{
                    modalWidgets.showToast(getResources().getText(R.string.calendar_quantitative_empty).toString());
                }
            }

            //Toast.makeText(getContext(), "list " + calendar.getSelectedDates().toString(), Toast.LENGTH_LONG).show();
        });
        return root;
    }



    public static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);


        return cal.getTime();
    }

    void setCalendarDate(Date start, Date end){
            ArrayList<Date> arrayList = new ArrayList<>();
            arrayList.add(start);

            if(end == null){
                arrayList.add(start);
            } else {
                arrayList.add(end);
            }

        calendar.init(addDays(start,-31), addDays(end,31), new SimpleDateFormat("MMMM, YYYY", Locale.getDefault())) //
                .inMode(CalendarPickerView.SelectionMode.RANGE)
                .withSelectedDates(arrayList)
                .displayOnly();

        calendar.scrollToDate(start);

    }


    @Override
    public void informAboutFailedGetFinalGoals() {

    }

    @Override
    public void drawFinalGoals(FinalGoalFlag[] finalGoals) {
        for (int i =0; i < finalGoals.length; i++) {
            System.out.println(finalGoals[i].getDate() + " Final " + finalGoals[i].getDays());
            this.finalGoals.add(finalGoals[i]);
        }
    }

    @Override
    public void informAboutEmptyFinalGoals() {

    }

    @Override
    public void informAboutFailedGetQuantitativeGoals() {

    }

    @Override
    public void drawQuantitativeGoals(QuantitativeGoalFlag[] quantitativeGoals) {
        for (int i =0; i < quantitativeGoals.length; i++) {
            System.out.println(quantitativeGoals[i].getDate() + " Quanti " + quantitativeGoals[i].getDays());
            this.quantitativeGoals.add(quantitativeGoals[i]);
        }
    }

    @Override
    public void informAboutEmptyQuantitativeGoals() {

    }

    @Override
    public void informAboutFailedPreview(String flag) {

    }

    @Override
    public void drawQuantitativeProgress() {

    }

    @Override
    public void drawFinalProgress() {

    }

}