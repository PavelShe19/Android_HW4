package com.example.android_ex4;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class petek_wall_activity extends AppCompatActivity {


    ListView listView;
    private final DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petek_wall);

        listView = findViewById(R.id.listView);
        Button button = (Button) findViewById(R.id.addButton);
        Context context = getApplicationContext();
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                Intent fresh_intent = new Intent(petek_wall_activity.this,editPetek.class);
                startActivity(fresh_intent);
            }
        });

        Intent new_petek_intent = getIntent();
        if(new_petek_intent.hasExtra("title")) {

            String fresh_title = new_petek_intent.getStringExtra("title");
            String fresh_content = new_petek_intent.getStringExtra("content");
            if(fresh_title.equals("CANCEL") || fresh_content.equals("CANCEL")) {
                Toast toast = Toast.makeText(context, fresh_title,Toast.LENGTH_LONG);
                toast.show();
            }
            else {
                Petek p = new Petek(0,
                        fresh_title,
                        fresh_content);
                db.getWritableDatabase().execSQL(p.getSQLInsertString());
            }
        }

        ArrayList<Petek> peteks = new ArrayList<>();
        Cursor c = db.getReadableDatabase().rawQuery(Petek.SELECT_ALL,
                null);

        c.moveToFirst();
        while(!c.isAfterLast()){
            Petek p = new Petek(c);
            peteks.add(p);
            c.moveToNext();
        }

        Log.i("peteks", peteks.size() + "");

        listView.setAdapter(new ArrayAdapter<Petek>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                peteks
        ));




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
