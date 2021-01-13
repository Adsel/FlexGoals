package pl.artsit.flexgoals.ui.home;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.List;

import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.http.goals.PredefinedGoalCallback;
import pl.artsit.flexgoals.http.services.FinalGoalService;
import pl.artsit.flexgoals.http.services.QuantitativeGoalService;
import pl.artsit.flexgoals.model.ModalWidgets;
import pl.artsit.flexgoals.model.goal.finals.PredefinedFinalGoal;
import pl.artsit.flexgoals.model.goal.quantitative.PredefinedQuantitativeGoal;
import pl.artsit.flexgoals.ui.home.adapters.ImageAdapter;
import pl.artsit.flexgoals.ui.home.adapters.Model;
import pl.artsit.flexgoals.ui.home.adapters.PredefinedFinalGoalsAdapter;
import pl.artsit.flexgoals.ui.home.adapters.PredefinedQuantitativeGoalsAdapter;

public class HomeFragment extends Fragment implements PredefinedGoalCallback {
    private Integer[] colors;
    private HomeViewModel homeViewModel;
    private ViewPager viewPager;
    private ImageAdapter adapter;
    private List<Model> models;
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private Context context;
    private RecyclerView preQuantGoalRecyclerView;
    private RecyclerView preFinalGoalRecyclerView;
    private ModalWidgets modal;
    private ScrollView scrollView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        context = root.getContext();
        scrollView = root.findViewById(R.id.scrolll_view);
        viewPager = root.findViewById(R.id.viewPager);
        models = getModels();
        modal = new ModalWidgets(root.getContext());

        preQuantGoalRecyclerView = root.findViewById(R.id.pre_quantitative_goals_recycleview);
        preQuantGoalRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        new QuantitativeGoalService().getQuantitativeGoals(this);

        preFinalGoalRecyclerView = root.findViewById(R.id.pre_final_goals_recycleview);
        preFinalGoalRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        new FinalGoalService().getPredefinedFinalGoals(this);

        addSlider();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private List<Model> getModels() {
        List<Model> newModels = new ArrayList<>();
        newModels.add(new Model(R.drawable.mike,"Inspiracja","Z NAMI ODKRYJESZ SIEBIE! \n\nZnajdź z nami to co cię napedza. "));
        newModels.add(new Model(R.drawable.image_five,"Sport","Poszerzaj swoje granice"));
        newModels.add(new Model(R.drawable.lora,"Organizer","Twój osobisty menadzer, bedzie pomocnikiem w skutecznym planowaniu dnia"));
        newModels.add(new Model(R.drawable.image_four,"Motywacja","Zmień swojeń życie na lepsze z aplikacją Flexgo. \n \n POMOŻEMY CI SPEŁNIĆ MARZENIA!!"));
        newModels.add(new Model(R.drawable.chester_wade,"Cele"," Codzienne przypomnienie o twoich celach pomoże ci nie zapomniec do kąd zmierzasz "));

        return newModels;
    }

    private void addSlider() {
        colors = new Integer[]{
                getResources().getColor(R.color.color_one),
                getResources().getColor(R.color.color_two),
                getResources().getColor(R.color.color_three),
                getResources().getColor(R.color.color_four),
                getResources().getColor(R.color.color_five)
        };
        adapter = new ImageAdapter(models, context);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);
        viewPager.setBackground(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter.getCount() - 1) && position < (colors.length - 1 )){
                    scrollView.setBackgroundColor((Integer) argbEvaluator.evaluate(
                                positionOffset,
                                colors[position],
                                colors[position + 1]
                        )
                    );
                } else {
                    scrollView.setBackgroundColor(colors[colors.length - 1]);
                }
            }
            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void informAboutFailed() {
        modal.showToast("Failed to load predefined goals");
    }

    @Override
    public void drawPreFinal(PredefinedFinalGoal[] predefinedFinalGoals) {
        PredefinedFinalGoalsAdapter predefinedFinalGoalAdapter = new PredefinedFinalGoalsAdapter(predefinedFinalGoals);

        preFinalGoalRecyclerView.setAdapter(predefinedFinalGoalAdapter);
        preFinalGoalRecyclerView.setVisibility(View.VISIBLE);
        preFinalGoalRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void drawPreQuantitative(PredefinedQuantitativeGoal[] predefinedQuantitativeGoals) {
        PredefinedQuantitativeGoalsAdapter predefinedQuantitativeGoalsAdapter = new PredefinedQuantitativeGoalsAdapter(predefinedQuantitativeGoals);

        preQuantGoalRecyclerView.setAdapter(predefinedQuantitativeGoalsAdapter);
        preQuantGoalRecyclerView.setVisibility(View.VISIBLE);
        preQuantGoalRecyclerView.setHasFixedSize(true);
    }
}
