package edx.android.galileo.gustavo.androidchat.lib;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

/**
 * Created by gustavo on 09/06/16.
 */
public class GlideImageLoader  implements ImageLoader {
    private RequestManager manager;

    public GlideImageLoader(Context context) {
        this.manager= Glide.with(context);
    }

    @Override
    public void load(ImageView imageView, String url) {
        manager.load(url).into(imageView);
    }
}
