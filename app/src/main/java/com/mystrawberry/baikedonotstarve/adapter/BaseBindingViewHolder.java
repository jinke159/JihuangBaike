package com.mystrawberry.baikedonotstarve.adapter;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by jk on 2017/10/16.
 */

public class BaseBindingViewHolder<Binding extends ViewDataBinding> extends BaseViewHolder {
    private Binding mBinding;
    public BaseBindingViewHolder(View view) {
        super(view);
    }

    public Binding getBinding() {
        return mBinding;
    }

    public void setBinding(Binding binding) {
        mBinding = binding;
    }
}
