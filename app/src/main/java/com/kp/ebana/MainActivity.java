package com.kp.ebana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private static final int BUTTON_REQUEST =1 ;
    private Button ButtonOpenAwarie;
    private Button AddEvent;

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mReference = mDatabase.getReference("TEST");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        AddEvent = findViewById (R.id.addEvent);
        AddEvent.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                openAddEvent();
            }
        });

        ButtonOpenAwarie = findViewById (R.id.warning);
        ButtonOpenAwarie.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                openAwarie();
            }
        });


        //Toolbar toolbar = findViewById (R.id.toolbar);
        //setSupportActionBar (toolbar);
    }

    public void openAwarie()
    {
        Intent intent = new Intent(this, Awarie.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void openAddEvent()
    {
        Intent Event = new Intent (this, AddRecord.class);
        startActivityForResult(Event,BUTTON_REQUEST);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}
