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

/**
 * Created by mradl on 18/06/2018.
 */

public class Fragment3 extends Fragment {
    private static final String TAG = "Fragment3";

    private Button btnPrevious;
    private Button btnNext;
    private ImageView imgZebra;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          //create view object
        View view = inflater.inflate(R.layout.distance_fragment, container, false);




        Log.d(TAG, "onCreateView: started..");


        return view;
    }
}
