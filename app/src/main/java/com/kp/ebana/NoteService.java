package com.kp.ebana;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class NoteService extends IntentService {

   private RequestQueue mRequestQue;
   private String URL = "https://fcm.googleapis.com/fcm/send";

    public NoteService()
    {
        super("NoteService");
    }

    public void onCreate()
    {
        super.onCreate();
        FirebaseMessaging.getInstance ().subscribeToTopic ("DD");
        mRequestQue = Volley.newRequestQueue (this);
    }

    private void notification()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel ("News", "News", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService (NotificationManager.class);
            manager.createNotificationChannel (channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder (this, "n")
                .setSmallIcon (R.drawable.caution_64)
                .setAutoCancel (true)
                .setContentText ("DODANO NOWE ZDARZENIE");

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from (this);
        managerCompat.notify (999, builder.build ());
    }

    protected void onHandleIntent(Intent intent)
    {

        Toast.makeText (NoteService.this, "CZEMU NIE DZIALA", Toast.LENGTH_LONG).show ();
        JSONObject mObject = new JSONObject ();
        try {
            notification ();
            mObject.put ("to", "/topics/"+"DD");
            JSONObject notificationObj = new JSONObject ();
            notificationObj.put ("title", "eBana - NOWE ZDARZENIE");
            notificationObj.put ("body", "DODANO NOWE ZDARZENIE - SPRAWADZ");
            mObject.put ("notification", notificationObj);


            JsonObjectRequest request = new JsonObjectRequest (Request.Method.POST, URL, mObject, new Response.Listener<JSONObject> () {
                @Override
                public void onResponse(JSONObject response) {
                    //Toast.makeText (NoteService.this, "I AM HERE", Toast.LENGTH_LONG).show ();
                }
            }, new Response.ErrorListener () {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            })
            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> header = new HashMap<> ();
                    header.put("content-type","application/json");
                    header.put("authorization","key=AAAAhCZ5TfI:APA91bEeLgHNV3bTLcJU9imAM556BZXarHfDcgzFUPK-33xSEiQm62QHgxncXD3Je26uIq2Np-h3W1PpScZUn8oI21H8rTykFIQuHGsHx327rGmIT94UZUHOJhqfR6vZw7lBINhTISdi");
                    return header;

                }

            };

            mRequestQue.add (request);

        } catch (JSONException e) {
            e.printStackTrace ();
        }

    }
}

