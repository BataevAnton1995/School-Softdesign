package com.softdesign.school.ui.activities;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.AndroidCharacter;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.softdesign.school.R;
import com.softdesign.school.utils.Lg;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*Объявление констант и приватных переменных
    *Константы - описывающие положение EditText и хранящие значение цвета
    *Кнопки отвечающие за цвет тулбара и статус бара
    * */
    public static final String VISIBLE_KEY = "visible";
    public static int COLOR_TOOL_BAR = R.color.blue;
    public static int COLOR_STATUS_BAR = R.color.blue_dark;
    EditText mEditText;
    EditText mEditText2;
    CheckBox mCheckBox;
    Button mButtonRed;
    Button mButtonGreen;
    Button mButtonBlue;
    Toolbar mToolbar;

    /*Установка иконки на actionbar*/
    private void setupToolBar(){
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
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
        setTitle("School Lesson 1");
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mEditText = (EditText) findViewById(R.id.editText);
        mEditText2 = (EditText) findViewById(R.id.editText2);
        mCheckBox = (CheckBox) findViewById(R.id.checkBox);
        mButtonRed = (Button) findViewById(R.id.btn_red);
        mButtonGreen = (Button) findViewById(R.id.btn_green);
        mButtonBlue = (Button) findViewById(R.id.btn_blue);
        mCheckBox.setOnClickListener(this);
        mButtonRed.setOnClickListener(this);
        mButtonGreen.setOnClickListener(this);
        mButtonBlue.setOnClickListener(this);
        setupToolBar();
        changeColorToolBar();
        changeColorStatusBar();
    }
    /*Изменения цвета toolbar*/
    void changeColorToolBar(){
        mToolbar.setBackgroundColor(this.getResources().getColor(COLOR_TOOL_BAR));
    }
    /*Изменения цвета statusbar*/
    void changeColorStatusBar(){
        getWindow().setStatusBarColor(this.getResources().getColor(COLOR_STATUS_BAR));
    }
    /*Вывод сообщения по нажатия на кнопку Home*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            Toast.makeText(this,"Menu Click",Toast.LENGTH_SHORT).show();
        }
        return  super.onOptionsItemSelected(item);
    }
    /*Изменение цвета toolbar и statusbar по кнопкам
    * а так же изменение видимости поля по клику на checkBox*/
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_red:{
                COLOR_TOOL_BAR=R.color.red;
                COLOR_STATUS_BAR=R.color.red_dark;
                mToolbar.setBackgroundColor(this.getResources().getColor(COLOR_TOOL_BAR));
                getWindow().setStatusBarColor(this.getResources().getColor(COLOR_STATUS_BAR));
                break;
            }
            case R.id.btn_green:{
                COLOR_TOOL_BAR=R.color.green;
                COLOR_STATUS_BAR=R.color.green_dark;
                mToolbar.setBackgroundColor(this.getResources().getColor(COLOR_TOOL_BAR));
                getWindow().setStatusBarColor(this.getResources().getColor(COLOR_STATUS_BAR));

                break;
            }
            case R.id.btn_blue:{
                COLOR_TOOL_BAR=R.color.blue;
                COLOR_STATUS_BAR=R.color.blue_dark;
                mToolbar.setBackgroundColor(this.getResources().getColor(COLOR_TOOL_BAR));
                getWindow().setStatusBarColor(this.getResources().getColor(COLOR_STATUS_BAR));

                break;
            }
            case R.id.checkBox:{
                Toast.makeText(this, "Click", Toast.LENGTH_LONG).show();
                if (mCheckBox.isChecked()) {
                    mEditText.setVisibility(View.INVISIBLE);
                } else mEditText.setVisibility(View.VISIBLE);
            }
        }


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
