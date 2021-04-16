package com.wenju.pictureorvideoselector;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.app.PictureAppMaster;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.language.LanguageConfig;
import com.luck.picture.lib.style.PictureSelectorUIStyle;
import com.luck.picture.lib.tools.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: chuanmo
 * Date: 4/16/21 1:22 PM
 * Description: 图片视频选择器
 */
public class PictureOrVideoSelectorManager {

    /**
     * @param type 查看类型，全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
     * @param maxSelectNum 最大图片选择数量
     * @param isSingleselect 单个图片选择
     * @param isCrop 是否裁剪图片
     * @param aspectRatioX  // 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
     * @param aspectatioY  // 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
     * @param isGif 是否支持Gif
     * @param oldList 传入选中的图片
     */
    public static void pictureSelector(Context context,int type,int maxSelectNum,int drawableTopCompleteDefaultBtnBackground,int drawableTopCompleteNormalBtnBackground,boolean isSingleselect,boolean isCrop,int aspectRatioX,int aspectatioY,boolean isGif,List<LocalMedia> oldList) {
        PictureSelector.create((Activity) context)
                .openGallery(type)
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .setPictureUIStyle(ofNewStyle(drawableTopCompleteDefaultBtnBackground,drawableTopCompleteNormalBtnBackground))
                .isWeChatStyle(true)// 是否开启微信图片选择风格
                .setLanguage(LanguageConfig.ENGLISH)// 设置语言，默认中文
                .isMaxSelectEnabledMask(true)// 选择数到了最大阀值列表是否启用蒙层效果
                .maxSelectNum(maxSelectNum)
                .minSelectNum(1)
                .maxVideoSelectNum(1)// 视频最大选择数量
                .imageSpanCount(4)// 每行显示个数
                .isReturnEmpty(false)// 未选择数据时点击按钮是否可以返回
                .selectionMode(isSingleselect?
                        PictureConfig.SINGLE:PictureConfig.MULTIPLE)
                .isSingleDirectReturn(true)// 单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
                .isPreviewImage(true)// 是否可预览图片
                .isPreviewVideo(true)// 是否可预览视频
                .isCamera(true)// 是否显示拍照按钮
                .isEnableCrop(isCrop)// 是否裁剪
                .withAspectRatio(aspectRatioX, aspectatioY)
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .isCompress(false)// 是否压缩
                .isGif(isGif)
                .selectionData(getData(oldList))// 是否传入已选图片
                .isPreviewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    public static void pictureSelector(Context context){
        pictureSelector(context,PictureMimeType.ofImage(),com.luck.picture.lib.R.drawable.picture_send_button_default_bg,
                com.luck.picture.lib.R.drawable.picture_send_button_bg,9,false,false,1,1,false,null);
    }

    /**
     * @param maxSelectNum 最大图片选择数量
     */
    public static void pictureSelector(Context context,int maxSelectNum){
        pictureSelector(context,PictureMimeType.ofImage(),com.luck.picture.lib.R.drawable.picture_send_button_default_bg,
                com.luck.picture.lib.R.drawable.picture_send_button_bg,maxSelectNum,false,false,1,1,false,null);
    }

    /**
     * @param drawableTopCompleteDefaultBtnBackground 顶部确认按钮默认样式
     * @param drawableTopCompleteNormalBtnBackground 顶部确认按钮选择样式
     */
    public static void pictureSelector(Context context,int drawableTopCompleteDefaultBtnBackground,int drawableTopCompleteNormalBtnBackground){
        pictureSelector(context,PictureMimeType.ofImage(),drawableTopCompleteDefaultBtnBackground,
                drawableTopCompleteNormalBtnBackground,9,false,false,1,1,false,null);
    }

    /**
     * @param maxSelectNum 最大图片选择数量
     * @param drawableTopCompleteDefaultBtnBackground 顶部确认按钮默认样式
     * @param drawableTopCompleteNormalBtnBackground 顶部确认按钮选择样式
     */
    public static void pictureSelector(Context context,int maxSelectNum,int drawableTopCompleteDefaultBtnBackground,int drawableTopCompleteNormalBtnBackground){
        pictureSelector(context,PictureMimeType.ofImage(),drawableTopCompleteDefaultBtnBackground,
                drawableTopCompleteNormalBtnBackground,maxSelectNum,false,false,1,1,false,null);
    }

    /**
     * @param isCrop 是否裁剪图片
     * @param aspectRatioX  // 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
     * @param aspectatioY  // 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
     */
    public static void pictureIsCropSelector(Context context,boolean isCrop,int aspectRatioX,int aspectatioY){
        pictureSelector(context,PictureMimeType.ofImage(),com.luck.picture.lib.R.drawable.picture_send_button_default_bg,
                com.luck.picture.lib.R.drawable.picture_send_button_bg,9,false,isCrop,aspectRatioX,aspectatioY,false,null);
    }

    public static void pictureisSingleSelectSelector(Context context){
        pictureSelector(context,PictureMimeType.ofImage(),com.luck.picture.lib.R.drawable.picture_send_button_default_bg,
                com.luck.picture.lib.R.drawable.picture_send_button_bg,9,true,false,1,1,false,null);
    }

    public static void VideoSelectSelector(Context context){
        pictureSelector(context,PictureMimeType.ofVideo(),com.luck.picture.lib.R.drawable.picture_send_button_default_bg
                ,com.luck.picture.lib.R.drawable.picture_send_button_bg,9,false,false,1,1,false,null);
    }


    /**
     * 微信样式
     * @return
     */
    public static PictureSelectorUIStyle ofNewStyle(int drawableTopCompleteDefaultBtnBackground,int drawableTopCompleteNormalBtnBackground) {
        PictureSelectorUIStyle uiStyle = new PictureSelectorUIStyle();
        uiStyle.isNewSelectStyle = true;
        uiStyle.picture_statusBarBackgroundColor = Color.parseColor("#393a3e");
        uiStyle.picture_container_backgroundColor = Color.parseColor("#000000");
        uiStyle.picture_switchSelectNumberStyle = true;
        uiStyle.picture_navBarColor = Color.parseColor("#393a3e");
        uiStyle.picture_check_style = com.luck.picture.lib.R.drawable.picture_wechat_num_selector;
        uiStyle.picture_top_leftBack = com.luck.picture.lib.R.drawable.picture_icon_back;
        uiStyle.picture_top_titleRightTextColor = new int[]{Color.parseColor("#53575e"), Color.parseColor("#FFFFFF")};
        uiStyle.picture_top_titleRightTextSize = 14;
        uiStyle.picture_top_titleTextSize = 16;
        uiStyle.picture_top_titleArrowUpDrawable = com.luck.picture.lib.R.drawable.picture_icon_arrow_up;
        uiStyle.picture_top_titleArrowDownDrawable = com.luck.picture.lib.R.drawable.picture_icon_arrow_down;
        uiStyle.picture_top_titleTextColor = Color.parseColor("#FFFFFF");
        uiStyle.picture_top_titleBarBackgroundColor = Color.parseColor("#393a3e");
        uiStyle.picture_top_titleAlbumBackground = com.luck.picture.lib.R.drawable.picture_album_bg;

        uiStyle.picture_album_textSize = 16;
        uiStyle.picture_album_backgroundStyle = com.luck.picture.lib.R.drawable.picture_item_select_bg;
        uiStyle.picture_album_textColor = Color.parseColor("#4d4d4d");
        uiStyle.picture_album_checkDotStyle = com.luck.picture.lib.R.drawable.picture_orange_oval;

        uiStyle.picture_bottom_previewTextSize = 16;
        uiStyle.picture_bottom_previewTextColor = new int[]{Color.parseColor("#9b9b9b"), Color.parseColor("#FFFFFF")};

        uiStyle.picture_bottom_completeTextColor = new int[]{Color.parseColor("#9b9b9b"), Color.parseColor("#FA632D")};
        uiStyle.picture_bottom_barBackgroundColor = Color.parseColor("#393a3e");

        uiStyle.picture_adapter_item_camera_backgroundColor = Color.parseColor("#999999");
        uiStyle.picture_adapter_item_camera_textColor = Color.parseColor("#FFFFFF");
        uiStyle.picture_adapter_item_camera_textSize = 14;
        uiStyle.picture_adapter_item_camera_textTopDrawable = com.luck.picture.lib.R.drawable.picture_icon_camera;

        uiStyle.picture_adapter_item_textSize = 12;
        uiStyle.picture_adapter_item_textColor = Color.parseColor("#FFFFFF");
        uiStyle.picture_adapter_item_video_textLeftDrawable = com.luck.picture.lib.R.drawable.picture_icon_video;
        uiStyle.picture_adapter_item_audio_textLeftDrawable = com.luck.picture.lib.R.drawable.picture_icon_audio;

        uiStyle.picture_bottom_originalPictureTextSize = 14;
        uiStyle.picture_bottom_originalPictureCheckStyle = com.luck.picture.lib.R.drawable.picture_original_wechat_checkbox;
        uiStyle.picture_bottom_originalPictureTextColor = Color.parseColor("#FFFFFF");
        uiStyle.picture_top_titleRightTextDefaultBackground = drawableTopCompleteDefaultBtnBackground;
        uiStyle.picture_top_titleRightTextNormalBackground = drawableTopCompleteNormalBtnBackground;
        Context appContext = PictureAppMaster.getInstance().getAppContext();
        if (appContext != null) {
            uiStyle.picture_top_titleBarHeight = ScreenUtils.dip2px(appContext, 48);
            uiStyle.picture_top_titleRightDefaultText = com.luck.picture.lib.R.string.picture_send;
            uiStyle.picture_top_titleRightNormalText = com.luck.picture.lib.R.string.picture_cancel;
            uiStyle.picture_bottom_barHeight = ScreenUtils.dip2px(appContext, 45);
            uiStyle.picture_bottom_previewDefaultText = com.luck.picture.lib.R.string.picture_preview;
            uiStyle.picture_bottom_previewNormalText = com.luck.picture.lib.R.string.picture_preview_num;
            uiStyle.picture_bottom_originalPictureText = com.luck.picture.lib.R.string.picture_original_image;
            uiStyle.picture_bottom_completeDefaultText = com.luck.picture.lib.R.string.picture_please_select;
            uiStyle.picture_bottom_completeNormalText = com.luck.picture.lib.R.string.picture_completed;
            uiStyle.picture_adapter_item_camera_text = com.luck.picture.lib.R.string.picture_take_picture;
            uiStyle.picture_bottom_selectedText = com.luck.picture.lib.R.string.picture_select;
            uiStyle.picture_bottom_selectedCheckStyle = com.luck.picture.lib.R.drawable.picture_wechat_select_cb;
            // 如果文本内容设置(%1$d/%2$d)，请开启true
            uiStyle.isCompleteReplaceNum = true;
            uiStyle.picture_top_titleArrowLeftPadding = ScreenUtils.dip2px(appContext, 3);
            uiStyle.picture_bottom_selectedTextColor = Color.parseColor("#FFFFFF");
            uiStyle.picture_bottom_selectedTextSize = 16;
            uiStyle.picture_bottom_gallery_height = ScreenUtils.dip2px(appContext, 80);
            uiStyle.picture_bottom_gallery_backgroundColor = Color.parseColor("#a0393a3e");
            uiStyle.picture_bottom_gallery_frameBackground = com.luck.picture.lib.R.drawable.picture_preview_gallery_border_bg;
        }

        return uiStyle;
    }

    public static List<LocalMedia> getData(List<LocalMedia> list) {
        return list == null ? new ArrayList<>() : list;
    }

}
