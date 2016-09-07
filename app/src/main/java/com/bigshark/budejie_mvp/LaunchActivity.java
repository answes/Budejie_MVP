package com.bigshark.budejie_mvp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by bigShark on 2016/5/25.
 */
public class LaunchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        //target ：绑定动画的view
        //propertyname 执行的动画的属性名称(缩放动画：ScaleX)
        //ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(findViewById(R.id.iv_bg), "scaleX",
        // 0.5f, 1.0f);
        //渐变动画用的属性名

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(findViewById(R.id.iv_bg), "alpha",
                0.0f, 1.0f);
        objectAnimator.setDuration(2000);
        //启动动画
        objectAnimator.start();

        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                LaunchActivity.this.finish();
            }
        });

    }
}
