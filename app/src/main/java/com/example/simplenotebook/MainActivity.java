package com.example.simplenotebook;


import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etNote;
    private Button btnSave;
    private TextView tvSavedNote;

    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "MyNotes";
    private static final String KEY_NOTE = "note";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNote = findViewById(R.id.etNote);
        btnSave = findViewById(R.id.btnSave);
        tvSavedNote = findViewById(R.id.tvSavedNote);


        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);


        String savedNote = sharedPreferences.getString(KEY_NOTE, "");
        if (!savedNote.isEmpty()) {
            tvSavedNote.setText(savedNote);
        }

        btnSave.setOnClickListener(v -> {
            String note = etNote.getText().toString();
            if (!note.isEmpty()) {
                // ذخیره در SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NOTE, note);
                editor.apply();

                // نمایش در TextView
                tvSavedNote.setText(note);
            }
        });
    }
}
