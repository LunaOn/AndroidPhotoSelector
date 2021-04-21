package com.wenju.pictureorvideoselector;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.luck.picture.lib.customused.MediaSelectManager;
import com.luck.picture.lib.customused.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.entity.LocalMediaFolder;
import com.luck.picture.lib.model.LocalMediaLoader;
import com.luck.picture.lib.thread.PictureThreadUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "测试";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case MediaSelectManager.CHOOSE_REQUEST:
                    // 结果回调
                    List<LocalMedia> selectList = new ArrayList<>(MediaSelectManager.obtainMultipleResult(data));
                    if (selectList == null) {
                        Log.d(TAG, "获取选择图片数据失败=.=");
                    } else {
                        Log.d(TAG, "获取选择图片数据成功^-^");
                        for (int i = 0; i < selectList.size(); i++) {
                            Log.d(TAG,"item："+ i+" name: "+ selectList.get(0).getFileName());
                        }
                    }

                    break;
                default:
                    break;
            }
        }
    }

    public void selectorDefaultPicture(View view) {
        new MediaSelectManager()
                .create(this);
    }

    public void selectorSinglePicture(View view) {
        new MediaSelectManager()
                .setSingleselect(true)
                .create(this);
    }

    public void selectorVideo(View view) {
        new MediaSelectManager()
                .setType(PictureMimeType.TYPE_VIDEO)
                .create(this);
    }


    public void getPictureList(View view) {
        PictureThreadUtils.executeByIo(new PictureThreadUtils.SimpleTask<List<LocalMediaFolder>>() {
            @Override
            public List<LocalMediaFolder> doInBackground() {
                return new LocalMediaLoader(MainActivity.this).loadAllMedia();
            }

            @Override
            public void onSuccess(List<LocalMediaFolder> folders) {
                Log.d("获取图片数据列表", folders.get(1).getFirstImagePath());
            }
        });
    }
}