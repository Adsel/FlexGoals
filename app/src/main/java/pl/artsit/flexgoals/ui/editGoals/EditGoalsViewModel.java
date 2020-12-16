package pl.artsit.flexgoals.ui.editGoals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EditGoalsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EditGoalsViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}