package com.kp.ebana;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import static androidx.core.app.ActivityCompat.startActivityForResult;
import static androidx.core.content.ContextCompat.startActivity;

public class RecyclerViewConfig {


    private Context mContext;
    private EventAdapter mEventAdapter;
    private RecyclerViewClickListener listener;


    public void setConfig(RecyclerView recyclerView, Context context, List<Record> awarie, List<String> keys)
    {
        mContext = context;
        mEventAdapter = new EventAdapter (awarie, keys);
        recyclerView.setLayoutManager (new LinearLayoutManager (context));
        recyclerView.setAdapter (mEventAdapter);

    }

    class EventItemView extends RecyclerView.ViewHolder
            {
                private TextView mName;
                private TextView mPlace;
                private TextView mData;
                private TextView mHour;

                private  String key;

                public EventItemView (ViewGroup parent)
                {
                    super(LayoutInflater.from (mContext).inflate(R.layout.event_list_item, parent, false));
                    mName = (TextView) itemView.findViewById (R.id.name_of_event);
                    mPlace = (TextView) itemView.findViewById (R.id.place_of_event);
                    mData = (TextView) itemView.findViewById (R.id.date_of_event);
                    mHour = (TextView) itemView.findViewById (R.id.hour_of_value);

                }

                public void bind (Record record, String key )
                {
                    mName.setText (record.getNazwa_zdarzenia ());
                    mPlace.setText ("Miejsce zdarzenia: "+record.getMiejsce_zdarzenia ());
                    mData.setText ("Data: "+record.getData_zdarzenia ());
                    mHour.setText ("Godzina: "+record.getGodzina_zdarzenia ());
                    this.key= key;
                }
            }

            class EventAdapter extends  RecyclerView.Adapter<EventItemView>
            {
                private  List<Record> mRecordList;
                private  List<String> mKeys;


                public EventAdapter(List<Record> mRecordList, List<String> mKeys) {
                    this.mRecordList = mRecordList;
                    this.mKeys = mKeys;
                }

                @Override
                public EventItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    return new EventItemView (parent);
                }

                @Override
                public void onBindViewHolder(@NonNull EventItemView holder, int position) {
                    holder.bind (mRecordList.get(position), mKeys.get(position));
                }

                @Override
                public int getItemCount() {
                    return  mRecordList.size ();
                }
            }
            public interface RecyclerViewClickListener
            {
                void onClick(View v, int position);
            }


}
