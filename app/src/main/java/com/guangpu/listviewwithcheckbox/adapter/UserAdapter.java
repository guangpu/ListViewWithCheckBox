package com.guangpu.listviewwithcheckbox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.guangpu.listviewwithcheckbox.R;
import com.guangpu.listviewwithcheckbox.bean.User;

import java.util.List;

/**
 * Created by roy on 2015/9/29.
 */
public class UserAdapter extends BaseAdapter {

    private List<User> mData;
    private Context mContext;

    public UserAdapter (Context context, List<User> userList) {
        this.mData = userList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_user, null);
        CheckBox checkBox = ViewHolder.get(convertView, R.id.checkBox);
        ImageView iv_photo = ViewHolder.get(convertView, R.id.iv_photo);
        TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);
        TextView tv_intro = ViewHolder.get(convertView, R.id.tv_intro);

        User user = mData.get(position);

        checkBox.setChecked(user.isChecked());
        if(user.isEdited()) {
            checkBox.setVisibility(View.VISIBLE);
        } else {
            checkBox.setVisibility(View.GONE);
        }

        tv_name.setText(user.getName());
        tv_intro.setText(user.getIntro());
        iv_photo.setImageResource(R.drawable.photo);
        return convertView;
    }
}
