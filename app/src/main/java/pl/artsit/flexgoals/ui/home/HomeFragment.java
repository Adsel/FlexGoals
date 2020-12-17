package pl.artsit.flexgoals.ui.home;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import pl.artsit.flexgoals.R;

public class HomeFragment extends Fragment {


    ViewPager viewPager;
    ImageAdapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //View Pager-------------------------------------
        models = new ArrayList<>();
        models.add(new Model(R.drawable.mike,"Inspiracja","Z NAMI ODKRYJESZ SIEBIE! \n\nZnajdź z nami to co cię napedza. "));
        models.add(new Model(R.drawable.image_five,"Sport","Poszerzaj swoje granice"));
        models.add(new Model(R.drawable.lora,"Organizer","Twój osobisty menadzer, bedzie pomocnikiem w skutecznym planowaniu dnia"));
        models.add(new Model(R.drawable.image_four,"Motywacja","Zmień swojeń życie na lepsze z aplikacją Flexgo. \n \n POMOŻEMY CI SPEŁNIĆ MARZENIA!!"));
        models.add(new Model(R.drawable.chester_wade,"Cele"," Codzienne przypomnienie o twoich celach pomoże ci nie zapomniec do kąd zmierzasz "));

        //adapter = new Adapter(models,MainActivity.this);


        adapter = new ImageAdapter(models,context );
        viewPager = root.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color_one),
                getResources().getColor(R.color.color_two),
                getResources().getColor(R.color.color_three),
                getResources().getColor(R.color.color_four),
                getResources().getColor(R.color.color_five)
        };

        colors = colors_temp;
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position < (adapter.getCount() - 1) && position < (colors.length - 1 )){
                    viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(
                            positionOffset,
                            colors[position],
                            colors[position + 1]
                            )
                    );

                }else {
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

        return root;

    }

    @Override
    public void onResume() {
        super.onResume();
    }
}