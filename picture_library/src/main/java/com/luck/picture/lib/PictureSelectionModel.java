package com.luck.picture.lib;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;

import com.luck.picture.lib.animators.AnimationType;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.config.PictureSelectionConfig;
import com.luck.picture.lib.engine.ImageEngine;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnCustomCameraInterfaceListener;
import com.luck.picture.lib.listener.OnCustomImagePreviewCallback;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.listener.OnVideoSelectedPlayCallback;
import com.luck.picture.lib.style.PictureParameterStyle;
import com.luck.picture.lib.style.PictureSelectorUIStyle;
import com.luck.picture.lib.style.PictureWindowAnimationStyle;
import com.luck.picture.lib.tools.DoubleUtils;
import com.luck.picture.lib.tools.SdkVersionUtils;

import java.lang.ref.WeakReference;
import java.util.List;

import androidx.annotation.ColorInt;
import androidx.annotation.StyleRes;
import androidx.fragment.app.Fragment;

import static android.os.Build.VERSION_CODES.KITKAT;

/**
 * @author：luck
 * @date：2017-5-24 21:30
 * @describe：PictureSelectionModel
 */

public class PictureSelectionModel {
    private PictureSelectionConfig selectionConfig;
    private PictureSelector selector;

    public PictureSelectionModel(PictureSelector selector, int chooseMode) {
        this.selector = selector;
        selectionConfig = PictureSelectionConfig.getCleanInstance();
        selectionConfig.chooseMode = chooseMode;
    }

    public PictureSelectionModel(PictureSelector selector, int chooseMode, boolean camera) {
        this.selector = selector;
        selectionConfig = PictureSelectionConfig.getCleanInstance();
        selectionConfig.camera = camera;
        selectionConfig.chooseMode = chooseMode;
    }

    /**
     * @param themeStyleId PictureSelector Theme style
     * @return PictureSelectionModel
     * Use { R.style#picture_default_style#picture_Sina_style#picture_white_style#picture_QQ_style#picture_WeChat_style}
     */
    public PictureSelectionModel theme(@StyleRes int themeStyleId) {
        selectionConfig.themeStyleId = themeStyleId;
        return this;
    }

    /**
     * Setting PictureSelector UI Style
     *
     * @param uiStyle <p>
     *                {@link PictureSelectorUIStyle}
     *                </p>
     * @return
     */
    public PictureSelectionModel setPictureUIStyle(PictureSelectorUIStyle uiStyle) {
        if (uiStyle != null) {
            PictureSelectionConfig.uiStyle = uiStyle;
            if (!selectionConfig.isWeChatStyle) {
                selectionConfig.isWeChatStyle = PictureSelectionConfig.uiStyle.isNewSelectStyle;
            }
        }
        return this;
    }

    /**
     *  locale Language
     * @return PictureSelectionModel
     */
    public PictureSelectionModel setLanguage(int language) {
        selectionConfig.language = language;
        return this;
    }

    /**
     * Change the desired orientation of this activity.  If the activity
     * is currently in the foreground or otherwise impacting the screen
     * orientation, the screen will immediately be changed (possibly causing
     * the activity to be restarted). Otherwise, this will be used the next
     * time the activity is visible.
     *
     * @param requestedOrientation An orientation constant as used in
     *                             { ActivityInfo#screenOrientation ActivityInfo.screenOrientation}.
     */
    public PictureSelectionModel setRequestedOrientation(int requestedOrientation) {
        selectionConfig.requestedOrientation = requestedOrientation;
        return this;
    }

    /**
     * @param engine Image Load the engine
     * @return Use {@link .imageEngine()}.
     */
    @Deprecated
    public PictureSelectionModel loadImageEngine(ImageEngine engine) {
        if (PictureSelectionConfig.imageEngine != engine) {
            PictureSelectionConfig.imageEngine = engine;
        }
        return this;
    }

    /**
     * @param engine Image Load the engine
     * @return
     */
    public PictureSelectionModel imageEngine(ImageEngine engine) {
        if (PictureSelectionConfig.imageEngine != engine) {
            PictureSelectionConfig.imageEngine = engine;
        }
        return this;
    }


    /**
     * @param selectionMode PictureSelector Selection model and PictureConfig.MULTIPLE or PictureConfig.SINGLE
     * @return
     */
    public PictureSelectionModel selectionMode(int selectionMode) {
        selectionConfig.selectionMode = selectionMode;
        return this;
    }

