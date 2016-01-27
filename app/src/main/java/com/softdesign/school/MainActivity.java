package com.softdesign.school;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.softdesign.school.utils.Lg;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String VISIBLE_KEY = "visible";
    EditText mEditText;
    EditText mEditText2;
    CheckBox mCheckBox;
    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Lg.e(this.getLocalClassName(), "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        setTitle("School Lesson 1");
        mEditText = (EditText) findViewById(R.id.editText);
        mEditText2 = (EditText) findViewById(R.id.editText2);
        mCheckBox = (CheckBox) findViewById(R.id.checkBox);
        mButton = (Button) findViewById(R.id.btn_red);
        mCheckBox.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       int id=v.getId();
       // Log.d(this.getLocalClassName(), );
        switch (id){
            case R.id.checkBox:
                Toast.makeText(this,"Click",Toast.LENGTH_LONG).show();
                if (mCheckBox.isChecked()){
                    mEditText.setVisibility(View.INVISIBLE);
                }else mEditText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Lg.e(this.getLocalClassName(), "onCreate");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Lg.e(this.getLocalClassName(), "onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Lg.e(this.getLocalClassName(), "onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Lg.e(this.getLocalClassName(), "onStop");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Lg.e(this.getLocalClassName(), "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Lg.e(this.getLocalClassName(), "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Lg.e(this.getLocalClassName(), "onSaveInstanceState");
        outState.getBoolean(VISIBLE_KEY,mEditText.getVisibility()==View.VISIBLE);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Lg.e(this.getLocalClassName(), "onRestoreInstanceState");
        int visibleState=savedInstanceState.getBoolean(VISIBLE_KEY)?View.VISIBLE:View.INVISIBLE;
        mEditText.setVisibility(visibleState);
    }

}
