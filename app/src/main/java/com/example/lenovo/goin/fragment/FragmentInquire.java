package com.example.lenovo.goin.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.lenovo.wusaiGoin.R;
/**
 * Created by lenovo on 2017/7/14.
 */

public class FragmentInquire extends Fragment   implements BDLocationListener {
    // 百度地图控件
    private MapView mMapView = null;
    // 百度地图对象
    private BaiduMap mBaiduMap;
    //按钮 定位当前位置
    private Button request;
    private View rootView;
    public LocationClient mLocationClient = null;
    // 地图标注
    private BitmapDescriptor mCurrentMarker;
    private MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getActivity().getApplicationContext());
        rootView = inflater.inflate(R.layout.page_inquire, null);
        initView();
        return rootView;
    }

    private void initView() {
        mMapView = (MapView) rootView.findViewById(R.id.bmapview);
        request = (Button) rootView.findViewById(R.id.request);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        mLocationClient = new LocationClient(getActivity().getApplicationContext());
        mLocationClient.registerLocationListener( this );
        initSelfPosition();
        mLocationClient.start();
    }

    private void initSelfPosition() {

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系
        int span=1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps
        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    .latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude())
                    .direction(100)
                    .build();
            // 设置定位数据
            mBaiduMap.setMyLocationData(locData);
            // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
            mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.icon_gcoding);
            MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);
            mBaiduMap.setMyLocationConfiguration(config);

            //=====自定义标注及动态移动到当前位置到屏幕中间=====
            //定义Maker坐标点
            LatLng point = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
            //构建Marker图标
            mCurrentMarker = BitmapDescriptorFactory
                    .fromResource(R.drawable.img_btn_false);
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(mCurrentMarker);
            //在地图上添加Marker，并显示
            mBaiduMap.addOverlay(option);
            //=====当前位置移动到屏幕中间代码
            MapStatusUpdate u = MapStatusUpdateFactory
                    .newLatLng(point);
            /*u = MapStatusUpdateFactory.newLatLngZoom(point,
                    mBaiduMap.getMaxZoomLevel());*/
            mBaiduMap.animateMapStatus(u);
            mBaiduMap.setMapStatus(u);
            //=====当前位置移动到屏幕中间代码
            //=====自定义标注及动态移动到当前位置到屏幕中间=====

            // 当不需要定位图层时关闭定位图层
//            mBaiduMap.setMyLocationEnabled(false);
        }
        @Override
        public void onConnectHotSpotMessage(String s, int i) {
        }
}
