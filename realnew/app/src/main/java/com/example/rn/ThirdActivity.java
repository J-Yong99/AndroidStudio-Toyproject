package com.example.rn;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;



public class ThirdActivity extends Activity {
    Button button;
    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);
        final String TAG="Activity";

        Intent inIntent =getIntent();
        final int hapValue=inIntent.getIntExtra("num1",0)+inIntent.getIntExtra("num2",0);

        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent=new Intent(getApplicationContext(),MainActivity.class);
                outIntent.putExtra("Hap",hapValue);
                setResult(RESULT_OK,outIntent);
                finish();
            }
        });
        button1=(Button)findViewById(R.id.button2);
        button2=(Button)findViewById(R.id.button3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("tel:010-8206-0438");
                Intent intent=new Intent(Intent.ACTION_CALL,uri);
                Log.d(TAG, "onClick: d");
                Log.v(TAG, "onClick: v");
                Log.i(TAG, "onClick: i");
                Log.w(TAG, "onClick: w");
                Log.e(TAG, "onClick: e");
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("http://www.naver.com");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
    }
}
