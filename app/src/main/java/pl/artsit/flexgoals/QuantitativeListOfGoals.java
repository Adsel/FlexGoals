package pl.artsit.flexgoals;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import pl.artsit.flexgoals.model.goal.QuantitativeGoal;


public class QuantitativeListOfGoals extends RecyclerView.Adapter<QuantitativeListOfGoals.ViewHolder>{

    private List<QuantitativeGoal> goalsQuantitativeList = new ArrayList<>();
    private Context context;
    public QuantitativeListOfGoals (List<QuantitativeGoal> goalsQuantitativeList,Context context){
        this.goalsQuantitativeList = goalsQuantitativeList;
        this.context = context;
    }
    @NonNull
    @Override
    public QuantitativeListOfGoals.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_content,parent,false);
        QuantitativeListOfGoals.ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        QuantitativeGoal positionOnList = goalsQuantitativeList.get(position);
        holder.nameOfGoal.setText(positionOnList.getName());
        holder.howToEnd.setText(positionOnList.getDays());
        holder.descriptionOfGoal.setText(positionOnList.getDescription());
        holder.howPercentage.setText(positionOnList.getProgress());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            view = holder.itemView;
            alertDialog(view,positionOnList);
        }
        });
        holder.acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return goalsQuantitativeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameOfGoal;
        TextView descriptionOfGoal;
        TextView howToEnd;
        TextView howPercentage;
        Button acceptBtn;
        Button deleteBtn;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOfGoal = itemView.findViewById(R.id.name_of_goal);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            descriptionOfGoal = itemView.findViewById(R.id.description_of_goal);
            howToEnd = itemView.findViewById(R.id.description_day_to_change);
            howPercentage = itemView.findViewById(R.id.description_to_change_percent);
            acceptBtn = itemView.findViewById(R.id.accept_button);
            deleteBtn = itemView.findViewById(R.id.delete_button);
        }
    }
    private Dialog alertDialog(View view ,QuantitativeGoal positionOnList ){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setMessage("Na pewno chcesz usunac cel?");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Tak", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                goalsQuantitativeList.remove(positionOnList.getId());
                Toast.makeText(view.getContext(), "Usunales cel", Toast.LENGTH_LONG).show();
            }
        });
        dialogBuilder.setNegativeButton("Nie", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(view.getContext(), "Dobrze jest miec jakis cel w zyciu", Toast.LENGTH_LONG).show();
            }
        });
        return dialogBuilder.create();

    }

}
