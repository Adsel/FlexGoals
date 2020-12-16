package pl.artsit.flexgoals.ui.home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.model.goal.PredefinedFinalGoal;
import pl.artsit.flexgoals.shared.Helper;
import pl.artsit.flexgoals.ui.addGoals.PreviewFinalActivity;

public class PredefinedFinalGoalsAdapter extends RecyclerView.Adapter<PredefinedFinalGoalsAdapter.ViewHolder>{

    private PredefinedFinalGoal[] localDataSet;

    public PredefinedFinalGoalsAdapter(PredefinedFinalGoal[] localDataSet) {
        this.localDataSet = localDataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_predefined_goals_final, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.predefinedfinalGoal = localDataSet[position];
        viewHolder.textViewPredefinedFinalGoalName.setText(localDataSet[position].getName());
        viewHolder.textViewPredefinedFinalGoalDesc.setText(localDataSet[position].getGoal());
        viewHolder.textViewPredefinedFinalGoalDesc2.setText(localDataSet[position].getDescription());
        viewHolder.description_day_to_change2.setText(localDataSet[position].getDays().toString());
        viewHolder.predefined_goals_limit.setVisibility(View.INVISIBLE);

        viewHolder.getDescriptionToPercentage.setText(String.valueOf(finishCount));


        Date date1 = new Date(System.currentTimeMillis());
        Date date2 = localDataSet[position].getDate();
        Helper.getDateDiff(date1, date2, TimeUnit.DAYS);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewPredefinedFinalGoalName;
        private final TextView textViewPredefinedFinalGoalDesc;
        private final TextView textViewPredefinedFinalGoalDesc2;
        private final TextView description_day_to_change2;
        private final TextView textViewPredefinedFinalGoalLimit;
        private final LinearLayout predefined_goals_limit;
        private final Button accept_button;
        private PredefinedFinalGoal predefinedfinalGoal;



        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            textViewPredefinedFinalGoalName = view.findViewById(R.id.textViewPredefinedFinalGoalName);
            textViewPredefinedFinalGoalDesc = view.findViewById(R.id.textViewPredefinedFinalGoalDesc);
            textViewPredefinedFinalGoalDesc2 = view.findViewById(R.id.textViewPredefinedFinalGoalDesc2);
            description_day_to_change2 = view.findViewById(R.id.description_day_to_change2);
            textViewPredefinedFinalGoalLimit = view.findViewById(R.id.textViewPredefinedFinalGoalLimit);
            predefined_goals_limit = view.findViewById(R.id.predefined_goals_limit);
            accept_button = view.findViewById(R.id.accept_button);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.previewPredefinedFinalGoal = predefinedfinalGoal;
                    Intent intent = new Intent(view.getContext(), PreviewFinalActivity.class);
                    view.getContext().startActivity(intent);
                }
            });
            accept_button.setOnClickListener(v -> {
                MainActivity.previewPredefinedFinalGoal = predefinedfinalGoal;
                MainActivity.previewGoalType = MainActivity.GOAL_TYPE.FINAL;
                Navigation.findNavController(v).navigate(R.id.nav_edit_goal);
            });

        }
    }
}
