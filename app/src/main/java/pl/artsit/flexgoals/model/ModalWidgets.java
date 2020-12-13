package pl.artsit.flexgoals.model;

import android.content.Context;
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

}
