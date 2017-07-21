package com.example.lenovo.goin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.example.lenovo.goin.adapter.SimpleAdapter;
import com.example.lenovo.wusaiGoin.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lenovo on 2017/7/14.
 */

public class FragmentClock extends Fragment {


    private Button id_add_clock;
    private Button id_delete_clock;

    private RecyclerView recyclerView;
    private List<Integer> mData;
    private SimpleAdapter mAdapter;
    private ChangeFragmentListener changeFragmentListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context != null) {
            changeFragmentListener = (ChangeFragmentListener) context;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id_add_clock = (Button) view.findViewById(R.id.id_add_clock);
        id_delete_clock = (Button) view.findViewById(R.id.id_delete_clock);

        id_add_clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragmentListener.changeFragment(new FragmentAddClock());
            }
        });
        id_delete_clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        inData();

        recyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new SimpleAdapter(getContext(), mData);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new SimpleAdapter.OnRecyclerViewItemClickListener() {
            public void onItemClick(View view, int data) {
              if (view instanceof ToggleButton){
                    switch (view.getId()){
                        case R.id.img_btn:
                            Toast.makeText(getContext(),"点击了开关",Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.radio_btn_data:
                            Toast.makeText(getContext(),"点击了点选",Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }
        });
    }

    private void inData() {
        mData = new ArrayList<Integer>();
        for (int i = 0 ;i <5 ;i++){
            mData.add(i);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.page_alarm_clock, null);

    }



    public interface ChangeFragmentListener {
        void changeFragment(Fragment newFragmentm);
    }
}