    /**
     * @param isWeChatStyle Select style with or without WeChat enabled
     * @return
     */
    public PictureSelectionModel isWeChatStyle(boolean isWeChatStyle) {
        selectionConfig.isWeChatStyle = isWeChatStyle;
        return this;
    }

    /**
     * @param isUseCustomCamera Whether to use a custom camera
     * @return
     */
    public PictureSelectionModel isUseCustomCamera(boolean isUseCustomCamera) {
        selectionConfig.isUseCustomCamera = Build.VERSION.SDK_INT > KITKAT && isUseCustomCamera;
        return this;
    }

    /**
     * @param callback Provide video playback control，Users are free to customize the video display interface
     * @return
     */
    public PictureSelectionModel bindCustomPlayVideoCallback(OnVideoSelectedPlayCallback callback) {
        PictureSelectionConfig.customVideoPlayCallback = new WeakReference<>(callback).get();
        return this;
    }

    /**
     * @param callback Custom preview callback function
     * @return
     */
    public PictureSelectionModel bindCustomPreviewCallback(OnCustomImagePreviewCallback callback) {
        PictureSelectionConfig.onCustomImagePreviewCallback = new WeakReference<>(callback).get();
        return this;
    }

    /**
     * # The developer provides an additional callback interface to the user where the user can perform some custom actions
     * {link 如果是自定义相机则必须使用.startActivityForResult(this,PictureConfig.REQUEST_CAMERA);方式启动否则PictureSelector处理不了相机后的回调}
     *
     * @param listener
     * @return Use ${bindCustomCameraInterfaceListener}
     */
    @Deprecated
    public PictureSelectionModel bindPictureSelectorInterfaceListener(OnCustomCameraInterfaceListener listener) {
        PictureSelectionConfig.onCustomCameraInterfaceListener = new WeakReference<>(listener).get();
        return this;
    }

    /**
     * # The developer provides an additional callback interface to the user where the user can perform some custom actions
     * {link 如果是自定义相机则必须使用.startActivityForResult(this,PictureConfig.REQUEST_CAMERA);方式启动否则PictureSelector处理不了相机后的回调}
     *
     * @param listener
     * @return
     */
    public PictureSelectionModel bindCustomCameraInterfaceListener(OnCustomCameraInterfaceListener listener) {
        PictureSelectionConfig.onCustomCameraInterfaceListener = new WeakReference<>(listener).get();
        return this;
    }

    /**
     * @param buttonFeatures Set the record button function
     *                       # 具体参考 CustomCameraView.BUTTON_STATE_BOTH、BUTTON_STATE_ONLY_CAPTURE、BUTTON_STATE_ONLY_RECORDER
     * @return
     */
//    public PictureSelectionModel setButtonFeatures(int buttonFeatures) {
//        selectionConfig.buttonFeatures = buttonFeatures;
//        return this;
//    }


    /**
     *  enablePreviewAudio { use isEnablePreviewAudio}
     * @return
     */
    @Deprecated
    public PictureSelectionModel enablePreviewAudio(boolean enablePreviewAudio) {
        selectionConfig.enablePreviewAudio = enablePreviewAudio;
        return this;
    }

    /**
     * @param enablePreviewAudio
     * @return
     */
    public PictureSelectionModel isEnablePreviewAudio(boolean enablePreviewAudio) {
        selectionConfig.enablePreviewAudio = enablePreviewAudio;
        return this;
    }

    /**
     * @param scaleEnabled Crop frame is zoom ?
     * @return
     */
    public PictureSelectionModel scaleEnabled(boolean scaleEnabled) {
        selectionConfig.scaleEnabled = scaleEnabled;
        return this;
    }

    /**
     * @param rotateEnabled Crop frame is rotate ?
     * @return
     */
    public PictureSelectionModel rotateEnabled(boolean rotateEnabled) {
        selectionConfig.rotateEnabled = rotateEnabled;
        return this;
    }

    /**
     * @param circleDimmedLayer Circular head cutting
     * @return
     */
    public PictureSelectionModel circleDimmedLayer(boolean circleDimmedLayer) {
        selectionConfig.circleDimmedLayer = circleDimmedLayer;
        return this;
    }

    /**
     * @param circleDimmedColor setCircleDimmedColor
     * @return
     */
    @Deprecated
    public PictureSelectionModel setCircleDimmedColor(int circleDimmedColor) {
        selectionConfig.circleDimmedColor = circleDimmedColor;
        return this;
    }

