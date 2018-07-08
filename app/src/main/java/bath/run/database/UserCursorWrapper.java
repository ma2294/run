package bath.run.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import bath.run.DayOfTheWeek;
import bath.run.MainActivity;

/**
 * Created by mradl on 07/07/2018.
 */

public class UserCursorWrapper extends CursorWrapper {
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public User getUser() {
        DayOfTheWeek dotw = new DayOfTheWeek();
        int weekCol = getInt(getColumnIndex(UserDbSchema.UserTable.Cols.WEEK));
        boolean monday = Boolean.parseBoolean(getString(getColumnIndex(UserDbSchema.UserTable.Cols.MONDAY)));
        boolean tuesday = Boolean.parseBoolean(getString(getColumnIndex(UserDbSchema.UserTable.Cols.TUESDAY)));
        boolean wednesday = Boolean.parseBoolean(getString(getColumnIndex(UserDbSchema.UserTable.Cols.WEDNESDAY)));
        boolean thursday = Boolean.parseBoolean(getString(getColumnIndex(UserDbSchema.UserTable.Cols.THURSDAY)));
        boolean friday = Boolean.parseBoolean(getString(getColumnIndex(UserDbSchema.UserTable.Cols.FRIDAY)));
        boolean saturday = Boolean.parseBoolean(getString(getColumnIndex(UserDbSchema.UserTable.Cols.SATURDAY)));
        boolean sunday = Boolean.parseBoolean(getString(getColumnIndex(UserDbSchema.UserTable.Cols.SUNDAY)));

        User user = new User(dotw.getWeek());
        User.setMonday(monday);
        User.setTuesday(tuesday);
        User.setWednesday(wednesday);
        User.setThursday(thursday);
        User.setFriday(friday);
        User.setSaturday(saturday);
        User.setSunday(sunday);


        System.out.println("Week " + user.getWeek());
        return user;
    }
}
