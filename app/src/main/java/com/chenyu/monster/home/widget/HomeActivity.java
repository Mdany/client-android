package com.chenyu.monster.home.widget;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.chenyu.monster.DoitApplication;
import com.chenyu.monster.R;
import com.chenyu.monster.login.LoginActivity;
import com.chenyu.monster.framework.BaseActivity;
import com.chenyu.monster.home.View.HomeView;
import com.chenyu.monster.home.presenter.HomePresenter;
import com.chenyu.monster.home.presenter.HomePresenterImpl;
import com.chenyu.monster.util.SnackUtil;

/**
 * 首页显示四个类别
 */
public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, HomeView {
    /**
     * toolbar
     */
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private ActionBarDrawerToggle toggle;
    /**
     * 侧边栏
     */
    private NavigationView navigationView;
    /**
     * 侧滑菜单
     */
    private DrawerLayout drawer;
    /**
     * 退出时间
     */
    private int exitTime = 0;
    /**
     * 头像
     */
    private ImageView avatar;
    /**
     * controller
     */
    private HomePresenter mHomePresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.a_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDidLoad();
    }

    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        avatar = (ImageView) headerView.findViewById(R.id.imageView);
        avatar.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (System.currentTimeMillis() - exitTime > 2000) {
            SnackUtil.show(drawer, R.string.home_exit);
            exitTime = (int) System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * view完成后初始化
     */
    public void viewDidLoad() {
        mHomePresenter = new HomePresenterImpl(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        mHomePresenter.switchDrawerItem(id);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.imageView:
                if (DoitApplication.application.getUser() == null) {
                    startActivity(LoginActivity.class);
                } else {
                    //TODO 个人资料？
                }
                break;
        }
    }

    @Override
    public void switch2News() {
        //getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new ).commit();
        toolbar.setTitle(getString(R.string.news));
    }

    @Override
    public void switch2Image() {
        //getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new ).commit();
        toolbar.setTitle(getString(R.string.image));
    }

    @Override
    public void switch2Weather() {
        //getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new ).commit();
        toolbar.setTitle(getString(R.string.weather));
    }

    @Override
    public void switch2About() {
        //getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new ).commit();
        toolbar.setTitle(getString(R.string.about));
    }
}
