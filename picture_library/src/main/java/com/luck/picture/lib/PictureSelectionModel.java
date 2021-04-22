package com.luck.picture.lib;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.luck.picture.lib.animators.AnimationType;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.config.PictureSelectionConfig;
import com.luck.picture.lib.engine.ImageEngine;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.style.PictureWindowAnimationStyle;
import com.luck.picture.lib.tools.DoubleUtils;
import com.luck.picture.lib.tools.SdkVersionUtils;

import java.util.List;

import androidx.annotation.ColorInt;
import androidx.fragment.app.Fragment;

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

//
//    /**
//     * Setting PictureSelector UI Style
//     *
//     * @param uiStyle <p>
//     *                {@link PictureSelectorUIStyle}
//     *                </p>
//     * @return
//     */
//    public PictureSelectionModel setPictureUIStyle(PictureSelectorUIStyle uiStyle) {
//        if (uiStyle != null) {
//            PictureSelectionConfig.uiStyle = uiStyle;
//            if (!selectionConfig.isWeChatStyle) {
//                selectionConfig.isWeChatStyle = PictureSelectionConfig.uiStyle.isNewSelectStyle;
//            }
//        }
//        return this;
//    }

    /**
     *  locale Language
     * @return PictureSelectionModel
     */
    public PictureSelectionModel setLanguage(int language) {
        selectionConfig.language = language;
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
     *  Specify get image format
     * @return
     */
    public PictureSelectionModel querySpecifiedFormatSuffix(String specifiedFormat) {
        selectionConfig.specifiedFormat = specifiedFormat;
        return this;
    }

    /**
     *  isOpenClickSound Whether to open click voice
     * @return
     */
    public PictureSelectionModel isOpenClickSound(boolean openClickSound) {
        selectionConfig.openClickSound = openClickSound;
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
     * 开启数字模式：开启后选中会显示数字
     * @return
     */
    public PictureSelectionModel enableNumCheckMode() {
        selectionConfig.checkNumMode = true;
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
            Intent intent = new Intent(activity, selectionConfig.isWeChatStyle ? PictureSelectorWeChatStyleActivity.class
                    : PictureSelectorActivity.class);
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
