package com.it.onex.materialdesigndemo;

import android.animation.AnimatorSet;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {


    private TranslateAnimation animation;
    private AnimatorSet set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gzmx_details);

        final Button btn = (Button) findViewById(R.id.btn_start);
        final TextView tv = (TextView) findViewById(R.id.tv);

        animation = new TranslateAnimation(0, 0, 500, 800);
        animation.setDuration(8000);
        animation.setFillAfter(true);
        tv.startAnimation(animation);


//        tv.animate().translationX(800);
//        tv.animate().translationY(800);
//        tv.animate().start();
//        tv.animate().setDuration(4000);



//        set = new AnimatorSet();
//        ObjectAnimator oa = ObjectAnimator.ofFloat(tv, "translationX", 0, 800);
//        ObjectAnimator oa2 = ObjectAnimator.ofFloat(tv, "translationY", 0, 800);
//
//        set.setTarget(tv);
//        set.playTogether(oa,oa2);
//        set.setDuration(5000);
//        set.start();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                set.start();

            }
        });


    }
}
