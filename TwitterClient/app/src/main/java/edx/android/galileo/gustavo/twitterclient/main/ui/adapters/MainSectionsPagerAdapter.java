package edx.android.galileo.gustavo.twitterclient.main.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by gustavo on 16/06/16.
 */
public class MainSectionsPagerAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private Fragment[] fragments;

    public MainSectionsPagerAdapter(FragmentManager fm, Fragment[] fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.titles[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
