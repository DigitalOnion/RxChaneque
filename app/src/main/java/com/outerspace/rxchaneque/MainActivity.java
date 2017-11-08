package com.outerspace.rxchaneque;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private SpecialEffects effects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        effects = new SpecialEffects(this.getApplicationContext());
    }

    @Override
    protected void onStart() {
        super.onStart();

        Button btn;
        btn = (Button) findViewById(R.id.btn_standard_listener);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                effects.flipColor(v);
            } 
        });

    }


}
