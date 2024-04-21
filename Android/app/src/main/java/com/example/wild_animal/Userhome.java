package com.example.wild_animal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.time.LocalDateTime;

public class Userhome extends AppCompatActivity {

    TextView t1;
    Button b1;
    private boolean isAnimationRunning = false;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhome);
        // Assuming you have an ImageView with ID R.id.imageView3 in your XML layout
//
//        ImageView im1 = findViewById(R.id.imageView);
//
//// Load GIF from resources using Glide
//        int gifResource = R.drawable.lion; // Replace with the resource ID of your GIF
//        Glide.with(this).asGif().load(gifResource).into(im1);
//
//// Set GIF from resources directly (without Glide)
//// Comment out or remove the Glide code above if you only want to use setImageResource
//// int gifResource = R.drawable.bellrun2; // Already declared above
//        im1.setImageResource(gifResource);

        b1 = (Button) findViewById(R.id.sendcom);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateButtonToCenter(b1);  // Pass the actual Button reference

            }

            private void animateButtonToCenter(Button b1) {


                float centerX = ((View) b1.getParent()).getWidth() / 2.0f;
                float buttonCenterX = b1.getX() + b1.getWidth() / 2.0f;

                float translationX = centerX - buttonCenterX;

                b1.animate()
                        .translationXBy(translationX)
                        .setDuration(500)  // You can adjust the duration as needed
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                isAnimationRunning = false;
                            }
                        })
                        .start();
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),Sendcomplaint.class));
                    }
                });
            }


        });
         Button b2=(Button) findViewById(R.id.searchanimals);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateButtonToCenter(b2);  // Pass the actual Button reference

            }

            private void animateButtonToCenter(Button b2) {


                float centerX = ((View) b2.getParent()).getWidth() / 2.0f;
                float buttonCenterX = b2.getX() + b2.getWidth() / 2.0f;

                float translationX = centerX - buttonCenterX;

                b2.animate()
                        .translationXBy(translationX)
                        .setDuration(500)  // You can adjust the duration as needed
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                isAnimationRunning = false;
                            }
                        })
                        .start();
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),Registartion.class));
                    }
                });
            }


        });

        Button b3=(Button) findViewById(R.id.viewcontact);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateButtonToCenter(b3);  // Pass the actual Button reference

            }

            private void animateButtonToCenter(Button b3) {


                float centerX = ((View) b3.getParent()).getWidth() / 2.0f;
                float buttonCenterX = b3.getX() + b3.getWidth() / 2.0f;

                float translationX = centerX - buttonCenterX;

                b3.animate()
                        .translationXBy(translationX)
                        .setDuration(500)  // You can adjust the duration as needed
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                isAnimationRunning = false;
                            }
                        })
                        .start();
                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),Contact_details.class));
                    }
                });
            }


        });
        Button b4=(Button) findViewById(R.id.notification);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateButtonToCenter(b4);  // Pass the actual Button reference

            }

            private void animateButtonToCenter(Button b4) {


                float centerX = ((View) b4.getParent()).getWidth() / 2.0f;
                float buttonCenterX = b4.getX() + b4.getWidth() / 2.0f;

                float translationX = centerX - buttonCenterX;

                b4.animate()
                        .translationXBy(translationX)
                        .setDuration(500)  // You can adjust the duration as needed
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                isAnimationRunning = false;
                            }
                        })
                        .start();
                b4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),Registartion.class));
                    }
                });
            }


        });
        Button b5=(Button) findViewById(R.id.logout);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateButtonToCenter(b5);  // Pass the actual Button reference

            }

            private void animateButtonToCenter(Button b5) {


                float centerX = ((View) b5.getParent()).getWidth() / 2.0f;
                float buttonCenterX = b5.getX() + b5.getWidth() / 2.0f;

                float translationX = centerX - buttonCenterX;

                b5.animate()
                        .translationXBy(translationX)
                        .setDuration(500)  // You can adjust the duration as needed
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                isAnimationRunning = false;
                            }
                        })
                        .start();
                b5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),Registartion.class));
                    }
                });
            }


        });

    }
}