package pl.artsit.flexgoals.ui.goals;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.http.goals.DeleteGoalCallback;
import pl.artsit.flexgoals.http.services.FinalGoalService;
import pl.artsit.flexgoals.http.goals.GoalAchieveCallback;
import pl.artsit.flexgoals.model.ModalWidgets;
import pl.artsit.flexgoals.model.goal.finals.FinalGoalFlag;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoalFlag;
import pl.artsit.flexgoals.shared.Helper;

import static pl.artsit.flexgoals.shared.Helper.GOAL_FINISHED;

public class FinalGoalsAdapter extends RecyclerView.Adapter<FinalGoalsAdapter.ViewHolder>  {
    private static final char PROGRESS_DONE = '1';
    private List<FinalGoalFlag> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder implements GoalAchieveCallback,
            DeleteGoalCallback {
        private TextView nameOfGoal;
        private TextView descriptionOfGoal;
        private ProgressBar progressBar;
        private TextView descriptionDayToChange;
        private TextView getDescriptionToPercentage;
        private FinalGoalFlag finalGoal;
        private View currentView;
        private Button acceptButton;
        private Button deleteButton;
        private final ModalWidgets modal;
        private FinalGoalsAdapter parent;
        private ConstraintLayout constraintLayout;
        private LinearLayout item;

        @RequiresApi(api = Build.VERSION_CODES.O)
        public ViewHolder(final View view) {
            super(view);

            nameOfGoal = view.findViewById(R.id.name_of_goal);
            descriptionOfGoal = view.findViewById(R.id.description_of_goal);
            progressBar = view.findViewById(R.id.progress_bar);
            descriptionDayToChange = view.findViewById(R.id.description_day_to_change);
            getDescriptionToPercentage = view.findViewById(R.id.description_to_change_percent);
            deleteButton = view.findViewById(R.id.delete_button);
            modal = new ModalWidgets(view.getContext());
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
            modal.showToast(Resources.getSystem().getString(R.string.failed_update_final_goal));
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        private void addActions() {
            currentView.setOnClickListener((View v) -> {
                if (finalGoal.getFlag() == GOAL_FINISHED){
                    Dialog myDialog = new Dialog(currentView.getContext());
                    myDialog.setContentView(R.layout.end_task);
                    Button myResults = myDialog.findViewById(R.id.ended_dialog_results);
                    Button close = myDialog.findViewById(R.id.ended_dialog_close);
                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    myDialog.show();
                    myResults.setOnClickListener(view -> {
                        goToPreview();
                    });

                    close.setOnClickListener(view -> myDialog.hide());
                } else {
                    goToPreview();
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

            deleteButton.setOnClickListener((View v) ->
                    new FinalGoalService().deleteFinalGoal(this, finalGoal)
            );
        }

        private void goToPreview() {
            MainActivity.previewFinalGoal = finalGoal;
            Intent intent = new Intent(currentView.getContext(), PreviewQuantitativeActivity.class);
            currentView.getContext().startActivity(intent);
        }

        @Override
        public void deleteFinalCallback(FinalGoalFlag deletedFinalGoal) {
            parent.localDataSet.remove(deletedFinalGoal);
            parent.notifyItemRemoved(getAdapterPosition());
            parent.notifyDataSetChanged();
            modal.showToast(currentView.getContext().getString(R.string.deleted_final_goal));
        }

        @Override
        public void deleteQuantitativeCallback(QuantitativeGoalFlag quantitativeGoalId) {

        }

        @Override
        public void informAboutFailedDeleteFinalGoal() {
            modal.showToast(currentView.getContext().getString(R.string.failed_delete_goal));
        }

        @Override
        public void informAboutFailedDeleteQuantitativeGoal() {

        }

    }

    public FinalGoalsAdapter(FinalGoalFlag[] dataSet) {
        List<FinalGoalFlag> data = new ArrayList<>();
        for (int i = 0; i < dataSet.length; i++) {
            data.add(dataSet[i]);
        }
        localDataSet = data;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_final_goal, viewGroup, false);

        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.finalGoal = localDataSet.get(position);
        if (viewHolder.finalGoal.getFlag() < 0) {
            viewHolder.currentView.findViewById(R.id.accept_button).setVisibility(View.GONE);

            if (viewHolder.finalGoal.getFlag() == Helper.GOAL_FINISHED) {
                viewHolder.currentView.findViewById(R.id.view_finished).setVisibility(View.VISIBLE);
            }
        } else {
            (viewHolder.currentView.findViewById(R.id.accept_button)).setVisibility(View.VISIBLE);
        }
        viewHolder.getNameOfGoal().setText(localDataSet.get(position).getName());
        viewHolder.descriptionOfGoal.setText(localDataSet.get(position).getDescription());

        int progressCount = getProgressCount(localDataSet.get(position).getProgress());
        int finishCount = getProgressPercentage(progressCount, localDataSet.get(position).getDays());
        if (finishCount > 0) {
            viewHolder.progressBar.setProgress(finishCount);
        } else {
            viewHolder.progressBar.setProgress(1);
        }
        viewHolder.getDescriptionToPercentage.setText(finishCount + "%");

        long leftDays = Helper.getLeftDays(localDataSet.get(position).getDate(), localDataSet.get(position).getDays());

        if (leftDays == 1) {
            ClassLoader context;
            viewHolder.descriptionDayToChange.setText(leftDays + viewHolder.currentView.getContext().getString(R.string.days));
        } else {
            viewHolder.descriptionDayToChange.setText(leftDays + viewHolder.currentView.getContext().getString(R.string.days));
        }
        viewHolder.parent = this;
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
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

