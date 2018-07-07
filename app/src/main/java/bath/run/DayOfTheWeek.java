package bath.run;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by mradl on 04/07/2018.
 */

public class DayOfTheWeek {

    private String today = "";
    private int day = 0;
    private int thursdaySteps=0;
    private int wednesdaySteps=7200;
    public final int DAYS_IN_WEEK = 7;



    Calendar sCalendar = Calendar.getInstance();
    public int getWeek() {
        return sCalendar.get(Calendar.WEEK_OF_YEAR);
    }
    public int getCurrentMins() {
        int currentMins = sCalendar.get(Calendar.MINUTE);

        return currentMins;
    }
    public int getCurrentHour() {
        int currentHour = sCalendar.get(Calendar.HOUR_OF_DAY);

        return currentHour;
    }
    public void setDayTextView(int day, int steps) {

    }
    public int getWednesdaySteps() {
        return wednesdaySteps;
    }

    public int getThursdaySteps() {

        return thursdaySteps;
    }

    public void setThursdaySteps(int steps) {
        thursdaySteps = steps;
    }

    public void setWednesdaySteps(int steps){
        wednesdaySteps =  steps;
    }

    public boolean setDayIcon(int day) {
        int d = day;
        if (getDay() == day){
            return true;
        }

        else {
            return false;
        }

    }
    public int getDay(){
        today = sCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        switch (today) {
            case "Monday":
                day = 1;
                break;

            case "Tuesday":
                day = 2;
                break;

            case "Wednesday":
                day = 3;
                break;

            case "Thursday":
                day = 4;
                break;

            case "Friday":
                day = 5;
                break;

            case "Saturday":
                day = 6;
                break;

            case "Sunday":
                day = 7;
                break;

            default:
                day = 1;
        }

        return day;

    }



}
