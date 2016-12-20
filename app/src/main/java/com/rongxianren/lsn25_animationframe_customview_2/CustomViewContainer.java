package com.rongxianren.lsn25_animationframe_customview_2;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.view.ViewPager.SCROLL_STATE_DRAGGING;
import static android.support.v4.view.ViewPager.SCROLL_STATE_IDLE;

/**
 * Created by wty on 2016/12/19.
 */

public class CustomViewContainer extends FrameLayout implements ViewPager.OnPageChangeListener {

    private List<BaseFragment> fragmentList = new ArrayList<>();
    private ImageView iv_man;

    public CustomViewContainer(Context context) {
        this(context, null);
    }

    public CustomViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setUp(int[] layouts) {
        ViewPager viewPager = new ViewPager(getContext());
        viewPager.setId(R.id.viewpager_id);

        for (int i = 0; i < layouts.length; i++) {
            BaseFragment fragment = new BaseFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(BaseFragment.LAYOUT, layouts[i]);
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }
        MyAdapter adapter = new MyAdapter(((FragmentActivity) getContext()).getSupportFragmentManager(), fragmentList);

        ViewGroup.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        viewPager.setLayoutParams(params);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        this.addView(viewPager, 0);/////添加在最底层
    }

    public void setIv_man(ImageView iv_man) {
        this.iv_man = iv_man;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int containerWidth = this.getWidth();
        System.out.println("-------positionOffsetPixels------ = " + positionOffsetPixels);
        BaseFragment outFragment = null;
        BaseFragment inFragment = null;
        try {
            outFragment = fragmentList.get(position);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            inFragment = fragmentList.get(position - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (outFragment != null) {
            List<View> viewList = outFragment.getViewList();
            for (View view : viewList) {
                ViewTagObj tag = (ViewTagObj) view.getTag(R.id.view_attrs_tag);
                if (tag != null) {
                    ViewCompat.setTranslationX(view, -(positionOffsetPixels) * tag.x_out);
                    ViewCompat.setTranslationY(view, -(positionOffsetPixels) * tag.y_out);
//                    ViewCompat.setAlpha(view, 1 - positionOffset);
                }
            }
        }


        if (inFragment != null) {
            List<View> viewList = inFragment.getViewList();
            for (View view : viewList) {
                ViewTagObj tag = (ViewTagObj) view.getTag(R.id.view_attrs_tag);
                if (tag != null) {
                    ViewCompat.setTranslationX(view, (containerWidth - positionOffsetPixels) * tag.x_in);
                    ViewCompat.setTranslationY(view, (positionOffsetPixels - positionOffsetPixels) * tag.y_in);
//                    ViewCompat.setAlpha(view, positionOffset);
                }
            }
        }

    }

    @Override
    public void onPageSelected(int position) {
        if (position == fragmentList.size() - 1) {
            iv_man.setVisibility(GONE);
        } else {
            iv_man.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == SCROLL_STATE_DRAGGING) {
            ((AnimationDrawable) iv_man.getDrawable()).start();
        }
        if (state == SCROLL_STATE_IDLE) {
            ((AnimationDrawable) iv_man.getDrawable()).stop();
        }
    }

    public class MyAdapter extends FragmentPagerAdapter {

        List<BaseFragment> fragments = new ArrayList<>();

        public MyAdapter(FragmentManager fm, List<BaseFragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return null == fragments ? 0 : fragments.size();
        }
    }
}

