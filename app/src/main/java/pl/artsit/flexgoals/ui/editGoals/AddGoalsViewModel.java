package pl.artsit.flexgoals.ui.editGoals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddGoalsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddGoalsViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}