    /**
     * @param dimmedColor
     * @return
     */
    public PictureSelectionModel setCropDimmedColor(int dimmedColor) {
        selectionConfig.circleDimmedColor = dimmedColor;
        return this;
    }

    /**
     * @param circleDimmedBorderColor setCircleDimmedBorderColor
     * @return
     */
    public PictureSelectionModel setCircleDimmedBorderColor(int circleDimmedBorderColor) {
        selectionConfig.circleDimmedBorderColor = circleDimmedBorderColor;
        return this;
    }

    /**
     * @param circleStrokeWidth setCircleStrokeWidth
     * @return
     */
    public PictureSelectionModel setCircleStrokeWidth(int circleStrokeWidth) {
        selectionConfig.circleStrokeWidth = circleStrokeWidth;
        return this;
    }

    /**
     * @param hideBottomControls Whether is Clipping function bar
     *                           单选有效
     * @return
     */
    public PictureSelectionModel hideBottomControls(boolean hideBottomControls) {
        selectionConfig.hideBottomControls = hideBottomControls;
        return this;
    }

    /**
     * @param aspect_ratio_x Crop Proportion x
     * @param aspect_ratio_y Crop Proportion y
     * @return
     */
    public PictureSelectionModel withAspectRatio(int aspect_ratio_x, int aspect_ratio_y) {
        selectionConfig.aspect_ratio_x = aspect_ratio_x;
        selectionConfig.aspect_ratio_y = aspect_ratio_y;
        return this;
    }

    /**
     * @param isWithVideoImage Whether the pictures and videos can be selected together
     * @return
     */
    public PictureSelectionModel isWithVideoImage(boolean isWithVideoImage) {
        selectionConfig.isWithVideoImage =
                selectionConfig.selectionMode != PictureConfig.SINGLE
                        && selectionConfig.chooseMode == PictureMimeType.ofAll() && isWithVideoImage;
        return this;
    }

    /**
     * When the maximum number of choices is reached, does the list enable the mask effect
     *
     * @param isMaxSelectEnabledMask
     * @return
     */
    public PictureSelectionModel isMaxSelectEnabledMask(boolean isMaxSelectEnabledMask) {
        selectionConfig.isMaxSelectEnabledMask = isMaxSelectEnabledMask;
        return this;
    }

    /**
     * @param maxSelectNum PictureSelector max selection
     * @return
     */
    public PictureSelectionModel maxSelectNum(int maxSelectNum) {
        selectionConfig.maxSelectNum = maxSelectNum;
        return this;
    }

    /**
     * @param minSelectNum PictureSelector min selection
     * @return
     */
    public PictureSelectionModel minSelectNum(int minSelectNum) {
        selectionConfig.minSelectNum = minSelectNum;
        return this;
    }

    /**
     * @param maxVideoSelectNum PictureSelector video max selection
     * @return
     */
    public PictureSelectionModel maxVideoSelectNum(int maxVideoSelectNum) {
        selectionConfig.maxVideoSelectNum = selectionConfig.chooseMode == PictureMimeType.ofVideo() ? 0 : maxVideoSelectNum;
        return this;
    }

    /**
     * @param minVideoSelectNum PictureSelector video min selection
     * @return
     */
    public PictureSelectionModel minVideoSelectNum(int minVideoSelectNum) {
        selectionConfig.minVideoSelectNum = minVideoSelectNum;
        return this;
    }

    /**
     * Turn off Android Q to solve the problem that the width and height are reversed
     *
     * @param isChangeWH
     * @return
     */
    public PictureSelectionModel closeAndroidQChangeWH(boolean isChangeWH) {
        selectionConfig.isAndroidQChangeWH = isChangeWH;
        return this;
    }

    /**
     * Turn off Android Q to solve the problem that the width and height are reversed
     *
     * @param isChangeVideoWH
     * @return
     */
    public PictureSelectionModel closeAndroidQChangeVideoWH(boolean isChangeVideoWH) {
        selectionConfig.isAndroidQChangeVideoWH = isChangeVideoWH;
        return this;
    }

    /**
     * By clicking the title bar consecutively, RecyclerView automatically rolls back to the top
     *
     * @param isAutomaticTitleRecyclerTop
     * @return
     */
    public PictureSelectionModel isAutomaticTitleRecyclerTop(boolean isAutomaticTitleRecyclerTop) {
        selectionConfig.isAutomaticTitleRecyclerTop = isAutomaticTitleRecyclerTop;
        return this;
    }


