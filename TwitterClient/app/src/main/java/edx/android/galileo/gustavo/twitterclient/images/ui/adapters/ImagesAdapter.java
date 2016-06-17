package edx.android.galileo.gustavo.twitterclient.images.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edx.android.galileo.gustavo.twitterclient.R;
import edx.android.galileo.gustavo.twitterclient.entities.Image;
import edx.android.galileo.gustavo.twitterclient.lib.base.ImageLoader;

/**
 * Created by gustavo on 16/06/16.
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {
    private List<Image> dataset;
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;

    public ImagesAdapter(OnItemClickListener clickListener, List<Image> dataset, ImageLoader imageLoader) {
        this.clickListener = clickListener;
        this.dataset = dataset;
        this.imageLoader = imageLoader;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.images_content,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
     Image imageTweet=dataset.get(position);
        holder.setOnClickListener(imageTweet,clickListener);
        holder.txtTweet.setText(imageTweet.getTweetText());
        imageLoader.load(holder.imgMedia,imageTweet.getImageURL());
    }

    @Override
    public int getItemCount() {
        return dataset.size() ;
    }

    public void setItems(List<Image> newItems){
        dataset.addAll(newItems);
        notifyDataSetChanged();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        private  View view;
        @Bind(R.id.imgMedia)
        ImageView imgMedia;
        @Bind(R.id.txtTweet)
        TextView txtTweet;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            this.view=itemView;
        }
        public void  setOnClickListener(final Image image, final OnItemClickListener onItemClickListener){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(image);
                }
            });
        }
    }
}
