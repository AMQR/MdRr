package com.am.mdrr.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.am.mdrr.R;
import com.am.mdrr.entity.WidgetBean;

import java.util.List;

/**
 * Created by AM on 2016/7/16.
 */
public  class RecycRxJavaAdapter extends RecyclerView.Adapter<RecycRxJavaAdapter.WidgetHolder>{

    private Context mContext;
    private List<String> mDatas;

    public RecycRxJavaAdapter(Context mContext, List<String> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public WidgetHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WidgetHolder widgetHolder = new WidgetHolder(LayoutInflater.from(mContext).inflate(R.layout.item_md_widget,parent,false));
        return widgetHolder;
    }

    // 点击回调
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);  // 点击
        void onItemLongClick(View view, int position); // 长按
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    // =======  点击回调

    @Override
    public void onBindViewHolder(final WidgetHolder holder, final int position) {

        holder.mTvItemName.setText(mDatas.get(position));

        if(mOnItemClickLitener!= null){
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickLitener.onItemClick(holder.mCardView,position);
                }
            });

            holder.mCardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mOnItemClickLitener.onItemLongClick(holder.mCardView,position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    class WidgetHolder extends RecyclerView.ViewHolder{

        TextView mTvItemName;
        ImageView mIvPic;
        CardView mCardView;

        public WidgetHolder(View itemView) {
            super(itemView);
            mTvItemName = (TextView) itemView.findViewById(R.id.mTvItemName);
            mIvPic = (ImageView) itemView.findViewById(R.id.mIvPic);
            mCardView = (CardView) itemView.findViewById(R.id.mCardView);
        }
    }
}