    /**
     *  Select whether to return directly
     * @return
     */
    public PictureSelectionModel isSingleDirectReturn(boolean isSingleDirectReturn) {
        selectionConfig.isSingleDirectReturn = selectionConfig.selectionMode
                == PictureConfig.SINGLE && isSingleDirectReturn;
        selectionConfig.isOriginalControl = (selectionConfig.selectionMode != PictureConfig.SINGLE || !isSingleDirectReturn) && selectionConfig.isOriginalControl;
        return this;
    }

    /**
     * Whether to turn on paging mode
     *
     * @param isPageStrategy
     * @param pageSize       Maximum number of pages { PageSize is preferably no less than 20}
     * @return
     */
    public PictureSelectionModel isPageStrategy(boolean isPageStrategy, int pageSize) {
        selectionConfig.isPageStrategy = isPageStrategy;
        selectionConfig.pageSize = pageSize < PictureConfig.MIN_PAGE_SIZE ? PictureConfig.MAX_PAGE_SIZE : pageSize;
        return this;
    }

    /**
     * Whether to turn on paging mode
     *
     * @param isPageStrategy
     * @param pageSize            Maximum number of pages {  PageSize is preferably no less than 20}
     * @param isFilterInvalidFile Whether to filter invalid files { Some of the query performance is consumed,Especially on the Q version}
     * @return
     */
    public PictureSelectionModel isPageStrategy(boolean isPageStrategy, int pageSize, boolean isFilterInvalidFile) {
        selectionConfig.isPageStrategy = isPageStrategy;
        selectionConfig.pageSize = pageSize < PictureConfig.MIN_PAGE_SIZE ? PictureConfig.MAX_PAGE_SIZE : pageSize;
        selectionConfig.isFilterInvalidFile = isFilterInvalidFile;
        return this;
    }

    /**
     * Whether to turn on paging mode
     *
     * @param isPageStrategy
     * @return
     */
    public PictureSelectionModel isPageStrategy(boolean isPageStrategy) {
        selectionConfig.isPageStrategy = isPageStrategy;
        return this;
    }

    /**
     * Whether to turn on paging mode
     *
     * @param isPageStrategy
     * @param isFilterInvalidFile Whether to filter invalid files { Some of the query performance is consumed,Especially on the Q version}
     * @return
     */
    public PictureSelectionModel isPageStrategy(boolean isPageStrategy, boolean isFilterInvalidFile) {
        selectionConfig.isPageStrategy = isPageStrategy;
        selectionConfig.isFilterInvalidFile = isFilterInvalidFile;
        return this;
    }

    /**
     * @param videoQuality video quality and 0 or 1
     * @return
     */
    public PictureSelectionModel videoQuality(int videoQuality) {
        selectionConfig.videoQuality = videoQuality;
        return this;
    }

    /**
     * <p>
     * if Android SDK >=Q Please use the video/mp4 or video/jpeg ... PictureMimeType.MP4_Q or PictureMimeType.PNG_Q
     * else PictureMimeType.PNG or PictureMimeType.JPEG
     * </p>
     *
     * @param suffixType PictureSelector media format
     * @return
     */
    public PictureSelectionModel imageFormat(String suffixType) {
        if (SdkVersionUtils.checkedAndroid_Q() || SdkVersionUtils.checkedAndroid_R()) {
            if (TextUtils.equals(suffixType, PictureMimeType.PNG)) {
                suffixType = PictureMimeType.PNG_Q;
            }
            if (TextUtils.equals(suffixType, PictureMimeType.JPEG)) {
                suffixType = PictureMimeType.JPEG_Q;
            }
            if (TextUtils.equals(suffixType, PictureMimeType.MP4)) {
                suffixType = PictureMimeType.MP4_Q;
            }
        }
        selectionConfig.suffixType = suffixType;
        return this;
    }

    /**
     * @param videoMaxSecond selection video max second
     * @return
     */
    public PictureSelectionModel videoMaxSecond(int videoMaxSecond) {
        selectionConfig.videoMaxSecond = (videoMaxSecond * 1000);
        return this;
    }

    /**
     * @param videoMinSecond selection video min second
     * @return
     */
    public PictureSelectionModel videoMinSecond(int videoMinSecond) {
        selectionConfig.videoMinSecond = videoMinSecond * 1000;
        return this;
    }


    /**
     * @param recordVideoSecond video record second
     * @return
     */
    public PictureSelectionModel recordVideoSecond(int recordVideoSecond) {
        selectionConfig.recordVideoSecond = recordVideoSecond;
        return this;
    }

