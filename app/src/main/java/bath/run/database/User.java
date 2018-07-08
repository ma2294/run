package bath.run.database;


import bath.run.DayOfTheWeek;

/**
 * Created by mradl on 07/07/2018.
 */

public class User {
    private static int week;
    private static boolean monday = false;
    private static boolean tuesday = false;
    private static boolean wednesday = false;
    private static boolean thursday = false;
    private static boolean friday = false;
    private static boolean saturday = false;
    private static boolean sunday = false;

    public User() {
        //  System.out.println("test");
    }

    public User(int weekNum) {
        week = weekNum;
    }

    public static void setWeek(int weekk) {
        week = weekk;
    }

    public static void setMonday(boolean mondayy) {
        monday = mondayy;
    }

    public static void setTuesday(boolean tuesdayy) {
        tuesday = tuesdayy;
    }

    public static void setWednesday(boolean wednesdayy) {
        wednesday = wednesdayy;
    }

    public static void setThursday(boolean thursdayy) {
        thursday = thursdayy;
    }

    public static void setFriday(boolean fridayy) {
        friday = fridayy;
    }

    public static void setSaturday(boolean saturdayy) {
        saturday = saturdayy;
    }

    public static void setSunday(boolean sundayy) {
        sunday = sundayy;
    }

    public static boolean isMonday() {
        return monday;
    }

    public static boolean isTuesday() {
        return tuesday;
    }

    public static boolean isWednesday() {
        return wednesday;
    }

    public static boolean isThursday() {
        return thursday;
    }

    public static boolean isFriday() {
        return friday;
    }

    public static boolean isSaturday() {
        return saturday;
    }

    public static boolean isSunday() {
        return sunday;
    }

    public static int getWeek() {
        return week;
    }

    public static void setDay(boolean stepsComplete, int day) {
        switch (day) {
            case 1:
                setMonday(stepsComplete);
                break;

            case 2:
                setTuesday(stepsComplete);
                break;

            case 3:
                setWednesday(stepsComplete);
                break;

            case 4:
                setThursday(stepsComplete);
                break;

            case 5:
                setFriday(stepsComplete);
                break;

            case 6:
                setSaturday(stepsComplete);
                break;

            case 7:
                setSunday(stepsComplete);
                break;

            default:
                //
                break;
        }
    }

    @Override
    public String toString() {
        return "Monday steps: "
                + isMonday() + ". Tuesday steps: "
                + isTuesday() + ". Wednesday steps: "
                + isWednesday() + ". Thursday steps: "
                + isThursday() + ". Friday steps: "
                + isFriday() + ". Saturday steps: "
                + isSaturday() + ". Sunday steps: "
                + isSunday() + ". Week of year: " + getWeek();
    }
}
