package com.kp.ebana;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddRecord  extends AppCompatActivity {

    private RequestQueue mRequestQue;
    private String URL = "https://fcm.googleapis.com/fcm/send";
    EditText name, surname, type, info;
    TextView date_of_value, hour_of_value;
    Button addButton, senNotify;
    FirebaseDatabase database;
    DatabaseReference reference;
    Record record;
    long maxid = 0;
    String value_of_event;
    boolean time_val = false;
    boolean date_validation = false;
    boolean record_is_ready = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_event);
        FirebaseMessaging.getInstance ().subscribeToTopic ("EE");
        Date date = Calendar.getInstance ().getTime ();
        Date time = Calendar.getInstance ().getTime ();
        DateFormat new_time = new SimpleDateFormat ("HH:mm");
        String nowTime = new_time.format (time);
        TextView textViewTime = findViewById (R.id.hour_event);
        textViewTime.setText (nowTime);
        DateFormat new_form = new SimpleDateFormat ("dd.MM.YYYY");
        String nowDate = new_form.format (date);
        TextView textViewDate = findViewById (R.id.Date_event);
        textViewDate.setText (nowDate);
        ImageView arrow = findViewById (R.id.next_arrow);
        Spinner spinner = findViewById (R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource (this, R.array.zdarzenia, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter (adapter);
        setSpinnerItemSelectedListener ();
        name = (EditText) findViewById (R.id.nazwa_zdarzenia);
        surname = (EditText) findViewById (R.id.miejsce_zdarzenia);
        date_of_value = (TextView) findViewById (R.id.Date_event);
        hour_of_value = (TextView) findViewById (R.id.hour_event);
        info = (EditText) findViewById (R.id.info_event);
        addButton = (Button) findViewById (R.id.addbuttton);
        //senNotify = (Button) findViewById (R.id.send_notification);
        mRequestQue = Volley.newRequestQueue (this);

        reference = database.getInstance ().getReference ().child ("TEST");
        reference.addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists ()) {
                    maxid = snapshot.getChildrenCount ();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public static final int BUTTON_REQUEST2 = 1;

    public void setSpinnerItemSelectedListener() {

        Spinner spinner = findViewById (R.id.spinner);

        if (spinner != null) {
            spinner.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    switch (position) {
                        case 0:
                            value_of_event = "AWARIA";
                            break;
                        case 1:
                            value_of_event = "WYPADEK";
                            break;
                        case 2:
                            value_of_event = "USTERKA";
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    value_of_event = "AWARIA";
                }
            });
        }
    }


    public void AddRecord(View view)  {
        String name_of_value = name.getText ().toString ();
        String place_of_value = surname.getText ().toString ();
        String date_of_val = date_of_value.getText ().toString ();
        String hour_of_val = hour_of_value.getText ().toString ();
        if (hour_of_val.matches ("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")) {
            time_val = true;
        }
        if (date_of_val.matches ("([0-3]?[0-9])-([0]?[1-9]|[1]?[0-2])-([2]?[0]?[0-2]?[0-9])")) {
            date_validation = true;
        }
        String info_of_value = info.getText ().toString ();
        String type_of_value = value_of_event;

            record = new Record (name_of_value, place_of_value, date_of_val, hour_of_val, info_of_value, type_of_value);
            reference.child (String.valueOf (maxid + 1)).setValue (record);

        Intent intent = new Intent (this, NoteService.class);
        startService (intent);
        finish ();



        InputMethodManager imm = (InputMethodManager) getSystemService (Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow (view.getWindowToken (), 0);

        setResult (RESULT_OK);
        finish ();

    }


}
