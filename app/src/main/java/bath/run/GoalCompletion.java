package bath.run;

/**
 * Created by mradl on 04/07/2018.
 */

public class GoalCompletion {

    public static double dailyStepsGoal = 1300;
    private static double total = 0;
    private boolean mondayGoalAchieved = false;
    private boolean tuesdayGoalAchieved = false;
    private boolean wednesdayGoalAchieved = false;
    private boolean thursdayGoalAchieved = false;
    private boolean fridayGoalAchieved = false;
    private boolean saturdayGoalAchieved = false;
    private boolean sundayGoalAchieved = false;

    private double distance;

    public static double workOutRemainingPercentage(double currentValue,double goal) {
        total = ((currentValue/goal)*100);

        if(total > 100){
            total = 100;
        }
        return total;
    }


    public static double getDailyStepsGoal() {
        return dailyStepsGoal;
    }

    public static void setDailyStepsGoal(double steps){
        dailyStepsGoal = steps;
    }


    public boolean isMondayGoalAchieved() {
        return mondayGoalAchieved;
    }

    public void setMondayGoalAchieved(boolean mondayGoalAchieved) {
        this.mondayGoalAchieved = mondayGoalAchieved;
    }

    public boolean isTuesdayGoalAchieved() {
        return tuesdayGoalAchieved;
    }

    public void setTuesdayGoalAchieved(boolean tuesdayGoalAchieved) {
        this.tuesdayGoalAchieved = tuesdayGoalAchieved;
    }

    public boolean isWednesdayGoalAchieved() {
        return wednesdayGoalAchieved;
    }

    public void setWednesdayGoalAchieved(boolean wednesdayGoalAchieved) {
        this.wednesdayGoalAchieved = wednesdayGoalAchieved;
    }

    public boolean isThursdayGoalAchieved() {
        return thursdayGoalAchieved;
    }

    public void setThursdayGoalAchieved(boolean thursdayGoalAchieved) {
        this.thursdayGoalAchieved = thursdayGoalAchieved;
    }

    public boolean isFridayGoalAchieved() {
        return fridayGoalAchieved;
    }

    public void setFridayGoalAchieved(boolean fridayGoalAchieved) {
        this.fridayGoalAchieved = fridayGoalAchieved;
    }

    public boolean isSaturdayGoalAchieved() {
        return saturdayGoalAchieved;
    }

    public void setSaturdayGoalAchieved(boolean saturdayGoalAchieved) {
        this.saturdayGoalAchieved = saturdayGoalAchieved;
    }

    public boolean isSundayGoalAchieved() {
        return sundayGoalAchieved;
    }

    public void setSundayGoalAchieved(boolean sundayGoalAchieved) {
        this.sundayGoalAchieved = sundayGoalAchieved;
    }
}