    /**
     * @param recordVideoMinSecond video record second
     * @return
     */
    public PictureSelectionModel recordVideoMinSecond(int recordVideoMinSecond) {
        selectionConfig.recordVideoMinSecond = recordVideoMinSecond;
        return this;
    }


    /**
     * @param imageSpanCount PictureSelector image span count
     * @return
     */
    public PictureSelectionModel imageSpanCount(int imageSpanCount) {
        selectionConfig.imageSpanCount = imageSpanCount;
        return this;
    }


    /**
     * @param returnEmpty No data can be returned
     * @return
     */
    public PictureSelectionModel isReturnEmpty(boolean returnEmpty) {
        selectionConfig.returnEmpty = returnEmpty;
        return this;
    }


    /**
     * After recording with the system camera, does it support playing the video immediately using the system player
     *
     * @param isQuickCapture
     * @return
     */
    public PictureSelectionModel isQuickCapture(boolean isQuickCapture) {
        selectionConfig.isQuickCapture = isQuickCapture;
        return this;
    }

    /**
     * @param isOriginalControl Whether the original image is displayed
     * @return
     */
    public PictureSelectionModel isOriginalImageControl(boolean isOriginalControl) {
        selectionConfig.isOriginalControl = !selectionConfig.camera
                && selectionConfig.chooseMode != PictureMimeType.ofVideo()
                && selectionConfig.chooseMode != PictureMimeType.ofAudio() && isOriginalControl;
        return this;
    }


    /**
     * Camera custom local file name
     * # Such as xxx.png
     *
     * @param fileName
     * @return
     */
    public PictureSelectionModel cameraFileName(String fileName) {
        selectionConfig.cameraFileName = fileName;
        return this;
    }

    /**
     * @param zoomAnim Picture list zoom anim
     * @return
     */
    public PictureSelectionModel isZoomAnim(boolean zoomAnim) {
        selectionConfig.zoomAnim = zoomAnim;
        return this;
    }

    /**
     * @param previewEggs preview eggs  It doesn't make much sense
     * @return Use {link .isPreviewEggs()}
     */
    @Deprecated
    public PictureSelectionModel previewEggs(boolean previewEggs) {
        selectionConfig.previewEggs = previewEggs;
        return this;
    }

    /**
     * @param previewEggs preview eggs  It doesn't make much sense
     * @return
     */
    public PictureSelectionModel isPreviewEggs(boolean previewEggs) {
        selectionConfig.previewEggs = previewEggs;
        return this;
    }

    /**
     * @param isCamera Whether to open camera button
     * @return
     */
    public PictureSelectionModel isCamera(boolean isCamera) {
        selectionConfig.isCamera = isCamera;
        return this;
    }

    /**
     * Extra used with { #Environment.getExternalStorageDirectory() +  File.separator + "CustomCamera" + File.separator}  to indicate that
     *
     * @param outPutCameraPath Camera save path 只支持Build.VERSION.SDK_INT < Build.VERSION_CODES.Q
     * @return
     */
    public PictureSelectionModel setOutputCameraPath(String outPutCameraPath) {
        selectionConfig.outPutCameraPath = outPutCameraPath;
        return this;
    }


    /**
     * # file size The unit is M
     *
     * @param fileSize Filter file size
     * @return
     */
    public PictureSelectionModel queryMaxFileSize(float fileSize) {
        selectionConfig.filterFileSize = fileSize;
        return this;
    }

    /**
     * @param isGif Whether to open gif
     * @return
     */
    public PictureSelectionModel isGif(boolean isGif) {
        selectionConfig.isGif = isGif;
        return this;
    }

    /**
     * @param isWebp Whether to open .webp
     * @return
     */
    public PictureSelectionModel isWebp(boolean isWebp) {
        selectionConfig.isWebp = isWebp;
        return this;
    }

    /**
     * @param isBmp Whether to open .isBmp
     * @return
     */
    public PictureSelectionModel isBmp(boolean isBmp) {
        selectionConfig.isBmp = isBmp;
        return this;
    }


    /**
     * @param enablePreview Do you want to preview the picture?
     * @return
     */
    public PictureSelectionModel isPreviewImage(boolean enablePreview) {
        selectionConfig.enablePreview = enablePreview;
        return this;
    }


    /**
     * @param enPreviewVideo Do you want to preview the video?
     * @return
     */
    public PictureSelectionModel isPreviewVideo(boolean enPreviewVideo) {
        selectionConfig.enPreviewVideo = enPreviewVideo;
        return this;
    }

