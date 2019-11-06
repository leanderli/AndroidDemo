package com.leanderli.android.demo.layout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.leanderli.android.demo.R;

import static com.leanderli.android.demo.common.util.DensityUtils.dip2px;

public class FlowLayoutTestActivity extends AppCompatActivity implements View.OnClickListener {

    private FlowLayout mFlow;
    private ImageButton mPullDown;
    private boolean isPull = false;

    String[] texts = new String[]{
            "good", "bad", "understand", "it is a good day !",
            "how are you", "ok", "fine", "name", "momo",
            "lankton", "lan", "flowlayout demo", "soso"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout_test);

        mFlow = findViewById(R.id.flowlayout);
        mPullDown = findViewById(R.id.pull_down);
        mPullDown.setOnClickListener(this);
        addAll();
        mFlow.specifyLines(1);
        mFlow.relayoutToCompressAndAlign();

    }

    @Override
    public void onClick(View view) {
        if (view.getTag() instanceof Tag) {
            TextView textView = (TextView) view;
            Toast.makeText(this, textView.getText(), Toast.LENGTH_SHORT).show();
        } else {
            switch (view.getId()) {
                case R.id.pull_down:
                    if (isPull) {
                        mFlow.specifyLines(1);
                        isPull = false;
                    } else {
                        mFlow.removeAllViews();
                        addAll();
                        isPull = true;
                    }
                    break;
            }
        }

    }

    public void addAll() {
        int ranHeight = dip2px(this, 30);
        for (String text : texts) {
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ranHeight);
            lp.setMargins(dip2px(this, 10), 0, dip2px(this, 10), 0);
            TextView tv = new TextView(this);
            tv.setPadding(dip2px(this, 15), 0, dip2px(this, 15), 0);
            tv.setTextColor(Color.WHITE);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            tv.setText(text);
            tv.setGravity(Gravity.CENTER_VERTICAL);
            tv.setLines(1);
            tv.setOnClickListener(this);
            tv.setBackgroundResource(R.drawable.flow_tag_background);
            tv.setTag(new Tag());
            mFlow.addView(tv, lp);
        }
    }

    public class Tag {

    }
}
