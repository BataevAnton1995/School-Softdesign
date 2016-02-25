package com.softdesign.school.ui.activities;
//applicationId "com.softdesign.school"

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.softdesign.school.R;
import com.softdesign.school.ui.fragments.ContactFragment;
import com.softdesign.school.ui.fragments.ProfileFragment;
import com.softdesign.school.ui.fragments.SettingsFragment;
import com.softdesign.school.ui.fragments.TasksFragment;
import com.softdesign.school.ui.fragments.TeamFragment;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {


    /*Объявление констант и приватных переменных
    *Константы - описывающие положение EditText и хранящие значение цвета
    *Кнопки отвечающие за цвет тулбара и статус бара
    * */

    private Fragment mFragment;

   // private ListView mListView;
       @Bind(R.id.toolbar) Toolbar mToolbar;
       @Bind(R.id.navigation_drawer) DrawerLayout mDrawerLayout;
       @Bind(R.id.navigation_view) NavigationView mNavigationView;
       @Bind(R.id.appbar_layout) AppBarLayout mAppBar;
       @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbar;

    AppBarLayout.LayoutParams params = null;

    public MainActivity() {
    }

    /*Создание activity и других View элементов, так же запуск некоторых процедур*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
/*111111111111111111111111111111111111111111111111111111111111111111*/
        /*222222222222222222222222222222222222222222222222222222222222222222222*/


        setupDrawer();
        setupToolBar();
        mCollapsingToolbar.setExpandedTitleGravity(Gravity.BOTTOM);
        mCollapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.icon_color));
        mCollapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.black));
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_conteiner,
                new ProfileFragment()).commit();
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("statusbar", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelOffset(resourceId);
        }
        return result;
    }


    public void lockAppBar(boolean collapse) {

        params = (AppBarLayout.LayoutParams) mCollapsingToolbar.getLayoutParams();
        if (collapse) {
            Log.e(this.getLocalClassName(), "collapse yes");
            mAppBar.setExpanded(false);
            AppBarLayout.OnOffsetChangedListener mListener = new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (mCollapsingToolbar.getHeight() + verticalOffset
                            <= ViewCompat.getMinimumHeight(mCollapsingToolbar) + getStatusBarHeight()) {
                        params.setScrollFlags(0);
                        mCollapsingToolbar.setLayoutParams(params);
                        mAppBar.removeOnOffsetChangedListener(this);
                    }
                }
            };
            mAppBar.removeOnOffsetChangedListener(mListener);
        } else {

            mAppBar.setExpanded(true);
            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL |
                    AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
            mCollapsingToolbar.setLayoutParams(params);
        }
    }


    /*Установка иконки на actionbar*/

    private void setupToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }



    /*Вывод сообщения по нажатия на кнопку Home*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(this, "Menu Click", Toast.LENGTH_SHORT).show();
        }
        mDrawerLayout.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }



    private void setupDrawer() {
        mNavigationView.setNavigationItemSelectedListener
                (new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.drawer_profile: {
                                mFragment = new ProfileFragment();
                                mNavigationView.getMenu().findItem(R.id.drawer_profile);
                                break;
                            }
                            case R.id.drawer_contacts: {
                                mFragment = new ContactFragment();
                                mNavigationView.getMenu().findItem(R.id.drawer_contacts).setChecked(true);
                                break;
                            }
                            case R.id.drawer_team: {
                                mFragment = new TeamFragment();
                                mNavigationView.getMenu().findItem(R.id.drawer_team).setChecked(true);
                                break;
                            }
                            case R.id.drawer_task: {
                                mFragment = new TasksFragment();
                                mNavigationView.getMenu().findItem(R.id.drawer_task).setChecked(true);
                                break;
                            }
                            case R.id.drawer_setting: {
                                mFragment = new SettingsFragment();
                                mNavigationView.getMenu().findItem(R.id.drawer_setting).setChecked(true);
                                break;
                            }

                        }

                        if (mFragment != null) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_conteiner,
                                    mFragment).addToBackStack(null).commit();
                        }
                        mDrawerLayout.closeDrawers();
                        return false;
                    }
                });
    }



}
