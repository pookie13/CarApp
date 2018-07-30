package limocity.cbistech.com.limocity.onboarding;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by piyush on 10/12/17.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {
    ArrayList<HomeOnBoadingFragment> list = new ArrayList<>();

    public void setList(ArrayList<HomeOnBoadingFragment> list) {
        this.list=list;
        notifyDataSetChanged();
    }

    public HomePagerAdapter(FragmentManager fm, ArrayList<HomeOnBoadingFragment> list) {
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