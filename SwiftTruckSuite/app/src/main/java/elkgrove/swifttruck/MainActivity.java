package elkgrove.swifttruck;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;

import elkgrove.swifttruck.tabs.MainTabBusiness;
import elkgrove.swifttruck.tabs.MainTabCenter;
import elkgrove.swifttruck.tabs.MainTabUser;

public class MainActivity extends FragmentActivity {
    //private static final int numberOfTabs = 3;
    private static final int numberOfTabs = 2;
    // TODO: will change when user tab is implemented
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    //hey pablo
    /* Override for FragmentActivity */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.main_activity_container);
        viewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.main_activity_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    MainTabBusiness mtb = new MainTabBusiness();
                    return mtb;
                case 1:
                    MainTabCenter mtc = new MainTabCenter();
                    return mtc;
                //case 2:
                //    MainTabUser mtu = new MainTabUser();
                //    return mtu;
                // TODO: implement when user section is available
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return numberOfTabs;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Business";
                case 1:
                    return "Main";
                //case 2:
                //    return "User";
                // TODO: implement when user section is available
            }
            return null;
        }
    }
}
