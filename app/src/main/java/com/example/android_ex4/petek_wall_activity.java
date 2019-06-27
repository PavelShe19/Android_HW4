package com.example.android_ex4;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class petek_wall_activity extends AppCompatActivity {


    public static final int ADD_PETEK_REQUEST_CODE = 1;
    ListView listView;
    private final DBHelper db = new DBHelper(this);
    private ArrayAdapter<Petek> petekArrayAdapter;
    private ArrayList<Petek> peteks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petek_wall);

        listView = findViewById(R.id.listView);
        Button button = (Button) findViewById(R.id.addButton);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                Intent fresh_intent = new Intent(petek_wall_activity.this, editPetek.class);
                startActivityForResult(fresh_intent, ADD_PETEK_REQUEST_CODE);
            }
        });

        //Intent new_petek_intent = getIntent();
        //if (new_petek_intent.hasExtra("title")) {

        //String fresh_title = new_petek_intent.getStringExtra("title");
        // String fresh_content = new_petek_intent.getStringExtra("content");
        //if (fresh_title.equals("CANCEL") || fresh_content.equals("CANCEL")) {
        //    Toast toast = Toast.makeText(this, fresh_title, Toast.LENGTH_LONG);
        //    toast.show();
        //} else {
        // Petek p = new Petek(0,
        //         fresh_title,
        //         fresh_content);
        // db.getWritableDatabase().execSQL(p.getSQLInsertString());
        //}
        // }

         peteks = new ArrayList<>();
        Cursor c = db.getReadableDatabase().rawQuery(Petek.SELECT_ALL,
                null);

        petekArrayAdapter = new ArrayAdapter<Petek>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                peteks
        );
        listView.setAdapter(petekArrayAdapter);

        c.moveToFirst();
        while (!c.isAfterLast()) {
            Petek p = new Petek(c);
            //peteks.add(p);
            petekArrayAdapter.add(p);
            c.moveToNext();
        }

        Log.i("peteks", peteks.size() + "");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ADD_PETEK_REQUEST_CODE:
                if (resultCode == RESULT_OK && data != null) {
                    String newPetekTitle = data.getStringExtra("title");
                    String newPetekContent = data.getStringExtra("content");

                    Petek p = new Petek( petekArrayAdapter.getCount()+1,
                            newPetekTitle,
                            newPetekContent);
                    db.getWritableDatabase().execSQL(p.getSQLInsertString());

                    petekArrayAdapter.add(p);

                }
                break;
        }
    }

    //        EditText et = findViewById(R.id.textView);
//
//        final SharedPreferences prefs =
//                PreferenceManager
//                        .getDefaultSharedPreferences(this);
//
//
//        String s = prefs.getString("text", "");
//        et.setText(s);
//
//        et.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                prefs.edit().putString("text", editable.toString()).commit();
//            }
//        });
//
//    }
}
