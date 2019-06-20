package com.example.android_ex4;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {
        EditText user = (EditText)findViewById(R.id.username);
        EditText pass = (EditText)findViewById(R.id.password);

        Context context = getApplicationContext();

        if(user.getText().toString().equals("user")&& pass.getText().toString().equals("pass")) {
            //Intent intent = new Intent(getApplicationContext(),nextScreen.class); //open next screen after successful login
            //startActivity(intent);
        }
        else{
            Toast toast = Toast.makeText(context, "Username or Password are incorrect", 100);
            toast.show();
        }
    }
}
