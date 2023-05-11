package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences myPreferenceRef;
    private SharedPreferences.Editor myPreferenceEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myPreferenceRef = getSharedPreferences("MyPreferenceName", MODE_PRIVATE);
       // SharedPreferences.Editor myPreferenceEditor = myPreferenceRef.edit();
       // myPreferenceRef = getPreferences(MODE_PRIVATE);
        myPreferenceEditor = myPreferenceRef.edit();

     //   TextView prefTextRef = findViewById(R.id.prefText);
     //   prefTextRef.setText(myPreferenceRef.getString("MyappPreferenceString", "No preference found."));

      //  TextView prefTextRef=new TextView(this);
      //  prefTextRef=(TextView)findViewById(R.id.prefText);
      //  prefTextRef.setText(myPreferenceRef.getString("MyAppPreferenceString", "No preference found."));
    }

    @Override
    protected void onResume(){
        super.onResume();
        TextView prefTextRef = findViewById(R.id.prefText);
        prefTextRef.setText(myPreferenceRef.getString("MyappPreferenceString", "No preference found."));
    }

    public void savePref(View view){
        // Get the text
        EditText newPrefText = findViewById(R.id.edit_Text);

      //  EditText newPrefText=new EditText(this);
      //  newPrefText=(EditText)findViewById(R.id.edit_Text);

        // Store the new preference
        myPreferenceEditor.putString("MyAppPreferenceString", newPrefText.getText().toString());
        myPreferenceEditor.apply();

        // Display the new preference
        TextView prefTextRef=new TextView(this);
        prefTextRef=(TextView)findViewById(R.id.prefText);
        prefTextRef.setText(myPreferenceRef.getString("MyAppPreferenceString", "No preference found."));

        // Clear the EditText
        newPrefText.setText("");
    }

    public void openSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }


}