    /**
     * @param isNotPreviewDownload Previews do not show downloads
     * @return
     */
    public PictureSelectionModel isNotPreviewDownload(boolean isNotPreviewDownload) {
        selectionConfig.isNotPreviewDownload = isNotPreviewDownload;
        return this;
    }

    /**
     *  Specify get image format
     * @return
     */
    public PictureSelectionModel querySpecifiedFormatSuffix(String specifiedFormat) {
        selectionConfig.specifiedFormat = specifiedFormat;
        return this;
    }

    /**
     * @param openClickSound Whether to open click voice
     * @return Use {link .isOpenClickSound()}
     */
    @Deprecated
    public PictureSelectionModel openClickSound(boolean openClickSound) {
        selectionConfig.openClickSound = !selectionConfig.camera && openClickSound;
        return this;
    }

    /**
     *  isOpenClickSound Whether to open click voice
     * @return
     */
    public PictureSelectionModel isOpenClickSound(boolean openClickSound) {
        selectionConfig.openClickSound = !selectionConfig.camera && openClickSound;
        return this;
    }

    /**
     * 是否可拖动裁剪框(setFreeStyleCropEnabled 为true 有效)
     */
    public PictureSelectionModel isDragFrame(boolean isDragFrame) {
        selectionConfig.isDragFrame = isDragFrame;
        return this;
    }

    /**
     * Whether the multi-graph clipping list is animated or not
     *
     * @param isAnimation
     * @return
     */
    public PictureSelectionModel isMultipleRecyclerAnimation(boolean isAnimation) {
        selectionConfig.isMultipleRecyclerAnimation = isAnimation;
        return this;
    }


    /**
     * 设置摄像头方向(前后 默认后置)
     */
    public PictureSelectionModel isCameraAroundState(boolean isCameraAroundState) {
        selectionConfig.isCameraAroundState = isCameraAroundState;
        return this;
    }

    /**
     * @param selectionMedia Select the selected picture set
     * @return Use {link .selectionData()}
     */
    @Deprecated
    public PictureSelectionModel selectionMedia(List<LocalMedia> selectionMedia) {
        if (selectionConfig.selectionMode == PictureConfig.SINGLE && selectionConfig.isSingleDirectReturn) {
            selectionConfig.selectionMedias = null;
        } else {
            selectionConfig.selectionMedias = selectionMedia;
        }
        return this;
    }

    /**
     * @param selectionData Select the selected picture set
     * @return
     */
    public PictureSelectionModel selectionData(List<LocalMedia> selectionData) {
        if (selectionConfig.selectionMode == PictureConfig.SINGLE && selectionConfig.isSingleDirectReturn) {
            selectionConfig.selectionMedias = null;
        } else {
            selectionConfig.selectionMedias = selectionData;
        }
        return this;
    }

    /**
     * 是否改变状态栏字段颜色(黑白字体转换)
     * #适合所有style使用
     *
     * @param isChangeStatusBarFontColor
     * @return
     */
    @Deprecated
    public PictureSelectionModel isChangeStatusBarFontColor(boolean isChangeStatusBarFontColor) {
        selectionConfig.isChangeStatusBarFontColor = isChangeStatusBarFontColor;
        return this;
    }

    /**
     * 是否开启数字选择模式
     * #适合qq style 样式使用
     *
     * @param isOpenStyleCheckNumMode
     * @return 使用setPictureStyle方法
     */
    @Deprecated
    public PictureSelectionModel isOpenStyleCheckNumMode(boolean isOpenStyleCheckNumMode) {
        selectionConfig.isOpenStyleCheckNumMode = isOpenStyleCheckNumMode;
        return this;
    }

    /**
     * 设置标题栏背景色
     *
     * @param color
     * @return 使用setPictureStyle方法
     */
    @Deprecated
    public PictureSelectionModel setTitleBarBackgroundColor(@ColorInt int color) {
        selectionConfig.titleBarBackgroundColor = color;
        return this;
    }


    /**
     * 状态栏背景色
     *
     * @param color
     * @return 使用setPictureStyle方法
     */
    @Deprecated
    public PictureSelectionModel setStatusBarColorPrimaryDark(@ColorInt int color) {
        selectionConfig.pictureStatusBarColor = color;
        return this;
    }


    /**
     * 设置相册标题右侧向上箭头图标
     *
     * @param resId
     * @return 使用setPictureStyle方法
     */
    @Deprecated
    public PictureSelectionModel setUpArrowDrawable(int resId) {
        selectionConfig.upResId = resId;
        return this;
    }

