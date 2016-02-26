package com.softdesign.school.ui.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.softdesign.school.R;
import com.softdesign.school.data.managers.storage.models.ModelUser;
import com.softdesign.school.data.managers.storage.models.Team;
import com.softdesign.school.data.managers.storage.models.User;
import com.softdesign.school.ui.activities.MainActivity;
import com.softdesign.school.ui.adapters.RecycleUserAdapter;
import com.softdesign.school.ui.adapters.TeamAdapter;
import com.softdesign.school.ui.adapters.UserAdapter;
import com.softdesign.school.ui.adapters.UserAdapterHolder;
import com.softdesign.school.ui.adapters.UserViewHolder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<User>> {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


   @Bind(R.id.btn_add_contact) Button mAddUser;

    Spinner mSpinner;
    EditText mFirstName, mLastName, mNameComand;
    View dialogViewComand, dialogViewUser;
    List<User> mUsers;
    View mainView;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

  @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /* Объявляем лоудер, ставим ему индитификатор, по которому будем его вызывать, с какими данными будет работать наш лоудер и объект */
        getLoaderManager().initLoader(0, savedInstanceState, this);
        /* Подключаем наш RecyclerView, подключаем к нему LayoutManager и адаптер */
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycle_view);
        /* Указываем в менеджере как необходимо формировать на List */
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        /* Указываем, что в адаптер нужно положить все значения из нашей модели ActiveUser */
        mAdapter = new RecycleUserAdapter(User.getAll());
        mRecyclerView.setAdapter(mAdapter);

        /* Удаление записи по свайпу справа-налево */
       ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                 //Обращаемся к нашему holder'у в RecyclerView и указываем метод, который берет все значения из ActiveUser */
                ((UserAdapterHolder) viewHolder).getUser().delete();
                /* Обновляем наши контакты*/
                reloadUserData();
            }
        });
        itemTouchHelper.attachToRecyclerView(mRecyclerView);


    }
    /*обновление данных в списке*/
    private void reloadUserData() {
        getLoaderManager().getLoader(0).forceLoad();
    }
    /*создание фрагмена и инизиализация в butterknife*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (mainView == null) {
            mainView = inflater.inflate(R.layout.fragment_contact, container, false);
            getActivity().setTitle(getResources().getString(R.string.drawer_contacts));
        }
        ((MainActivity) getActivity()).lockAppBar(true);
        loadDataUser();
        ButterKnife.bind(this, mainView);


        return mainView;
    }
    /*добавить контакт*/
  @OnClick(R.id.btn_add_contact)
  public void addContact() {
      LayoutInflater inflater = getActivity().getLayoutInflater();
      dialogViewUser = inflater.inflate(R.layout.dialog_add_user, null, false);
      AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
      mFirstName = (EditText) dialogViewUser.findViewById(R.id.first_name_add);
      mLastName = (EditText) dialogViewUser.findViewById(R.id.last_name_add);
      getSpinner();
      builder.setTitle("Добавить контакт")
              .setCancelable(false)
              .setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      saveContact();
                      dialog.cancel();
                  }
              })
              .setNegativeButton("Отмена",
                      new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int id) {
                              dialog.cancel();
                          }
                      }).setView(dialogViewUser);
      AlertDialog alert = builder.create();
      alert.show();
  }
    /*добавить контакт*/
    @OnClick(R.id.btn_add_comand)
    public void addComand(){
        LayoutInflater inflater = getActivity().getLayoutInflater();
        dialogViewComand = inflater.inflate(R.layout.dialog_add_comand, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        mNameComand = (EditText) dialogViewComand.findViewById(R.id.add_comand);
        builder.setTitle("Добавить команду")
                .setCancelable(false)
                .setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveComand();
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).setView(dialogViewComand);
        AlertDialog alert = builder.create();
        alert.show();
    }
    /*сохранить команду*/
    private void saveComand() {
        Team team = new Team(mNameComand.getText().toString());
        team.save();

    }
    /*сохранить контакт*/
    private void saveContact() {

        User user = new User(mFirstName.getText().toString(),
                mLastName.getText().toString(), mSpinner.getSelectedItem().toString());
        user.save();
        reloadUserData();

    }
    /*заполнение спиннера данными из БД*/
    private void getSpinner() {
        TeamAdapter adapter = new TeamAdapter(getActivity(),Team.getAll());
        mSpinner = (Spinner) dialogViewUser.findViewById(R.id.spinner_team);
        mSpinner.setAdapter(adapter);
    }
    /*запрос на получение списка всех контактов*/
    public void loadDataUser() {
        mUsers = getDataListUser();
    }

    public List<User> getDataListUser() {
        return new Select()
                .from(User.class)
                .execute();
    }
    /*пересоздание активити*/
    @Override
    public Loader<List<User>> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<List<User>>(getContext()) {
            @Override
            public List<User> loadInBackground() {
                return User.getAll();
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<List<User>> loader, List<User> data) {
            mAdapter = new RecycleUserAdapter(data);
            mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onLoaderReset(Loader<List<User>> loader) {

    }

}