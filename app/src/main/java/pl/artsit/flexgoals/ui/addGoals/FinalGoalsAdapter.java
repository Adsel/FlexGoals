package pl.artsit.flexgoals.ui.addGoals;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.http.services.FinalGoalService;
import pl.artsit.flexgoals.http.goals.GoalAchieveCallback;
import pl.artsit.flexgoals.model.goal.finals.FinalGoalFlag;
import pl.artsit.flexgoals.shared.Helper;

import static pl.artsit.flexgoals.shared.Helper.GOAL_FINISHED;

public class FinalGoalsAdapter extends RecyclerView.Adapter<FinalGoalsAdapter.ViewHolder>  {
    private static final char PROGRESS_DONE = '1';
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
        private ConstraintLayout constraintLayout;
        private Button acceptButton;
        private LinearLayout item;



        @RequiresApi(api = Build.VERSION_CODES.O)
        public ViewHolder(final View view) {
            super(view);

            nameOfGoal = view.findViewById(R.id.name_of_goal);
            descriptionOfGoal = view.findViewById(R.id.description_of_goal);
            progressBar = view.findViewById(R.id.progress_bar);
            descriptionDayToChange = view.findViewById(R.id.description_day_to_change);
            getDescriptionToPercentage = view.findViewById(R.id.description_to_change_percent);
            constraintLayout = view.findViewById(R.id.parent_layout);
            currentView = view;
            acceptButton = view.findViewById(R.id.accept_button);
            item = view.findViewById(R.id.item_linear);

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

        @RequiresApi(api = Build.VERSION_CODES.O)
        private void addActions() {
            Dialog myDialog;
            myDialog = new Dialog(currentView.getContext());
            myDialog.setContentView(R.layout.end_task);
            Button myResults = (Button) myDialog.findViewById(R.id.button4);
            Button close = (Button) myDialog.findViewById(R.id.button5);

            myResults.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.previewFinalGoal = finalGoal;
                    Intent intent = new Intent(currentView.getContext(), PreviewFinalActivity.class);
                    currentView.getContext().startActivity(intent);
                }
            });
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.hide();
                }
            });
            currentView.setOnClickListener((View v) -> {

                if(getGetDescriptionToPercentage().getText().toString()== "100%"){
                    myDialog.show();
                    System.out.println("Cel zostal zakonczony !!! ");
                }else {
                    MainActivity.previewFinalGoal = finalGoal;
                    Intent intent = new Intent(currentView.getContext(), PreviewFinalActivity.class);
                    currentView.getContext().startActivity(intent);
                    System.out.println("Nie zakonczono celu !!! " );
                }


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
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_final_goal, viewGroup, false);


        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.finalGoal = localDataSet[position];
        if (viewHolder.finalGoal.getFlag() < 0) {
            viewHolder.currentView.findViewById(R.id.accept_button).setVisibility(View.GONE);

            if (viewHolder.finalGoal.getFlag() == Helper.GOAL_FINISHED) {

                viewHolder.currentView.findViewById(R.id.view_finished).setVisibility(View.VISIBLE);
            }
        } else {
            (viewHolder.currentView.findViewById(R.id.accept_button)).setVisibility(View.VISIBLE);
        }
        viewHolder.getNameOfGoal().setText(localDataSet[position].getName());
        viewHolder.descriptionOfGoal.setText(localDataSet[position].getDescription());


        int progressCount = getProgressCount(localDataSet[position].getProgress());
        int finishCount = getProgressPercentage(progressCount, localDataSet[position].getDays());
        if (finishCount > 0) {
            viewHolder.progressBar.setProgress(finishCount);
        } else {
            viewHolder.progressBar.setProgress(1);
        }
        viewHolder.getDescriptionToPercentage.setText(finishCount + "%");

        //viewHolder.getDescriptionToPercentage.setText("100%");

        long leftDays = Helper.getLeftDays(localDataSet[position].getDate(), localDataSet[position].getDays());

        if (leftDays == 1) {
            viewHolder.descriptionDayToChange.setText(leftDays + " dzień");
        } else {
            viewHolder.descriptionDayToChange.setText(leftDays + " dni");
        }
        /* DateTimeFormatter dtf;
         LocalDateTime now;
        dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        now = LocalDateTime.now();
        System.out.println();*/
        if(viewHolder.getDescriptionToPercentage.getText() == "100%"){
            viewHolder.acceptButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog myDialog;
                    Context context = null;
                    myDialog = new Dialog(context);
                    myDialog.setContentView(R.layout.end_task);

                    viewHolder.item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.show();
                        }
                    });
                }
            });
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }

    private int getProgressCount(String progress) {
        int progressCount = 0;
        for (int i = 0; i < progress.length(); i++){
            if (progress.charAt(i) == PROGRESS_DONE){
                progressCount += 1;
            }
        }

        return progressCount;
    }

    private int getProgressPercentage(int progressCount, int daysCount) {
        return progressCount * 100 / daysCount;
    }
}

