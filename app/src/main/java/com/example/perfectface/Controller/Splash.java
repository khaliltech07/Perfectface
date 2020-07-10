package com.example.perfectface.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.perfectface.Module.dashbord;
import com.example.perfectface.R;

import static android.view.WindowManager.LayoutParams;

public class Splash extends AppCompatActivity {
    Animation Topanimation;
    ImageView image;
    private  static int Splash_Screen=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(LayoutParams.FLAG_FULLSCREEN, LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splashscreen);

        //animation
        Topanimation= AnimationUtils.loadAnimation(this,R.anim.top_animation);

        //hooks
        image=findViewById(R.id.imageView);

        image.setAnimation(Topanimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Splash.this, dashbord.class);
                startActivity(intent);
                finish();
            }
        },Splash_Screen);



    }
}
