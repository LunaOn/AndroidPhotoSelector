package com.wenju.pictureorvideoselector;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.luck.picture.lib.customused.PictureMimeType;
import com.luck.picture.lib.customused.PictureOrVideoSelectorManager;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.entity.LocalMediaFolder;
import com.luck.picture.lib.model.LocalMediaLoader;
import com.luck.picture.lib.thread.PictureThreadUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
                case PictureOrVideoSelectorManager.CHOOSE_REQUEST:
                    // 结果回调
                    List<LocalMedia> selectList = new ArrayList<>(PictureOrVideoSelectorManager.obtainMultipleResult(data));
                    Log.d("获取选择图片数据成功----》",selectList.get(0).getRealPath());
                    break;
                default:
                    break;
            }
        }
    }

    public void selectorDefaultPicture(View view) {
        new PictureOrVideoSelectorManager(this)
                .create();
    }

    public void selectorSinglePicture(View view) {
        new PictureOrVideoSelectorManager(this)
                .setSingleselect(true)
                .create();
    }

    public void selectorVideo(View view) {
        new PictureOrVideoSelectorManager(this)
                .setType(PictureMimeType.TYPE_VIDEO)
                .create();
    }


    public void getPictureList(View view) {
        PictureThreadUtils.executeByIo(new PictureThreadUtils.SimpleTask<List<LocalMediaFolder>>() {
            @Override
            public List<LocalMediaFolder> doInBackground() {
                return new LocalMediaLoader(MainActivity.this).loadAllMedia();
            }

            @Override
            public void onSuccess(List<LocalMediaFolder> folders) {
                Log.d("获取图片数据列表",folders.get(1).getFirstImagePath());
            }
        });
    }
}