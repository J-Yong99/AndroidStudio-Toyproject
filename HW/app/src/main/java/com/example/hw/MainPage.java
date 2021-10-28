package com.example.hw;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainPage extends AppCompatActivity {
    String name="Push to register!";
    Button returnButton;
    Button bottomButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_main);

        /* Bottom button */
        //회원일경우 Hello! name이 뜨고
        //비회원일경우 버튼이 활성화되며 회원가입가능하게 한다.
        bottomButton =(Button) findViewById(R.id.text1);
        Intent getintent=getIntent();
        if(getintent.getStringExtra("name")!=null) {
            name = getintent.getStringExtra("name");
            bottomButton.setEnabled(false);
        }
        bottomButton.setText("Hello! "+name);
        bottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterPage.class);
                startActivity(intent);
            }
        });
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