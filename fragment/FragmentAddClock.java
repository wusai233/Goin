package com.example.lenovo.goin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.wusaiGoin.R;

/**
 * Created by lenovo on 2017/7/18.
 */

public class FragmentAddClock extends Fragment implements View.OnClickListener{

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmeng_add_clock,null);




    }

    @Override
    public void onClick(View v) {

    }
}
