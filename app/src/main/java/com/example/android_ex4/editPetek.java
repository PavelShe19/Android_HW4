package com.example.android_ex4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class editPetek extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_petek);

        Button okButton = (Button) findViewById(R.id.okButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);

        Context context = getApplicationContext();

        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText titleText = (EditText)findViewById(R.id.titleEditText);
                EditText contentText = (EditText)findViewById(R.id.contentEditText);
                String titleValue = titleText.getText().toString();
                String contentValue = contentText.getText().toString();
                Intent fresh_intent = new Intent(editPetek.this, petek_wall_activity.class);
                fresh_intent.putExtra("title", titleValue);
                fresh_intent.putExtra("content", contentValue);
                startActivity(fresh_intent);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent fresh_intent = new Intent(editPetek.this, petek_wall_activity.class);
                fresh_intent.putExtra("title", "CANCEL");
                fresh_intent.putExtra("content", "CANCEL");
                startActivity(fresh_intent);
            }
        });

    }

}
