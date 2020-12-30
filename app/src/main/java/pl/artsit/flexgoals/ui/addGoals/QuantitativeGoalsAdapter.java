package pl.artsit.flexgoals.ui.addGoals;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.model.goal.QuantitativeGoalFlag;
import pl.artsit.flexgoals.shared.Helper;

public class QuantitativeGoalsAdapter extends RecyclerView.Adapter<QuantitativeGoalsAdapter.ViewHolder> {

    private QuantitativeGoalFlag[] localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */


    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameOfGoal;
        private TextView descriptionOfGoal;
        private ProgressBar progressBar;
        private TextView descriptionDayToChange;
        private TextView getDescriptionToPercentage;
        private QuantitativeGoalFlag quantitativeGoal;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            nameOfGoal = view.findViewById(R.id.name_of_goal);
            descriptionOfGoal = view.findViewById(R.id.description_of_goal);
            progressBar = view.findViewById(R.id.progress_bar);
            descriptionDayToChange = view.findViewById(R.id.description_day_to_change);
            getDescriptionToPercentage = view.findViewById(R.id.description_to_change_percent);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.previewQuantitativeGoal = quantitativeGoal;
                    Intent intent = new Intent(view.getContext(), PreviewQuantitativeActivity.class);
                    view.getContext().startActivity(intent);
                }
            });

            ((Button) view.findViewById(R.id.edit_button)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.previewQuantitativeGoal = quantitativeGoal;
                    MainActivity.previewGoalType = MainActivity.GOAL_TYPE.QUANTITATIVE;
                    Navigation.findNavController(v).navigate(R.id.nav_edit_goal);
                }
            });

            ((Button) view.findViewById(R.id.accept_button)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  //  new HttpClient().
                    // TODO:
                    // ACHIEVE
                }
            });
        }



        public TextView getNameOfGoal() {
            return nameOfGoal;
        }

        public TextView getDescriptionOfGoal() {
            return descriptionOfGoal;
        }

        public ProgressBar getProgressBar() {
            return progressBar;
        }

        public TextView getDescriptionDayToChange() {
            return descriptionDayToChange;
        }

        public TextView getGetDescriptionToPercentage() {
            return getDescriptionToPercentage;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public QuantitativeGoalsAdapter(QuantitativeGoalFlag[] dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_quantitative_goal, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.quantitativeGoal = localDataSet[position];

        viewHolder.getNameOfGoal().setText(localDataSet[position].getName());
        viewHolder.descriptionOfGoal.setText(localDataSet[position].getDescription());
        viewHolder.descriptionDayToChange.setText(localDataSet[position].getDays().toString());





        viewHolder.getGetDescriptionToPercentage().setText("");
        // viewHolder.getProgressBar().setProgress();
    }



    // ITERATING AFTER PROGRESS TABLE
    private int estaminateQuantitative(String progressString){
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

//        for(int i = 0; i < numbers.length(); i++){
//            this.labels[this.labels.length] = "Dzień " + (i + 1);
//        }
        return numbers.size();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }

}

