package com.rongxianren.lsn25_animationframe_customview_2;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by wty on 2016/12/19.
 */

public class CustomLayoutInflater extends LayoutInflater {

    private BaseFragment baseFragment;

    protected CustomLayoutInflater(BaseFragment fragment, Context newContext) {
        super(newContext);
        this.baseFragment = fragment;
        this.setFactory(new CustomFactory(this));
    }

    @Override
    public LayoutInflater cloneInContext(Context context) {
        return new CustomLayoutInflater(baseFragment, context);
    }


    public class CustomFactory implements Factory {

        private LayoutInflater inflater;

        public CustomFactory(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            View result = null;
            System.out.println("---------onCreateView---------");

            String[] prefixArray = {"android.view.", "android.widget."};

            if (-1 == name.indexOf('.')) {////系统控件
                for (int i = 0; i < prefixArray.length; i++) {
                    result = createViewOrFialQuietly(name, prefixArray[i], attrs);
                    if (result != null) {
                        break;
                    }
                }
            } else {/////自定义控件
                result = createViewOrFialQuietly(name, null, attrs);
            }

            if (result != null) {
                getCustomAttrs(result, context, attrs);
            }
            return result;
        }


        private View createViewOrFialQuietly(String name, String prefix, AttributeSet attr) {
            View view;
            try {
                view = inflater.createView(name, prefix, attr);
            } catch (ClassNotFoundException e) {
                return null;
            }
            return view;
        }
    }


    private void getCustomAttrs(View view, Context context, AttributeSet attrSet) {
        int[] attrs = {
                R.attr.a_in,
                R.attr.a_out,
                R.attr.x_in,
                R.attr.x_out,
                R.attr.y_in,
                R.attr.y_out};
        ViewTagObj tag = new ViewTagObj();
        TypedArray typeArray = context.obtainStyledAttributes(attrSet, attrs);

        if (typeArray != null && typeArray.length() > 0) {
//            tag.a_in = typeArray.getFloat(typeArray.getIndex(0), 0);
//            tag.a_out = typeArray.getFloat(typeArray.getIndex(1), 0);
//            tag.x_in = typeArray.getFloat(typeArray.getIndex(2), 0);
//            tag.x_out = typeArray.getFloat(typeArray.getIndex(3), 0);
//            tag.y_in = typeArray.getFloat(typeArray.getIndex(4), 0);
//            tag.y_out = typeArray.getFloat(typeArray.getIndex(5), 0);
            tag.a_in = typeArray.getFloat(0, 0);
            tag.a_out = typeArray.getFloat(1, 0);
            tag.x_in = typeArray.getFloat(2, 0);
            tag.x_out = typeArray.getFloat(3, 0);
            tag.y_in = typeArray.getFloat(4, 0);
            tag.y_out = typeArray.getFloat(5, 0);
            view.setTag(R.id.view_attrs_tag, tag);
        }
        typeArray.recycle();

        System.out.println(tag);
        baseFragment.getViewList().add(view);

    }


}
