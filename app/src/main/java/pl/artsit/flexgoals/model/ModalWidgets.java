package pl.artsit.flexgoals.model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;

public class ModalWidgets {
    Context context;
    int ToastDuration = Toast.LENGTH_LONG;
    Toast toast;

    public ModalWidgets(Context context){
        this.context = context;
    }
    public void showToast(String text){
        Toast toast = Toast.makeText(context,text, ToastDuration);
        toast.show();
    }

    public void setBackColor(View view, int color, int drawable){
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, drawable);
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, color);
        view.setBackground(wrappedDrawable);
    }

    public static int getColorWithAlpha(int color, float ratio) {
        int newColor = 0;
        int alpha = Math.round(Color.alpha(color) * ratio);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        newColor = Color.argb(alpha, r, g, b);
        return newColor;
    }

}
