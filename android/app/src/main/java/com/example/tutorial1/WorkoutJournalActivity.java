package com.example.tutorial1;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WorkoutJournalActivity extends AppCompatActivity  {

    Chronometer chrono;
    Button btnStart, btnEnd, btnEND;
    RadioButton rdoCal,rdoTime ;
    CalendarView calendar;
    TimePicker time;
    TextView tvYear,tvMonth,tvDay,tvHour,tvMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        setTitle("출석 체크");
        //  화면 전환 - 인텐트 날림
        // 1. 넘어갈 화면 - menuActivity
        // 2. AndroidManifest.xml 에 Activity 등록
        // 3. Intent 객체 만들어서 startActivity함
        RadioButton button = (RadioButton)findViewById(R.id.subActivity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MemoActivity.class);
                startActivity(intent);
            }
        });



        chrono = (Chronometer)findViewById(R.id.chronometer1);
        btnStart = (Button)findViewById(R.id.btnStart);
        btnEnd = (Button)findViewById(R.id.btnEnd);
        btnEND = (Button)findViewById(R.id.btnEND);

        rdoCal = (RadioButton)findViewById(R.id.rdoCal);
        rdoTime = (RadioButton)findViewById(R.id.rdoTime);
        calendar = (CalendarView)findViewById(R.id.calendarView1);
        time = (TimePicker)findViewById(R.id.timePicker1);
        tvYear = (TextView)findViewById(R.id.tvYear);
        tvMonth = (TextView)findViewById(R.id.tvMonth);
        tvDay = (TextView)findViewById(R.id.tvDay);
        tvHour = (TextView)findViewById(R.id.tvHour);
        tvMinute = (TextView)findViewById(R.id.tvMinute);

        calendar.setVisibility(View.INVISIBLE);
        time.setVisibility(View.INVISIBLE);

        rdoCal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                calendar.setVisibility(View.VISIBLE);
                time.setVisibility(View.INVISIBLE);
            }
        });

        rdoTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                calendar.setVisibility(View.INVISIBLE);
                time.setVisibility(View.VISIBLE);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();
                chrono.setTextColor(Color.RED);

            }
        });
        //출석체크
        btnEnd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chrono.setTextColor(Color.RED);
                // chrono.start();


                java.util.Calendar curDate = java.util.Calendar.getInstance();
                curDate.setTimeInMillis(calendar.getDate());
                tvYear.setText(Integer.toString(curDate.get(Calendar.YEAR)));
                tvMonth.setText(Integer.toString(curDate.get(Calendar.MONTH)));
                tvDay.setText(Integer.toString(curDate.get(Calendar.DATE)));

                tvHour.setText(Integer.toString(time.getCurrentHour()));
                tvMinute.setText(Integer.toString(time.getCurrentMinute()));
            }
        });
        // 운동 종료
        btnEND.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chrono.stop();
                chrono.setTextColor(Color.BLUE);
                //         chrono.setBase(SystemClock.elapsedRealtime());
                showElapsedTime();
                long elapseMillis = SystemClock.elapsedRealtime()
                        -chrono.getBase();
                //  Toast.makeText(MainActivity.this,"총 운동 시간: "+elapseMillis,
                //        Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showElapsedTime() {
        long elapsedMillis = SystemClock.elapsedRealtime() - chrono.getBase();
        Toast.makeText(WorkoutJournalActivity.this, "Elapsed milliseconds: " + elapsedMillis,
                Toast.LENGTH_SHORT).show();
    }
}
