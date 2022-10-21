package com.example.book_flight;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
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

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {
    LinearLayout l1,l2;
    TextView oneway,roundtrip;
    View v1,v2;

    Calendar calendar;

    TextView value;
    int Count=0;

    TextView valueRT;
    int CountRT=0;


    Button seeavailableflights_oneway;
    Button seeavailableflights_roundtrip;

    ArrayList<ModelClass> modelClassArrayList;

    TextView from,to;
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

        modelClassArrayList=new ArrayList<>();
        CalendarView calendarr = findViewById(R.id.calendarid);
        //Get yesterday's date
        calendarr.setMinDate(System.currentTimeMillis() - 1000);
        //calendar.add(Calendar.DATE, -1);
        //calendar.setTimeInMillis(System.currentTimeMillis() - 1000);

//  Round Trip

        CalendarView calendarViewRT=findViewById(R.id.calendarRTid);
//        calendarViewRT.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//
//            @Override
//            public void onSelectedDayChange(CalendarView view, int year,
//                                            int month, int dayOfMonth) {
//
//                Toast.makeText(
//                        getApplicationContext(),
//                        dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
//
//            }
//
//        });

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

                    processinsert(from.getText().toString(),to.getText().toString());


                    Intent intent=new Intent(MainActivity2.this,MainActivity3.class);

                    startActivity(intent);
                }


            }
        });
        seeavailableflights_roundtrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





//                processinsert(from.getText().toString(),to.getText().toString());

                Intent intent=new Intent(MainActivity2.this,MainActivity3.class);
//                intent.putExtra("key","value");
                startActivity(intent);
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