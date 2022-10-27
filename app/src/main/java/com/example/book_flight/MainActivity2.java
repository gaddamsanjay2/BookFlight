package com.example.book_flight;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialCalendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {
    LinearLayout l1,l2;
    TextView oneway,roundtrip;
    View v1,v2;

    CalendarView calendarView;
    private MaterialCalendar materialCalendar;
    private ArrayList<Date> dates;
    private Date date = new Date();
    Calendar cal = Calendar.getInstance();

    TextView value;
    int Count=0;

    TextView valueRT;
    int CountRT=0;


    Button seeavailableflights_oneway;
    Button seeavailableflights_roundtrip;

    ArrayList<ModelClass> modelClassArrayList;

    EditText from,to;
    EditText fromRT,toRt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ArrayList<String>list = new ArrayList<>();
        list.add("Economy");
        list.add("SuperClass");
        list.add("Genral");

        seeavailableflights_oneway=findViewById(R.id.button_SAF);
        seeavailableflights_roundtrip=findViewById(R.id.buttonRT_SAF);

        value=findViewById(R.id.valueid);
        valueRT=findViewById(R.id.valueRTid);


        Spinner spinner=new Spinner(this);

        Spinner spin=findViewById(R.id.spinnerid);
        Spinner spinRT=findViewById(R.id.spinnerRTid);

        ArrayAdapter adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list);
        spin.setAdapter(adapter);
        spinRT.setAdapter(adapter);

       from=findViewById(R.id.from_edt);
        to=findViewById(R.id.to_edt);

        fromRT=findViewById(R.id.fromRT_edt);
        toRt=findViewById(R.id.toRT_edt);

        modelClassArrayList=new ArrayList<>();
        CalendarView calendarr = findViewById(R.id.calendarid);
        calendarr.setSelected(true);
        calendarr.setDate(calendarr.getDate(),true,true);
        calendarr.setMinDate(System.currentTimeMillis()-1000);




//        calendarr.setMinDate(System.currentTimeMillis() - 1000);
        //calendar.add(Calendar.DATE, -1);
        //calendar.setTimeInMillis(System.currentTimeMillis() - 1000);

        calendarr.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView,
                                            int dayOfMonth, int month, int year) {

                Toast.makeText(
                        getApplicationContext(),
                        dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();

            }
        });


//  Round Trip

        CalendarView calendarViewRT=findViewById(R.id.calendarRTid);
        calendarViewRT.setMinDate(System.currentTimeMillis() -1000);
        calendarr.setSelected(true);
        calendarr.setDate(calendarr.getDate(),false,true);

//        calendarViewRT.setMaxDate(System.currentTimeMillis() -1000);

//        initializeCalendar();

        calendarViewRT.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {


//                Toast.makeText(
//                        getApplicationContext(),
//                        dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();

//                Calendar calendar = Calendar.getInstance();
//                calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
//                long endofYear = calendar.getTimeInMillis();
//                calendar = Calendar.getInstance();
//                calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
//
//                long presentDay = calendar.getTimeInMillis();
//                calendarViewRT.setMaxDate(endofYear);
//                calendarViewRT.setMinDate(presentDay);
//
//
//                String minDateString = new SimpleDateFormat("MM/dd/yyyy").format(new Date(calendarViewRT.getMinDate()));
//                String maxDateString = new SimpleDateFormat("MM/dd/yyyy").format(new Date(calendarViewRT.getMaxDate()));
//
//                Toast.makeText(getApplicationContext(), "MMDDYYYY Min date - " + minDateString + " Max Date is " + maxDateString, Toast.LENGTH_LONG).show();

            }

        });

        l1=findViewById(R.id.oneway);
        l2=findViewById(R.id.roundtrip);
        oneway=findViewById(R.id.tv_oneway);
        roundtrip=findViewById(R.id.tv_roundtrip);
        v1=findViewById(R.id.view1);
        v2=findViewById(R.id.view2);

        oneway.setTextColor(getResources().getColor(R.color.skyblue));
        v1.setBackgroundColor(getResources().getColor(R.color.skyblue));
        v1.setVisibility(View.VISIBLE);
        l1.setVisibility(View.VISIBLE);
        l2.setVisibility(View.GONE);


        oneway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                oneway.setTextColor(getResources().getColor(R.color.skyblue));
                roundtrip.setTextColor(getResources().getColor(R.color.gray));
                v1.setBackgroundColor(getResources().getColor(R.color.skyblue));
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.GONE);
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.GONE);
            }
        });

        roundtrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                roundtrip.setTextColor(getResources().getColor(R.color.skyblue));
                oneway.setTextColor(getResources().getColor(R.color.gray));
                v2.setBackgroundColor(getResources().getColor(R.color.skyblue));
                l2.setVisibility(View.VISIBLE);
                l1.setVisibility(View.GONE);
                v2.setVisibility(View.VISIBLE);
                v1.setVisibility(View.GONE);


            }
        });

        seeavailableflights_oneway.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (from.length()==0){
                    from.setError("This field required");
                }else if (to.length()==0){
                    to.setError("This field required");
                }else if (from.length()!=0||to.length()!=0){



                    String fromStr=from.getText().toString();
                    String toStr=to.getText().toString();

                    Intent intent=new Intent(MainActivity2.this,MainActivity3.class);
                    intent.putExtra("ff",fromStr);
                    intent.putExtra("tt",toStr);

                    processinsert(from.getText().toString(),to.getText().toString());
                    startActivity(intent);
                }


            }
        });
        seeavailableflights_roundtrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fromRT.length()==0){
                    fromRT.setError("This field required");
                }else if (toRt.length()==0){
                    toRt.setError("This field required");
                }else if (fromRT.length()!=0||toRt.length()!=0){


                    String fromStr=fromRT.getText().toString();
                    String toStr=toRt.getText().toString();

                    Intent intent=new Intent(MainActivity2.this,MainActivity3.class);
                    intent.putExtra("ff",fromStr);
                    intent.putExtra("tt",toStr);

                    processinsert(fromRT.getText().toString(),toRt.getText().toString());

                    startActivity(intent);
                }

            }
        });

    }

    //ONE WAY
    public void increment(View view){
        Count++;
        value.setText(""+Count);
    }
    public void decrement(View view){
        if (Count<=0)Count=0;
        else Count--;
        value.setText(""+Count);
    }

    //ROUND TRIP

    public void incrementRT(View view){
        CountRT++;
        valueRT.setText(""+CountRT);
    }
    public void decrementRT(View view){
        if (CountRT<=0)CountRT=0;
        else CountRT--;
        valueRT.setText(""+CountRT);
    }


    private void processinsert(String fromStr, String toStr){

        String result=new HelperClass(this).addrecord(fromStr,toStr);
            from.setText("");
            to.setText("");


    Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();

    }


}