package com.outerspace.rxchaneque;

import android.content.Context;
import android.view.View;

import java.util.HashMap;

public class SpecialEffects {

    final float maxColorSteps;
    HashMap<Integer, Integer> idStepMap;
    int initialColor;
    int finalColor;

    Context context;

    public SpecialEffects(Context context) {
        this.context = context;

        initialColor = context.getColor(R.color.initialColor);
        finalColor   = context.getColor(R.color.finalColor);
        maxColorSteps = context.getResources().getDimension(R.dimen.maxColorSteps);
        idStepMap = new HashMap<>();
    }

    public void flipColor(View view) {
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
