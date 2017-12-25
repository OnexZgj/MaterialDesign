package com.it.onex.materialdesigndemo.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Linsa on 2017/12/25:14:48.
 * des:
 */

public class MoviesFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        TextView tv=new TextView(getActivity());
        tv.setTextColor(Color.RED);
        tv.setText("wo shi tian kong ！");

        return tv;
    }



}
