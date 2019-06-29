package com.example.android_ex4;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DonationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_page);

        Button btn = (Button) findViewById(R.id.Send);
        final EditText ed  = (EditText)findViewById(R.id.Input);
        final TextView tv  = (TextView)findViewById(R.id.Output);
        tv.setText("0");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String str = ed.getText().toString();
                int sum = Integer.parseInt(str) + Integer.parseInt(tv.getText().toString());
                tv.setText(Integer.toString(sum));
                ed.setText("");
            }
        });
    }

}