    /**
     * 设置相册标题右侧向下箭头图标
     *
     * @param resId
     * @return 使用setPictureStyle方法
     */
    @Deprecated
    public PictureSelectionModel setDownArrowDrawable(int resId) {
        selectionConfig.downResId = resId;
        return this;
    }


    /**
     * 动态设置相册主题样式
     *
     * @param style 主题
     *              <p>{@link PictureSelectorUIStyle}</>
     * @return
     */
    @Deprecated
    public PictureSelectionModel setPictureStyle(PictureParameterStyle style) {
        if (style != null) {
            PictureSelectionConfig.style = style;
            if (!selectionConfig.isWeChatStyle) {
                selectionConfig.isWeChatStyle = style.isNewSelectStyle;
            }
        } else {
            PictureSelectionConfig.style = PictureParameterStyle.ofDefaultStyle();
        }
        return this;
    }

    /**
     * Dynamically set the album to start and exit the animation
     *
     *  style Activity Launch exit animation theme
     * @return
     */
    public PictureSelectionModel setPictureWindowAnimationStyle(PictureWindowAnimationStyle windowAnimationStyle) {
        if (windowAnimationStyle != null) {
            PictureSelectionConfig.windowAnimationStyle = windowAnimationStyle;
        } else {
            PictureSelectionConfig.windowAnimationStyle = PictureWindowAnimationStyle.ofDefaultWindowAnimationStyle();
        }
        return this;
    }

    /**
     * Photo album list animation {}
     * Use {@link AnimationType#ALPHA_IN_ANIMATION or SLIDE_IN_BOTTOM_ANIMATION} directly.
     *
     * @param animationMode
     * @return
     */
    public PictureSelectionModel setRecyclerAnimationMode(int animationMode) {
        selectionConfig.animationMode = animationMode;
        return this;
    }

    /**
     * # If you want to handle the Android Q path, if not, just return the uri，
     * The getAndroidQToPath(); field will be empty
     *
     * @param isAndroidQTransform
     * @return
     */
    public PictureSelectionModel isAndroidQTransform(boolean isAndroidQTransform) {
        selectionConfig.isAndroidQTransform = isAndroidQTransform;
        return this;
    }

    /**
     * # 内部方法-要使用此方法时最好先咨询作者！！！
     *
     * @param isFallbackVersion 仅供特殊情况内部使用 如果某功能出错此开关可以回退至之前版本
     * @return
     */
    public PictureSelectionModel isFallbackVersion(boolean isFallbackVersion) {
        selectionConfig.isFallbackVersion = isFallbackVersion;
        return this;
    }

    /**
     * # 内部方法-要使用此方法时最好先咨询作者！！！
     *
     * @param isFallbackVersion 仅供特殊情况内部使用 如果某功能出错此开关可以回退至之前版本
     * @return
     */
    public PictureSelectionModel isFallbackVersion2(boolean isFallbackVersion) {
        selectionConfig.isFallbackVersion2 = isFallbackVersion;
        return this;
    }

    /**
     * # 内部方法-要使用此方法时最好先咨询作者！！！
     *
     * @param isFallbackVersion 仅供特殊情况内部使用 如果某功能出错此开关可以回退至之前版本
     * @return
     */
    public PictureSelectionModel isFallbackVersion3(boolean isFallbackVersion) {
        selectionConfig.isFallbackVersion3 = isFallbackVersion;
        return this;
    }

    /**
     * Start to select media and wait for result.
     *
     * @param requestCode Identity of the request Activity or Fragment.
     */
    public void forResult(int requestCode) {
        if (!DoubleUtils.isFastDoubleClick()) {
            Activity activity = selector.getActivity();
            if (activity == null || selectionConfig == null) {
                return;
            }
            Intent intent;
//            if (selectionConfig.camera && selectionConfig.isUseCustomCamera) {
//                intent = new Intent(activity, PictureCustomCameraActivity.class);
//            } else {
            intent = new Intent(activity, selectionConfig.camera
                    ? PictureSelectorCameraEmptyActivity.class :
                    selectionConfig.isWeChatStyle ? PictureSelectorWeChatStyleActivity.class
                            : PictureSelectorActivity.class);
//            }
            selectionConfig.isCallbackMode = false;
            Fragment fragment = selector.getFragment();
            if (fragment != null) {
                fragment.startActivityForResult(intent, requestCode);
            } else {
                activity.startActivityForResult(intent, requestCode);
            }
            PictureWindowAnimationStyle windowAnimationStyle = PictureSelectionConfig.windowAnimationStyle;
            activity.overridePendingTransition(windowAnimationStyle.activityEnterAnimation, R.anim.picture_anim_fade_in);
        }
    }

