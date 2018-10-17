package com.hyphenate.helpdesk.easeui.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.request.RequestListener;


public class GlideManager {
    private BitmapRequestBuilder<GlideUrl, Bitmap> requestBuilder;

    private GlideManager() {
    }

    private static GlideManager sInstance;

    public static GlideManager getsInstance() {
        if (null == sInstance) {
            sInstance = new GlideManager();
        }
        return sInstance;
    }

    public void loadImageToUrL(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().into(imageView);
    }

    public void loadImageToUrL(Context context, String url, ImageView imageView, RequestListener requestListener) {
        Glide.with(context).load(url).listener(requestListener).crossFade(500).diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().into(imageView);
    }


}