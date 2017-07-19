package com.example.lenovo.goin;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import com.example.lenovo.goin.fragment.FragmentClock;
import com.example.lenovo.goin.fragment.FragmengCollect;
import com.example.lenovo.goin.fragment.FragmentInquire;
import com.example.lenovo.wusaiGoin.R;

public class MainActivity extends AppCompatActivity {
    private MainActivity mySelf = this;

    private RadioGroup tab_menu;
    Fragment inquire;
    FragmengCollect collect;
    FragmentClock aiarm_clock;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tab_menu = (RadioGroup) findViewById(R.id.tab_menu);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_content,new FragmentClock()).commit();

        initView();
    }


    public void initView() {
        inquire = new FragmentClock();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, inquire).commit();
        tab_menu = (RadioGroup) findViewById(R.id.tab_menu);
        tab_menu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                switch (checkedId) {
                    case R.id.inquire:
                        inquire = new FragmentInquire();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, inquire).commit();
                        break;
                    case R.id.alarm_clock:
                        if (aiarm_clock == null) {
                            aiarm_clock = new FragmentClock();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, aiarm_clock).commit();
                        break;
                    case R.id.collect:
                        collect = new FragmengCollect();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, collect).commit();
                        break;
                    default:
                        break;
                }

            }
        });
    }
}
