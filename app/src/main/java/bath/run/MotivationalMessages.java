package bath.run;

import java.util.Random;

/**
 * Created by mradl on 04/07/2018.
 */

public class MotivationalMessages {

public String motivationalMessage = "";


    public String getMotivationalMessage(){
        int random = getRandomNumberInRange(1, 10);

        switch(random) {
            case 1:
                motivationalMessage = "Strive for progress and not perfection";
                break;

            case 2:
                motivationalMessage = "Strength does not come from physical \n capacity. It comes from an indomitable will. \n- Mahatma Gandhi";
                break;

            case 3:
                motivationalMessage = "You're only one workout away from \n a good mood";
                break;

            case 4:
                motivationalMessage = "Today i will do what others won't \n so tomorrow i can do what others can't";
                break;
            case 5:
                motivationalMessage = "We generate fears while we sit. \n We overcome them by action \n- Dr. Henry Link";
                break;

            case 6:
                motivationalMessage = "We may encounter many defeats but\n we must not be defeated \n- Maya Angelou.";
                break;

            case 7:
                motivationalMessage = "The way to get started is to quit talking \n and begin doing - Walt Disney";
                break;

            case 8:
                motivationalMessage = "Failure will never overtake me if my \n determination to succeed is strong enough \n- Og Mandino";
                break;
            default:
                motivationalMessage = "Making excuses burns zero calories per hour";
        }
        return motivationalMessage;
    }


    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
