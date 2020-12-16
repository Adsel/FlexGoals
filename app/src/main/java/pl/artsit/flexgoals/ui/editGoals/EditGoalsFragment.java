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
        notify("Nie udało się zapisać");
    }

    private MainActivity.GOAL_TYPE currentTaskType;

    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        editGoalsViewModel = new ViewModelProvider(this).get(EditGoalsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_edit_goals, container, false);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(root.getContext(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.add_goals_options));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        currentTaskType = MainActivity.previewGoalType;
        newGoalName = root.findViewById(R.id.editGoalName);
        newGoalDesc = root.findViewById(R.id.editTextAddGoalsQDescription);
        newGoalTarget = root.findViewById(R.id.editTextAddGoalsQTarget);
        newGoalDays = root.findViewById(R.id.editTextAddGoalsDays);
        modal = new ModalWidgets(root.getContext());

        if (currentTaskType == MainActivity.GOAL_TYPE.FINAL) {
            FinalGoal finalGoal = MainActivity.previewFinalGoal;
            newGoalName.setText(finalGoal.getName());
            newGoalDesc.setText(finalGoal.getDescription());
            newGoalTarget.setText(finalGoal.getGoal());
            newGoalDays.setText(finalGoal.getDays().toString());
        } else if (currentTaskType == MainActivity.GOAL_TYPE.QUANTITATIVE) {
            QuantitativeGoal quantitativeGoal = MainActivity.previewQuantitativeGoal;
            newGoalName.setText(quantitativeGoal.getName());
            newGoalDesc.setText(quantitativeGoal.getDescription());
            newGoalTarget.setText(quantitativeGoal.getGoal());
            newGoalDays.setText(quantitativeGoal.getDays().toString());
            EditText step = root.findViewById(R.id.editTextEditStep);
            step.setVisibility(View.VISIBLE);
            step.setText(quantitativeGoal.getStep().toString());
            root.findViewById(R.id.editTextEditStepTitle).setVisibility(View.VISIBLE);
        }

        root.findViewById(R.id.buttonAddGoalsSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateGoal(v);
            }
        });

        return root;
    }

    public void updateGoal(View view) {
        String name = newGoalName.getText().toString();
        String description = newGoalDesc.getText().toString();
        String goal = newGoalTarget.getText().toString();
        int days = Integer.parseInt(newGoalDays.getText().toString());

        boolean isCorrect = true;
        if (name.equals("") || description.equals("") || goal.equals("") || days <= 0){
            isCorrect = false;
        }

        if (isCorrect) {
            if (this.currentTaskType == MainActivity.GOAL_TYPE.FINAL) {
                FinalGoal finalGoal = MainActivity.previewFinalGoal;
                if (!finalGoal.getName().equals(name)) {
                    finalGoal.setName(name);
                }

                if (!finalGoal.getDescription().equals(description)) {
                    finalGoal.setDescription(description);
                }

                if (!finalGoal.getGoal().equals(goal)) {
                    finalGoal.setGoal(goal);
                }

                if (finalGoal.getDays() != days) {
                    finalGoal.setDays(days);
                }

                finalGoal.setIdUser(MainActivity.currentUser.getId());
                finalGoal.setShared(false);

                new HttpClient().saveFinalGoal(this, finalGoal);
            } else if (this.currentTaskType == MainActivity.GOAL_TYPE.QUANTITATIVE) {
                Integer step = Integer.parseInt(newGoalDays.getText().toString());
                if (step > 0){
                    QuantitativeGoal quantitativeGoal = MainActivity.previewQuantitativeGoal;
                    if (!quantitativeGoal.getName().equals(name)) {
                        quantitativeGoal.setName(name);
                    }

                    if (!quantitativeGoal.getDescription().equals(description)) {
                        quantitativeGoal.setDescription(description);
                    }

                    if (!quantitativeGoal.getGoal().equals(goal)) {
                        quantitativeGoal.setGoal(goal);
                    }

                    if (quantitativeGoal.getDays() != days) {
                        quantitativeGoal.setDays(days);
                    }

                    if (quantitativeGoal.getStep() != step) {
                        quantitativeGoal.setStep(step);
                    }

                    quantitativeGoal.setIdUser(MainActivity.currentUser.getId());
                    quantitativeGoal.setShared(false);

                    new HttpClient().saveQuantitativeGoal(this, quantitativeGoal);
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