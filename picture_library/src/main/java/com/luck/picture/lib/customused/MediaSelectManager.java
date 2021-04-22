package com.luck.picture.lib.customused;

import android.app.Activity;
import android.content.Intent;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.language.LanguageConfig;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;

/**
 * Author: chuanmo
 * Date: 4/16/21 1:22 PM
 * Description: 图片视频选择器
 */
public class MediaSelectManager {
    public final static int CHOOSE_REQUEST = 188;
    private int type = PictureMimeType.TYPE_IMAGE;
    private int maxSelectNum = 8;
    private boolean isSingleselect = false;
    private boolean isGif = false;
    private List<LocalMedia> oldList = new ArrayList<>();
    private int requestCode = CHOOSE_REQUEST;


    public void create(Fragment fragment) {
        pictureSelector(PictureSelector.create(fragment), type, maxSelectNum, isSingleselect, isGif, oldList, requestCode);
    }

    public void create(Activity activity) {
        pictureSelector(PictureSelector.create(activity), type, maxSelectNum, isSingleselect, isGif, oldList, requestCode);
    }

    /**
     * @param type 查看类型，全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
     * @param maxSelectNum 最大图片选择数量
     * @param isSingleselect 单个图片选择
     * @param isGif 是否支持Gif
     * @param oldList 传入选中的图片
     * @param requestCode
     */
    public static void pictureSelector(PictureSelector pictureSelector, int type, int maxSelectNum,
                                       boolean isSingleselect, boolean isGif, List<LocalMedia> oldList, int requestCode) {
        pictureSelector
                .openGallery(type)
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .isWeChatStyle(true)// 是否开启微信图片选择风格
                .setLanguage(LanguageConfig.ENGLISH)// 设置语言，默认中文
                .isMaxSelectEnabledMask(true)// 选择数到了最大阀值列表是否启用蒙层效果
                .maxSelectNum(maxSelectNum)
                .minSelectNum(1)
                .maxVideoSelectNum(1)// 视频最大选择数量
                .imageSpanCount(4)// 每行显示个数
                .isReturnEmpty(false)// 未选择数据时点击按钮是否可以返回
                .selectionMode(isSingleselect ?
                        PictureConfig.SINGLE : PictureConfig.MULTIPLE)
                .isSingleDirectReturn(true)// 单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
                .isCamera(true)// 是否显示拍照按钮
                .isGif(isGif)
                .selectionData(oldList)// 是否传入已选图片
                .enableNumCheckMode()
                .forResult(requestCode);
    }


