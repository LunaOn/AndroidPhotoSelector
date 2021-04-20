package com.luck.picture.lib.customused;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.R;
import com.luck.picture.lib.app.PictureAppMaster;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMediaFolder;
import com.luck.picture.lib.language.LanguageConfig;
import com.luck.picture.lib.model.LocalMediaLoader;
import com.luck.picture.lib.style.PictureSelectorUIStyle;
import com.luck.picture.lib.thread.PictureThreadUtils;
import com.luck.picture.lib.tools.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: chuanmo
 * Date: 4/16/21 1:22 PM
 * Description: 图片视频选择器
 */
public class PictureOrVideoSelectorManager {
    public final static int CHOOSE_REQUEST = 188;
    private Context context;
    private int type = PictureMimeType.TYPE_IMAGE;
    private int maxSelectNum = 8;
    private int drawableTopCompleteDefaultBtnBackground = R.drawable.picture_send_button_default_bg;
    private int drawableTopCompleteNormalBtnBackground = R.drawable.picture_send_button_bg;
    private boolean isSingleselect = false;
    private boolean isGif = false;
    private List<LocalMedia> oldList = new ArrayList<>();
    private int requestCode = CHOOSE_REQUEST;

    public PictureOrVideoSelectorManager(Context context) {
        this.context = context;
    }

    public void create() {
        pictureSelector(context,type,maxSelectNum,drawableTopCompleteDefaultBtnBackground,
                drawableTopCompleteNormalBtnBackground,isSingleselect,isGif,oldList,requestCode);
    }

    /**
     * @param type 查看类型，全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
     * @param maxSelectNum 最大图片选择数量
     * @param isSingleselect 单个图片选择
     * @param isGif 是否支持Gif
     * @param oldList 传入选中的图片
     * @param requestCode
     */
    public static void pictureSelector(Context context, int type, int maxSelectNum, int drawableTopCompleteDefaultBtnBackground, int drawableTopCompleteNormalBtnBackground
            , boolean isSingleselect, boolean isGif, List<LocalMedia> oldList, int requestCode) {
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
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .isCompress(false)// 是否压缩
                .isGif(isGif)
                .selectionData(getData(oldList))// 是否传入已选图片
                .isPreviewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                .forResult(requestCode);
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
        uiStyle.picture_check_style = R.drawable.picture_wechat_num_selector;
        uiStyle.picture_top_leftBack = R.drawable.picture_icon_back;
        uiStyle.picture_top_titleRightTextColor = new int[]{Color.parseColor("#53575e"), Color.parseColor("#FFFFFF")};
        uiStyle.picture_top_titleRightTextSize = 14;
        uiStyle.picture_top_titleTextSize = 16;
        uiStyle.picture_top_titleArrowUpDrawable = R.drawable.picture_icon_arrow_up;
        uiStyle.picture_top_titleArrowDownDrawable = R.drawable.picture_icon_arrow_down;
        uiStyle.picture_top_titleTextColor = Color.parseColor("#FFFFFF");
        uiStyle.picture_top_titleBarBackgroundColor = Color.parseColor("#393a3e");
        uiStyle.picture_top_titleAlbumBackground = R.drawable.picture_album_bg;

        uiStyle.picture_album_textSize = 16;
        uiStyle.picture_album_backgroundStyle = R.drawable.picture_item_select_bg;
        uiStyle.picture_album_textColor = Color.parseColor("#4d4d4d");
        uiStyle.picture_album_checkDotStyle = R.drawable.picture_orange_oval;

        uiStyle.picture_bottom_previewTextSize = 16;
        uiStyle.picture_bottom_previewTextColor = new int[]{Color.parseColor("#9b9b9b"), Color.parseColor("#FFFFFF")};

        uiStyle.picture_bottom_completeTextColor = new int[]{Color.parseColor("#9b9b9b"), Color.parseColor("#FA632D")};
        uiStyle.picture_bottom_barBackgroundColor = Color.parseColor("#393a3e");

        uiStyle.picture_adapter_item_camera_backgroundColor = Color.parseColor("#999999");
        uiStyle.picture_adapter_item_camera_textColor = Color.parseColor("#FFFFFF");
        uiStyle.picture_adapter_item_camera_textSize = 14;
        uiStyle.picture_adapter_item_camera_textTopDrawable = R.drawable.picture_icon_camera;

        uiStyle.picture_adapter_item_textSize = 12;
        uiStyle.picture_adapter_item_textColor = Color.parseColor("#FFFFFF");
        uiStyle.picture_adapter_item_video_textLeftDrawable = R.drawable.picture_icon_video;
        uiStyle.picture_adapter_item_audio_textLeftDrawable = R.drawable.picture_icon_audio;

        uiStyle.picture_bottom_originalPictureTextSize = 14;
        uiStyle.picture_bottom_originalPictureCheckStyle = R.drawable.picture_original_wechat_checkbox;
        uiStyle.picture_bottom_originalPictureTextColor = Color.parseColor("#FFFFFF");
        uiStyle.picture_top_titleRightTextDefaultBackground = drawableTopCompleteDefaultBtnBackground;
        uiStyle.picture_top_titleRightTextNormalBackground = drawableTopCompleteNormalBtnBackground;
        Context appContext = PictureAppMaster.getInstance().getAppContext();
        if (appContext != null) {
            uiStyle.picture_top_titleBarHeight = ScreenUtils.dip2px(appContext, 48);
            uiStyle.picture_top_titleRightDefaultText = R.string.picture_send;
            uiStyle.picture_top_titleRightNormalText = R.string.picture_cancel;
            uiStyle.picture_bottom_barHeight = ScreenUtils.dip2px(appContext, 45);
            uiStyle.picture_bottom_previewDefaultText = R.string.picture_preview;
            uiStyle.picture_bottom_previewNormalText = R.string.picture_preview_num;
            uiStyle.picture_bottom_originalPictureText = R.string.picture_original_image;
            uiStyle.picture_bottom_completeDefaultText = R.string.picture_please_select;
            uiStyle.picture_bottom_completeNormalText = R.string.picture_completed;
            uiStyle.picture_adapter_item_camera_text = R.string.picture_take_picture;
            uiStyle.picture_bottom_selectedText = R.string.picture_select;
            uiStyle.picture_bottom_selectedCheckStyle = R.drawable.picture_wechat_select_cb;
            // 如果文本内容设置(%1$d/%2$d)，请开启true
            uiStyle.isCompleteReplaceNum = true;
            uiStyle.picture_top_titleArrowLeftPadding = ScreenUtils.dip2px(appContext, 3);
            uiStyle.picture_bottom_selectedTextColor = Color.parseColor("#FFFFFF");
            uiStyle.picture_bottom_selectedTextSize = 16;
            uiStyle.picture_bottom_gallery_height = ScreenUtils.dip2px(appContext, 80);
            uiStyle.picture_bottom_gallery_backgroundColor = Color.parseColor("#a0393a3e");
            uiStyle.picture_bottom_gallery_frameBackground = R.drawable.picture_preview_gallery_border_bg;
        }

        return uiStyle;
    }

