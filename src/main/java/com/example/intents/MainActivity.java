package com.example.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
 public static String TAG = "MainActivity";
    EditText url, phone;
    Button launchURL, launchPhone, closeApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url = findViewById(R.id.url);
        phone = findViewById(R.id.phone);

        launchURL = findViewById(R.id.launchUrl);
        launchPhone = findViewById(R.id.ring);
        closeApp = findViewById(R.id.close);


        launchURL.setOnClickListener(l->{
           if(validateUrl(url.getText().toString()) ){
               Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url.getText().toString()));
               startActivity(browserIntent);
           }else{
               Toast.makeText(this, "Enter a valid email", Toast.LENGTH_LONG).show();
           }
        });

        launchPhone.setOnClickListener(l->{

              Log.d(TAG, "onCreate: "+ phone.getText().toString().length());

            if((phone.getText().toString()).length()==10 ){
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phone.getText().toString()));
                startActivity(intent);
            }else{
                Toast.makeText(this, "Enter a valid mobile number", Toast.LENGTH_LONG).show();
            }
        });

        closeApp.setOnClickListener(l->{
            finish();
            System.exit(0);
        });
    }


    public boolean validateUrl(String url){
        return Patterns.WEB_URL.matcher(url).matches();
    }


}