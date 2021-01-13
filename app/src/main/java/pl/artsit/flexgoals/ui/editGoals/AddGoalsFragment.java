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
import pl.artsit.flexgoals.http.services.FinalGoalService;
import pl.artsit.flexgoals.http.goals.AddGoalCallback;
import pl.artsit.flexgoals.http.services.QuantitativeGoalService;
import pl.artsit.flexgoals.model.ModalWidgets;
import pl.artsit.flexgoals.model.goal.finals.FinalGoal;
import pl.artsit.flexgoals.model.goal.finals.FinalGoalData;
import pl.artsit.flexgoals.model.goal.finals.PredefinedFinalGoal;
import pl.artsit.flexgoals.model.goal.quantitative.PredefinedQuantitativeGoal;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoal;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoalData;

public class AddGoalsFragment extends Fragment implements AddGoalCallback {
    private EditText newGoalName;
    private EditText newGoalDesc;
    private EditText newGoalTarget;
    private EditText newGoalDays;
    private EditText newGoalStep;
    private EditText newGoalContent;
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
        newGoalTarget = root.findViewById(R.id.new_target);
        newGoalDays = root.findViewById(R.id.new_goal_days);
        newGoalStep = root.findViewById(R.id.new_goal_step);
        newGoalContent = root.findViewById(R.id.your_new_goal);
        modal = new ModalWidgets(root.getContext());

        root.findViewById(R.id.buttonAddGoalsSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createGoal(v);
            }
        });

        if (MainActivity.predefinedQuantitativeGoal != null) {
            setPreQuantitativeGoal(MainActivity.predefinedQuantitativeGoal);
            MainActivity.predefinedQuantitativeGoal = null;
        } else if (MainActivity.predefinedFinalGoal != null) {
            root.findViewById(R.id.LinearLayoutQ).setVisibility(View.GONE);
            spinnerAddGoals.setSelection(1);
            setPreFinalGoal(MainActivity.predefinedFinalGoal);
            MainActivity.predefinedFinalGoal = null;
        }

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
            }

        });

        return root;
    }

    private void setPreFinalGoal(PredefinedFinalGoal preFinalGoal) {
        currentTaskType = GOAL_TYPE.FINAL;
        newGoalName.setText(preFinalGoal.getName());
        newGoalDesc.setText(preFinalGoal.getDescription());
        newGoalContent.setText(preFinalGoal.getGoal());
        newGoalDays.setText(preFinalGoal.getDays().toString());
    }

    private void setPreQuantitativeGoal(PredefinedQuantitativeGoal preQuantitativeGoal) {
        newGoalTarget.setText(preQuantitativeGoal.getTarget().toString());
        newGoalName.setText(preQuantitativeGoal.getName());
        newGoalDesc.setText(preQuantitativeGoal.getDescription());
        newGoalContent.setText(preQuantitativeGoal.getGoal());
        newGoalDays.setText(preQuantitativeGoal.getDays().toString());
        newGoalStep.setText(preQuantitativeGoal.getStep().toString());
    }

    public void createGoal(View view) {
        String name = newGoalName.getText().toString();
        String description = newGoalDesc.getText().toString();
        String goal = newGoalContent.getText().toString();

        String days = newGoalDays.getText().toString();

        boolean isCorrect = true;
        if (name.equals("") || description.equals("") || goal.equals("") || days.equals("")){
            isCorrect = false;
        }

        if (isCorrect) {
            Integer countOfDays = Integer.parseInt(days);
            if (this.currentTaskType == GOAL_TYPE.FINAL) {
                new FinalGoalService().addFinalGoal(
                        this, new FinalGoalData(
                                MainActivity.currentUser.getId(), name,
                                description, goal, countOfDays
                        )
                );
                Navigation.findNavController(view).navigate(R.id.nav_home);
            } else if (this.currentTaskType == GOAL_TYPE.QUANTITATIVE) {
                String step = newGoalDays.getText().toString();
                if (!step.equals("") && Integer.parseInt(step) > 0){
                    new QuantitativeGoalService().addQuantitativeGoal(
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