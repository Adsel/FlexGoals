package pl.artsit.flexgoals.shared;

import android.os.Build;
import androidx.annotation.RequiresApi;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Helper {

    private static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static Date convertDateFromServer(Date dateFromServer) {
        Calendar convertedDate = Calendar.getInstance();
        convertedDate.setTime(dateFromServer);
        convertedDate.add(Calendar.MONTH, 1);

        return convertedDate.getTime();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static long getLeftDays(Date startedGoalDate, int days) {
        Date date1 = new Date(System.currentTimeMillis());
        Date date2 = Helper.convertDateFromServer(startedGoalDate);

        long diff = Helper.getDateDiff(date1, date2, TimeUnit.DAYS);;
        long left = days + diff;
        if (left < 0) {
            left = 0;
        }

        return left;
    }
}
