package pl.artsit.flexgoals.ui.editGoals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.http.services.HttpClient;
import pl.artsit.flexgoals.http.goals.AddGoalCallback;
import pl.artsit.flexgoals.model.ModalWidgets;
import pl.artsit.flexgoals.model.goal.FinalGoal;
import pl.artsit.flexgoals.model.goal.FinalGoalData;
import pl.artsit.flexgoals.model.goal.QuantitativeGoal;
import pl.artsit.flexgoals.model.goal.QuantitativeGoalData;

public class AddGoalsFragment extends Fragment implements AddGoalCallback {

    private EditText newGoalName;
    private EditText newGoalDesc;
    private EditText newGoalTarget;
    private EditText newGoalDays;
    private ModalWidgets modal;
    private AddGoalsViewModel addGoalsViewModel;

    private enum GOAL_TYPE {
        FINAL,
        QUANTITATIVE
    }

    private GOAL_TYPE currentTaskType;

    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        addGoalsViewModel = new ViewModelProvider(this).get(AddGoalsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_goals, container, false);

        final Spinner spinnerAddGoals = root.findViewById(R.id.spinnerAddGoals);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.add_goals_options));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAddGoals.setAdapter(adapter);

        newGoalName = root.findViewById(R.id.newGoalName);
        newGoalDesc = root.findViewById(R.id.editTextAddGoalsQDescription);
        newGoalTarget = root.findViewById(R.id.editTextAddGoalsQTarget);
        newGoalDays = root.findViewById(R.id.editTextAddGoalsQDays);
        modal = new ModalWidgets(root.getContext());

        View view = this.getView();
        root.findViewById(R.id.buttonAddGoalsSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createGoal(v);
            }
        });

//        ModalWidgets modalWidgets = new ModalWidgets(root.getContext());
//        modalWidgets.setBackColor(textViewAddingGoalsTitle,R.color.addGoals,R.drawable.title );
//        modalWidgets.setBackColor(textViewAddGoalsMess1,R.color.addGoals, R.drawable.title);
//        modalWidgets.setBackColor(editTextAddGoalsMess3,R.color.addGoals, R.drawable.edit_round_flat);
//        modalWidgets.setBackColor(editTextAddGoalsName,R.color.colorPrimary, R.drawable.edit_round_flat);
//        modalWidgets.setBackColor(addGoalsName,R.color.addGoals, R.drawable.edit_round_flat);

        spinnerAddGoals.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) {
                    currentTaskType = GOAL_TYPE.QUANTITATIVE;
                    root.findViewById(R.id.LinearLayoutQ).setVisibility(View.VISIBLE);
                } else {
                    currentTaskType = GOAL_TYPE.FINAL;
                    root.findViewById(R.id.LinearLayoutQ).setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        return root;
    }

    public void createGoal(View view) {
        String name = newGoalName.getText().toString();
        String description = newGoalDesc.getText().toString();
        String goal = newGoalTarget.getText().toString();

        String days = newGoalDays.getText().toString();

        boolean isCorrect = true;
        if (name.equals("") || description.equals("") || goal.equals("") || days.equals("")){
            isCorrect = false;
        }

        if (isCorrect) {
            Integer countOfDays = Integer.parseInt(days);
            if (this.currentTaskType == GOAL_TYPE.FINAL) {
                new HttpClient().addFinalGoal(
                        this, new FinalGoalData(
                                MainActivity.currentUser.getId(), name,
                                description, goal, countOfDays
                        )
                );
                Navigation.findNavController(view).navigate(R.id.nav_home);
            } else if (this.currentTaskType == GOAL_TYPE.QUANTITATIVE) {
                String step =newGoalDays.getText().toString();
                if (!step.equals("") && Integer.parseInt(step) > 0){
                    new HttpClient().addQuantitativeGoal(
                            this, new QuantitativeGoalData(
                                    name, description, MainActivity.currentUser.getId(),
                                    countOfDays, goal,  Integer.parseInt(step)
                            )
                    );
                    Navigation.findNavController(view).navigate(R.id.nav_home);
                } else {
                    notify("Nieprawidłowe dane");
                }

            }
        } else {
            notify("Nieprawidłowe dane");
        }
    }

    public void notify(String msg) {
        this.modal.showToast(msg);
    }

    @Override
    public void onAddedFinalGoalCallback(FinalGoal finalGoalData) {
        notify("ADDED FINAL GOAL");
    }

    @Override
    public void onAddedQuantitativeGoalCallback(QuantitativeGoal quantitativeGoalData) {
        notify("ADDED Quantitative GOAL");
    }
}