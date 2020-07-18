package com.leanderli.android.demo.file;

import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.leanderli.android.demo.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FileTestActivity extends AppCompatActivity {

    private static final String TAG = "FileTestActivity";

    private Button mShowFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_test);
        mShowFilePath = findViewById(R.id.showFilePath);
        mShowFilePath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        printFilePath();
                    }
                }).start();
            }
        });
    }

    private void printFilePath() {
        String rootPath = Environment.getExternalStorageDirectory().getPath();
        Log.i(TAG, "onCreate: rootPath:[" + rootPath + "]");
        File folder = new File(rootPath + "/tencent/MicroMsg/");
        if (folder.exists() && folder.isDirectory()) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2018, 12 - 1, 16, 21, 36);
            Date startDate = calendar.getTime();
            calendar.set(2018, 12 - 1, 16, 21, 50);
            Date endDate = calendar.getTime();
            List<File> files = new ArrayList<>();
            files = getAllFiles(folder, files);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Log.i(TAG, "onClick: startDate:[" + dateFormat.format(startDate) + "]," +
                    "endDate:[" + dateFormat.format(endDate) + "]");
            for (File file : files) {
                Date lastModifiedDate = new Date(file.lastModified());
                if (lastModifiedDate.before(endDate) && lastModifiedDate.after(startDate)) {
                    Log.i(TAG, "onCreate: filePath: " + file.getPath());
//                    System.out.println(file.getPath());
                }
            }
        }
    }

    private List<File> getAllFiles(File folder, List<File> fileList) {
        File[] files = folder.listFiles();
        if (null != files && files.length > 0) {
            for (File file : files) {
                if (file.exists() && file.isFile()) {
                    fileList.add(file);
                } else {
                    getAllFiles(file, fileList);
                }
            }
        }
        return fileList;
    }

}
