package com.kp.ebana;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private List<Record> awarie = new ArrayList<> ();

    public interface  DataStatus
    {
        void DataIsLoaded (List<Record> awarie, List<String> klucze);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper(){
        mDatabase = FirebaseDatabase.getInstance ();
        mReference = mDatabase.getReference ("TEST");
    }

    public void readBooks(final DataStatus dataStatus)
    {
        mReference.addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                awarie.clear ();
                List<String> klucze = new ArrayList<> ();
                for(DataSnapshot keyNode: dataSnapshot.getChildren ())
                {
                   klucze.add(keyNode.getKey ());
                   Record record = keyNode.getValue(Record.class);
                   awarie.add(record);
                }
                dataStatus.DataIsLoaded (awarie, klucze);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
