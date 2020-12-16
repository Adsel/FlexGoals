package pl.artsit.flexgoals.ui.goals;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.model.goal.FinalGoal;
import pl.artsit.flexgoals.shared.Helper;

public class FinalGoalsAdapter extends RecyclerView.Adapter<FinalGoalsAdapter.ViewHolder> {

    private FinalGoal[] localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private  TextView nameOfGoal;
        private TextView descriptionOfGoal;
        private ProgressBar progressBar;
        private TextView descriptionDayToChange;
        private TextView getDescriptionToPercentage;
        private FinalGoal finalGoal;


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
                    MainActivity.previewFinalGoal = finalGoal;
                    Intent intent = new Intent(view.getContext(), PreviewFinalActivity.class);
                    view.getContext().startActivity(intent);
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
    public FinalGoalsAdapter(FinalGoal[] dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_final_goal, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // ADD DATA:
        //        viewHolder.getTextView().setText(localDataSet[position].getName());
        //        viewHolder.getTextView().setText(localDataSet[position].getDescription());
        viewHolder.finalGoal = localDataSet[position];
        viewHolder.getNameOfGoal().setText(localDataSet[position].getName());
        viewHolder.descriptionOfGoal.setText(localDataSet[position].getDescription());
        viewHolder.descriptionDayToChange.setText(localDataSet[position].getDays().toString());

        int progressCount = 0;
        String progress = localDataSet[position].getProgress();
        for(int i =0; i<progress.length();i++){
            if(progress.matches("1")){
                progressCount+=1;
            }
        }
        int finishCount = progressCount/localDataSet[position].getDays();
        viewHolder.progressBar.setProgress(finishCount);

      //  finishCount=finishCount*100;

        viewHolder.getDescriptionToPercentage.setText(String.valueOf(finishCount));


        Date date1 = new Date(System.currentTimeMillis());
        Date date2 = localDataSet[position].getDate();
        Helper.getDateDiff(date1, date2, TimeUnit.DAYS);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}

