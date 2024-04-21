package com.example.wild_animal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Registartion extends AppCompatActivity implements JsonResponse,AdapterView.OnItemSelectedListener{


    Spinner s1;

    EditText e1,e2,e3,e4,e5,e6,e7;

    String[] forest_station_id,station_name,value;

    Button b1;

    String fname,lname,place,phone,email,username,password;

    public static String forestid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registartion);
        s1=(Spinner) findViewById(R.id.spinner);
        s1=(Spinner) findViewById(R.id.spinner);


        s1.setOnItemSelectedListener(this);
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Registartion.this;
        String q = "/apispinner";
        q = q.replace(" ", "%20");
        JR.execute(q);

        e1=(EditText) findViewById(R.id.fname);
        e2=(EditText) findViewById(R.id.lname);
        e3=(EditText) findViewById(R.id.place);
        e4=(EditText) findViewById(R.id.phone);
        e5=(EditText) findViewById(R.id.email);
        e6=(EditText) findViewById(R.id.username);
        e7=(EditText) findViewById(R.id.password);
        b1=(Button) findViewById(R.id.reg);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname=e1.getText().toString();
                lname=e2.getText().toString();
                place=e3.getText().toString();
                phone=e4.getText().toString();
                email=e5.getText().toString();
                username=e6.getText().toString();
                password=e7.getText().toString();
                if(fname.equalsIgnoreCase("")) {
                    e1.setError("Enter Your Firstname");
                    e1.setFocusable(true);
                } else if (lname.equalsIgnoreCase("")) {
                    e2.setError("Enter Your Lastname");
                    e2.setFocusable(true);


                } else if (place.equalsIgnoreCase("")) {
                    e3.setError("Enter Your Place");
                    e3.setFocusable(true);

                } else if (phone.equalsIgnoreCase("")) {
                    e4.setError("Enter Your Email");
                    e4.setFocusable(true);

                } else if (username.equalsIgnoreCase("")) {
                    e5.setError("Enter Your Username");
                    e5.setFocusable(true);

                } else if (password.equalsIgnoreCase("")) {
                    e6.setError("Enter Your Password");
                    e6.setFocusable(true);

                }else {

                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Registartion.this;
                    String q = "/apiregister?fname=" + fname + "&lname=" + lname + "&place=" + place + "&phone=" + phone + "&email=" + email + "&username=" + username + "&password=" + password + "&forestid=" + forestid;
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                }







            }
        });











    }

    @Override
    public void response(JSONObject jo) throws JSONException {
        Toast.makeText(getApplicationContext(), "forestid : ", Toast.LENGTH_LONG).show();

        try {
            String method = jo.getString("method");
            Log.d("pearl", method);
            if (method.equalsIgnoreCase("spinner")) {
                String status = jo.getString("status");
                Log.d("pearl", method);


                if (status.equalsIgnoreCase("success")) {


                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    forest_station_id = new String[ja1.length()];
                    station_name = new String[ja1.length()];


                    value = new String[ja1.length()];


                    for (int i = 0; i < ja1.length(); i++) {
                        forest_station_id[i] = ja1.getJSONObject(i).getString("forest_station_id");
                        station_name[i] = ja1.getJSONObject(i).getString("station_name");

                        value[i] = "forest_station_id : " + forest_station_id[i] + "\nstation_name : " + station_name[i];

//                        Toast.makeText(getApplicationContext(),val[i], Toast.LENGTH_SHORT).show();
//                        val[i]="Fuel Type : "+fuel_type[i]+"\nVehicle : "+vehicle[i]+"\nReg.No : "+regnum[i]+"\nDriver Name : "+dname[i]+"\nPhone : "+phone[i]+"\nEmail : "+email[i];

                    }
                    ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, station_name);
                    s1.setAdapter(ar);


                }
            }
            if (method.equalsIgnoreCase("register")) {
                String status = jo.getString("status");
                Log.d("pearl", method);

                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "REGISTRATION COMPLETED", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),Login.class));

                }

            }




        } catch (Exception e) {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        forestid=forest_station_id[position];
        Toast.makeText(getApplicationContext(), "forestid : "+forestid, Toast.LENGTH_LONG).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Login.class);
        startActivity(b);
    }
}