    public static List<com.luck.picture.lib.entity.LocalMedia> getData(List<LocalMedia> mylist) {
        if(mylist == null){
            mylist = new ArrayList<>();
        }
        List<com.luck.picture.lib.entity.LocalMedia> list = new ArrayList<>();
        for (int i = 0; i < mylist.size();i++){
            com.luck.picture.lib.entity.LocalMedia localMedia = new com.luck.picture.lib.entity.LocalMedia();
            localMedia.setAndroidQToPath(mylist.get(i).getAndroidQToPath());
            localMedia.setBucketId(mylist.get(i).getBucketId());
            localMedia.setChecked(mylist.get(i).isChecked());
            localMedia.setChooseModel(mylist.get(i).getChooseModel());
            localMedia.setCompressed(mylist.get(i).isCompressed());
            localMedia.setCompressPath(mylist.get(i).getCompressPath());
            localMedia.setCut(mylist.get(i).isCut());
            localMedia.setCutPath(mylist.get(i).getCutPath());
            localMedia.setDuration(mylist.get(i).getDuration());
            localMedia.setFileName(mylist.get(i).getFileName());
            localMedia.setWidth(mylist.get(i).getWidth());
            localMedia.setHeight(mylist.get(i).getHeight());
            localMedia.setId(mylist.get(i).getId());
            localMedia.setMaxSelectEnabledMask(mylist.get(i).isMaxSelectEnabledMask());
            localMedia.setMimeType(mylist.get(i).getMimeType());
            localMedia.setNum(mylist.get(i).getNum());
            localMedia.setOrientation(mylist.get(i).getOrientation());
            localMedia.setOriginal(mylist.get(i).isOriginal());
            localMedia.setOriginalPath(mylist.get(i).getOriginalPath());
            localMedia.setRealPath(mylist.get(i).getRealPath());
            localMedia.setPosition(mylist.get(i).getPosition());
            localMedia.setSize(mylist.get(i).getSize());
            localMedia.setParentFolderName(mylist.get(i).getParentFolderName());
            localMedia.setPath(mylist.get(i).getPath());
            list.add(localMedia);
        }
        return list;
    }

