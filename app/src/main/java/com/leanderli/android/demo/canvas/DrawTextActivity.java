package com.leanderli.android.demo.canvas;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.leanderli.android.demo.R;

public class DrawTextActivity extends AppCompatActivity {

    ImageView textImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_text);

        textImg = findViewById(R.id.iv_text);
        System.out.println("坐标：top:{" + textImg.getTop() + "}," +
                "bottom:{" + textImg.getBottom() + "}," +
                "left:{" + textImg.getLeft() + "}," +
                "right:{" + textImg.getRight() + "}");
        int[] location = new int[2];
        textImg.getLocationInWindow(location);
        System.out.println(location[0] + "-" + location[1]);
    }
}
