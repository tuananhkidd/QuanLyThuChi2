package com.example.lavotinh.quanlythuchi;

import android.app.Dialog;
import java.util.Calendar;;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DialogTitle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Locale;

import static android.icu.util.GregorianCalendar.*;

public class MainActivity extends AppCompatActivity {
    Button btn_add;
    ListView lv_work;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidget();
        setEvent();

    }

    public void initWidget(){
        btn_add = (Button) findViewById(R.id.bt_add);
       // btn_add.setOnClickListener(this);
        lv_work = (ListView) findViewById(R.id.lv_work );
    }

    public void setEvent(){
        btn_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setTitle("Thêm Công Việc");
                dialog.setContentView(R.layout.addwork_layout);

                EditText edt_tencv = dialog.findViewById(R.id.edt_tencv);
                EditText edt_sotien = dialog.findViewById(R.id.edt_sotien);

                final TextView txt_ngay = dialog.findViewById(R.id.txt_date);
                final TextView txt_gio = dialog.findViewById(R.id.txt_time);

                ImageButton img_ngay = dialog.findViewById(R.id.img_date);
                ImageButton img_gio = dialog.findViewById(R.id.img_time);

                Calendar calendar =  Calendar.getInstance();
                txt_ngay.setText(calendar.get(Calendar.DAY_OF_MONTH) + "/" +(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR));
                txt_gio.setText(calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE)+"  "+calendar.get(Calendar.AM_PM));

                img_ngay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatePicker datePicker = new DatePicker(MainActivity.this);
                        Calendar calendar =  Calendar.getInstance();
                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                        int month = (calendar.get(Calendar.MONTH)+1);
                        int year = calendar.get(Calendar.YEAR);

                        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
                            @Override
                            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                                txt_ngay.setText(i+"/"+i1+"/"+i2);
                            }
                        });
                    }
                });

                img_gio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TimePicker timePicker = new TimePicker(MainActivity.this);
                        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                            @Override
                            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                                txt_gio.setText(i+":"+i1);
                            }
                        });
                    }
                });

                dialog.show();
            }
        });
    }
}
