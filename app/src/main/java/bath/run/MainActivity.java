package bath.run;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.result.DailyTotalResult;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import bath.run.database.DatabaseHelper;
import bath.run.database.User;


//add  View.OnClickListener to implements list if using onClick switch in the future
public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "l";
    final Handler handler = new Handler();
    public static int dailySteps = 0;
    public static int weeklySteps = 0;
    private FormStatePagerAdapter mFormStatePagerAdapter;
    private ViewPager mViewPager;
    Toolbar toolbar;
    public static int timer = 3000;
    GoalCompletion goalCompletion = new GoalCompletion();
    DayOfTheWeek dotw = new DayOfTheWeek();
    private TextView tvWeekSteps;
    public static boolean hasUpdated = false;
    public int t;

    DatabaseHelper db = new DatabaseHelper(this);

    private ImageView imgMon;
    private ImageView imgTue;
    private ImageView imgWed;
    private ImageView imgThu;
    private ImageView imgFri;
    private ImageView imgSat;
    private ImageView imgSun;
    private GoogleApiClient mGoogleApiClient;

    List<User> mUsers = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //   db = new DatabaseHelper(this);

        initViews();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Fitness.HISTORY_API)
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE))
                .addConnectionCallbacks(this)
                .enableAutoManage(this, 0, this)
                .build();


        mFormStatePagerAdapter = new FormStatePagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);


        toolbar = (Toolbar) findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);

        System.out.println("occdfsff");
        // db.reset();
        runDb();
        workUserList();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initViews() {
        imgMon = (ImageView) findViewById(R.id.imgMon);
        imgTue = (ImageView) findViewById(R.id.imgTue);
        imgWed = (ImageView) findViewById(R.id.imgWed);
        imgThu = (ImageView) findViewById(R.id.imgThu);
        imgFri = (ImageView) findViewById(R.id.imgFri);
        imgSat = (ImageView) findViewById(R.id.imgSat);
        imgSun = (ImageView) findViewById(R.id.imgSun);
    }

    public void update() {
        startUpdating();
    }


    public void onConnected(@Nullable Bundle bundle) {
        Log.e("HistoryAPI", "onConnected");

        setDayTextView();
    }

    //this creates database if one does not already exist.
    public void runDb() {
        SQLiteDatabase collectionDB = db.getWritableDatabase();
        //db.insert(collectionDB); //use this this insert values in a new method tmrw
    }

    public void workUserList() {
        List<User> userList = db.getUsers();
        setUsers(userList);

        System.out.println("week = " + User.getWeek());

        for (int i = 0; i < userList.size(); i++) {
            System.out.println("List " + userList.get(i));
        }
    }


    public void setUsers(List<User> users) {
        mUsers = users;
        System.out.println(mUsers.toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //gets specific item
        int id = item.getItemId();

        switch (id) {
            case R.id.action_newCalorieGoal:
                //do something
                break;

            case R.id.action_newStepGoal:
                goalCompletion.setDailyStepsGoal(goalCompletion.getDailyStepsGoal() * 1.5);
                Toast.makeText(getApplicationContext(), "Your daily steps are updating..", Toast.LENGTH_SHORT).show();
                System.out.println(timer);
                break;

            case R.id.action_weeklyStepGoal:
                //do something
                break;
        }
        System.out.println(id + " clicked");
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /*
    *   Automatically starts the first fragment in the list below.
    *   Other fragments are called upon button click as apparent in
    *   each fragment class.
     */
    private void setupViewPager(ViewPager viewPager) {
        FormStatePagerAdapter adapter = new FormStatePagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new Fragment1(), "Steps");
        adapter.addFragment(new Fragment2(), "Heartrate");
        adapter.addFragment(new Fragment3(), "Distance");
        adapter.addFragment(new Fragment4(), "Calories");
        viewPager.setAdapter(adapter);
    }


    public void setViewPager(int fragmentNumber) {
        mViewPager.setCurrentItem(fragmentNumber);
    }


    //In use, call this every 30 seconds in active mode, 60 in ambient on watch faces
    //TODO implement a callback
    private void displayStepDataForToday() {
        DailyTotalResult result = Fitness.HistoryApi.readDailyTotal(mGoogleApiClient, DataType.TYPE_STEP_COUNT_DELTA).await(3, TimeUnit.SECONDS);
        showDataSet(result.getTotal(), 1);
        //  System.out.println(result);

    }


    private void showDataSet(DataSet dataSet, int days) {

        //  Log.e("History", "Data returned for Data type: " + dataSet.getDataType().getName());
        DateFormat dateFormat = DateFormat.getDateInstance();
        DateFormat timeFormat = DateFormat.getTimeInstance();

        for (DataPoint dp : dataSet.getDataPoints()) {

            for (Field field : dp.getDataType().getFields()) {
                Log.i(TAG, "\tField: " + field.getName() + " Value: " + dp.getValue(field));
                if (days == 1) {
                    dailySteps = dp.getValue(field).asInt();
                }
            }
        }
    }

    private void checkWeeklyIcons() {
        int d;
        d = dotw.getDay();

        if (dailySteps >= GoalCompletion.getDailyStepsGoal()) {
            //hit limit for the day. lets update ui
            //for each day update
            System.out.println("1");
            for (int i = 1; i <= dotw.DAYS_IN_WEEK; i++) {
                System.out.println("2");
                if (d == i) {
                    System.out.println("3");
                    db.updateRow(d);
                    System.out.println("Updating row");
                }
            }
        } else {
            System.out.println("Daily steps not reached");
        }
    }


    public void startUpdating() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                new ViewTodaysStepCountTask().execute();
                if (!hasUpdated && dailySteps >= goalCompletion.getDailyStepsGoal()) {
                    checkWeeklyIcons();
                    System.out.println("sjdhfjdsjh");
                }
                handler.postDelayed(this, timer);
            }
        }, 0); //the time is in miliseconds*/
    }

    public void setDayTextView() {
        if (User.isMonday()) {
            imgMon.setImageResource(R.drawable.tickicon);
        } else {
            imgMon.setImageResource(R.drawable.crossicon);
        }
        if (User.isTuesday()) {
            imgTue.setImageResource(R.drawable.tickicon);
        } else {
            imgTue.setImageResource(R.drawable.crossicon);
        }
        if (User.isWednesday()) {
            imgWed.setImageResource(R.drawable.tickicon);
        } else {
            imgWed.setImageResource(R.drawable.crossicon);
        }
        if (User.isThursday()) {
            imgThu.setImageResource(R.drawable.tickicon);
        } else {
            imgThu.setImageResource(R.drawable.crossicon);
        }
        if (User.isFriday()) {
            imgFri.setImageResource(R.drawable.tickicon);
        } else {
            imgFri.setImageResource(R.drawable.crossicon);
        }
        if (User.isSaturday()) {
            imgSat.setImageResource(R.drawable.tickicon);
        } else {
            imgSat.setImageResource(R.drawable.crossicon);
        }
        if (User.isSunday()) {
            imgSun.setImageResource(R.drawable.tickicon);
        } else {
            imgSun.setImageResource(R.drawable.crossicon);
        }
    }

    public class ViewTodaysStepCountTask extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... params) {
            displayStepDataForToday();
            return null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Main Activity", "onPause");
        //when user pauses app, check if daily goal is reached.
        goalCompletion.goalReached(db);
    }


    @Override
    public void onConnectionSuspended(int i) {

        Log.e("HistoryAPI", "onConnectionSuspended");

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("HistoryAPI", "onConnectionFailed");
    }
}

