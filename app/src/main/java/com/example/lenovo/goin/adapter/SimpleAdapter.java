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

import com.example.lenovo.wusaiGoin.R;

import java.util.List;

/**
 * Created by lenovo on 2017/7/18.
 */

public class SimpleAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<Integer> mDatas;

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
//        MyViewHolder itemViewHolder = (MyViewHolder) holder;
//        itemViewHolder.clock_text.setText(mDatas.get(position)+"");
//        itemViewHolder.img_btn.setOnClickListener((View.OnClickListener) this);
//        itemViewHolder.radio_btn_data.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}


class MyViewHolder extends RecyclerView.ViewHolder {
    LayoutInflater mInflater;
    TextView clock_text;
    ImageView img_btn;
    RadioButton radio_btn_data;
    boolean flag = true;
    boolean radioFlag = false;
    final GlobalValue globalValue = new GlobalValue();

    public MyViewHolder(View itemView) {
        super(itemView);
        clock_text = (TextView) itemView.findViewById(R.id.clock_text);
        img_btn = (ImageView) itemView.findViewById(R.id.img_btn);
        radio_btn_data = (RadioButton) itemView.findViewById(R.id.radio_btn_data);

        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flag == true) {
                    img_btn.setImageResource(R.drawable.img_btn_false);
                    flag = false;
                } else {
                    img_btn.setImageResource(R.drawable.img_btn_ture);
                    flag = true;
                }
                Log.e("aaaa", "--------------" + 2 + "--------------");
            }
        });
        radio_btn_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCheck = globalValue.isCheck();
                if(isCheck)
                {
                    if(v==radio_btn_data)radio_btn_data.setChecked(false);
                }
                else
                {
                    if(v==radio_btn_data)radio_btn_data.setChecked(true);
                }
                globalValue.setCheck(!isCheck);
                Log.e("aaaa", "--------------" + 1 + "--------------");
            }
        });
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
