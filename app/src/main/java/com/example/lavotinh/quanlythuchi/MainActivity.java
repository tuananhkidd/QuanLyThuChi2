package com.example.lavotinh.quanlythuchi;

import android.app.DatePickerDialog;
import android.app.Dialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;;
import android.app.TimePickerDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DialogTitle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.lavotinh.quanlythuchi.adapter.WorkAdapter;
import com.example.lavotinh.quanlythuchi.model.work;

public class MainActivity extends AppCompatActivity {
    Button btn_add;
    ListView lv_work;
    ArrayAdapter<work> adapter;
    ArrayList<work> listwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidget();
        setEvent();

    }

    public void initWidget() {
        btn_add = (Button) findViewById(R.id.bt_add);
        lv_work = (ListView) findViewById(R.id.lv_work);

        listwork = new ArrayList<>();
        adapter = new WorkAdapter(MainActivity.this, R.layout.work_layout, listwork);
        lv_work.setAdapter(adapter);

    }

    public void setEvent() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setTitle("Thêm Công Việc");
                dialog.setContentView(R.layout.addwork_layout);

                final EditText edt_tencv = dialog.findViewById(R.id.edt_tencv);
                final EditText edt_sotien = dialog.findViewById(R.id.edt_sotien);

                final TextView txt_ngay = dialog.findViewById(R.id.txt_date);
                final TextView txt_gio = dialog.findViewById(R.id.txt_time);

                final ImageButton img_ngay = dialog.findViewById(R.id.img_date);
                final ImageButton img_gio = dialog.findViewById(R.id.img_time);

                Button btn_luu = dialog.findViewById(R.id.btn_luu);

                Calendar calendar = Calendar.getInstance();
                txt_ngay.setText(calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR));
                String am_pm = null;
                if (calendar.get(Calendar.HOUR) >= 12) {
                    am_pm = "PM";
                } else {
                    am_pm = "AM";
                }
                txt_gio.setText((calendar.get(Calendar.HOUR_OF_DAY) - 1) + ":" + calendar.get(Calendar.MINUTE) + "  " + am_pm);


                img_ngay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final Calendar cal = Calendar.getInstance();


                        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
                            //Sự kiện khi click vào nút Done trên Dialog
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                txt_ngay.setText(day + "/" + (month + 1) + "/" + year);
                                cal.set(year, month, day);

                            }
                        };

                        int day = cal.get(Calendar.DAY_OF_MONTH);
                        int month = (cal.get(Calendar.MONTH));
                        int year = cal.get(Calendar.YEAR);

                        DatePickerDialog datepicker = new DatePickerDialog(MainActivity.this, callback, year, month, day);
                        datepicker.setTitle("Chọn ngày");
                        datepicker.show();


                    }
                });

                img_gio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Calendar cal = Calendar.getInstance();
                        TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                txt_gio.setText(hour + ":" + minute + (hour > 12 ? " PM" : " AM"));

                                cal.set(Calendar.HOUR_OF_DAY, hour);
                                cal.set(Calendar.MINUTE, minute);

                            }
                        };

                        TimePickerDialog time = new TimePickerDialog(MainActivity.this, callback, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
                        time.setTitle("Chon Thoi Gian");
                        time.show();
                    }
                });

                dialog.show();

                btn_luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Random rd = new Random();
                        int id = rd.nextInt(100);

                        listwork.add(new work(id, edt_tencv.getText().toString(), Integer.parseInt(edt_sotien.getText().toString()), txt_ngay.getText().toString(), txt_gio.getText().toString()));

                        adapter.notifyDataSetChanged();

                        dialog.dismiss();

                    }
                });


            }
        });

    }

}
