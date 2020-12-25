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

import java.util.Date;
import java.util.concurrent.TimeUnit;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.http.services.FinalGoalService;
import pl.artsit.flexgoals.http.services.HttpClient;
import pl.artsit.flexgoals.http.goals.GoalAchieveCallback;
import pl.artsit.flexgoals.model.goal.FinalGoalFlag;
import pl.artsit.flexgoals.shared.Helper;

public class FinalGoalsAdapter extends RecyclerView.Adapter<FinalGoalsAdapter.ViewHolder> {
    private static final Integer GOAL_FINISHED = -1;
    private static final Integer GOAL_ACHIEVED = -2;
    private FinalGoalFlag[] localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements GoalAchieveCallback{
        private  TextView nameOfGoal;
        private TextView descriptionOfGoal;
        private ProgressBar progressBar;
        private TextView descriptionDayToChange;
        private TextView getDescriptionToPercentage;
        private FinalGoalFlag finalGoal;
        private View currentView;

        public ViewHolder(final View view) {
            super(view);

            nameOfGoal = view.findViewById(R.id.name_of_goal);
            descriptionOfGoal = view.findViewById(R.id.description_of_goal);
            progressBar = view.findViewById(R.id.progress_bar);
            descriptionDayToChange = view.findViewById(R.id.description_day_to_change);
            getDescriptionToPercentage = view.findViewById(R.id.description_to_change_percent);
            currentView = view;

            addActions();
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

        @Override
        public void informAboutGoalUpdated() {
            currentView.findViewById(R.id.accept_button).setVisibility(View.GONE);
        }

        @Override
        public void informAboutFailedUpdated() {
            // TODO:
            // TOAST
        }

        private void addActions() {
            currentView.setOnClickListener((View v) -> {
                MainActivity.previewFinalGoal = finalGoal;
                Intent intent = new Intent(currentView.getContext(), PreviewFinalActivity.class);
                currentView.getContext().startActivity(intent);
            });

            currentView.findViewById(R.id.edit_button).setOnClickListener((View v) -> {
                MainActivity.previewFinalGoal = finalGoal;
                MainActivity.previewGoalType = MainActivity.GOAL_TYPE.FINAL;
                Navigation.findNavController(v).navigate(R.id.nav_edit_goal);
            });

            currentView.findViewById(R.id.accept_button).setOnClickListener((View v) ->
                    new FinalGoalService().scoreFinalGoal(this, finalGoal.getId())
            );
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public FinalGoalsAdapter(FinalGoalFlag[] dataSet) {
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

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.finalGoal = localDataSet[position];
        if (viewHolder.finalGoal.getFlag() < 0) {
            viewHolder.currentView.findViewById(R.id.accept_button).setVisibility(View.GONE);

            if (viewHolder.finalGoal.getFlag() == GOAL_FINISHED) {
                viewHolder.currentView.findViewById(R.id.view_finished).setVisibility(View.VISIBLE);
            }
        }
        viewHolder.getNameOfGoal().setText(localDataSet[position].getName());
        viewHolder.descriptionOfGoal.setText(localDataSet[position].getDescription());
        viewHolder.descriptionDayToChange.setText(localDataSet[position].getDays().toString());

        int progressCount = 0;
        String progress = localDataSet[position].getProgress();
        for(int i = 0; i < progress.length();i++){
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

