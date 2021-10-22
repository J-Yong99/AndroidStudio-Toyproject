package com.example.hw;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterPage extends AppCompatActivity {
    TextView agreeTextView;
    Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_register);

        if(savedInstanceState==null){
            SharedPreferences prefs = getSharedPreferences("person_info",0);

            String id=prefs.getString("id"," ");
            String pw=prefs.getString("pw"," ");
            boolean man = prefs.getBoolean("man",false);

         }

        /*agreement scrollable*/
        agreeTextView=(TextView) findViewById(R.id.AgreeTextView);
        agreeTextView.setMovementMethod(new ScrollingMovementMethod());

        /* return button */
        returnButton=(Button) findViewById(R.id.ReturnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}