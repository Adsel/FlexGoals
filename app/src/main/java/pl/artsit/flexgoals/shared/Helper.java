package pl.artsit.flexgoals.shared;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Helper {
    public static final Integer GOAL_FINISHED = -1;
    public static final Integer GOAL_ACHIVED = -1;


    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
}
