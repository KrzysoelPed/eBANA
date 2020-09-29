package com.kp.ebana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity  {
    private  RecyclerView mRecyclerView;
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
                Intent Event = new Intent (getApplicationContext (), AddRecord.class);
                startActivityForResult(Event,BUTTON_REQUEST);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    /*
        ButtonOpenAwarie = findViewById (R.id.warning);
        ButtonOpenAwarie.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                openAwarie();
            }
        });

     */
        mRecyclerView = (RecyclerView) findViewById (R.id.recyclerview_event);
        new FirebaseDatabaseHelper().readBooks (new FirebaseDatabaseHelper.DataStatus () {
            @Override
            public void DataIsLoaded(List<Record> awarie, List<String> klucze) {
                new RecyclerViewConfig ().setConfig (mRecyclerView, MainActivity.this, awarie, klucze);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

        //Toolbar toolbar = findViewById (R.id.toolbar);
        //setSupportActionBar (toolbar);
    }





    //public void openAddEvent()
    //{

    //}

}
