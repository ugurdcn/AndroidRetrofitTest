package com.zeygame.javamvvm.network;

import android.content.Context;
import android.media.Image;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ImageProccess {
    public void initImage(String url, Context context, ImageView imgView){
        Glide.with(context).load(url).apply(RequestOptions.centerCropTransform()).into(imgView);
    }
}
