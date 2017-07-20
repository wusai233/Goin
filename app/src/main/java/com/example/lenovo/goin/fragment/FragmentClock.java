package com.example.lenovo.goin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.lenovo.goin.adapter.SimpleAdapter;
import com.example.lenovo.wusaiGoin.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lenovo on 2017/7/14.
 */

public class FragmentClock extends Fragment implements View.OnClickListener {

    private Button id_add_clock;
    private Button id_delete_clock;

    private RecyclerView recyclerView;
    private List mData;
    private SimpleAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.page_alarm_clock, null);

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id_add_clock = (Button) view.findViewById(R.id.id_add_clock);
        id_delete_clock = (Button) view.findViewById(R.id.id_delete_clock);

        inData();

        recyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new SimpleAdapter(getContext(), mData);
        recyclerView.setAdapter(mAdapter);
    }


    private void inData() {
        mData = new ArrayList();
        for (int i = 0 ;i <5 ;i++){
            mData.add((char)i);
        }
    }

    @Override
    public void onClick(View v) {
    }
}
