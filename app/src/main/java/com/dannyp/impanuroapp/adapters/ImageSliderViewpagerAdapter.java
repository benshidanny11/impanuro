package com.dannyp.impanuroapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.dannyp.impanuroapp.R;
import com.dannyp.impanuroapp.constants.ApiLinks;

import java.util.Objects;

public class ImageSliderViewpagerAdapter extends PagerAdapter {
    Context context;
    String[] images;
    LayoutInflater mLayoutInflater;

    public ImageSliderViewpagerAdapter(Context context, String[] images) {
        this.context = context;
        this.images = images;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.slide_image_viewer, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.image_slider_viewer);

        Glide.with( context).load(images[position])
                .apply(new RequestOptions().dontTransform().dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL)
                        .skipMemoryCache(false).placeholder(R.drawable.ic_img_place_holder)).into(imageView);
        Objects.requireNonNull(container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
