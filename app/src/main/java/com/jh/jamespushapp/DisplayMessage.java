package com.jh.jamespushapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class DisplayMessage extends AppCompatActivity {

    private static final String TAG = "DisplayMessge";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        TextView msgDisplay = (TextView) findViewById(R.id.msgDisplayTextView);
        Button btnBack = (Button) findViewById(R.id.backButton);
        Button btnEnd = (Button) findViewById(R.id.endButton);

        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "key :" + key + " Value : " + value);
                msgDisplay.setText(key + " : " + value);
            }
        }
    }
}
