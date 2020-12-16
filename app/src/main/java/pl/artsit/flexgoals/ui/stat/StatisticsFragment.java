package pl.artsit.flexgoals.ui.stat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;

public class StatisticsFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_statistic, container, false);

        ((TextView) root.findViewById(R.id.textViewStatisticUsername)).setText(MainActivity.currentUser.getLogin());
        ((TextView) root.findViewById(R.id.textViewStatisticEmail)).setText(MainActivity.currentUser.getEmail());
        ((TextView) root.findViewById(R.id.textViewStatisticPoints)).setText(MainActivity.currentUser.getPoints().toString());
        return root;
    }
}