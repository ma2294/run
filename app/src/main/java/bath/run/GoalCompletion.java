package bath.run;

import android.database.sqlite.SQLiteDatabase;

import bath.run.database.DatabaseHelper;
import bath.run.database.User;

import static bath.run.MainActivity.dailySteps;

/**
 * Created by mradl on 04/07/2018.
 */

public class GoalCompletion {

    public static double dailyStepsGoal = 1300;
    private static double total = 0;
    DayOfTheWeek dotw = new DayOfTheWeek();

    public static double workOutRemainingPercentage(double currentValue, double goal) {
        total = ((currentValue / goal) * 100);

        if (total > 100) {
            total = 100;
        }
        return total;
    }

    public static double getDailyStepsGoal() {
        return dailyStepsGoal;
    }

    public static void setDailyStepsGoal(double steps) {
        dailyStepsGoal = steps;
    }

    public void goalReached(DatabaseHelper db) {
        int day = dotw.getDay();
        if (dailySteps >= getDailyStepsGoal()) {
            User.setDay(true, day);
            for (int i = 0; i <= dotw.DAYS_IN_WEEK; i++) {
                if (day == i) {
                    db.updateRow(day);
                }
            }
        }
    }
}
