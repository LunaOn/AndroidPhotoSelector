package com.luck.picture.lib.config;

import android.os.Parcel;
import android.os.Parcelable;

import com.luck.picture.lib.engine.ImageEngine;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.style.PictureWindowAnimationStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：luck
 * @date：2017-05-24 17:02
 * @describe：PictureSelector Config
 */

public final class PictureSelectionConfig implements Parcelable {
    public int chooseMode = PictureMimeType.ofImage();
    public boolean isSingleDirectReturn;
    public static PictureWindowAnimationStyle windowAnimationStyle = PictureWindowAnimationStyle.ofDefaultWindowAnimationStyle();
    public String suffixType;
    public String specifiedFormat;
    public boolean isCameraAroundState;
    public boolean isAndroidQTransform;
    public int selectionMode = PictureConfig.MULTIPLE;
    public int maxSelectNum = 9;
    public int minSelectNum = 0;
    public int maxVideoSelectNum = 1;
    public int minVideoSelectNum = 0;
    public int videoQuality = 1;
    public int videoMaxSecond;
    public int videoMinSecond;
    public int recordVideoSecond = 60;
    public int imageSpanCount = PictureConfig.DEFAULT_SPAN_COUNT;
    public float filterFileSize;
    public int language;
    public boolean isWeChatStyle;
    public boolean zoomAnim;
    public boolean isCamera = true;
    public boolean isGif;
    public boolean isWebp;
    public boolean isBmp;
    public boolean enablePreview;
    public boolean enPreviewVideo;
    public boolean enablePreviewAudio;
    public boolean checkNumMode;
    public boolean openClickSound;
    public boolean returnEmpty;
    public boolean isWithVideoImage;
    public static ImageEngine imageEngine;
    public static OnResultCallbackListener<LocalMedia> listener;
    public List<LocalMedia> selectionMedias;
    public String cameraFileName;
    public String outPutCameraPath;

    public String cameraPath;
    public int cameraMimeType = -1;
    public int pageSize = PictureConfig.MAX_PAGE_SIZE;
    public boolean isPageStrategy = true;
    public boolean isFilterInvalidFile;
    public boolean isMaxSelectEnabledMask;
    public int animationMode = -1;
    public boolean isAutomaticTitleRecyclerTop = true;
    public boolean isCallbackMode;
    public boolean isAndroidQChangeWH;
    public boolean isAndroidQChangeVideoWH;
    public boolean isQuickCapture = true;
    /**
     * 内测专用###########
     */
    public boolean isFallbackVersion;
    public boolean isFallbackVersion2;
    public boolean isFallbackVersion3;

    protected void initDefaultValue() {
        chooseMode = PictureMimeType.ofImage();
        selectionMode = PictureConfig.MULTIPLE;
        maxSelectNum = 9;
        minSelectNum = 0;
        maxVideoSelectNum = 1;
        minVideoSelectNum = 0;
        videoQuality = 1;
        language = -1;
        videoMaxSecond = 0;
        videoMinSecond = 0;
        filterFileSize = -1;
        recordVideoSecond = 60;
        imageSpanCount = PictureConfig.DEFAULT_SPAN_COUNT;
        isCameraAroundState = false;
        isWithVideoImage = false;
        isAndroidQTransform = false;
        isCamera = true;
        isGif = false;
        isWebp = true;
        isBmp = true;
        isSingleDirectReturn = false;
        enablePreview = true;
        enPreviewVideo = true;
        enablePreviewAudio = true;
        checkNumMode = false;
        openClickSound = false;
        isFallbackVersion = false;
        isFallbackVersion2 = true;
        isFallbackVersion3 = true;
        isWeChatStyle = false;
        returnEmpty = false;
        zoomAnim = true;
        suffixType = "";
        cameraFileName = "";
        specifiedFormat = "";
        selectionMedias = new ArrayList<>();
        outPutCameraPath = "";
        cameraPath = "";
        cameraMimeType = -1;
        pageSize = PictureConfig.MAX_PAGE_SIZE;
        isPageStrategy = true;
        isFilterInvalidFile = false;
        isMaxSelectEnabledMask = false;
        animationMode = -1;
        isAutomaticTitleRecyclerTop = true;
        isCallbackMode = false;
        isAndroidQChangeWH = true;
        isAndroidQChangeVideoWH = false;
        isQuickCapture = true;
    }

    public static PictureSelectionConfig getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public static PictureSelectionConfig getCleanInstance() {
        PictureSelectionConfig selectionSpec = getInstance();
        selectionSpec.initDefaultValue();
        return selectionSpec;
    }

    private static final class InstanceHolder {
        private static final PictureSelectionConfig INSTANCE = new PictureSelectionConfig();
    }

    public PictureSelectionConfig() {
    }

