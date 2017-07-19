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

public class SimpleAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private LayoutInflater mInflater;
    private Context mContext;
    private List mDatas;

    public SimpleAdapter(Context context, List datas) {
        this.mContext = context;
        this.mDatas = datas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        holder.tv.setText(mDatas.get(position));
//        holder.tv.setText(mDatas.get(position));
//        holder.tv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}


class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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

        img_btn.setOnClickListener(this);
        radio_btn_data.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch ((int) getItemId()) {
            case R.id.radio_btn_data:
                if (radio_btn_data.isChecked() == true) {
                    radio_btn_data.setChecked(false);
                } else {
                    radio_btn_data.setChecked(true);
                }
                break;
            case R.id.img_btn:
                if (flag == true) {
                    img_btn.setImageResource(R.drawable.img_btn_false);
                    flag = false;
                } else {
                    img_btn.setImageResource(R.drawable.img_btn_ture);
                    flag = true;
                }
                break;
        }
    }
}
