package com.example.wild_animal;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Custom_detected extends ArrayAdapter<String> {

    private Activity context;       //for to get current activity context
    SharedPreferences sh;
    private String[] file,animal,camera_name,time;


    public Custom_detected(Activity context, String[] file, String[] animal,String[] camera_name,String[] time) {
        super(context, R.layout.activity_custom_detected, file);
        this.context = context;
        this.file = file;
        this.animal=animal;
        this.camera_name=camera_name;
        this.time=time;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //override getView() method

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_custom_detected, null, true);
        //cust_list_view is xml file of layout created in step no.2

        ImageView im = (ImageView) listViewItem.findViewById(R.id.imageView4);
        TextView t1=(TextView)listViewItem.findViewById(R.id.textView9);
        TextView t2=(TextView)listViewItem.findViewById(R.id.textView10);
        TextView t3=(TextView)listViewItem.findViewById(R.id.textView11);







        t1.setText("Animal :"+ animal[position]);
        t2.setText("Camera :"+ camera_name[position]);
        t3.setText("Time : "+ time[position]);





        sh= PreferenceManager.getDefaultSharedPreferences(getContext());

        String pth = "http://"+sh.getString("ip", "")+"/"+file[position];
        pth = pth.replace("~", "");
//	       Toast.makeText(context, pth, Toast.LENGTH_LONG).show();

        Log.d("-------------", pth);
        Picasso.with(context)
                .load(pth)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).into(im);

        return  listViewItem;
    }





    private TextView setText(String string) {
        // TODO Auto-generated method stub
        return null;
    }
}