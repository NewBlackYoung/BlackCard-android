package com.sainti.blackcard.mhttp.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.request.RequestListener;
import com.sainti.blackcard.R;
import com.sainti.blackcard.widget.GlideRoundTransform;


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
    public void loadImageToUrLYsuo(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).error(R.mipmap.icon_def).crossFade(500).diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().into(imageView);
    }
    public void loadImageToUrL(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).error(R.mipmap.icon_def).diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().into(imageView);
    }
    public void loadImageToUrLs(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).error(R.mipmap.icon_def).diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().override(800, 500).into(imageView);
    }
    public void loadImageToUrLss(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).error(R.mipmap.icon_def).diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().override(300, 300).into(imageView);
    }
    public void loadImageToUrL(Context context, String url, ImageView imageView, int errorImgId) {
        Glide.with(context).load(url).error(errorImgId).crossFade(500).diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().into(imageView);
    }

    public void loadImageToBitmap(Context context, byte[] bytes, ImageView imageView) {
        Glide.with(context).load(bytes).error(R.mipmap.icon_def).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);

    }

    public void loadImageToUrL(Context context, String url, ImageView imageView, RequestListener requestListener) {
        Glide.with(context).load(url).listener(requestListener).error(R.mipmap.icon_def).crossFade(500).diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().into(imageView);
    }

    //原图处理成圆角，如果是四周都是圆角则是RoundedCornersTransformation.CornerType.ALL
    public void loadImageToUrLCircle(Context context, String url, ImageView imageView) {

        Glide.with(context).load(url).error(R.mipmap.icon_def).diskCacheStrategy(DiskCacheStrategy.ALL).transform(new GlideRoundTransform(context, 10)).into(imageView);
    }
    //原图处理成圆角，如果是四周都是圆角则是RoundedCornersTransformation.CornerType.ALL
    public void loadImageToUrLb(Context context, String url, ImageView imageView) {

        Glide.with(context).load(url).error(R.mipmap.icon_def).transform(new GlideRoundTransform(context, 10)).into(imageView);
    }

}