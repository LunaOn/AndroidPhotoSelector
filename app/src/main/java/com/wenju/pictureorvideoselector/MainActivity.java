package com.wenju.pictureorvideoselector;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

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
        PictureOrVideoSelectorManager.pictureSelector(this);
    }

    public void selectorSinglePicture(View view) {
        PictureOrVideoSelectorManager.pictureisSingleSelectSelector(this);
    }

    public void selectorDefaultPictureAndCrop(View view) {
        PictureOrVideoSelectorManager.pictureIsCropSelector(this,true,1,1);
    }

    public void selectorVideo(View view) {
        PictureOrVideoSelectorManager.VideoSelectSelector(this);
    }


}