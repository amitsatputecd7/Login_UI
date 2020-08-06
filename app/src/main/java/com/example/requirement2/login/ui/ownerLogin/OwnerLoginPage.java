package com.example.requirement2.login.ui.ownerLogin;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.requirement2.OwnerProfilePage;
import com.example.requirement2.R;
import com.example.requirement2.login.ui.customerLogin.CustomerLoginPage;
import com.squareup.picasso.Picasso;

public class OwnerLoginPage extends AppCompatActivity {

    ImageView imageView;
    TextView textView,tv_customerLogin;
    Button button_signin;
    int count = 0;
String morning="https://firebasestorage.googleapis.com/v0/b/firefirst-2b711.appspot.com/o/zeastro%2Fgood_morning_img.png?alt=media&token=7a4e406d-c009-48b1-80a2-3061a64e9b08",
    night="https://firebasestorage.googleapis.com/v0/b/firefirst-2b711.appspot.com/o/zeastro%2Fgood_night_img.png?alt=media&token=860b078d-0ad3-45e5-a5a1-79c65a561db0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_ui);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        button_signin=(Button)findViewById(R.id.button_signin);
        tv_customerLogin=(TextView)findViewById(R.id.tv_customerLogin);
        Picasso.with(OwnerLoginPage.this).load(morning).into(imageView);



        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(OwnerLoginPage.this, OwnerProfilePage.class));
            }
        });



        tv_customerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(OwnerLoginPage.this,CustomerLoginPage.class));
            }
        });

        imageView.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeTop() {
            }

            public void onSwipeRight() {
                if (count == 0) {
                    Picasso.with(OwnerLoginPage.this).load(night).into(imageView);
//                    imageView.setImageResource(R.drawable.good_night_img);

//                    textView.setText("Night");
                    count = 1;
                } else {
                    Picasso.with(OwnerLoginPage.this).load(morning).into(imageView);
                   // imageView.setImageResource(R.drawable.good_morning_img);
//                    textView.setText("Morning");
                    count = 0;
                }
            }

            public void onSwipeLeft() {
                if (count == 0) {
                    Picasso.with(OwnerLoginPage.this).load(night).into(imageView);
//                    imageView.setImageResource(R.drawable.good_night_img);
//                    textView.setText("Night");
                    count = 1;
                } else {
                    Picasso.with(OwnerLoginPage.this).load(morning).into(imageView);
//                    imageView.setImageResource(R.drawable.good_morning_img);
//                    textView.setText("Morning");
                    count = 0;
                }
            }

            public void onSwipeBottom() {
            }

        });
    }
}
