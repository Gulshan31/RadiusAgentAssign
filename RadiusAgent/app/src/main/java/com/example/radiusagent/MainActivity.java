package com.example.radiusagent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;


import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private static final String JSON_URL ="https://gist.githubusercontent.com/iranjith4/522d5b466560e91b8ebab54743f2d0fc/raw/7b108e4aaac287c6c3fdf93c3343dd1c62d24faf/radius-mobile-intern.json";
    RecyclerView recyclerView;
    DataAdapter adapter ;
    LinearLayoutManager linearLayoutManager;
    TextView name,address,rides,freeRides,creditValue;
    CircleImageView circleImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardView cardview = findViewById(R.id.card_view);
        cardview.setBackgroundResource(R.drawable.profile_layout_cardview_shape);
        name = findViewById(R.id.user_name);
        address = findViewById(R.id.user_address);
        rides = findViewById(R.id.total_rides);
        freeRides= findViewById(R.id.free_rides);
        creditValue = findViewById(R.id.credit_value);
        circleImageView = findViewById(R.id.user_pic);
        setup();
        getUserData();

    }

    public  void setup(){
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView = findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new DataAdapter(new ArrayList<DataModel>());
        //prepareDemoContent();

    }
        public  void prepareDemoContent(){
            ArrayList<DataModel> mData = new ArrayList<>();
            mData.add(new DataModel(
                    "jal",
                    "Ams","18:00","19:00","1 hour","8"));
            mData.add(new DataModel(
                    "jal",
                    "Ams","18:00","19:00","1 hour","8"));
            mData.add(new DataModel(
                 "jal",
                    "Ams","18:00","19:00","1 hour","8"));
            Log.v("Size", String.valueOf(mData.size()));
            adapter.addItems(mData);
            recyclerView.setAdapter(adapter);
        }

        // method for getting data for user profile
      public void getUserData(){

          // Create a request to send the request to the url

          StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                  try {
                      String firstName,lastName,profileImage,city,country,noOfRides,noOfFreeRides,creditValueR;

                      //getting the whole json object from the response
                      JSONObject obj = new JSONObject(response);
                      JSONObject dataObject = obj.getJSONObject("data");
                      // Profile Data
                      JSONObject profileData = dataObject.getJSONObject("profile");
                      firstName = profileData.getString("first_name");
                      lastName = profileData.getString("last_name");
                      city = profileData.getString("city");
                      profileImage = profileData.getString("middle_name");
                      country = profileData.getString("Country");
                      // Ride data and about credits
                      JSONObject rideData = dataObject.getJSONObject("stats");
                      noOfRides = rideData.getString("rides");
                      noOfFreeRides = rideData.getString("free_rides");
                      creditValueR = rideData.getJSONObject("credits").getString("value");
                      String fullName = firstName+" "+lastName;
                      String  fullAddress = city+" "+country;
                      name.setText(fullName);
                      address.setText(fullAddress);
                      rides.setText(noOfRides);
                      freeRides.setText(noOfFreeRides);
                      creditValue.setText("$"+creditValueR);
                      Glide.with(MainActivity.this).load(profileImage).into(circleImageView);
                      // here fetch data for recycle view
                      ArrayList<DataModel> mDataModel = new ArrayList<>();
                      JSONArray recs = dataObject.getJSONArray("trips");

                            for(int i =0;i<recs.length();i++){
                                JSONObject rec = recs.getJSONObject(i);
                                String from = rec.getString("from");
                                String to = rec.getString("to");
                                String fromTime = rec.getString("from_time");
                                   String fromT= returnDate(Long.parseLong(fromTime));
                                String toTime = rec.getString(("to_time"));
                                String toT= returnDate(Long.parseLong(toTime));
                                JSONObject cost = rec.getJSONObject("cost");
                                String fareValue = cost.getString("value");
                                String tripDuration = rec.getString("trip_duration_in_mins");
                                if(Integer.parseInt(tripDuration)>60){
                                 int min =    Integer.parseInt(tripDuration)%60;
                                 int hr = Integer.parseInt(tripDuration)/60;
                                 tripDuration = hr+"h"+" "+min+"min";
                                }
                                else {
                                    tripDuration = tripDuration+"min";
                                }
                                DataModel d = new DataModel(from,to,fromT,toT,tripDuration,fareValue);
                                mDataModel.add(d);
                            }

                      adapter.addItems(mDataModel);
                      recyclerView.setAdapter(adapter);

                  } catch (JSONException e) {
                      e.printStackTrace();
                  }
              }
          },
                  new Response.ErrorListener() {
                      @Override
                      public void onErrorResponse(VolleyError error) {
                          //displaying the error in toast if occurrs
                      }
                  });
          //creating a request queue
          RequestQueue requestQueue = Volley.newRequestQueue(this);

          //adding the string request to request queue
          requestQueue.add(stringRequest);

      }
      // Take time as input in milli seconds and convert it into desirable format and return
      public  String returnDate(long milli){
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(milli);
                int mMonth = calendar.get(Calendar.MONTH);
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);
                int hr = calendar.get(Calendar.HOUR);
                int min = calendar.get(Calendar.MINUTE);
                Log.v("Month", String.valueOf(mMonth));
                String date =convertnumtocharmonths(mMonth);
                if(min<10){
                    date = date+" "+mDay+", "+hr+":0"+min;
                }
               else
                   date = date+" "+mDay+", "+hr+":"+min;
                return  date;
            }

            // convert integer month to monthName

    public static String convertnumtocharmonths(int m){
        String charname=null;
        if(m==0){
            charname="Jan";
        }
        if(m==1){
            charname="Feb";
        }
        if(m==2){
            charname="Mar";
        }
        if(m==3){
            charname="Apr";
        }
        if(m==4){
            charname="May";
        }
        if(m==5){
            charname="Jun";
        }
        if(m==6){
            charname="Jul";
        }
        if(m==7){
            charname="Aug";
        }
        if(m==8){
            charname="Sep";
        }
        if(m==9){
            charname="Oct";
        }
        if(m==10){
            charname="Nov";
        }
        if(m==11){
            charname="Dec";
        }
        return charname;
    }



}
