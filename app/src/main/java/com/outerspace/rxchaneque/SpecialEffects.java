package com.outerspace.rxchaneque;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.util.HashMap;

public class SpecialEffects {

    final Integer maxColorSteps;
    HashMap<Integer, Integer> idStepMap;
    int initialColor;
    int finalColor;

    Context context;

    public SpecialEffects(Context context) {
        this.context = context;

        initialColor = context.getColor(R.color.initialColor);
        finalColor   = context.getColor(R.color.finalColor);
        maxColorSteps = context.getResources().getInteger(R.integer.maxColorSteps);
        idStepMap = new HashMap<>();
    }

    public void flipColor(View view) {
        int currentStep = 0;
        if(idStepMap.containsKey(view.getId()))
            currentStep = idStepMap.get(view.getId());

        Double ri = (double) ((initialColor & 0xff0000) >> 16);
        Double gi = (double) ((initialColor & 0x00ff00) >>  8);
        Double bi = (double) ((initialColor & 0x0000ff));

        Double rf = (double) ((finalColor & 0xff0000) >> 16);
        Double gf = (double) ((finalColor & 0x00ff00) >>  8);
        Double bf = (double) ((finalColor & 0x0000ff)      );

        Double incr = (rf - ri) / maxColorSteps;
        Double incg = (gf - gi) / maxColorSteps;
        Double incb = (bf - bi) / maxColorSteps;

        Double r = (ri + incr * (currentStep+1));
        Double g = (gi + incg * (currentStep+1));
        Double b = (bi + incb * (currentStep+1));

        int col = 0xFF000000 | r.intValue() << 16 | g.intValue() << 8 | b.intValue() ;

        // Log.d("FINAL TOTAL COLOR : ", String.format("%08X", initialColor) + " - " + String.format("%08X", finalColor) + " -> (" + currentStep + ") " + String.format("%08X", col));

        view.setBackgroundColor(col);

        currentStep += 1;
        currentStep = currentStep == maxColorSteps ? 0 : currentStep;
        idStepMap.put(view.getId(), currentStep);
    }
}
