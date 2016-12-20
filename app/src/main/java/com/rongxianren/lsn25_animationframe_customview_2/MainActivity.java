package com.rongxianren.lsn25_animationframe_customview_2;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private final int[] layouts = {
            R.layout.view_intro_1,
            R.layout.view_intro_2,
            R.layout.view_intro_3,
            R.layout.view_intro_4,
            R.layout.view_intro_5,
            R.layout.view_login
    };
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomViewContainer container = (CustomViewContainer) findViewById(R.id.container);
        imageView = (ImageView) findViewById(R.id.iv_man);
        container.setUp(layouts);

        container.setIv_man(imageView);
    }
}
