package pl.artsit.flexgoals.ui.editGoals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.http.HttpClient;
import pl.artsit.flexgoals.http.goals.AddGoalCallback;
import pl.artsit.flexgoals.http.goals.GoalUpdateCallback;
import pl.artsit.flexgoals.model.ModalWidgets;
import pl.artsit.flexgoals.model.goal.FinalGoal;
import pl.artsit.flexgoals.model.goal.FinalGoalData;
import pl.artsit.flexgoals.model.goal.QuantitativeGoal;

public class EditGoalsFragment extends Fragment implements AddGoalCallback, GoalUpdateCallback {

    private EditText newGoalName;
    private EditText newGoalDesc;
    private EditText newGoalTarget;
    private EditText newGoalDays;
    private ModalWidgets modal;
    private EditGoalsViewModel editGoalsViewModel;

    @Override
    public void goToMain() {
        Navigation.findNavController(this.getView()).navigate(R.id.nav_home);
    }

    @Override
    public void informAboutFailed() {

    }

    private enum GOAL_TYPE {
        FINAL,
        QUANTITATIVE
    }

    private GOAL_TYPE currentTaskType;

    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        editGoalsViewModel = new ViewModelProvider(this).get(EditGoalsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_edit_goals, container, false);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.add_goals_options));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        newGoalName = root.findViewById(R.id.newGoalName);
        newGoalDesc = root.findViewById(R.id.editTextAddGoalsQDescription);
        newGoalTarget = root.findViewById(R.id.editTextAddGoalsQTarget);
        newGoalDays = root.findViewById(R.id.editTextAddGoalsQDays);
        modal = new ModalWidgets(root.getContext());

        View view = this.getView();
        root.findViewById(R.id.buttonAddGoalsSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO Callback
                goToMain();


            }
        });

//        ModalWidgets modalWidgets = new ModalWidgets(root.getContext());
//        modalWidgets.setBackColor(textViewAddingGoalsTitle,R.color.addGoals,R.drawable.title );
//        modalWidgets.setBackColor(textViewAddGoalsMess1,R.color.addGoals, R.drawable.title);
//        modalWidgets.setBackColor(editTextAddGoalsMess3,R.color.addGoals, R.drawable.edit_round_flat);
//        modalWidgets.setBackColor(editTextAddGoalsName,R.color.colorPrimary, R.drawable.edit_round_flat);
//        modalWidgets.setBackColor(addGoalsName,R.color.addGoals, R.drawable.edit_round_flat);

        return root;
    }

    public void createGoal(View view) {
        String name = newGoalName.getText().toString();
        String description = newGoalDesc.getText().toString();
        String goal = newGoalTarget.getText().toString();
        int days = Integer.parseInt(newGoalDays.getText().toString());

        boolean isCorrect = true;
        if (name.equals("") || description.equals("") || goal.equals("") || days <= 0){
            isCorrect = false;
        }

        if (isCorrect) {
            if (this.currentTaskType == GOAL_TYPE.FINAL) {
                //TODO to change
                new HttpClient().addFinalGoal(
                        this, new FinalGoalData(
                                MainActivity.currentUser.getId(), name,
                                description, goal, days
                        )
                );
                Navigation.findNavController(view).navigate(R.id.nav_home);
            } else if (this.currentTaskType == GOAL_TYPE.QUANTITATIVE) {
                Integer step = Integer.parseInt(newGoalDays.getText().toString());
                if (step > 0){
                    //TODO to edit
//                    new HttpClient().saveQuantitativeGoal(this, new QuantitativeGoal(
//                                        MainActivity.GOAL_ID,
//                                        name,
//                                        description,
//                                        MainActivity.currentUser.getId(),
//                                days, goal, step
//                            )
//                    );
                    Navigation.findNavController(view).navigate(R.id.nav_home);
                } else {
                    notify(getString(R.string.incorrect_data));
                }

            }
        } else {
            getString(R.string.incorrect_data);
        }
    }

    public void notify(String msg) {
        this.modal.showToast(msg);
    }

    @Override
    public void onAddedFinalGoalCallback(FinalGoal finalGoalData) {
        notify(getString(R.string.added_goal));
    }

    @Override
    public void onAddedQuantitativeGoalCallback(QuantitativeGoal quantitativeGoalData) {
        notify(getString(R.string.added_goal));
    }
}