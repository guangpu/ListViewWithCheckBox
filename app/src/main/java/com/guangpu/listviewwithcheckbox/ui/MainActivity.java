package com.guangpu.listviewwithcheckbox.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guangpu.listviewwithcheckbox.R;
import com.guangpu.listviewwithcheckbox.adapter.UserAdapter;
import com.guangpu.listviewwithcheckbox.bean.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This is a test project.
 */
public class MainActivity extends Activity implements View.OnClickListener{

    private ListView listView;
    private UserAdapter adapter;
    private TextView tv_edit, tv_delete;
    private RelativeLayout delete_layout;
    private List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        init();
    }

    private void findView() {
        listView = (ListView) findViewById(R.id.listView);
        tv_edit = (TextView) findViewById(R.id.tv_edit);
        tv_delete = (TextView) findViewById(R.id.tv_delete);
        delete_layout = (RelativeLayout) findViewById(R.id.delete_layout);
    }

    private void init() {
        tv_edit.setOnClickListener(this);
        tv_delete.setOnClickListener(this);

        for (int i = 0; i < 6; i++) {
            User user = new User();
            user.setName("David");
            user.setIntro("Hello, I am David...");
            user.setIsChecked(false);
            users.add(user);
        }
        adapter = new UserAdapter(this, users);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_edit:
                if (tv_edit.getText().equals("编辑")) {
                    tv_edit.setText("取消");
                    delete_layout.setVisibility(View.VISIBLE);

                    for (int i = 0; i < users.size(); i++) {
                        User user = users.get(i);
                        if (user.isEdited()) {
                            user.setIsEdited(false);
                        } else {
                            user.setIsEdited(true);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            User event = (User) adapter.getItem(position);
                            if (event.isChecked()) {
                                event.setIsChecked(false);
                            } else {
                                event.setIsChecked(true);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    });

                } else if (tv_edit.getText().equals("取消")) {
                    tv_edit.setText("编辑");
                    delete_layout.setVisibility(View.GONE);
                    for (int i = 0; i < users.size(); i++) {
                        User user = users.get(i);
                        if (user.isEdited()) {
                            user.setIsEdited(false);
                        } else {
                            user.setIsEdited(true);
                        }
                        if (user.isChecked()) {
                            user.setIsChecked(false);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_delete:
                Iterator<User> it = users.iterator();
                while (it.hasNext()) {
                    User user = it.next();
                    if (user.isChecked()) {
                        it.remove();
                    }
                }
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }
}