    /**
     * @param data
     * @return Selector Multiple LocalMedia
     */
    public static List<LocalMedia> obtainMultipleResult(Intent data) {
        List<LocalMedia> list = new ArrayList<>();
        if (data != null) {
            List<com.luck.picture.lib.entity.LocalMedia> result = data.getParcelableArrayListExtra(PictureConfig.EXTRA_RESULT_SELECTION);
            for (int i = 0; i < result.size();i++){
                LocalMedia localMedia = new LocalMedia();
                localMedia.setAndroidQToPath(result.get(i).getAndroidQToPath());
                localMedia.setBucketId(result.get(i).getBucketId());
                localMedia.setChecked(result.get(i).isChecked());
                localMedia.setChooseModel(result.get(i).getChooseModel());
                localMedia.setCompressed(result.get(i).isCompressed());
                localMedia.setCompressPath(result.get(i).getCompressPath());
                localMedia.setCut(result.get(i).isCut());
                localMedia.setCutPath(result.get(i).getCutPath());
                localMedia.setDuration(result.get(i).getDuration());
                localMedia.setFileName(result.get(i).getFileName());
                localMedia.setWidth(result.get(i).getWidth());
                localMedia.setHeight(result.get(i).getHeight());
                localMedia.setId(result.get(i).getId());
                localMedia.setMaxSelectEnabledMask(result.get(i).isMaxSelectEnabledMask());
                localMedia.setMimeType(result.get(i).getMimeType());
                localMedia.setNum(result.get(i).getNum());
                localMedia.setOrientation(result.get(i).getOrientation());
                localMedia.setOriginal(result.get(i).isOriginal());
                localMedia.setOriginalPath(result.get(i).getOriginalPath());
                localMedia.setRealPath(result.get(i).getRealPath());
                localMedia.setPosition(result.get(i).getPosition());
                localMedia.setSize(result.get(i).getSize());
                localMedia.setParentFolderName(result.get(i).getParentFolderName());
                localMedia.setPath(result.get(i).getPath());
                list.add(localMedia);
            }

        }
        return list;
    }


    public int getType() {
        return type;
    }

    public PictureOrVideoSelectorManager setType(int type) {
        this.type = type;
        return this;
    }

    public int getMaxSelectNum() {
        return maxSelectNum;
    }

    public PictureOrVideoSelectorManager setMaxSelectNum(int maxSelectNum) {
        this.maxSelectNum = maxSelectNum;
        return this;
    }

    public int getDrawableTopCompleteDefaultBtnBackground() {
        return drawableTopCompleteDefaultBtnBackground;
    }

    public PictureOrVideoSelectorManager setDrawableTopCompleteDefaultBtnBackground(int drawableTopCompleteDefaultBtnBackground) {
        this.drawableTopCompleteDefaultBtnBackground = drawableTopCompleteDefaultBtnBackground;
        return this;
    }

    public int getDrawableTopCompleteNormalBtnBackground() {
        return drawableTopCompleteNormalBtnBackground;
    }

    public PictureOrVideoSelectorManager setDrawableTopCompleteNormalBtnBackground(int drawableTopCompleteNormalBtnBackground) {
        this.drawableTopCompleteNormalBtnBackground = drawableTopCompleteNormalBtnBackground;
        return this;
    }

    public boolean isSingleselect() {
        return isSingleselect;
    }

    public PictureOrVideoSelectorManager setSingleselect(boolean singleselect) {
        isSingleselect = singleselect;
        return this;
    }


    public boolean isGif() {
        return isGif;
    }

    public PictureOrVideoSelectorManager setGif(boolean gif) {
        isGif = gif;
        return this;
    }

    public List<LocalMedia> getOldList() {
        return oldList;
    }

    public PictureOrVideoSelectorManager setOldList(List<LocalMedia> oldList) {
        this.oldList = oldList;
        return this;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public PictureOrVideoSelectorManager setRequestCode(int requestCode) {
        this.requestCode = requestCode;
        return this;
    }

}
