package com.kp.ebana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class EventClass extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button openCal;
    private TextView date;
    private Button AddElement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_event);

        Spinner spinner = findViewById (R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource (this, R.array.zdarzenia, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter (adapter);
        spinner.setOnItemSelectedListener(this);

        date = findViewById (R.id.Date_event);


        Intent newIntent = getIntent ();
        String data = getIntent ().getStringExtra ("date");
        date.setText (data);
        openCal.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent open = new Intent (EventClass.this, CalendarActivity.class);
                startActivity (open);
            }
        });



}

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        notify ();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
