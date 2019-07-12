package com.leanderli.android.demo.common;

import android.view.View;

/**
 * @Description
 * @Author ls573
 * @Date 18.7.16
 */

public interface OnItemClickListener {

    void onItemClick(View view, int position);

    void onItemLongClick(View view, int position);
}
