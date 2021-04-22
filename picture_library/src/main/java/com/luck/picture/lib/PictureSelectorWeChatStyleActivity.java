package com.luck.picture.lib;


import android.view.View;
import android.widget.RelativeLayout;

import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;
import java.util.Locale;

/**
 * @author：luck
 * @date：2019-11-30 13:27
 * @describe：PictureSelector WeChatStyle
 */
public class PictureSelectorWeChatStyleActivity extends PictureSelectorActivity {
    private RelativeLayout rlAlbum;

    @Override
    public int getResourceId() {
        return R.layout.picture_wechat_style_selector;
    }

    @Override
    protected void initWidgets() {
        super.initWidgets();
        rlAlbum = findViewById(R.id.rlAlbum);
        mTvPictureRight.setOnClickListener(this);
        mTvPictureRight.setText(getString(R.string.picture_send));
        boolean isChooseMode = config.selectionMode == PictureConfig.SINGLE && config.isSingleDirectReturn;
        mTvPictureRight.setVisibility(isChooseMode ? View.GONE : View.VISIBLE);
        mTvPictureRight.setOnClickListener(this);
    }

    @Override
    public void initPictureSelectorStyle() {
        // 右侧的按钮文字颜色
//        int[] topRightTextColors = {Color.parseColor("#53575e"), Color.parseColor("#FFFFFF")};
//        mTvPictureRight.setTextColor(AttrsUtils.getColorStateList(topRightTextColors));
//        if (PictureSelectionConfig.uiStyle != null) {
//            if (PictureSelectionConfig.uiStyle.picture_top_titleRightTextDefaultBackground != 0) {
//                mTvPictureRight.setBackgroundResource(PictureSelectionConfig.uiStyle.picture_top_titleRightTextDefaultBackground);
//            } else {
//                mTvPictureRight.setBackgroundResource(R.drawable.picture_send_button_default_bg);
//            }
//            if (PictureSelectionConfig.uiStyle.picture_top_titleRightTextColor.length > 0) {
//                ColorStateList colorStateList = AttrsUtils.getColorStateList(PictureSelectionConfig.uiStyle.picture_top_titleRightTextColor);
//                if (colorStateList != null) {
//                    mTvPictureRight.setTextColor(colorStateList);
//                }
//            } else {
//                mTvPictureRight.setTextColor(ContextCompat.getColor(getContext(), R.color.picture_color_53575e));
//            }
//            if (PictureSelectionConfig.uiStyle.picture_top_titleRightTextSize != 0) {
//                mTvPictureRight.setTextSize(PictureSelectionConfig.uiStyle.picture_top_titleRightTextSize);
//            }

//            if (PictureSelectionConfig.uiStyle.picture_container_backgroundColor != 0) {
//                container.setBackgroundColor(PictureSelectionConfig.uiStyle.picture_container_backgroundColor);
//            }
//            if (PictureSelectionConfig.uiStyle.picture_top_titleAlbumBackground != 0) {
//                rlAlbum.setBackgroundResource(PictureSelectionConfig.uiStyle.picture_top_titleAlbumBackground);
//            } else {
//                rlAlbum.setBackgroundResource(R.drawable.picture_album_bg);
//            }
//            if (PictureSelectionConfig.uiStyle.picture_top_titleRightDefaultText != 0) {
//                mTvPictureRight.setText(getString(PictureSelectionConfig.uiStyle.picture_top_titleRightDefaultText));
//            }

//        } /*else {
//            mTvPictureRight.setBackgroundResource(R.drawable.picture_send_button_default_bg);
//            rlAlbum.setBackgroundResource(R.drawable.picture_album_bg);
//            mTvPictureRight.setTextColor(ContextCompat.getColor(getContext(), R.color.picture_color_53575e));
//
//            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.picture_icon_wechat_down);
//            mIvArrow.setImageDrawable(drawable);
//        }*/
        super.initPictureSelectorStyle();
    }


    @Override
    protected void changeImageNumber(List<LocalMedia> selectData) {
        int size = selectData.size();
        boolean enable = size > 0;
        if (enable) {
            mTvPictureRight.setEnabled(true);
            mTvPictureRight.setSelected(true);
            mTvPictureRight.setBackgroundResource(R.drawable.picture_send_button_bg);
            initCompleteText(selectData);
        } else {
            mTvPictureRight.setEnabled(false);
            mTvPictureRight.setSelected(false);
            mTvPictureRight.setBackgroundResource(R.drawable.picture_send_button_default_bg);
            mTvPictureRight.setText(getString(R.string.picture_send));
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.picture_right) {
            if (folderWindow != null
                    && folderWindow.isShowing()) {
                folderWindow.dismiss();
            } else {
                onComplete();
            }
        } else {
            super.onClick(v);
        }
    }

    @Override
    protected void onChangeData(List<LocalMedia> list) {
        super.onChangeData(list);
        initCompleteText(list);
    }

    @Override
    protected void initCompleteText(List<LocalMedia> list) {
        int size = list.size();
        if (config.selectionMode == PictureConfig.SINGLE) {
        } else {
            String message = String.format(Locale.getDefault(), getString(R.string.picture_send_num), size, config.maxSelectNum);
            mTvPictureRight.setText(message);
        }


    }
}
