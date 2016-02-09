package com.softdesign.school.ui.activities;
//applicationId "com.softdesign.school"
import android.annotation.TargetApi;
import android.os.Build;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.softdesign.school.R;
import com.softdesign.school.ui.fragments.ContactFragment;
import com.softdesign.school.ui.fragments.ProfileFragment;
import com.softdesign.school.ui.fragments.SettingsFragment;
import com.softdesign.school.ui.fragments.TasksFragment;
import com.softdesign.school.ui.fragments.TeamFragment;
import com.softdesign.school.utils.Lg;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    /*Объявление констант и приватных переменных
    *Константы - описывающие положение EditText и хранящие значение цвета
    *Кнопки отвечающие за цвет тулбара и статус бара
    * */
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private Fragment mFragment;
    private AppBarLayout mAppBar;
    private CollapsingToolbarLayout mCollapsingToolbar;
   // private ListView mListView;

    AppBarLayout.LayoutParams params = null;
    /*Создание activity и других View элементов, так же запуск некоторых процедур*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Lg.e(this.getLocalClassName(), "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer);
        mAppBar = (AppBarLayout) findViewById(R.id.appbar_layout);
        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        setupToolBar();
        setupDrawer();
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

    /*Изменение цвета toolbar и statusbar  по кнопкам
    * а так же изменение видимости поля по клику на checkBox*/
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {


        }


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

    /*Старт activity*/
    @Override
    protected void onStart() {
        super.onStart();
        Lg.e(this.getLocalClassName(), "onCreate");

    }

    /*Просмотр activity */
    @Override
    protected void onResume() {
        super.onResume();
        Lg.e(this.getLocalClassName(), "onResume");
    }

    /*Пауза activity*/
    @Override
    protected void onPause() {
        super.onPause();
        Lg.e(this.getLocalClassName(), "onPause");
    }

    /*Остановка activity*/
    @Override
    protected void onStop() {
        super.onStop();
        Lg.e(this.getLocalClassName(), "onStop");
    }

    /*Перезагрузка activity*/
    @Override
    protected void onRestart() {
        super.onRestart();
        Lg.e(this.getLocalClassName(), "onRestart");
    }

    /*Уничтожение activity*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Lg.e(this.getLocalClassName(), "onDestroy");
    }

    /*Сохранение значений положения элемента activity*/
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Lg.e(this.getLocalClassName(), "onSaveInstanceState");
        //outState.getBoolean(VISIBLE_KEY, mEditText.getVisibility() == View.VISIBLE);


    }

    /*Сохранение значений положения элемента activity*/
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Lg.e(this.getLocalClassName(), "onRestoreInstanceState");
        // int visibleState = savedInstanceState.getBoolean(VISIBLE_KEY) ? View.VISIBLE : View.INVISIBLE;
        // mEditText.setVisibility(visibleState);
    }

}
