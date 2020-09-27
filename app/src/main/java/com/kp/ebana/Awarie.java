package com.kp.ebana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Awarie extends AppCompatActivity {

    private Button backToMain;

    String date_n = new SimpleDateFormat ("dd.MM.yyyy", Locale.getDefault()).format(new Date ());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_awarie);

        //RecyclerView widokAwarii = findViewById (R.id.widokAwarii);
        //widokAwarii.setLayoutManager (new LinearLayoutManager (this));
        TextView actualDate = findViewById (R.id.ActualDate);
        actualDate.setText (date_n);
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
