package com.example.perfectface.Module;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;


import com.example.perfectface.Controller.SliderItem;
import com.example.perfectface.R;
import com.example.perfectface.Controller.SliderAdapter;

import java.util.ArrayList;
import java.util.List;

public class dashbord extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private Handler slideHandler=new Handler();
    private ImageView cameraLens;
    private ImageView Folder;
    private View mArButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);

        viewPager2=findViewById(R.id.viewpagerimageslider);

        List<SliderItem>sliderItems= new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.cover1));
        sliderItems.add(new SliderItem(R.drawable.image1));
        sliderItems.add(new SliderItem(R.drawable.cover2));
        sliderItems.add(new SliderItem(R.drawable.image3));

        viewPager2.setAdapter(new SliderAdapter(sliderItems,viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();

        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=1-Math.abs(position);
                page.setScaleY(0.85f+r*0.15f);

            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
     viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
         @Override
         public void onPageSelected(int position) {
             super.onPageSelected(position);
             slideHandler.removeCallbacks(sliderRunnable);
             slideHandler.postDelayed(sliderRunnable,6000);
         }
     });

     cameraLens=findViewById(R.id.cameraLens);
     Folder=findViewById(R.id.Folder);

     cameraLens.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent=new Intent(dashbord.this, Cameralens.class);
             startActivity(intent);
         }
     });

     Folder.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent=new Intent(dashbord.this, Galleryacces.class);
             startActivity(intent);

         }
     });



    }

    private Runnable sliderRunnable=new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };


}
