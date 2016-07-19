package com.am.mdrr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.am.mdrr.R;
import com.am.mdrr.entity.ContentlistBean;

import java.util.List;

/**
 * Created by AM on 2016/7/19.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private Context context;
    private List<ContentlistBean> mDatas;


    public RecyclerViewAdapter(Context context, List<ContentlistBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mDatas.size() == 0 ? 0 : mDatas.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {


            View view = LayoutInflater.from(context).inflate(R.layout.item_base, parent,
                    false);
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_foot, parent,
                    false);
            return new FootViewHolder(view);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, position);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemLongClick(holder.itemView, position);
                        return false;
                    }
                });
            }

            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            ContentlistBean contentlistBean = mDatas.get(position);
            itemViewHolder.mTvTitle.setText(contentlistBean.getTitle());
            itemViewHolder.mTvDesc.setText(contentlistBean.getText());
            itemViewHolder.mTvTime.setText(contentlistBean.getCt());

        }
    }


    static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView mTvTitle;
        TextView mTvDesc;
        TextView mTvTime;

        public ItemViewHolder(View view) {
            super(view);
            mTvTitle = (TextView) view.findViewById(R.id.mTvTitle);
            mTvDesc = (TextView) view.findViewById(R.id.mTvDesc);
            mTvTime = (TextView) view.findViewById(R.id.mTvTime);
        }
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View view) {
            super(view);
        }
    }
}