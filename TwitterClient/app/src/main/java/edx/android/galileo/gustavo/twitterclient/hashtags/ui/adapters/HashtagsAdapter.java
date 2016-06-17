package edx.android.galileo.gustavo.twitterclient.hashtags.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edx.android.galileo.gustavo.twitterclient.R;
import edx.android.galileo.gustavo.twitterclient.entities.Hashtag;
import edx.android.galileo.gustavo.twitterclient.hashtags.ui.CustomGridLayoutManager;
import edx.android.galileo.gustavo.twitterclient.lib.base.ImageLoader;

/**
 * Created by gustavo on 17/06/16.
 */
public class HashtagsAdapter extends RecyclerView.Adapter<HashtagsAdapter.ViewHolder>{
    private List<Hashtag> dataset;
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;

    public HashtagsAdapter(OnItemClickListener clickListener, List<Hashtag> dataset, ImageLoader imageLoader) {

        this.imageLoader = imageLoader;
    }

    public HashtagsAdapter(OnItemClickListener onItemClickListener, List<Hashtag> dataset) {
        this.clickListener = onItemClickListener;
        this.dataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hashtags_content,parent,false);
        return new ViewHolder(view,parent.getContext());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hashtag imageTweet=dataset.get(position);
        holder.setOnClickListener(imageTweet,clickListener);
        holder.txtTweet.setText(imageTweet.getTweetText());
        holder.setItems(imageTweet.getHashtags());
      //  imageLoader.load(holder.imgMedia,imageTweet.getHashtagURL());
    }

    @Override
    public int getItemCount() {
        return dataset.size() ;
    }

    public void setItems(List<Hashtag> newItems){
        dataset.addAll(newItems);
        notifyDataSetChanged();
    }
   static  class ViewHolder extends RecyclerView.ViewHolder {
        private  View view;

        @Bind(R.id.txtTweet)
        TextView txtTweet;
        @Bind(R.id.recyclerViewHashtags)
        RecyclerView recyclerView;
        private HashtagListAdapter adapter;
        private ArrayList<String> items;
        public ViewHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            this.view=itemView;
            items= new ArrayList<String>();
            adapter=new HashtagListAdapter(items);

            CustomGridLayoutManager layoutManager=new CustomGridLayoutManager(context,3);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

        }
        public void setItems(List<String> newItems){
            items.clear();
            items.addAll(newItems);
            adapter.notifyDataSetChanged();
        }

        public void  setOnClickListener(final Hashtag image, final OnItemClickListener l){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    l.onItemClick(image);
                }
            });
        }
    }
}
