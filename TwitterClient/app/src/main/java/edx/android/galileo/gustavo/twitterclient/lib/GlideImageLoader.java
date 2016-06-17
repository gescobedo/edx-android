package edx.android.galileo.gustavo.twitterclient.lib;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import edx.android.galileo.gustavo.twitterclient.lib.base.ImageLoader;

/**
 * Created by gustavo on 16/06/16.
 */
public class GlideImageLoader implements ImageLoader {

    private RequestManager manager;

    public GlideImageLoader(RequestManager requestManager) {
        this.manager= requestManager;
    }

    @Override
    public void load(ImageView imageView, String url) {
        manager
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .override(600,400)
                .into(imageView)

        ;
    }
}
