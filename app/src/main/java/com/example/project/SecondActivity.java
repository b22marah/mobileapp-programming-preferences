package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private SharedPreferences myPreferenceRef;
    private SharedPreferences.Editor myPreferenceEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Get a reference to the shared preference
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        myPreferenceRef = getSharedPreferences("MyPreferenceName", MODE_PRIVATE);
        myPreferenceEditor = myPreferenceRef.edit();
    }

    public void savePref(View view) {
        EditText editText = findViewById(R.id.edit_Text);
        String newPreference = editText.getText().toString();

        myPreferenceEditor.putString("MyappPreferenceString", newPreference);
        myPreferenceEditor.apply();

        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();

        EditText editText = findViewById(R.id.edit_Text);
        String newPreference = editText.getText().toString();

        myPreferenceEditor.putString("MyappPreferenceString", newPreference);
        myPreferenceEditor.apply();

        finish();
    }
}