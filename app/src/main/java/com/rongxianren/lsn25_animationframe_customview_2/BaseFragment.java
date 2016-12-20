package com.rongxianren.lsn25_animationframe_customview_2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wty on 2016/12/19.
 */

public class BaseFragment extends Fragment {

    public static final String LAYOUT = "layout";
    private List<View> viewList = new ArrayList<>();

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CustomLayoutInflater customLayoutInflater = new CustomLayoutInflater(this, getContext());
        Bundle bundle = getArguments();
        int layout = 0;
        if (null != bundle) {
            layout = bundle.getInt(LAYOUT);
        }
        View view = customLayoutInflater.inflate(layout, null);
        return view;
    }

    public List<View> getViewList() {
        return viewList;
    }
}
