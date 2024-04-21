package com.example.wild_animal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class View_Detected_animals extends AppCompatActivity implements JsonResponse{

    GridView g1;

    SharedPreferences sh;
    String[] animal,image,camera_name,time,alert_id,lati,longi;

    public static String id1,id2,id3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detected_animals);
        g1=(GridView) findViewById(R.id.grid);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) View_Detected_animals.this;
        String q = "/apiviewdetectedanimals?divisionid="+sh.getString("statid","");
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
                animal = new String[ja1.length()];
                image = new String[ja1.length()];
                camera_name = new String[ja1.length()];
                time = new String[ja1.length()];
                alert_id=new String[ja1.length()];
                lati=new String[ja1.length()];
                longi=new String[ja1.length()];








                for (int i = 0; i < ja1.length(); i++) {
                    animal[i] = ja1.getJSONObject(i).getString("description");
                    image[i] = ja1.getJSONObject(i).getString("image");
                    camera_name[i] = ja1.getJSONObject(i).getString("camera_name");
                    time[i] = ja1.getJSONObject(i).getString("time");
                    alert_id[i] = ja1.getJSONObject(i).getString("alert_id");
                    lati[i] = ja1.getJSONObject(i).getString("latitude");
                    longi[i] = ja1.getJSONObject(i).getString("longitude");





//                        Toast.makeText(getApplicationContext(),val[i], Toast.LENGTH_SHORT).show();
//                        val[i]="Fuel Type : "+fuel_type[i]+"\nVehicle : "+vehicle[i]+"\nReg.No : "+regnum[i]+"\nDriver Name : "+dname[i]+"\nPhone : "+phone[i]+"\nEmail : "+email[i];

                }
//                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, value);
//                g1.setAdapter(ar);

                Custom_detected ar=new Custom_detected(View_Detected_animals.this,image,animal,camera_name,time);
                g1.setAdapter(ar);
                g1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Handle item click here
                        id1 = alert_id[position];
                        id2 = lati[position];
                        id3 = longi[position];




                        AlertDialog.Builder builder = new AlertDialog.Builder(View_Detected_animals.this); // Use the correct context here
                        builder.setTitle("WAD")
                                .setMessage("Are you sure you want to proceed?");
                        builder.setPositiveButton("LOCATION", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Handle the "Yes" button click event
                                String url = "http://maps.google.com/maps?q=loc:"+id2+""+","+id3+""+"&&daddr="+id2+","+id3;
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                intent.setPackage("com.google.android.apps.maps"); // Specify package for Google Maps
                                startActivity(intent);


                            }
                        });

                        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Handle the "No" button click event
                                // Put your code here for the action to be taken on "No"
                                startActivity(new Intent(getApplicationContext(),View_Detected_animals.class));
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });



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