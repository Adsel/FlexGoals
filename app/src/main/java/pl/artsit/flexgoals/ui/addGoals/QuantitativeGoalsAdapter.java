package pl.artsit.flexgoals.ui.addGoals;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
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
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.quantitativeGoal = localDataSet[position];
        viewHolder.getNameOfGoal().setText(localDataSet[position].getName());
        viewHolder.descriptionOfGoal.setText(localDataSet[position].getDescription());

        int progressCount = getProgressCount(localDataSet[position].getProgress(), localDataSet[position].getStep());
        int finishCount = getProgressPercentage(progressCount, localDataSet[position].getDays(), localDataSet[position].getStep());
        if (finishCount > 0) {
            viewHolder.progressBar.setProgress(finishCount);
        } else {
            viewHolder.progressBar.setProgress(1);
        }
        viewHolder.getDescriptionToPercentage.setText(finishCount + "%");

        long leftDays = Helper.getLeftDays(localDataSet[position].getDate(), localDataSet[position].getDays());

        if (leftDays == 1) {
            viewHolder.descriptionDayToChange.setText(leftDays + " dzień");
        } else {
            viewHolder.descriptionDayToChange.setText(leftDays + " dni");
        }
    }

    private int getProgressCount(String progress, int step) {
        int progressCount = 0;

        String[] progressStr = progress.split(",");
        for (String prog: progressStr) {
            Integer temp = Integer.parseInt(prog);
            if (temp > step) {
                temp = step;
            }
            progressCount += temp;
        }

        return progressCount;
    }

    private int getProgressPercentage(int progressCount, int daysCount, int step) {
        return progressCount * 100 / (daysCount * step);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }

}