    /**
     * 释放监听器
     */
    public static void destroy() {
        PictureSelectionConfig.listener = null;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.chooseMode);
        dest.writeByte(this.isSingleDirectReturn ? (byte) 1 : (byte) 0);
        dest.writeString(this.suffixType);
        dest.writeString(this.specifiedFormat);
        dest.writeByte(this.isCameraAroundState ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isAndroidQTransform ? (byte) 1 : (byte) 0);
        dest.writeInt(this.selectionMode);
        dest.writeInt(this.maxSelectNum);
        dest.writeInt(this.minSelectNum);
        dest.writeInt(this.maxVideoSelectNum);
        dest.writeInt(this.minVideoSelectNum);
        dest.writeInt(this.videoQuality);
        dest.writeInt(this.videoMaxSecond);
        dest.writeInt(this.videoMinSecond);
        dest.writeInt(this.recordVideoSecond);
        dest.writeInt(this.imageSpanCount);
        dest.writeFloat(this.filterFileSize);
        dest.writeInt(this.language);
        dest.writeByte(this.isWeChatStyle ? (byte) 1 : (byte) 0);
        dest.writeByte(this.zoomAnim ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isCamera ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isGif ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isWebp ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isBmp ? (byte) 1 : (byte) 0);
        dest.writeByte(this.enablePreview ? (byte) 1 : (byte) 0);
        dest.writeByte(this.enPreviewVideo ? (byte) 1 : (byte) 0);
        dest.writeByte(this.enablePreviewAudio ? (byte) 1 : (byte) 0);
        dest.writeByte(this.checkNumMode ? (byte) 1 : (byte) 0);
        dest.writeByte(this.openClickSound ? (byte) 1 : (byte) 0);
        dest.writeByte(this.returnEmpty ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isWithVideoImage ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.selectionMedias);
        dest.writeString(this.cameraFileName);
        dest.writeString(this.outPutCameraPath);
        dest.writeString(this.cameraPath);
        dest.writeInt(this.cameraMimeType);
        dest.writeInt(this.pageSize);
        dest.writeByte(this.isPageStrategy ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isFilterInvalidFile ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isMaxSelectEnabledMask ? (byte) 1 : (byte) 0);
        dest.writeInt(this.animationMode);
        dest.writeByte(this.isAutomaticTitleRecyclerTop ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isCallbackMode ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isAndroidQChangeWH ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isAndroidQChangeVideoWH ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isQuickCapture ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isFallbackVersion ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isFallbackVersion2 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isFallbackVersion3 ? (byte) 1 : (byte) 0);
    }

    protected PictureSelectionConfig(Parcel in) {
        this.chooseMode = in.readInt();
        this.isSingleDirectReturn = in.readByte() != 0;
        this.suffixType = in.readString();
        this.specifiedFormat = in.readString();
//        this.buttonFeatures = in.readInt();
        this.isCameraAroundState = in.readByte() != 0;
        this.isAndroidQTransform = in.readByte() != 0;
        this.selectionMode = in.readInt();
        this.maxSelectNum = in.readInt();
        this.minSelectNum = in.readInt();
        this.maxVideoSelectNum = in.readInt();
        this.minVideoSelectNum = in.readInt();
        this.videoQuality = in.readInt();
        this.videoMaxSecond = in.readInt();
        this.videoMinSecond = in.readInt();
        this.recordVideoSecond = in.readInt();
        this.imageSpanCount = in.readInt();
        this.filterFileSize = in.readFloat();
        this.language = in.readInt();
        this.isWeChatStyle = in.readByte() != 0;
        this.zoomAnim = in.readByte() != 0;
        this.isCamera = in.readByte() != 0;
        this.isGif = in.readByte() != 0;
        this.isWebp = in.readByte() != 0;
        this.isBmp = in.readByte() != 0;
        this.enablePreview = in.readByte() != 0;
        this.enPreviewVideo = in.readByte() != 0;
        this.enablePreviewAudio = in.readByte() != 0;
        this.checkNumMode = in.readByte() != 0;
        this.openClickSound = in.readByte() != 0;
        this.returnEmpty = in.readByte() != 0;
        this.isWithVideoImage = in.readByte() != 0;
        this.selectionMedias = in.createTypedArrayList(LocalMedia.CREATOR);
        this.cameraFileName = in.readString();
        this.outPutCameraPath = in.readString();
        this.cameraPath = in.readString();
        this.cameraMimeType = in.readInt();
        this.pageSize = in.readInt();
        this.isPageStrategy = in.readByte() != 0;
        this.isFilterInvalidFile = in.readByte() != 0;
        this.isMaxSelectEnabledMask = in.readByte() != 0;
        this.animationMode = in.readInt();
        this.isAutomaticTitleRecyclerTop = in.readByte() != 0;
        this.isCallbackMode = in.readByte() != 0;
        this.isAndroidQChangeWH = in.readByte() != 0;
        this.isAndroidQChangeVideoWH = in.readByte() != 0;
        this.isQuickCapture = in.readByte() != 0;
        this.isFallbackVersion = in.readByte() != 0;
        this.isFallbackVersion2 = in.readByte() != 0;
        this.isFallbackVersion3 = in.readByte() != 0;
    }

    public static final Creator<PictureSelectionConfig> CREATOR = new Creator<PictureSelectionConfig>() {
        @Override
        public PictureSelectionConfig createFromParcel(Parcel source) {
            return new PictureSelectionConfig(source);
        }

        @Override
        public PictureSelectionConfig[] newArray(int size) {
            return new PictureSelectionConfig[size];
        }
    };
}
