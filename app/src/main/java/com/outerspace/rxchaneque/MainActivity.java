package com.outerspace.rxchaneque;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.reactivestreams.Subscription;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    final static float maxColorSteps = 10.0f;
    HashMap<Integer, Integer> idStepMap;
    int initialColor;
    int finalColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialColor = this.getApplicationContext().getColor(R.color.initialColor);
        finalColor   = this.getApplicationContext().getColor(R.color.finalColor);
        idStepMap = new HashMap<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Button btn;
        btn = (Button) findViewById(R.id.btn_standard_listener);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipColor(v);
            }
        });


    }

    private void flipColor(View view) {
        int currentStep = 0;
        if(idStepMap.containsKey(view.getId()))
            currentStep = idStepMap.get(view.getId());

        float p = currentStep / maxColorSteps;

        Integer r = (initialColor & 0xff0000) >> 16;
        Integer b = (initialColor & 0x00ff00) >>  8;
        Integer g = (initialColor & 0x0000ff);

        Integer rf = (finalColor & 0xff0000) >> 16;
        Integer bf = (finalColor & 0x00ff00) >>  8;
        Integer gf = (finalColor & 0x0000ff);

        r = r + Math.round((r - rf) * p);
        g = g + Math.round((g - gf) * p);
        b = b + Math.round((b - bf) * p);

        int col = 0xFF000000 | r << 16 | g << 8 | b ;

        view.setBackgroundColor(col);

        currentStep += 1;
        currentStep = currentStep == maxColorSteps ? 0 : currentStep;
        idStepMap.put(view.getId(), currentStep);
    }

}
