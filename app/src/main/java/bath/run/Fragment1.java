package bath.run;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.fitness.data.Goal;

/**
 * Created by mradl on 18/06/2018.
 */

public class Fragment1 extends Fragment {
    private static final String TAG = "Fragment1";


    private TextView tvDailyStepsPercentage;
    private TextView tvDailySteps;
    private TextView tvMotivationalMessage;
    private ProgressBar progressBarDailySteps;
    final Handler handler = new Handler();
    MotivationalMessages mm = new MotivationalMessages();
    double total = 0;
       int progress = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //create view object
        View view = inflater.inflate(R.layout.overview, container, false);

        tvDailySteps = (TextView) view.findViewById(R.id.tvDailySteps);
        tvDailyStepsPercentage = (TextView) view.findViewById(R.id.tvDailyStepsPercentage);
        progressBarDailySteps = (ProgressBar) view.findViewById(R.id.progressBarDailySteps);
        tvMotivationalMessage = (TextView) view.findViewById(R.id.tvMotivationalMessage);




        tvMotivationalMessage.setText(mm.getMotivationalMessage());
        total = GoalCompletion.workOutRemainingPercentage(MainActivity.dailySteps, GoalCompletion.getDailyStepsGoal());

        update();
        //set listener to start btn
        Log.d(TAG, "onCreateView: started..");

        return view;
    }



    public void update() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(MainActivity.dailySteps == 0)
                    return;

                tvDailySteps.setText(String.valueOf(MainActivity.dailySteps));
                total = GoalCompletion.workOutRemainingPercentage(MainActivity.dailySteps, GoalCompletion.getDailyStepsGoal());
                progress = ((int) total);
                tvDailyStepsPercentage.setText(String.valueOf(progress)+"%");
                progressBarDailySteps.setProgress(progress);

                handler.postDelayed(this, MainActivity.timer);


            }
        }, 1000);  //the time is in miliseconds

    }

}
