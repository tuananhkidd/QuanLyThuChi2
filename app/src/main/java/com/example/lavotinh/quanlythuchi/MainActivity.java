package com.example.lavotinh.quanlythuchi;

import android.app.DatePickerDialog;
import android.app.Dialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DialogTitle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.lavotinh.quanlythuchi.Database.DBManager;
import com.example.lavotinh.quanlythuchi.adapter.WorkAdapter;
import com.example.lavotinh.quanlythuchi.model.IWork;
import com.example.lavotinh.quanlythuchi.model.work;

public class MainActivity extends AppCompatActivity implements IWork {
    Button btn_add;
    ListView lv_work;
    ArrayAdapter<work> adapter;
    ArrayList<work> listwork;
    DBManager db;
    public int POS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidget();
        setEvent();

        registerForContextMenu(lv_work);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_work, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemEdit: {
                editInfo();
                break;
            }
            case R.id.itemDel: {
                delWork();
                break;
            }
        }
        return super.onContextItemSelected(item);
    }

    private void delWork() {
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(MainActivity.this);
        alBuilder.setMessage("Bạn có chắc chắn muốn xóa ?");
        alBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.delWork(listwork.get(POS));
                listwork.remove(POS);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Đã Xóa !" + db.loadlistWork().size() + " POS = " + POS, Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });

        alBuilder.setNegativeButton("Không", null);

        Dialog dialog = alBuilder.create();
        dialog.show();
    }


    private void editInfo() {

    }

    public void initWidget() {
        btn_add = (Button) findViewById(R.id.bt_add);
        lv_work = (ListView) findViewById(R.id.lv_work);

        db = new DBManager(this);
        if(db.loadlistWork().size()!=0)
        listwork = db.loadlistWork();
        else listwork = new ArrayList<>();

        adapter = new WorkAdapter(MainActivity.this, R.layout.work_layout, listwork, this);
        lv_work.setAdapter(adapter);
    }

    public void setEvent() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this, android.R.style.Theme_Material_Dialog_NoActionBar);
                //dialog.setTitle("Thêm Công Việc");
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

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
                if (calendar.get(Calendar.HOUR_OF_DAY) > 12) {
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

                Typeface typeface = Typeface.createFromAsset(getAssets(),"thuphap.TTF");


                dialog.show();

                btn_luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Random rd = new Random();
                        int id = rd.nextInt(1000);
                        int money = Integer.parseInt(edt_sotien.getText().toString());
                        work w = new work(id, edt_tencv.getText().toString(), Integer.parseInt(edt_sotien.getText().toString()), txt_ngay.getText().toString(), txt_gio.getText().toString());
                        if(money >= 500000){
                            w.setColor(Color.RED);
                        }else if(money>=200000){
                            w.setColor(Color.rgb(245,142,6));
                        }else if(money>=100000){
                            w.setColor(Color.GREEN);
                        }else{
                            w.setColor(Color.BLUE);
                        }


                        listwork.add(w);

                        db.addWork(w);

                        adapter.notifyDataSetChanged();

                        dialog.dismiss();

                    }
                });


            }
        });

    }

    @Override
    public void onItemselected(int pos) {
        POS = pos;
    }
}
