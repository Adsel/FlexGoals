package pl.artsit.flexgoals.ui.addGoals;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.model.goal.QuantitativeGoal;

public class GoalQuantitativePreviewFragment extends Fragment {
    private QuantitativeGoal quantitativeGoal;
    private enum GOAL_TYPE {
        FINAL,
        QUANTITATIVE
    }

    private GOAL_TYPE currentTaskType;

    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        //addGoalsViewModel = new ViewModelProvider(this).get(AddGoalsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_goal_preview, container, false);
        TextView header = root.findViewById(R.id.progressTitleBar);
        setHeaderBackgroundColor(header, R.color.colorHeaderAction, R.drawable.title, getContext());

        MainActivity mainActivity = (MainActivity) getActivity();

        return root;
    }



    public void setHeaderBackgroundColor(View view, int color, int drawable, Context context){
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, drawable);
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, color);
        view.setBackground(wrappedDrawable);
    }
}