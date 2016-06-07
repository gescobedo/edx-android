package com.galileo.gustavo.tipcalc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.galileo.gustavo.tipcalc.R;
import com.galileo.gustavo.tipcalc.models.TipRecord;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gustavo on 31/05/16.
 */
public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder> {
    private Context context;
    private List<TipRecord> dataset;
    private OnItemClickListener onItemClickListener;
    public TipAdapter(List<TipRecord> dataset,Context context,OnItemClickListener onItemClickListener) {
        this.dataset = dataset;
        this.context=context;
        this.onItemClickListener=onItemClickListener;
    }
    public TipAdapter(Context context,OnItemClickListener onItemClickListener) {
        this.dataset = new ArrayList<TipRecord>();
        this.context=context;
        this.onItemClickListener=onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TipRecord element= dataset.get(position);
        String strTip=String.format(
                context.getString(R.string.global_message_tip),element.getTip());
        holder.txtContent.setText(strTip);
        holder.setOnItemClickListener(element, onItemClickListener);
    }
    public void add(TipRecord record){
    dataset.add(0,record);
        notifyDataSetChanged();
    }
    public void clear(){
        dataset.clear();
    notifyDataSetChanged();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.txtContent)
        TextView txtContent;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setOnItemClickListener(final TipRecord element, final OnItemClickListener onItemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(element);
                }
            });
        }
    }
}