    /**
     * # replace for setPictureWindowAnimationStyle();
     * Start to select media and wait for result.
     * <p>
     * # Use PictureWindowAnimationStyle to achieve animation effects
     *
     * @param requestCode Identity of the request Activity or Fragment.
     */
    @Deprecated
    public void forResult(int requestCode, int enterAnim, int exitAnim) {
        if (!DoubleUtils.isFastDoubleClick()) {
            Activity activity = selector.getActivity();
            if (activity == null) {
                return;
            }
            Intent intent = new Intent(activity, selectionConfig != null && selectionConfig.camera
                    ? PictureSelectorCameraEmptyActivity.class :
                    selectionConfig.isWeChatStyle ? PictureSelectorWeChatStyleActivity.class :
                            PictureSelectorActivity.class);
            selectionConfig.isCallbackMode = false;
            Fragment fragment = selector.getFragment();
            if (fragment != null) {
                fragment.startActivityForResult(intent, requestCode);
            } else {
                activity.startActivityForResult(intent, requestCode);
            }
            activity.overridePendingTransition(enterAnim, exitAnim);
        }
    }


    /**
     * Start to select media and wait for result.
     *
     * @param listener The resulting callback listens
     */
    public void forResult(OnResultCallbackListener listener) {
        if (!DoubleUtils.isFastDoubleClick()) {
            Activity activity = selector.getActivity();
            if (activity == null || selectionConfig == null) {
                return;
            }
            // 绑定回调监听
            PictureSelectionConfig.listener = new WeakReference<>(listener).get();
            selectionConfig.isCallbackMode = true;
            Intent intent;
//            if (selectionConfig.camera && selectionConfig.isUseCustomCamera) {
//                intent = new Intent(activity, PictureCustomCameraActivity.class);
//            } else {
            intent = new Intent(activity, selectionConfig.camera
                    ? PictureSelectorCameraEmptyActivity.class :
                    selectionConfig.isWeChatStyle ? PictureSelectorWeChatStyleActivity.class
                            : PictureSelectorActivity.class);
//            }
            Fragment fragment = selector.getFragment();
            if (fragment != null) {
                fragment.startActivity(intent);
            } else {
                activity.startActivity(intent);
            }
            PictureWindowAnimationStyle windowAnimationStyle = PictureSelectionConfig.windowAnimationStyle;
            activity.overridePendingTransition(
                    windowAnimationStyle.activityEnterAnimation, R.anim.picture_anim_fade_in);
        }
    }

    /**
     * Start to select media and wait for result.
     *
     * @param requestCode Identity of the request Activity or Fragment.
     * @param listener    The resulting callback listens
     */
    public void forResult(int requestCode, OnResultCallbackListener listener) {
        if (!DoubleUtils.isFastDoubleClick()) {
            Activity activity = selector.getActivity();
            if (activity == null || selectionConfig == null) {
                return;
            }
            // 绑定回调监听
            PictureSelectionConfig.listener = new WeakReference<>(listener).get();
            selectionConfig.isCallbackMode = true;
            Intent intent;
//            if (selectionConfig.camera && selectionConfig.isUseCustomCamera) {
//                intent = new Intent(activity, PictureCustomCameraActivity.class);
//            } else {
            intent = new Intent(activity, selectionConfig.camera
                    ? PictureSelectorCameraEmptyActivity.class :
                    selectionConfig.isWeChatStyle ? PictureSelectorWeChatStyleActivity.class
                            : PictureSelectorActivity.class);
//            }
            Fragment fragment = selector.getFragment();
            if (fragment != null) {
                fragment.startActivityForResult(intent, requestCode);
            } else {
                activity.startActivityForResult(intent, requestCode);
            }
            PictureWindowAnimationStyle windowAnimationStyle = PictureSelectionConfig.windowAnimationStyle;
            activity.overridePendingTransition(windowAnimationStyle.activityEnterAnimation, R.anim.picture_anim_fade_in);
        }
    }

    /**
     * set preview video
     *
     * @param path
     */
    public void externalPictureVideo(String path) {
        if (selector != null) {
            selector.externalPictureVideo(path);
        } else {
            throw new NullPointerException("This PictureSelector is Null");
        }
    }
}
