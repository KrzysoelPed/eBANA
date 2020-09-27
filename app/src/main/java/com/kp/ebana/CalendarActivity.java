package com.kp.ebana;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity
{
    private static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
       super.onCreate (savedInstanceState);
       setContentView (R.layout.calendar_layout);
       mCalendarView = findViewById (R.id.calendarView);
       mCalendarView.setOnDateChangeListener (new CalendarView.OnDateChangeListener () {
           @Override
           public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
               String date = dayOfMonth +"."+(month+1)+"."+year;
                //Log.d(TAG, "onSelectedDayChange: dddd/MM/yyyy:"+date);

                Intent nowyIntent = new Intent (CalendarActivity.this, EventClass.class);
                nowyIntent.putExtra("date",date);
                startActivity (nowyIntent);
           }
       });
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
