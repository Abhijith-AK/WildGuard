package com.example.wild_animal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity implements JsonResponse{


    TextView t1;

    EditText e1,e2;

    Button b1;

    String uname,password;

    String loginid,usertype,user_id,div_id,stationid;

    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());



        t1=(TextView) findViewById(R.id.signup);
        e1=(EditText) findViewById(R.id.username);
        e2=(EditText) findViewById(R.id.password);
        b1=(Button) findViewById(R.id.login);





        CheckBox showPasswordCheckbox = findViewById(R.id.show_password_checkbox);
        showPasswordCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    e2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    e2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        Toast.makeText(getApplicationContext(), "WELCOME...!", Toast.LENGTH_LONG).show();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname=e1.getText().toString();
                password=e2.getText().toString();
                if (uname.equalsIgnoreCase("")) {
                    e1.setError("Enter Your Username");
                    e1.setFocusable(true);
                } else if (password.equalsIgnoreCase("")) {
                    e2.setError("Enter Your Password");
                    e2.setFocusable(true);


                }else {

                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Login.this;
                    String q = "/apilogin?uname=" + uname + "&password=" + password;
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                }

            }
        });


        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Registartion.class));
            }
        });

    }

    @Override
    public void response(JSONObject jo) throws JSONException {
        try {
            String status = jo.getString("status");
            Log.d("pearl", status);

            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                loginid = ja1.getJSONObject(0).getString("login_id");
                usertype = ja1.getJSONObject(0).getString("usertype");
                user_id = ja1.getJSONObject(0).getString("user_id");
                div_id = ja1.getJSONObject(0).getString("forest_division_id");
                stationid = ja1.getJSONObject(0).getString("forest_station_id");




                SharedPreferences.Editor e = sh.edit();
                e.putString("loginid", loginid);
                e.putString("userid", user_id);
                e.putString("divid", div_id);
                e.putString("statid", stationid);


                e.commit();


                if (usertype.equals("user")) {

                    Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),Mainhome.class));
                }





            } else if (status.equalsIgnoreCase("failed")) {
                Toast.makeText(getApplicationContext(), "invalid username & Password", Toast.LENGTH_LONG).show();


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
        Intent b=new Intent(getApplicationContext(),IpSetting.class);
        startActivity(b);
    }
}