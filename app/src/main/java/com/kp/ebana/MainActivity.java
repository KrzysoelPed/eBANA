package com.kp.ebana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity  {

    private  RecyclerView mRecyclerView;
   // private  RecyclerView mRecyclerV;
    private static final int BUTTON_REQUEST =1 ;
    private Button ButtonOpenAwarie;
    private Button AddEvent;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mReference = mDatabase.getReference("TEST");
    //private MyAdapter adapter;
    //private ArrayList<Record> mListRecord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        FirebaseMessaging.getInstance ().subscribeToTopic ("DD");
        //mRequestQue = Volley.newRequestQueue (this);
        AddEvent = findViewById (R.id.addEvent);
        AddEvent.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent Event = new Intent (getApplicationContext (), AddRecord.class);
                startActivityForResult(Event,BUTTON_REQUEST);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater ();
        inflater.inflate (R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId ())
        {
            case R.id.rejestracja_item:
                Intent sign = new Intent (getApplicationContext (), Sign.class);
                startActivityForResult (sign, 0);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.logowanie_item:
                Intent logowanie = new Intent (getApplicationContext (), Login.class);
                startActivityForResult (logowanie, 0);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.kontakt_item:
                Intent kontakt = new Intent (getApplicationContext (), Contact.class);
                startActivityForResult (kontakt, 0);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
        return super.onOptionsItemSelected (item);
    }
    //public void openAddEvent()
    //{

    //}

}
