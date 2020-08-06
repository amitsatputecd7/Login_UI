package com.example.requirement2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.requirement2.login.ui.ownerLogin.OwnerLoginPage;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OwnerProfilePage extends AppCompatActivity {

    private ViewFlipper[] viewFlippers=new ViewFlipper[8];
    private ImageView img_restaurent,background;
    private Button back,stats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_profile_page);
          viewFlippers[0] = (ViewFlipper) findViewById(R.id.flipper11);
          viewFlippers[1] = (ViewFlipper) findViewById(R.id.flipper12);
          viewFlippers[2] = (ViewFlipper) findViewById(R.id.flipper21);
          viewFlippers[3] = (ViewFlipper) findViewById(R.id.flipper22);
          viewFlippers[4] = (ViewFlipper) findViewById(R.id.flipper31);
          viewFlippers[5] = (ViewFlipper) findViewById(R.id.flipper32);
          viewFlippers[6] = (ViewFlipper) findViewById(R.id.flipper41);
          viewFlippers[7] = (ViewFlipper) findViewById(R.id.flipper42);
          img_restaurent=(ImageView)findViewById(R.id.iv_restImage);
          background=(ImageView)findViewById(R.id.iv_background);
          back=(Button)findViewById(R.id.button_back);
          stats=(Button)findViewById(R.id.button_Stats);
          Picasso.with(OwnerProfilePage.this).load("https://firebasestorage.googleapis.com/v0/b/firefirst-2b711.appspot.com/o/zeastro%2Fstrawberry.jpg?alt=media&token=8828d880-6531-4f6a-9b04-30de9ae6ff87").into(background);
          Picasso.with(OwnerProfilePage.this).load("https://firebasestorage.googleapis.com/v0/b/firefirst-2b711.appspot.com/o/zeastro%2Frestaurent.jpg?alt=media&token=ed90edb7-17d7-4484-8b9a-8694ebfedb8b").into(img_restaurent);


        setImagesUrlFlipper(viewFlippers);

        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(OwnerProfilePage.this, OwnerLoginPage.class));
            }
        });


    }



    public void setImagesUrlFlipper(final ViewFlipper[] viewFlippers)
    {
        //final String pictureUrls[]=new String[3];
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
"https://firebasestorage.googleapis.com/v0/b/firefirst-2b711.appspot.com/o/zeastro%2Fpictures.json?alt=media&token=cbfb319e-b1ee-4a38-bdb1-fd513414b570",                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Do something with response
                        // Process the JSON
                        try{
                            // Get the JSON array
                            JSONArray array = response.getJSONArray("pics");
                            // Loop through the array elements
                            for(int i=0;i<array.length();i++){
                                // Get current json object
                                JSONObject pictures = array.getJSONObject(i);
                                // Get the current student (json object) data

                                for (int k=0;k<viewFlippers.length;k++)
                                {
                                    ImageView img=new ImageView(getApplicationContext());
                                    Picasso.with(OwnerProfilePage.this).load(pictures.getString("pic")).into(img);
                                    viewFlippers[k].addView(img);
                                    viewFlippers[k].setInAnimation(getApplicationContext(),R.anim.slide_in_right);
                                    viewFlippers[k].setOutAnimation(getApplicationContext(),R.anim.slide_out_left);
                                    viewFlippers[k].setFlipInterval(1000);
                                    viewFlippers[k].startFlipping();

                                }

                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred

                    }
                }

        );
        // Add JsonObjectRequest to the RequestQueue
        requestQueue.add(jsonObjectRequest);

    }
}