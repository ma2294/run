package bath.run.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

import bath.run.DayOfTheWeek;
import bath.run.database.UserDbSchema.UserTable;

/**
 * Created by mradl on 04/07/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "user";
    private static final String TAG = "DatabaseHelper";
    private List<User> mUser;

    // private List<User> mUser;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    DayOfTheWeek dotw = new DayOfTheWeek();
    List<User> users;
    private String whereClause = "week = ?";
    private String[] whereArgs = new String[]{
            String.valueOf(dotw.getWeek())
    };

    public DatabaseHelper(Context context) {
        super(context, "user.db", null, VERSION);

        users = new ArrayList<>();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserTable.SQL_CREATE_ENTRIES);
        Log.i(TAG, "onCreate");
        insert(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }


    private UserCursorWrapper queryUser(String whereClause, String[] whereArgs) {

        mDatabase = this.getReadableDatabase();
        System.out.println("query user called");
        Cursor cursor = mDatabase.query(
                UserDbSchema.UserTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new UserCursorWrapper(cursor);
    }

    public List<User> getUsers() {
        System.out.println("get users called");
        UserCursorWrapper cursor = queryUser(whereClause, whereArgs);
        //  System.out.println(cursor.getUser());
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                users.add(cursor.getUser());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return users;
    }


    public void updateRow(int day) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        switch (day) {
            case 1:
                contentValues.put("monday", User.isMonday() ? 1 : 0);
                Log.i(TAG, "updateRow: Monday. ");
                break;

            case 2:
                contentValues.put("tuesday", User.isTuesday() ? 1 : 0);
                Log.i(TAG, "updateRow: Tuesday = Yes ");
                break;

            case 3:
                contentValues.put("wednesday", User.isWednesday() ? 1 : 0);
                Log.i(TAG, "updateRow: Wednesday = Yes ");
                break;

            case 4:
                contentValues.put("thursday", User.isThursday() ? 1 : 0);
                Log.i(TAG, "updateRow: Thursday = Yes ");
                break;

            case 5:
                contentValues.put("friday", User.isFriday() ? 1 : 0);
                Log.i(TAG, "updateRow: Friday = Yes ");
                break;

            case 6:
                contentValues.put("saturday", User.isSaturday() ? 1 : 0);
                Log.i(TAG, "updateRow: Saturday = Yes ");
                break;

            case 7:
                contentValues.put("sunday", User.isSunday() ? 1 : 0);
                Log.i(TAG, "updateRow: Sunday ");
                break;

            default:
                //nothing
                break;
        }
        db.update("user", contentValues, whereClause, whereArgs);
    }


    //insert into db
    //TODO  call this when week is over.
    public void insert(SQLiteDatabase db)
            throws SQLException {
        ContentValues contentValues = new ContentValues();
        User user = new User();
        contentValues.put("week", dotw.getWeek());
        contentValues.put("monday", user.isMonday() ? 1 : 0);
        contentValues.put("tuesday", user.isTuesday() ? 1 : 0);
        contentValues.put("wednesday", user.isWednesday() ? 1 : 0);
        contentValues.put("thursday", user.isThursday() ? 1 : 0);
        contentValues.put("friday", user.isFriday() ? 1 : 0);
        contentValues.put("saturday", user.isSaturday() ? 1 : 0);
        contentValues.put("sunday", user.isSunday() ? 1 : 0);

        db.insert(DATABASE_NAME, null, contentValues);

    }
}