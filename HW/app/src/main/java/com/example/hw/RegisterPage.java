package com.example.hw;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;

import java.util.ArrayList;

public class RegisterPage extends AppCompatActivity {
    TextView agreeTextView;
    Button returnButton;
    ArrayList<String> UserLlist = new ArrayList<String>();
    EditText UIdText;
    EditText UPwText;
    EditText NameText;
    EditText PnText;
    EditText AddrText;
    Button submitButton;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_register);

            SharedPreferences prefs = getSharedPreferences("person_info",0);


        /* input text */
        UIdText=(EditText)findViewById(R.id.IdHintText);
        UPwText=(EditText)findViewById(R.id.PwHintText);
        NameText=(EditText)findViewById(R.id.NameHintText);
        PnText=(EditText)findViewById(R.id.PNHintText);
        AddrText=(EditText)findViewById(R.id.AddrHintText);


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
        }
        );

        /* submit button */
        submitButton=(Button) findViewById(R.id.SubmitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id =UIdText.getText().toString();
                String pw=UPwText.getText().toString();
                //만약 이미 리스트가 차있다면 원소를 더 추가하지 않는다
                if(UserLlist.size()<5) {
                    UserLlist.add(id);
                    UserLlist.add(pw);
                    UserLlist.add(NameText.getText().toString());
                    UserLlist.add(PnText.getText().toString());
                    UserLlist.add(AddrText.getText().toString());
                }
                String tmp=prefs.getString(id,null);
                //id 중복 체크
                if(tmp!=null){
                    Toast.makeText(RegisterPage.this,"중복된 id",Toast.LENGTH_SHORT).show();
                    UserLlist.clear();
                }
                //pw 길이 체크
                else if(pw.length()<8){
                    Toast.makeText(RegisterPage.this,"password는 8자리 이상이어야 합니다",Toast.LENGTH_SHORT).show();
                    UserLlist.clear();
                }
                else{
                    setArray(UIdText.getText().toString(),UserLlist);
                    finish();
                }
            }
        });

        /* accept button */
        radioGroup=(RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId ==R.id.radio1){
                    Toast.makeText(RegisterPage.this,"accepted",Toast.LENGTH_SHORT).show();
                    submitButton.setEnabled(true);
                }else if(checkedId ==R.id.radio2){
                    Toast.makeText(RegisterPage.this,"declined",Toast.LENGTH_SHORT).show();
                    submitButton.setEnabled(false);
                }
            }
        });
    }
    /* homepage에서 썼던 setArray*/
    private void setArray(String key, ArrayList<String> values) {
        SharedPreferences prefs=getSharedPreferences("person_info",0);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray klist = new JSONArray();
        for (int i = 0; i < values.size(); i++) {
            klist.put(values.get(i));
        }
        if (!values.isEmpty()) {
            editor.putString(key, klist.toString());
        } else {
            editor.putString(key, "error");
        }
        editor.apply();
    }
}