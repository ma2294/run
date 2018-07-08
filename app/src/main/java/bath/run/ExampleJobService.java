package bath.run;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by mradl on 08/07/2018.
 */

public class ExampleJobService extends JobService {
    private static final String TAG = "ExampleJobService";
    private boolean jobcancelled = false;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.i(TAG, "onStartJob: ");
        doBackgroundWork(jobParameters);
        return true; //this runs in ui thread so must be handled upon completion
    }

    private void doBackgroundWork(final JobParameters jobParameters) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (jobcancelled) {
                    return; //leave immediately.
                }
                MainActivity.setSteps();
                Log.i(TAG, "run: Job Finished");
                jobFinished(jobParameters, false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.i(TAG, "onStopJob: Job cancelled before Job Finished");
        jobcancelled = true;
        return true; //we want the job to reattempt to finish.
    }
}
