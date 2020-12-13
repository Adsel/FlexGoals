package pl.artsit.flexgoals.modal;

import android.content.Context;
import android.widget.Toast;

public class Popup {
        Context context;
        int duration = Toast.LENGTH_LONG;
        Toast toast;

        public Popup(Context context){
                this.context = context;
                toast = Toast.makeText(context,"Empty Message", duration);
        }
        public void show(String text){
                toast = Toast.makeText(context,text, duration);
                toast.show();
        }
}
