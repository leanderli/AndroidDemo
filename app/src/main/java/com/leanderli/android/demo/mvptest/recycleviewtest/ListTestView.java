package com.leanderli.android.demo.mvptest.recycleviewtest;

import com.leanderli.android.demo.mvptest.base.BaseView;
import com.leanderli.android.demo.mvptest.domain.User;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018-07-03.
 */

public interface ListTestView extends BaseView {

    void setAdapter(ArrayList<User> users);

    void notifyUpdateData();
}
