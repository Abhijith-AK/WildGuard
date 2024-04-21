package com.example.wild_animal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class View_complaints extends AppCompatActivity implements JsonResponse{

    ListView l1;

    SharedPreferences sh;

    String[] title,description,date,value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complaints);
        l1=(ListView) findViewById(R.id.list);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());



        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) View_complaints.this;
        String q = "/apiviewcom?userid="+sh.getString("userid","");
        q = q.replace(" ", "%20");
        JR.execute(q);
    }

    @Override
    public void response(JSONObject jo) throws JSONException {
        try {
            String status = jo.getString("status");
            Log.d("pearl", status);
            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                title = new String[ja1.length()];
                description = new String[ja1.length()];
                date = new String[ja1.length()];


                value = new String[ja1.length()];


                for (int i = 0; i < ja1.length(); i++) {
                    title[i] = ja1.getJSONObject(i).getString("title");
                    description[i] = ja1.getJSONObject(i).getString("description");
                    date[i] = ja1.getJSONObject(i).getString("date");


                    value[i] = "title : " + title[i] + "\ndescription : " + description[i] + "\ndate :" + date[i];

//                        Toast.makeText(getApplicationContext(),val[i], Toast.LENGTH_SHORT).show();
//                        val[i]="Fuel Type : "+fuel_type[i]+"\nVehicle : "+vehicle[i]+"\nReg.No : "+regnum[i]+"\nDriver Name : "+dname[i]+"\nPhone : "+phone[i]+"\nEmail : "+email[i];

                }
//                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, value);
//                l1.setAdapter(ar);
                Custom_Viewcomplaints ar=new Custom_Viewcomplaints(View_complaints.this,title,description,date);
                l1.setAdapter(ar);




            }
        }
            catch (Exception e) {
                // TODO: handle exception

                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }


            }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}