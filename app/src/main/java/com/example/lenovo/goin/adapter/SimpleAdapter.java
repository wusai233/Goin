package com.example.lenovo.goin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.lenovo.wusaiGoin.R;

import java.util.List;

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
        itemViewHolder.clock_text.setText(mDatas.get(position) + "");
        itemViewHolder.img_btn.setOnClickListener(this);
        itemViewHolder.img_btn.setTag(mDatas.get(position));
        itemViewHolder.radio_btn_data.setOnClickListener(this);
        itemViewHolder.radio_btn_data.setTag(mDatas.get(position));
//        itemViewHolder.id_days.setText(mDatas.get(position) + "");
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView clock_text;
        private ToggleButton img_btn;
        private ToggleButton radio_btn_data;
        private TextView id_days;

        public MyViewHolder(View itemView) {
            super(itemView);
            clock_text = (TextView) itemView.findViewById(R.id.clock_text);
            img_btn = (ToggleButton) itemView.findViewById(R.id.img_btn);
            radio_btn_data = (ToggleButton) itemView.findViewById(R.id.radio_btn_data);
            id_days = (TextView) itemView.findViewById(R.id.id_days);
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

    // radioButton 切换状态
    public class GlobalValue {
        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        private boolean isCheck;
    }
}
