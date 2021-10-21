package com.example.rn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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
    Button button5;
    LinearLayout baseLayout;
    MainFragment mainFragment;
    MenuFragment menuFragment;
    int value=0;
    TextView textView;
    //MainHandler handler;
    Handler handlerN=new Handler();
    private EditText IdText;
    private EditText PWText;
    private RadioGroup radioGroup;
    private RadioButton radio1;

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

        button5=findViewById(R.id.button5);
        textView=findViewById(R.id.textview);
        //handler=new MainHandler();

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundThread thread =new BackgroundThread();
                thread.start();
            }
        });
        //shared preference
        radioGroup=(RadioGroup) findViewById(R.id.radioGroup);
        radio1=(RadioButton)findViewById(R.id.radio1);
        IdText=(EditText) findViewById(R.id.IdText);
        PWText=(EditText) findViewById(R.id.PWText);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId ==R.id.radio1){
                    Toast.makeText(MainActivity.this,"male",Toast.LENGTH_SHORT).show();
                }else if(checkedId ==R.id.radio2){
                    Toast.makeText(MainActivity.this,"female",Toast.LENGTH_SHORT).show();
                }
            }
        });
        /* preference에서 데이터 읽어오기*/
        if(savedInstanceState==null){
            SharedPreferences prefs = getSharedPreferences("person_info",0);

            String id=prefs.getString("id"," ");
            String pw=prefs.getString("pw"," ");
            boolean man = prefs.getBoolean("man",false);

            IdText.setText(id);
            PWText.setText(pw);
            radio1.setChecked(man);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        /* preference에 데이터를 저장한다. */
        SharedPreferences prefs=getSharedPreferences("person_info",0);
        SharedPreferences.Editor editor= prefs.edit();

        String id= IdText.getText().toString();
        String pw= PWText.getText().toString();
        boolean man=radio1.isChecked();

        editor.putString("id",id);
        editor.putString("pw",pw);
        editor.putBoolean("man",man);
        editor.apply();
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

    class BackgroundThread extends Thread{
        public void run(){
            for (int i=0;i<100;i++){
                try{
                    Thread.sleep(1000);

                }catch (Exception e){
                }
                value+=1;
                Log.d("Thread","value"+value);

                handlerN.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("value 값:"+value);
                    }
                });
            }

        }
    }

//    class BackgroundThread extends Thread{
//        public void run(){
//            for (int i=0;i<100;i++){
//                try{
//                    Thread.sleep(1000);
//
//                }catch (Exception e){
//                }
//                value+=1;
//                Log.d("Thread","value"+value);
//
//                Message message=handler.obtainMessage();
//                Bundle bundle=new Bundle();
//                bundle.putInt("value",value);
//                message.setData(bundle);
//
//                handler.sendMessage(message);
//            }
//
//        }
//    }
//    class MainHandler extends Handler {
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//
//            Bundle bundle=msg.getData();
//            int value=bundle.getInt("value");
//            textView.setText("value 값: "+value);
//        }
//    }
}

