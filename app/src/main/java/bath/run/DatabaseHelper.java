package bath.run;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by mradl on 04/07/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private final String TAG = "DatabaseHelper";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE user(week integer, mondaycompletedsteps boolean, tuesdaycompletedsteps boolean, wednesdaycompletedsteps boolean," +
                    "thursdaycompletedsteps boolean, fridaycompletedsteps boolean, saturdaycompletedsteps boolean, sundaycompletedsteps boolean)";

    DayOfTheWeek dotw = new DayOfTheWeek();

    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
        insert();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists user");
    }


    public void updateRow(int day) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        switch (day) {
            case 1:
                contentValues.put("mondaycompletedsteps", "yes");
                Log.i(TAG, "updateRow: Monday = Yes ");
                break;

            case 2:
                contentValues.put("tuesdaycompletedsteps", "yes");
                Log.i(TAG, "updateRow: Tuesday = Yes ");
                break;

            case 3:
                contentValues.put("wednesdaycompletedsteps", "yes");
                Log.i(TAG, "updateRow: Wednesday = Yes ");
                break;

            case 4:
                contentValues.put("thursdaycompletedsteps", "yes");
                Log.i(TAG, "updateRow: Thursday = Yes ");
                break;

            case 5:
                contentValues.put("fridaycompletedsteps", "yes");
                Log.i(TAG, "updateRow: Friday = Yes ");
                break;

            case 6:
                contentValues.put("saturdaycompletedsteps", "yes");
                Log.i(TAG, "updateRow: Saturday = Yes ");
                break;

            case 7:
                contentValues.put("sundaycompletedsteps", "yes");
                Log.i(TAG, "updateRow: Sunday = Yes ");
                break;

            default:
                //nothing
                break;
        }
        db.update("user", contentValues, null, null);
    }


    //insert into db
    //String mondaycompletedsteps, String thursdaycompletedsteps
    //TODO  call this when week is over.
    public void insert()
            throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        HashMap calendar = null;
        contentValues.put("week", dotw.getWeek());
        contentValues.put("mondaycompletedsteps", "no");
        contentValues.put("tuesdaycompletedsteps", "no");
        contentValues.put("wednesdaycompletedsteps", "no");
        contentValues.put("thursdaycompletedsteps", "no");
        contentValues.put("fridaycompletedsteps", "no");
        contentValues.put("saturdaycompletedsteps", "no");
        contentValues.put("sundaycompletedsteps", "no");

        long ins = db.insert("user", null, contentValues);

        System.out.println("ins " + ins);
    }

    public void reset() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("drop table if exists user");
        onCreate(db);
    }

    //has this day met daily step goal stored in db? //String day, int num
    public boolean checkStatus() throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        String t = "yes";
        String empName = "";

        cursor = db.rawQuery("SELECT * FROM user WHERE tuesdaycompletedsteps = '" + t + "'", null);

        //if returns 1 . db recognises yes under tuesdaycompletedsteps!
        System.out.println("get count " + cursor.getCount());
        if (cursor.getCount() < 1) {

            return false;
        } else {
            //get string :)
            cursor.moveToFirst();
            empName = cursor.getString(cursor.getColumnIndex("tuesdaycompletedsteps"));
            if (empName.equalsIgnoreCase("yes")) {
                System.out.println("tuesday steps updated, todo update view and store as boolean (true)");
            }
            System.out.println("Testttttttttttt" + empName);

            return true;
        }
    }
}