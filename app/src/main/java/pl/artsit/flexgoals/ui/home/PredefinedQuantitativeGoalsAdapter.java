package pl.artsit.flexgoals.ui.home;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.http.goals.DeleteGoalCallback;
import pl.artsit.flexgoals.http.goals.GoalAchieveCallback;
import pl.artsit.flexgoals.model.ModalWidgets;
import pl.artsit.flexgoals.model.goal.quantitative.PredefinedQuantitativeGoal;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoalFlag;
import pl.artsit.flexgoals.shared.Helper;
import pl.artsit.flexgoals.ui.addGoals.FinalGoalsAdapter;

public class PredefinedQuantitativeGoalsAdapter extends RecyclerView.Adapter<PredefinedQuantitativeGoalsAdapter.ViewHolder>  {
    private List<PredefinedQuantitativeGoal> localDataSet;
    private Context context;
    private View currentView;
    private TextView name;
    private TextView desc;
    private Button useBtn;

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.name_of_goal_q);
            desc = view.findViewById(R.id.description_of_goal_q);
            useBtn = view.findViewById(R.id.use_pre_quantitative_button);
        }

        public TextView getNameOfGoal() {
            return name;
        }

        public TextView getDescOfGoal() {
            return desc;
        }
    }

    public PredefinedQuantitativeGoalsAdapter(PredefinedQuantitativeGoal[] dataSet) {
        List<PredefinedQuantitativeGoal> data = new ArrayList<>();
        for (int i = 0; i < dataSet.length; i++) {
            data.add(dataSet[i]);
        }
        localDataSet = data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_pre_quantitative_goal, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getNameOfGoal().setText(localDataSet.get(position).getName());
        viewHolder.getDescOfGoal().setText(localDataSet.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
