package com.example.kacent.mytaksdemo2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Kacent on 2016/3/1.
 */
public class MyAdapter extends BaseAdapter {
    public List<StuData> listdata;
    public LayoutInflater mInflater;
    public LoadImage loadImage;

    public MyAdapter(List<StuData> listdata, Context context) {
        mInflater = LayoutInflater.from(context);
        this.listdata = listdata;
    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int position) {
        return listdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHlder viewHlder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            viewHlder = new ViewHlder();
            viewHlder.imageUrl = (ImageView) convertView.findViewById(R.id.imageView);
            viewHlder.title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHlder.context = (TextView) convertView.findViewById(R.id.tv_context);
            convertView.setTag(viewHlder);
        } else {
            viewHlder = (ViewHlder) convertView.getTag();
        }
        StuData data = listdata.get(position);
        viewHlder.imageUrl.setImageResource(R.mipmap.ic_launcher);
        viewHlder.imageUrl.setTag(data.imgIcon);
        loadImage=new LoadImage(viewHlder.imageUrl,data.imgIcon);
        loadImage.ShowByTask(data.imgIcon);
        viewHlder.title.setText(data.title);
        viewHlder.context.setText(data.context);
        return convertView;
    }

    class ViewHlder {
        ImageView imageUrl;
        TextView title;
        TextView context;
    }
}
