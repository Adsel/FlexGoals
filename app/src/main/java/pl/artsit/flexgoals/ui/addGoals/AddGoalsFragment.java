package pl.artsit.flexgoals.ui.addGoals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import pl.artsit.flexgoals.R;

public class AddGoalsFragment extends Fragment {


    private AddGoalsViewModel addGoalsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        addGoalsViewModel = new ViewModelProvider(this).get(AddGoalsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_add_goals, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);


        final TextView textViewAddingGoalsTitle = root.findViewById(R.id.textViewAddGoalsTitle);

        //textViewAddingGoalsTitle.getRootView().setBackgroundColor(getResources().getColor(R.color.addGoals));

        final TextView textViewAddGoalsMess1 = root.findViewById(R.id.textViewAddGoalsMess1);
        final TextView editTextAddGoalsName  = root.findViewById(R.id.textViewAddGoalsMess3);
        final LinearLayout addGoalsName = root.findViewById(R.id.AddGoalsName);
        final Spinner spinnerAddGoals = root.findViewById(R.id.spinnerAddGoals);
        final EditText editTextAddGoalsMess3 = root.findViewById(R.id.editTextAddGoalsQDescription);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.add_goals_options));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAddGoals.setAdapter(adapter);



//        ModalWidgets modalWidgets = new ModalWidgets(root.getContext());
//        modalWidgets.setBackColor(textViewAddingGoalsTitle,R.color.addGoals,R.drawable.title );
//        modalWidgets.setBackColor(textViewAddGoalsMess1,R.color.addGoals, R.drawable.title);
//        modalWidgets.setBackColor(editTextAddGoalsMess3,R.color.addGoals, R.drawable.edit_round_flat);
//        modalWidgets.setBackColor(editTextAddGoalsName,R.color.colorPrimary, R.drawable.edit_round_flat);
//        modalWidgets.setBackColor(addGoalsName,R.color.addGoals, R.drawable.edit_round_flat);

        spinnerAddGoals.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                System.out.println("xd " + position + " XD " + id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        return root;
    }
}