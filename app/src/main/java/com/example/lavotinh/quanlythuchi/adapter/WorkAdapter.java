package com.example.lavotinh.quanlythuchi.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.lavotinh.quanlythuchi.R;
import com.example.lavotinh.quanlythuchi.model.work;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuaan on 9/12/2017.
 */

public class WorkAdapter extends ArrayAdapter<work>{
    private Context context;
    private int layout;
    private ArrayList<work> list;
    public WorkAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<work> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        viewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.work_layout,parent,false);

            viewHolder.txt_Avt = convertView.findViewById(R.id.txt_avt);
            viewHolder.txt_Conten = convertView.findViewById(R.id.txt_content);
            viewHolder.txt_Money = convertView.findViewById(R.id.txt_money);
            viewHolder.txt_dateTime = convertView.findViewById(R.id.txt_datetime);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (WorkAdapter.viewHolder) convertView.getTag();
        }

        work w = list.get(position);



        viewHolder.txt_Conten.setText(w.getName());
        viewHolder.txt_Money.setText(w.getSumOfMoney()+" VND");
        viewHolder.txt_dateTime.setText(w.getDate()+"   "+w.getTime());
        viewHolder.txt_Avt.setText((position+1)+"");
        viewHolder.txt_Avt.setBackgroundColor(w.getColor());

        return convertView;
    }

    class viewHolder{
        TextView txt_Avt;
        TextView txt_Conten;
        TextView txt_Money;
        TextView txt_dateTime;
    }
}
