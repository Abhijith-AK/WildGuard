package com.example.wild_animal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Sendcomplaint extends AppCompatActivity implements JsonResponse{

    EditText e1,e2;
    Button b1;

    String com,title;

    SharedPreferences sh;
    LinearLayout t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendcomplaint);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        e1=(EditText) findViewById(R.id.complaint);
        e2=(EditText) findViewById(R.id.title);
        b1=(Button) findViewById(R.id.send);
        t1=(LinearLayout) findViewById(R.id.view);

        ImageView im1 = findViewById(R.id.img);

// Load GIF from resources using Glide
        int gifResource = R.drawable.lion; // Replace with the resource ID of your GIF
        Glide.with(this).asGif().load(gifResource).into(im1);

// Set GIF from resources directly (without Glide)
// Comment out or remove the Glide code above if you only want to use setImageResource
// int gifResource = R.drawable.bellrun2; // Already declared above
        im1.setImageResource(gifResource);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com=e1.getText().toString();
                title=e2.getText().toString();

                if (title.equalsIgnoreCase("")) {
                    e2.setError("Enter An Title");
                    e2.setFocusable(true);
                } else if (com.equalsIgnoreCase("")) {
                    e1.setError("Enter Your Complaint");
                    e1.setFocusable(true);
                }
                else {

                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Sendcomplaint.this;
                    String q = "/apicom?com=" + com + "&title=" + title + "&userid=" + sh.getString("userid", "");
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                }
            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),View_complaints.class));
            }
        });
    }

    @Override
    public void response(JSONObject jo) throws JSONException {
        try {
            String method = jo.getString("method");
            Log.d("pearl", method);
            if (method.equalsIgnoreCase("com")) {
                String status = jo.getString("status");
                Log.d("pearl", method);


                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), " Complaint send successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),Sendcomplaint.class));

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
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),HomeUser.class);
        startActivity(b);
    }
}