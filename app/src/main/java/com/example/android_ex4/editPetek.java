package com.example.android_ex4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class editPetek extends AppCompatActivity {
    int itemIndex = -1;
    boolean isEditMode = false;
    public static final int PETEK_EDIT_MODE_RESULT_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_petek);

        Button okButton = (Button) findViewById(R.id.okButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        TextView title = findViewById(R.id.titleEditText);
        TextView content = findViewById(R.id.contentEditText);

        isEditMode = getIntent().getBooleanExtra("isEditMode", false);
        if (isEditMode) {
            title.setText(getIntent().getStringExtra("title"));
            content.setText(getIntent().getStringExtra("content"));
            itemIndex = getIntent().getIntExtra("index", -1);
            okButton.setText("save");
        }

        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText titleText = (EditText) findViewById(R.id.titleEditText);
                EditText contentText = (EditText) findViewById(R.id.contentEditText);
                String titleValue = titleText.getText().toString();
                String contentValue = contentText.getText().toString();

                Intent petekData = new Intent();
                petekData.putExtra("title", titleValue);
                petekData.putExtra("content", contentValue);
                petekData.putExtra("index", itemIndex);
                setResult(isEditMode? PETEK_EDIT_MODE_RESULT_CODE : RESULT_OK, petekData);
                finish();

                //startActivity(fresh_intent);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Intent fresh_intent = new Intent(editPetek.this, petek_wall_activity.class);
                //fresh_intent.putExtra("title", "CANCEL");
                // fresh_intent.putExtra("content", "CANCEL");
                // startActivity(fresh_intent);
                setResult(RESULT_CANCELED);
                finish();
            }
        });

    }

}
