package limocity.cbistech.com.limocity.onboarding;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by piyush on 10/12/17.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    ArrayList<OnBoadingFragment> list = new ArrayList<>();

    public void setList(ArrayList<OnBoadingFragment> list) {
        this.list=list;
        notifyDataSetChanged();
    }

    public MyPagerAdapter(FragmentManager fm,ArrayList<OnBoadingFragment> list) {
        super(fm);
        this.list=list;

    }

    @Override
    public Fragment getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public int getCount() {
        return 3;
    }
}