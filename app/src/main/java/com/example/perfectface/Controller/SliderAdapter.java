package com.example.perfectface.Controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.perfectface.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SlideRViewHolder> {
    private List<SliderItem> sliderItems;
    private ViewPager2 viewPager2;

     public SliderAdapter(List<SliderItem> sliderItems, ViewPager2 viewPager2) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SlideRViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SlideRViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_item_container,parent,false) );
    }

    @Override
    public void onBindViewHolder(@NonNull SlideRViewHolder holder, int position) {
        holder.setimage(sliderItems.get(position));

    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    class SlideRViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView imageView;
         SlideRViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageslide);
        }
        void setimage(SliderItem sliderItem){
            imageView.setImageResource(sliderItem.getImage());
        }


    }
}
