package pl.artsit.flexgoals.model;

import android.content.Context;
import android.widget.Toast;

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
}
