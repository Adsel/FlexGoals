package pl.artsit.flexgoals.ui.addGoals;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.graphics.Color;
import android.text.InputType;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
import pl.artsit.flexgoals.http.goals.GoalAchieveCallback;
import pl.artsit.flexgoals.http.services.QuantitativeGoalService;
import pl.artsit.flexgoals.model.ModalWidgets;
import pl.artsit.flexgoals.model.goal.finals.FinalGoalFlag;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoalFlag;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoalProgress;
import pl.artsit.flexgoals.shared.Helper;

public class QuantitativeGoalsAdapter extends RecyclerView.Adapter<QuantitativeGoalsAdapter.ViewHolder> {

    private List<QuantitativeGoalFlag> localDataSet;
    private ModalWidgets modal;
    private Context context;
    private Button deleteButton;
    private View currentView;


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */


    class ViewHolder extends RecyclerView.ViewHolder implements GoalAchieveCallback,
            DeleteGoalCallback {
        private final TextView nameOfGoal;
        private TextView descriptionOfGoal;
        private ProgressBar progressBar;
        private TextView descriptionDayToChange;
        private TextView getDescriptionToPercentage;
        private QuantitativeGoalFlag quantitativeGoal;
        private View currentView;
        private Button acceptButton;
        private TextView finishedText;
        private ConstraintLayout layout;
        private RelativeLayout endTask;
        private QuantitativeGoalsAdapter parent;

        public ViewHolder(View view) {
            super(view);

            endTask = itemView.findViewById(R.id.end_task);
            nameOfGoal = view.findViewById(R.id.name_of_goal);
            descriptionOfGoal = view.findViewById(R.id.description_of_goal);
            progressBar = view.findViewById(R.id.progress_bar);
            descriptionDayToChange = view.findViewById(R.id.description_day_to_change);
            getDescriptionToPercentage = view.findViewById(R.id.description_to_change_percent);
            acceptButton = view.findViewById(R.id.accept_quantitative_button_s);
            finishedText = view.findViewById(R.id.view_finished);
            deleteButton = view.findViewById(R.id.delete_button);
            currentView = view;

            deleteButton.setOnClickListener((View v) ->
                    new QuantitativeGoalService().deleteQuantitativeGoal(this, quantitativeGoal)
            );
            layout = view.findViewById(R.id.parent_layout);

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

        private void addActions() {
            currentView.setOnClickListener(v -> {
                if (quantitativeGoal.getFlag() == Helper.GOAL_FINISHED){
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

            currentView.findViewById(R.id.edit_button).setOnClickListener(v -> {
                MainActivity.previewQuantitativeGoal = quantitativeGoal;
                MainActivity.previewGoalType = MainActivity.GOAL_TYPE.QUANTITATIVE;
                Navigation.findNavController(v).navigate(R.id.nav_edit_goal);
            });

            acceptButton.setOnClickListener(v -> makePromptWithGoalAchievement(v, quantitativeGoal));
        }

        private void goToPreview() {
            MainActivity.previewQuantitativeGoal = quantitativeGoal;
            Intent intent = new Intent(currentView.getContext(), PreviewQuantitativeActivity.class);
            currentView.getContext().startActivity(intent);
        }

        private void makePromptWithGoalAchievement(View view, QuantitativeGoalFlag quantitativeGoal) {
            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(view.getContext(), R.style.FlexGoalsAlertDialog));
            builder.setTitle(R.string.quantitative_progress_dialog_title);

            final EditText input = new EditText(view.getContext());
            input.setTextColor(Color.WHITE);
            input.setWidth(100);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton(R.string.quantitative_progress_dialog_accept, (dialog, which) -> { saveProgress(input.getText().toString(), quantitativeGoal);
            });
            builder.setNegativeButton(R.string.quantitative_progress_dialog_cancel, (dialog, which) -> dialog.cancel());

            builder.show();
        }

        @Override
        public void informAboutGoalUpdated() {
            acceptButton.setVisibility(View.GONE);
            modal.showToast(context.getString(R.string.quantitative_progress_modal_saved));
        }

        @Override
        public void informAboutFailedUpdated() {
            modal.showToast(context.getString(R.string.quantitative_progress_modal_failed));
        }

        private void saveProgress(String progressStr, QuantitativeGoalFlag quantitativeGoalFlag) {
            if (!progressStr.equals("")) {
                Integer progress = Integer.parseInt(progressStr);
                if (progress > 0) {
                    new QuantitativeGoalService().scoreQuantitativeGoal(
                            this, new QuantitativeGoalProgress(
                                    quantitativeGoalFlag.getId(),
                                    progress
                            ));
                } else {
                    modal.showToast("invalid");
                }
            }
        }

        @Override
        public void deleteFinalCallback(FinalGoalFlag finalGoalId) {

        }

        @Override
        public void deleteQuantitativeCallback(QuantitativeGoalFlag deletedQuantitativeGoal) {
            parent.localDataSet.remove(deletedQuantitativeGoal);
            parent.notifyItemRemoved(getAdapterPosition());
            parent.notifyDataSetChanged();
            modal.showToast(currentView.getContext().getString(R.string.deleted_quantitative_goal));
        }

        @Override
        public void informAboutFailedDeleteFinalGoal() {

        }

        @Override
        public void informAboutFailedDeleteQuantitativeGoal() {
            modal.showToast(currentView.getContext().getString(R.string.failed_delete_goal));
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public QuantitativeGoalsAdapter(QuantitativeGoalFlag[] dataSet) {
        List<QuantitativeGoalFlag> data = new ArrayList<>();
        for (int i = 0; i < dataSet.length; i++) {
            data.add(dataSet[i]);
        }
        localDataSet = data;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_quantitative_goal, viewGroup, false);
        modal = new ModalWidgets(view.getContext());
        context = view.getContext();

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.quantitativeGoal = localDataSet.get(position);
        viewHolder.getNameOfGoal().setText(localDataSet.get(position).getName());
        viewHolder.descriptionOfGoal.setText(localDataSet.get(position).getDescription());

        int progressCount = getProgressCount(localDataSet.get(position).getProgress(), localDataSet.get(position).getStep());
        int finishCount = getProgressPercentage(progressCount, localDataSet.get(position).getDays(), localDataSet.get(position).getStep());
        if (finishCount > 0) {
            viewHolder.progressBar.setProgress(finishCount);
        } else {
            viewHolder.progressBar.setProgress(1);
        }

        viewHolder.getDescriptionToPercentage.setText(finishCount + "%");

        if (viewHolder.quantitativeGoal.getFlag() < 0) {
            viewHolder.acceptButton.setVisibility(View.GONE);

            if (viewHolder.quantitativeGoal.getFlag() == Helper.GOAL_FINISHED) {
                viewHolder.finishedText.setVisibility(View.VISIBLE);
            } else {
                viewHolder.finishedText.setVisibility(View.GONE);
            }
        } else {
            viewHolder.acceptButton.setVisibility(View.VISIBLE);
            viewHolder.finishedText.setVisibility(View.GONE);
        }

        long leftDays = Helper.getLeftDays(localDataSet.get(position).getDate(), localDataSet.get(position).getDays());

        if (leftDays == 1) {
            viewHolder.descriptionDayToChange.setText(leftDays + " dzień");
        } else {
            viewHolder.descriptionDayToChange.setText(leftDays + " dni");
        }

        viewHolder.parent = this;
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
        return localDataSet.size();
    }
}
