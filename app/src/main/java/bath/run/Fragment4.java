package bath.run;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mradl on 18/06/2018.
 */

public class Fragment4 extends Fragment {
    private static final String TAG = "Fragment4";

    private Button btnSave;
    private TextView txtThankYou;
    private ImageView imgZebra;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          //create view object
        View view = inflater.inflate(R.layout.calories_fragment, container, false);

        //xml -- since we are in a fragment we must do view. instead of findViewbyId like we would in an activity


        Log.d(TAG, "onCreateView: started..");

        return view;
    }
}
