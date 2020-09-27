package com.kp.ebana;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddRecord  extends AppCompatActivity {

    EditText name, surname,date_of_value, hour_of_value, type, info;
    Button addButton;
    FirebaseDatabase database;
    DatabaseReference reference;
    Record record;
    long maxid = 0;
    String value_of_event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_event);
        setSpinnerItemSelectedListener();
        name = (EditText) findViewById (R.id.nazwa_zdarzenia);
        surname = (EditText) findViewById (R.id.nazwa_zdarzenia);
        date_of_value = (EditText) findViewById (R.id.Date_event);
        hour_of_value = (EditText) findViewById (R.id.hour_event);
        info = (EditText) findViewById (R.id.info_event);
        addButton = (Button) findViewById (R.id.addbuttton);

        reference = database.getInstance ().getReference ().child ("TEST");
        reference.addValueEventListener(new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists())
                {
                    maxid = snapshot.getChildrenCount();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        addButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                AddRecord ();
            }
        });
    }
    public static final int BUTTON_REQUEST2 = 1;

    public void setSpinnerItemSelectedListener() {

        Spinner spinner = findViewById (R.id.spinner);

        if(spinner!=null)
        {
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    switch (position)
                    {
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


    public void AddRecord ()
    {
        String name_of_value = name.getText ().toString ();
        String surnname_of_value = surname.getText ().toString ();
        String date_of_val = date_of_value.getText ().toString ();
        String hour_of_val = hour_of_value.getText ().toString ();
        String info_of_value = info.getText ().toString ();

        record = new Record (name_of_value, surnname_of_value,date_of_val, hour_of_val, info_of_value);

        reference.child(String.valueOf (maxid+1)).setValue (record);



        setResult(RESULT_OK);
        finish();

    }
}
