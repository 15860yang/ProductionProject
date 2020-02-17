package com.snowman.baselibrary.ioc.api;

import android.app.Activity;
import android.view.View;

/**
 * ViewByID的辅助类
 */
class ViewFinder {

    private Activity mActivity;
    private View mView;

    ViewFinder(View view) {
        this.mView = view;
    }

    ViewFinder(Activity activity) {
        this.mActivity = activity;
    }

    View findViewById(int viewId) {
        return mActivity != null ? mActivity.findViewById(viewId) : mView.findViewById(viewId);
    }
}
