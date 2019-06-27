package com.example.android_ex4;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.android_ex4.Petek.TABLE_NAME;
import static com.example.android_ex4.editPetek.PETEK_EDIT_MODE_RESULT_CODE;

public class petek_wall_activity extends AppCompatActivity {


    public static final int ADD_PETEK_REQUEST_CODE = 1;
    ListView listView;
    private final DBHelper db = new DBHelper(this);
    private ArrayAdapter<Petek> petekArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petek_wall);

        listView = findViewById(R.id.listView);
        Button button = findViewById(R.id.addButton);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent fresh_intent = new Intent(petek_wall_activity.this, editPetek.class);
                startActivityForResult(fresh_intent, ADD_PETEK_REQUEST_CODE);
            }
        });


        Cursor c = db.getReadableDatabase().rawQuery(Petek.SELECT_ALL,
                null);

        petekArrayAdapter = new ArrayAdapter<Petek>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                new ArrayList<Petek>()
        );
        listView.setAdapter(petekArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent editPetek = new Intent(petek_wall_activity.this, com.example.android_ex4.editPetek.class);
                editPetek.putExtra("isEditMode", true);
                String itemTitle = ((Petek) listView.getAdapter().getItem(i)).title;
                String itemContent = ((Petek) listView.getAdapter().getItem(i)).content;
                editPetek.putExtra("title", itemTitle);
                editPetek.putExtra("content", itemContent);
                editPetek.putExtra("index", i);
                startActivityForResult(editPetek, ADD_PETEK_REQUEST_CODE);
            }
        });

        c.moveToFirst();
        while (!c.isAfterLast()) {
            Petek p = new Petek(c);
            petekArrayAdapter.add(p);
            c.moveToNext();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case ADD_PETEK_REQUEST_CODE:
                if (resultCode == RESULT_OK && data != null) {
                    String newPetekTitle = data.getStringExtra("title");
                    String newPetekContent = data.getStringExtra("content");

                    Petek p = new Petek(petekArrayAdapter.getCount() + 1,
                            newPetekTitle,
                            newPetekContent);
                    db.getWritableDatabase().execSQL(p.getSQLInsertString());

                    petekArrayAdapter.add(p);

                } else if (resultCode == PETEK_EDIT_MODE_RESULT_CODE && data != null) {

                    String newPetekTitle = data.getStringExtra("title");
                    String newPetekContent = data.getStringExtra("content");
                    int i = data.getIntExtra("index", -1);
                    if (i == -1) return;

                    petekArrayAdapter.getItem(i).title = newPetekTitle;
                    petekArrayAdapter.getItem(i).content = newPetekContent;

                    ContentValues cv = new ContentValues();
                    cv.put("title", newPetekTitle); //These Fields should be your String values of actual column names
                    cv.put("content", newPetekContent);

                    db.getWritableDatabase().update(TABLE_NAME, cv, "id=" + petekArrayAdapter.getItem(i).id, null);
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
