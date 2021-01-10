package pl.artsit.flexgoals.ui.home;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.List;
import pl.artsit.flexgoals.R;

public class HomeFragment extends Fragment {
    private Integer[] colors;
    private HomeViewModel homeViewModel;
    private ViewPager viewPager;
    private ImageAdapter adapter;
    private List<Model> models;
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        context = root.getContext();
        viewPager = root.findViewById(R.id.viewPager);
        models = getModels();

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

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter.getCount() - 1) && position < (colors.length - 1 )){
                    viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(
                                positionOffset,
                                colors[position],
                                colors[position + 1]
                        )
                    );

                } else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
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
}
