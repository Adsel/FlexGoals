package pl.artsit.flexgoals.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.http.HttpClient;
import pl.artsit.flexgoals.model.goal.QuantitativeGoal;

public class QuantitativeGoalsAdapter extends RecyclerView.Adapter<QuantitativeGoalsAdapter.ViewHolder>{

    private List<QuantitativeGoal> goalsList = new ArrayList<>();
    private Context context;
    public QuantitativeGoalsAdapter(List<QuantitativeGoal> goalsList, HttpClient context) {
        this.goalsList = goalsList;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_content, parent, false);
        QuantitativeGoalsAdapter.ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuantitativeGoalsAdapter.ViewHolder holder, int position) {
        QuantitativeGoal positionOne = goalsList.get(position);
        holder.nameOfGoal.setText(positionOne.getName());
        holder.descriptionOfGoal.setText(positionOne.getDescription());
        holder.descriptionDayToChange.setText(positionOne.getDays());
        holder.descriptionToChangePercent.setText(positionOne.getStep());
        System.out.println("++++" + positionOne);

    }

    @Override
    public int getItemCount() {
        return goalsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameOfGoal;
        TextView descriptionOfGoal;
        TextView descriptionDayToChange;
        TextView descriptionToChangePercent;
        Button deleteButton;
        Button acceptButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOfGoal = itemView.findViewById(R.id.name_of_goal);
            descriptionOfGoal = itemView.findViewById(R.id.description_of_goal);
            descriptionDayToChange = itemView.findViewById(R.id.description_day_to_change);
            descriptionToChangePercent = itemView.findViewById(R.id.description_to_change_percent);
            deleteButton = itemView.findViewById(R.id.delete_button);
            acceptButton = itemView.findViewById(R.id.accept_button);

        }

    }
}
