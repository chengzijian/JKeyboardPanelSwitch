/*
 * Copyright (C) 2015-2017 Jacksgong(blog.dreamtobe.cn)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.dreamtobe.kpswitch.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import cn.dreamtobe.kpswitch.handler.KPSwitchRootLayoutHandler;
import cn.dreamtobe.kpswitch.util.StatusBarHeightUtil;
import cn.dreamtobe.kpswitch.util.ViewUtil;

/**
 * Created by Jacksgong on 3/30/16.
 * <p/>
 * To keep watch on the keyboard status before occur layout-conflict.
 * <p/>
 * This layout must be the root layout in your Activity. In other words, must be the
 * child of content view.
 * <p/>
 * Resolve the layout-conflict from switching the keyboard and the Panel.
 *
 */
public class KPSwitchRootRelativeLayoutFixed extends RelativeLayout {

    private KPSwitchRootLayoutHandler conflictHandler;
    private int mRealWidth;
    private int mRealHeight;

    public KPSwitchRootRelativeLayoutFixed(Context context) {
        super(context);
        init();
    }

    public KPSwitchRootRelativeLayoutFixed(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KPSwitchRootRelativeLayoutFixed(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public KPSwitchRootRelativeLayoutFixed(Context context, AttributeSet attrs, int defStyleAttr,
                                           int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        conflictHandler = new KPSwitchRootLayoutHandler(this);
        conflictHandler.setFixedScreen(true);
        mRealWidth = getContext().getResources().getDisplayMetrics().widthPixels;
        mRealHeight = getContext().getResources().getDisplayMetrics().heightPixels - StatusBarHeightUtil.getStatusBarHeight(getContext());
        if (ViewUtil.isTranslucentStatus((Activity) getContext())
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && getFitsSystemWindows()) {
            // In this case, the height is always the same one, so, we have to calculate below.
            final Rect rect = new Rect();
            getWindowVisibleDisplayFrame(rect);
            mRealHeight = rect.bottom - rect.top;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        conflictHandler.handleBeforeMeasure(MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(heightMeasureSpec));
        super.onMeasure(MeasureSpec.makeMeasureSpec(mRealWidth, MeasureSpec.EXACTLY)
                , MeasureSpec.makeMeasureSpec(mRealHeight, MeasureSpec.EXACTLY));
    }

}
