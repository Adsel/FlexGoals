package pl.artsit.flexgoals.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.model.goal.finals.PredefinedFinalGoal;

public class PredefinedFinalGoalsAdapter extends RecyclerView.Adapter<PredefinedFinalGoalsAdapter.ViewHolder>  {
    private List<PredefinedFinalGoal> localDataSet;
    private Context context;
    private View currentView;
    private TextView name;
    private TextView desc;
    private Button useBtn;

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.name_of_goal_f);
            desc = view.findViewById(R.id.description_of_goal_f);
            useBtn = view.findViewById(R.id.use_pre_f_button);
        }

        public TextView getNameOfGoal() {
            return name;
        }

        public TextView getDescOfGoal() {
            return desc;
        }
    }

    public PredefinedFinalGoalsAdapter(PredefinedFinalGoal[] dataSet) {
        List<PredefinedFinalGoal> data = new ArrayList<>();
        for (int i = 0; i < dataSet.length; i++) {
            data.add(dataSet[i]);
        }
        localDataSet = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_pre_final_goal, viewGroup, false);

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
