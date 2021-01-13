package pl.artsit.flexgoals.ui.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.model.goal.finals.PredefinedFinalGoal;

public class PredefinedFinalGoalsAdapter extends RecyclerView.Adapter<PredefinedFinalGoalsAdapter.ViewHolder>  {
    private List<PredefinedFinalGoal> localDataSet;
    private Context context;
    private View currentView;
    private TextView name;
    private TextView desc;
    private Button useBtn;
    private TextView finalGoalCon;

    class ViewHolder extends RecyclerView.ViewHolder {
        private PredefinedFinalGoal predefinedFinalGoal;

        public ViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.name_of_goal_f);
            desc = view.findViewById(R.id.description_of_goal_f);
            useBtn = view.findViewById(R.id.use_pre_f_button);
            finalGoalCon = view.findViewById(R.id.final_goal_con);

            useBtn.setOnClickListener(v -> {
                MainActivity.predefinedFinalGoal = predefinedFinalGoal;
                Navigation.findNavController(view).navigate(R.id.nav_add_goal);
            });
        }

        public TextView getNameOfGoal() {
            return name;
        }

        public TextView getDescOfGoal() {
            return desc;
        }
        public TextView getFinalGoalCon(){return finalGoalCon;}
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
        viewHolder.predefinedFinalGoal = localDataSet.get(position);
        viewHolder.getNameOfGoal().setText(localDataSet.get(position).getName());
        viewHolder.getDescOfGoal().setText(localDataSet.get(position).getDescription());
        viewHolder.getFinalGoalCon().setText(localDataSet.get(position).getGoal());
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
