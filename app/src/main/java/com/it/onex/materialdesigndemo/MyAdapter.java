package com.it.onex.materialdesigndemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.it.onex.materialdesigndemo.bean.Movie;

/**
 * Created by Linsa on 2017/12/21:17:18.
 * des:
 */

class MyAdapter extends RecyclerView.Adapter implements View.OnClickListener {


    private Movie mData;
    private Context mContext;

    public MyAdapter(Movie movie, Context context) {

        this.mData = movie;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //会出现item填充不满的bug
        View view2 = View.inflate(parent.getContext(), R.layout.item, null);


        View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        view.setOnClickListener(this);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder) {

            ((MyHolder) holder).tv_name.setText(mData.getSubjects().get(position).getTitle());
            ((MyHolder) holder).tv_star.setText(mData.getSubjects().get(position).getYear());
            ((MyHolder) holder).tv_year.setText(mData.getSubjects().get(position).getRating().getAverage() + "");
            ((MyHolder) holder).tv_avatars.setText(mData.getSubjects().get(position).getCasts().get(0).getName());
            Glide.with(mContext).load(mData.getSubjects().get(position).getImages().getMedium())
                    .centerCrop()
                    .crossFade(2000)
                    .into(((MyHolder) holder).iv_icon);

            holder.itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener!=null){
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {

        public TextView tv_name;
        public ImageView iv_icon;
        public TextView tv_year;
        public TextView tv_avatars;
        public TextView tv_star;


        public MyHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_avatars = itemView.findViewById(R.id.tv_avatars);
            tv_year = itemView.findViewById(R.id.tv_year);
            tv_star = itemView.findViewById(R.id.tv_star);
            iv_icon = itemView.findViewById(R.id.iv_icon);

        }
    }

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    private OnItemClickListener mOnItemClickListener = null;


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}

