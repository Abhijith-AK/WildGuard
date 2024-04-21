package com.example.wild_animal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Search_Animals extends AppCompatActivity implements JsonResponse{

    GridView g1;
    SharedPreferences sh;

    String[] name,image;
    EditText e1;
    String anmialsearch;

    TextView t1;

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_animals);
        g1 = (GridView) findViewById(R.id.grid);
        e1 = (EditText) findViewById(R.id.animal);
        t1 = (TextView) findViewById(R.id.textView12);
        img=(ImageView) findViewById(R.id.search);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), View_Detected_animals.class));
            }
        });


        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Search_Animals.this;
        String q = "/viewanimals?division_id=" + sh.getString("divid", "");
        q = q.replace(" ", "%20");
        JR.execute(q);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anmialsearch=e1.getText().toString();
                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) Search_Animals.this;
                String q = "/search_animal?search="+anmialsearch+"&divisionid=" + sh.getString("divid", "");
                q = q.replace(" ", "%20");
                JR.execute(q);


            }
        });

        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) Search_Animals.this;
                String q = "/viewanimals?division_id=" + sh.getString("divid", "");
                q = q.replace(" ", "%20");
                JR.execute(q);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                anmialsearch=e1.getText().toString();

                JsonReq JR=new JsonReq();
                JR.json_response=(JsonResponse) Search_Animals.this;
                String q = "/Search_product?search="+anmialsearch + "&divisionid=" + sh.getString("divid", "");
                q=q.replace(" ","%20");
                JR.execute(q);
            }
        });

        }


    @Override
    public void response(JSONObject jo) throws JSONException {

        try {
            String method = jo.getString("method");
            Log.d("pearl", method);
            if (method.equalsIgnoreCase("viewanimal")) {
                String status = jo.getString("status");
                Log.d("pearl", method);


                if (status.equalsIgnoreCase("success")) {


                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    name = new String[ja1.length()];
                    image = new String[ja1.length()];


                    for (int i = 0; i < ja1.length(); i++) {
                        name[i] = ja1.getJSONObject(i).getString("animal_name");
                        image[i] = ja1.getJSONObject(i).getString("animal_image");


                        Toast.makeText(getApplicationContext(), "" + name[i], Toast.LENGTH_SHORT).show();
//                        val[i]="Fuel Type : "+fuel_type[i]+"\nVehicle : "+vehicle[i]+"\nReg.No : "+regnum[i]+"\nDriver Name : "+dname[i]+"\nPhone : "+phone[i]+"\nEmail : "+email[i];

                    }
//                    ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, station_name);
//                    s1.setAdapter(ar);
                    Customviewanimals ar = new Customviewanimals(Search_Animals.this, image, name);
                    g1.setAdapter(ar);


                }
            }
//                else if(method.equalsIgnoreCase("view_animal")) {
//                    String status = jo.getString("status");
//                    Log.d("pearl", method);
//
//
//                    if (status.equalsIgnoreCase("success")) {
//
//
//                        JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
//                        name = new String[ja1.length()];
//                        image = new String[ja1.length()];
//
//
//                        for (int i = 0; i < ja1.length(); i++) {
//                            name[i] = ja1.getJSONObject(i).getString("animal_name");
//                            image[i] = ja1.getJSONObject(i).getString("animal_image");
//
//
//                            Toast.makeText(getApplicationContext(), "" + name[i], Toast.LENGTH_SHORT).show();
////                        val[i]="Fuel Type : "+fuel_type[i]+"\nVehicle : "+vehicle[i]+"\nReg.No : "+regnum[i]+"\nDriver Name : "+dname[i]+"\nPhone : "+phone[i]+"\nEmail : "+email[i];
//
//                        }
////                    ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, station_name);
////                    s1.setAdapter(ar);
//                        Customviewanimals ar = new Customviewanimals(Search_Animals.this, image, name);
//                        g1.setAdapter(ar);
//
//
//                    }
//
//            }

        }

                 catch (Exception e) {
                    // TODO: handle exception

                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),HomeUser.class);
        startActivity(b);
    }
}