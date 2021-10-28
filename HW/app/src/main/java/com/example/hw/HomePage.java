package com.example.hw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    Button regButton;
    Button mainPgButton;
    Button loginButton;
    private EditText IdText;
    private EditText PWText;
    String id="";
    String pw="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_home);

        /* login button */
        IdText=(EditText) findViewById(R.id.Id);
        PWText=(EditText) findViewById(R.id.password);
        loginButton=(Button)findViewById(R.id.Login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("check");
                SharedPreferences prefs = getSharedPreferences("person_info", 0);
                id = IdText.getText().toString();
                pw = PWText.getText().toString();
                String password = null;
                //preference에서 key를 이용해 pw를 가져와서 검증한다.
                //klist의 원소가 5개보다 적으면 로그인 불가능하게 만든다.
                ArrayList<String> klist = getStringArrayPref(id);
                if (klist.size()>4) {
                    password = klist.get(1);
                }
                //klist에 제대로된 원소가 들어있을때 pw를 비교해서 접근을 확인한다.
                if (pw.equals(password)) {
                    Intent intent = new Intent(getApplicationContext(), MainPage.class);
                    intent.putExtra("name",klist.get(2));
                    startActivity(intent);
                } else {
                    Toast.makeText(HomePage.this, "Access Denied", Toast.LENGTH_SHORT).show();
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

    private void setStringArrayPref(String key, ArrayList<String> values) {
        SharedPreferences prefs=getSharedPreferences("person_info",0);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();
        for (int i = 0; i < values.size(); i++) {
            a.put(values.get(i));
        }
        if (!values.isEmpty()) {
            editor.putString(key, a.toString());
        } else {
            editor.putString(key, "error");
        }
        editor.apply();
    }

    private ArrayList<String> getStringArrayPref(String key) {
        SharedPreferences prefs=getSharedPreferences("person_info",0);
        String json = prefs.getString(key, null);
        ArrayList<String> urls = new ArrayList<String>();
        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);
                for (int i = 0; i < a.length(); i++) {
                    String url = a.optString(i);
                    urls.add(url);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return urls;
    }
}