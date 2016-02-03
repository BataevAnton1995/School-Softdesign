package com.softdesign.school.ui.activities;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.softdesign.school.R;
import com.softdesign.school.ui.fragments.ProfileFragment;
import com.softdesign.school.ui.fragments.ContactFragment;

import com.softdesign.school.ui.fragments.SettingsFragment;
import com.softdesign.school.ui.fragments.TasksFragment;
import com.softdesign.school.ui.fragments.TeamFragment;
import com.softdesign.school.utils.Lg;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*Объявление констант и приватных переменных
    *Константы - описывающие положение EditText и хранящие значение цвета
    *Кнопки отвечающие за цвет тулбара и статус бара
    * */
    public static final String VISIBLE_KEY = "visible";
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private Fragment mFragment;
   // private FrameLayout mFrameConteiner;
    EditText mEditText;
    EditText mEditText2;
    CheckBox mCheckBox;
    Toolbar mToolbar;


    /*Установка иконки на actionbar*/
    private void setupToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /*Создание activity и других View элементов, так же запуск некоторых процедур*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Lg.e(this.getLocalClassName(), "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        setupToolBar();
        setupDrawer();

        getSupportFragmentManager().beginTransaction().replace(R.id.mane_frame_conteiner,
                new ProfileFragment()).commit();
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

            case R.id.checkBox: {
                Toast.makeText(this, "Click", Toast.LENGTH_LONG).show();
                if (mCheckBox.isChecked()) {
                    mEditText.setVisibility(View.INVISIBLE);
                } else mEditText.setVisibility(View.VISIBLE);
            }
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
                                mNavigationView.getMenu().findItem(R.id.drawer_contacts);
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
                            getSupportFragmentManager().beginTransaction().replace(R.id.mane_frame_conteiner,
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
        outState.getBoolean(VISIBLE_KEY, mEditText.getVisibility() == View.VISIBLE);


    }

    /*Сохранение значений положения элемента activity*/
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Lg.e(this.getLocalClassName(), "onRestoreInstanceState");
        int visibleState = savedInstanceState.getBoolean(VISIBLE_KEY) ? View.VISIBLE : View.INVISIBLE;
        mEditText.setVisibility(visibleState);
    }

}
