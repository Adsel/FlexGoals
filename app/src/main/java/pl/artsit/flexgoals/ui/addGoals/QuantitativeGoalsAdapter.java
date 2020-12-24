package pl.artsit.flexgoals.ui.addGoals;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.InputType;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import pl.artsit.flexgoals.http.goals.GoalAchieveCallback;
import pl.artsit.flexgoals.http.services.QuantitativeGoalService;
import pl.artsit.flexgoals.model.ModalWidgets;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoalFlag;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoalProgress;
import pl.artsit.flexgoals.shared.Helper;

import static pl.artsit.flexgoals.shared.Helper.GOAL_FINISHED;

public class QuantitativeGoalsAdapter extends RecyclerView.Adapter<QuantitativeGoalsAdapter.ViewHolder> {

    private QuantitativeGoalFlag[] localDataSet;
    private ModalWidgets modal;
    private Context context;
    private View currentView;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */


    class ViewHolder extends RecyclerView.ViewHolder implements GoalAchieveCallback  {
        private final TextView nameOfGoal;
        private TextView descriptionOfGoal;
        private ProgressBar progressBar;
        private TextView descriptionDayToChange;
        private TextView getDescriptionToPercentage;
        private QuantitativeGoalFlag quantitativeGoal;
        private Button acceptButton;
        private TextView finishedText;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            nameOfGoal = view.findViewById(R.id.name_of_goal);
            descriptionOfGoal = view.findViewById(R.id.description_of_goal);
            progressBar = view.findViewById(R.id.progress_bar);
            descriptionDayToChange = view.findViewById(R.id.description_day_to_change);
            getDescriptionToPercentage = view.findViewById(R.id.description_to_change_percent);
            acceptButton = view.findViewById(R.id.accept_quantitative_button);
            finishedText = view.findViewById(R.id.view_finished);

            view.setOnClickListener(v -> {
                MainActivity.previewQuantitativeGoal = quantitativeGoal;
                Intent intent = new Intent(view.getContext(), PreviewQuantitativeActivity.class);
                view.getContext().startActivity(intent);
            });

            ((Button) view.findViewById(R.id.edit_button)).setOnClickListener((View.OnClickListener) v -> {
                MainActivity.previewQuantitativeGoal = quantitativeGoal;
                MainActivity.previewGoalType = MainActivity.GOAL_TYPE.QUANTITATIVE;
                Navigation.findNavController(v).navigate(R.id.nav_edit_goal);
            });

            acceptButton.setOnClickListener((View.OnClickListener) v -> makePromptWithGoalAchievement(v, quantitativeGoal));
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
                    modal.showToast(context.getString(R.string.quantitative_progress_modal_invalid));
                }
            }
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
        modal = new ModalWidgets(view.getContext());
        context = view.getContext();
        currentView = view;

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.quantitativeGoal = localDataSet[position];

        viewHolder.getNameOfGoal().setText(localDataSet[position].getName());
        viewHolder.descriptionOfGoal.setText(localDataSet[position].getDescription());
        viewHolder.descriptionDayToChange.setText(localDataSet[position].getDays().toString());

        if (viewHolder.quantitativeGoal.getFlag() < 0) {
            System.out.println(viewHolder.quantitativeGoal.getFlag() + "   " + viewHolder.quantitativeGoal.getName());

            viewHolder.acceptButton.setVisibility(View.GONE);

            if (viewHolder.quantitativeGoal.getFlag() == GOAL_FINISHED) {
                viewHolder.finishedText.setVisibility(View.VISIBLE);
            }
        } else {
            viewHolder.acceptButton.setVisibility(View.VISIBLE);
        }

        Date date1 = new Date(System.currentTimeMillis());
        Date date2 = localDataSet[position].getDate();
        Helper.getDateDiff(date1, date2, TimeUnit.DAYS);

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
