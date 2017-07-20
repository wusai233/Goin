package com.example.lenovo.goin.adapter;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.lenovo.wusaiGoin.R;

import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by lenovo on 2017/7/18.
 */

public class SimpleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context mContext;
    private List<Integer> mDatas;

    public SimpleAdapter(Context context, List datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        ViewGroup vItem = (ViewGroup) mInflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(vItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder itemViewHolder = (MyViewHolder) holder;
        itemViewHolder.clock_text.setText(mDatas.get(position)+"");
        itemViewHolder.img_btn.setOnClickListener(this);
        itemViewHolder.radio_btn_data.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private LayoutInflater mInflater;
        private TextView clock_text;
        private ImageView img_btn;
        private RadioButton radio_btn_data;
        boolean flag = true;

        public MyViewHolder(View itemView) {
            super(itemView);
            clock_text = (TextView) itemView.findViewById(R.id.clock_text);
            img_btn = (ImageView) itemView.findViewById(R.id.img_btn);
            radio_btn_data = (RadioButton) itemView.findViewById(R.id.radio_btn_data);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int data);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
