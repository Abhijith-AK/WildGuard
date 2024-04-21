
package com.example.wild_animal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeUser extends AppCompatActivity implements JsonResponse{

    TextView t1,t2,t3;

    SharedPreferences sh;


    ImageView img1;

    public static String[] animal,camera,date,id,time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);
        t1 = (TextView) findViewById(R.id.search);
        t2 = (TextView) findViewById(R.id.com);
        t3 = (TextView) findViewById(R.id.contact);
        img1=(ImageView) findViewById(R.id.logout);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });


        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Search_Animals.class));
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Sendcomplaint.class));


            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Contact_details.class));


            }
        });
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) HomeUser.this;
        String q = "/apialert?divisionid=" + sh.getString("divid", "");
        q = q.replace(" ", "%20");
        JR.execute(q);

    }

        private String getCurrentDate() {
            // Get current date and time
            Calendar calendar = Calendar.getInstance();
            Date today = calendar.getTime();

            // Format the date as needed
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(today);

            return formattedDate;
        }

        String currentDate = getCurrentDate();




    @Override
    public void response(JSONObject jo) throws JSONException {

        try {


            String status = jo.getString("status");
            Log.d("method",status);



            if (status.equalsIgnoreCase("success")){
                JSONArray ja1=(JSONArray) jo.getJSONArray("data");


                animal= new String[ja1.length()];
                camera= new String[ja1.length()];
                time= new String[ja1.length()];
                date= new String[ja1.length()];



                for(int i=0;i<ja1.length();i++) {

                    animal[i] = ja1.getJSONObject(i).getString("description");
                    camera[i] = ja1.getJSONObject(i).getString("camera_name");
                    time[i] = ja1.getJSONObject(i).getString("time");
                    date[i] = ja1.getJSONObject(i).getString("date");


                    if (date[i].equals(currentDate)){
                        SharedPreferences.Editor e = sh.edit();
                        e.putString("animals", animal[i]);

                        e.commit();




                        startService(new Intent(getApplicationContext(), SocialDistanceAlert.class));


                    }else {
                        Toast.makeText(this, "no messages", Toast.LENGTH_SHORT).show();
                    }

                }



            }


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Mainhome.class);
        startActivity(b);
    }
}