package com.example.wild_animal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

public class Mainhome extends AppCompatActivity {



    ImageView img;
    LinearLayout l2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainhome);

        ImageView im1 = findViewById(R.id.img);

// Load GIF from resources using Glide
        int gifResource = R.drawable.lion; // Replace with the resource ID of your GIF
        Glide.with(this).asGif().load(gifResource).into(im1);

// Set GIF from resources directly (without Glide)
// Comment out or remove the Glide code above if you only want to use setImageResource
// int gifResource = R.drawable.bellrun2; // Already declared above
        im1.setImageResource(gifResource);


        l2=(LinearLayout) findViewById(R.id.l1);

        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeUser.class));
            }
        });












    }
}