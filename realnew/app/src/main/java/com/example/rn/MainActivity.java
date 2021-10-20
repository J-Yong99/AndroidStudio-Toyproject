package com.example.rn;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> resultLauncher;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    LinearLayout baseLayout;
    MainFragment mainFragment;
    MenuFragment menuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "push", Toast.LENGTH_SHORT).show();
            }
        });
        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        });


        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text1=findViewById(R.id.text1);
                EditText text2=findViewById(R.id.text2);
                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                intent.putExtra("num1",Integer.parseInt(text1.getText().toString()));
                intent.putExtra("num2",Integer.parseInt(text2.getText().toString()));
                resultLauncher.launch(intent);
            }
        });
        resultLauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()==RESULT_OK){
                            Intent intent = result.getData();
                            int hap=intent.getIntExtra("Hap",0);
                            Toast.makeText(getApplicationContext(),"sum is :"+hap,Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        baseLayout=findViewById(R.id.baseLayout);
        button4=findViewById(R.id.button4);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater=getMenuInflater();
        mInflater.inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ((item.getItemId())){
            case R.id.itemRed:
                baseLayout.setBackgroundColor(Color.RED);
                return true;
            case R.id.itemBlue:
                baseLayout.setBackgroundColor(Color.BLUE);
                return true;
            case R.id.itemGreen:
                baseLayout.setBackgroundColor(Color.GREEN);
                return true;
            case R.id.subRotate:
                button4.setRotation(45);
                return true;
            case R.id.subSize:
                baseLayout.setScaleX(2);
                return true;
        }
        return false;
    }
//    public void onFragmentChanged(int index){
//        if (index==0){
//            getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity)
//        }
//    }
}

