package com.outerspace.rxchaneque;

import android.util.Log;
import java.util.HashMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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

        btn = (Button) findViewById(R.id.btn_rx_observable);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtObservable = (TextView) findViewById(R.id.txt_rx_observable);
                BasicRxObservableObserver observableObserver = new BasicRxObservableObserver(txtObservable);
                observableObserver.justDoIt();
            }
        });

        btn = (Button) findViewById(R.id.btn_rx_interval);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtObsInterval = (TextView) findViewById(R.id.txt_rx_interval);
                BasicRxObservableObserver observableObserver = new BasicRxObservableObserver(txtObsInterval);
                observableObserver.justCountIt();
            }
        });

    }




}
