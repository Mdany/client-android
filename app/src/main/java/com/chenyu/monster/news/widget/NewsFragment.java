package com.chenyu.monster.news.widget;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.chenyu.monster.R;
import com.chenyu.monster.framework.BaseFragment;
import com.chenyu.monster.news.NewsType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyu on 16/4/12.
 */
public class NewsFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public NewsFragment() {
        super(R.layout.f_news_list);
    }

    @Override
    public void viewDidLoad() {
        tabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);
        setUpViewPager();
        setUpTabLayout();
    }

    private void setUpViewPager() {
        viewPager.setOffscreenPageLimit(3);
        NewsPagerAdapter adapter = new NewsPagerAdapter(getChildFragmentManager());
        adapter.addFragment(getString(R.string.news_top), NewsListFragment.newInstance(NewsType.NEWS_TYPE_TOP));
        adapter.addFragment(getString(R.string.news_nba), NewsListFragment.newInstance(NewsType.NEWS_TYPE_NBA));
        adapter.addFragment(getString(R.string.news_cars), NewsListFragment.newInstance(NewsType.NEWS_TYPE_CARS));
        adapter.addFragment(getString(R.string.news_jokes), NewsListFragment.newInstance(NewsType.NEWS_TYPE_JOKES));
        viewPager.setAdapter(adapter);
    }

    private void setUpTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText(R.string.news_top));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.news_nba));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.news_cars));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.news_jokes));
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * tab layout下的adapter
     */
    public class NewsPagerAdapter extends FragmentPagerAdapter {
        private List<String> pageTitles;
        private List<Fragment> pageFragments;

        public NewsPagerAdapter(FragmentManager fm) {
            super(fm);
            pageTitles = new ArrayList<>();
            pageFragments = new ArrayList<>();
        }

        public void addFragment(String title, Fragment fragment) {
            pageTitles.add(title);
            pageFragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return pageFragments.get(position);
        }

        @Override
        public int getCount() {
            return pageFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pageTitles.get(position);
        }
    }
}
