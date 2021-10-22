package com.example.hw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {
    Button regButton;
    Button mainPgButton;
    Button loginButton;
    private EditText IdText;
    private EditText PWText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_home);

        /* login button */
        IdText=(EditText) findViewById(R.id.Id);
        PWText=(EditText) findViewById(R.id.password);
        String id= IdText.getText().toString();
        String pw= PWText.getText().toString();
        loginButton=(Button)findViewById(R.id.Login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(savedInstanceState==null){
                    SharedPreferences prefs = getSharedPreferences("person_info",0);

                    String password=prefs.getString(id,"");
                    if(pw==password){
                        Intent intent= new Intent(getApplicationContext(),MainPage.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(HomePage.this,"Access Denied",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        /* register button */
        regButton=(Button) findViewById(R.id.Register);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterPage.class);
                startActivity(intent);
            }
        });

        /* go to mainPage button */
        mainPgButton=(Button) findViewById(R.id.Mainpage);
        mainPgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),MainPage.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        /* preference에 데이터를 저장한다. */
        SharedPreferences prefs=getSharedPreferences("person_info",0);
        SharedPreferences.Editor editor= prefs.edit();

        String id= "1";
        String pw= "1";

        editor.putString(id,pw);
        editor.apply();
    }
}