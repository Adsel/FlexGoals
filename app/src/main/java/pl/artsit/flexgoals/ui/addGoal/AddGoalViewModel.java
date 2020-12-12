package pl.artsit.flexgoals.ui.addGoal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddGoalViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddGoalViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}