    /**
     * 微信样式
     * @return
     */
//    public static PictureSelectorUIStyle wechatStyle(int drawableTopCompleteDefaultBtnBackground, int drawableTopCompleteNormalBtnBackground) {
//        PictureSelectorUIStyle uiStyle = new PictureSelectorUIStyle();
//        uiStyle.isNewSelectStyle = true;
//        uiStyle.picture_statusBarBackgroundColor = Color.parseColor("#393a3e");
//        uiStyle.picture_container_backgroundColor = Color.parseColor("#000000");
//        uiStyle.picture_switchSelectNumberStyle = true;
//        uiStyle.picture_navBarColor = Color.parseColor("#393a3e");
//        uiStyle.picture_check_style = R.drawable.picture_wechat_num_selector;
//        uiStyle.picture_top_leftBack = R.drawable.picture_icon_back;
//        uiStyle.picture_top_titleRightTextColor = new int[]{Color.parseColor("#53575e"), Color.parseColor("#FFFFFF")};
//        uiStyle.picture_top_titleRightTextSize = 14;
//        uiStyle.picture_top_titleTextSize = 16;
//        uiStyle.picture_top_titleArrowUpDrawable = R.drawable.picture_icon_arrow_up;
//        uiStyle.picture_top_titleArrowDownDrawable = R.drawable.picture_icon_arrow_down;
//        uiStyle.picture_top_titleTextColor = Color.parseColor("#FFFFFF");
//        uiStyle.picture_top_titleBarBackgroundColor = Color.parseColor("#393a3e");
//        uiStyle.picture_top_titleAlbumBackground = R.drawable.picture_album_bg;
//
//        uiStyle.picture_album_textSize = 16;
//        uiStyle.picture_album_backgroundStyle = R.drawable.picture_item_select_bg;
//        uiStyle.picture_album_textColor = Color.parseColor("#4d4d4d");
//        uiStyle.picture_album_checkDotStyle = R.drawable.picture_orange_oval;
//
//        uiStyle.picture_adapter_item_camera_backgroundColor = Color.parseColor("#999999");
//        uiStyle.picture_adapter_item_camera_textColor = Color.parseColor("#FFFFFF");
//        uiStyle.picture_adapter_item_camera_textSize = 14;
//        uiStyle.picture_adapter_item_camera_textTopDrawable = R.drawable.picture_icon_camera;
//
//        uiStyle.picture_adapter_item_textSize = 12;
//        uiStyle.picture_adapter_item_textColor = Color.parseColor("#FFFFFF");
//        uiStyle.picture_adapter_item_video_textLeftDrawable = R.drawable.picture_icon_video;
//        uiStyle.picture_adapter_item_audio_textLeftDrawable = R.drawable.picture_icon_audio;
//
//        uiStyle.picture_top_titleRightTextDefaultBackground = drawableTopCompleteDefaultBtnBackground;
//        uiStyle.picture_top_titleRightTextNormalBackground = drawableTopCompleteNormalBtnBackground;
//        Context appContext = PictureAppMaster.getInstance().getAppContext();
//        if (appContext != null) {
//            uiStyle.picture_top_titleBarHeight = ScreenUtils.dip2px(appContext, 48);
//            uiStyle.picture_top_titleRightDefaultText = R.string.picture_send;
//            uiStyle.picture_top_titleRightNormalText = R.string.picture_cancel;
//            uiStyle.picture_adapter_item_camera_text = R.string.picture_take_picture;
//            uiStyle.picture_bottom_selectedText = R.string.picture_select;
//            uiStyle.picture_bottom_selectedCheckStyle = R.drawable.picture_wechat_select_cb;
//            // 如果文本内容设置(%1$d/%2$d)，请开启true
//            uiStyle.isCompleteReplaceNum = true;
////            uiStyle.picture_top_titleArrowLeftPadding = ScreenUtils.dip2px(appContext, 3);
////            uiStyle.picture_bottom_selectedTextColor = Color.parseColor("#FFFFFF");
////            uiStyle.picture_bottom_selectedTextSize = 16;
////            uiStyle.picture_bottom_gallery_height = ScreenUtils.dip2px(appContext, 80);
////            uiStyle.picture_bottom_gallery_backgroundColor = Color.parseColor("#a0393a3e");
////            uiStyle.picture_bottom_gallery_frameBackground = R.drawable.picture_preview_gallery_border_bg;
//        }
//
//        return uiStyle;
//    }

    /**
     * @param data
     * @return Selector Multiple LocalMedia
     */
    public static List<LocalMedia> obtainMultipleResult(Intent data) {
        ArrayList<LocalMedia> result = data.getParcelableArrayListExtra(PictureConfig.EXTRA_RESULT_SELECTION);
        return result == null ? new ArrayList<>() : result;
    }


    public int getType() {
        return type;
    }

    public MediaSelectManager setType(int type) {
        this.type = type;
        return this;
    }

    public int getMaxSelectNum() {
        return maxSelectNum;
    }

    public MediaSelectManager setMaxSelectNum(int maxSelectNum) {
        this.maxSelectNum = maxSelectNum;
        return this;
    }

    public boolean isSingleselect() {
        return isSingleselect;
    }

    public MediaSelectManager setSingleselect(boolean singleselect) {
        isSingleselect = singleselect;
        return this;
    }


    public boolean isGif() {
        return isGif;
    }

    public MediaSelectManager setGif(boolean gif) {
        isGif = gif;
        return this;
    }

    public List<LocalMedia> getOldList() {
        return oldList;
    }

    public MediaSelectManager setOldList(List<LocalMedia> oldList) {
        this.oldList = oldList;
        return this;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public MediaSelectManager setRequestCode(int requestCode) {
        this.requestCode = requestCode;
        return this;
    }

}
