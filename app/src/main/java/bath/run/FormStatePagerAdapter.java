package bath.run;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mradl on 18/06/2018.
 */

public class FormStatePagerAdapter extends FragmentStatePagerAdapter {


    private final List<Fragment> mFragmentList = new ArrayList<>();

    //Not needed for this small application, but handy for keeping track of fragments if in the future you were to add more forms
    private final List<String> mFragmentTitleList = new ArrayList<>();


    //Our default constructor
    public FormStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        // return null;
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        //return 0;

        return mFragmentList.size();
    }
}
