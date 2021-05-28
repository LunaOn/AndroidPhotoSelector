package com.luck.picture.lib.entity;


import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.luck.picture.lib.config.PictureConfig;

/**
 * @author：luck
 * @date：2017-5-24 16:21
 * @describe：Media Entity
 */

public class LocalMedia implements Parcelable {
    /**
     * uri
     */
    private Uri uri;
    /**
     * original path
     */
    private String path;

    /**
     * The real path，But you can't get access from AndroidQ
     * <p>
     * It could be empty
     * <p/>
     */
    private String realPath;

    /**
     * Note: this field is only returned in Android Q version
     * <p>
     * Android Q image or video path
     */
    private String androidQToPath;
    /**
     * video duration
     */
    private long duration;
    /**
     * If the selected
     * # Internal use
     */
    private boolean isChecked;
    /**
     * media position of list
     */
    public int position;
    /**
     * The media number of qq choose styles
     */
    private int num;
    /**
     * The media resource type
     */
    private String mimeType;

    /**
     * Gallery selection mode
     */
    private int chooseModel;

    /**
     * image or video width
     * <p>
     * # If zero occurs, the developer needs to handle it extra
     */
    private int width;
    /**
     * image or video height
     * <p>
     * # If zero occurs, the developer needs to handle it extra
     */
    private int height;

    /**
     * file size
     */
    private long size;

    /**
     * file name
     */
    private String fileName;

    /**
     * Parent  Folder Name
     */
    private String parentFolderName;

    /**
     * orientation info
     * # For internal use only
     */
    private int orientation = -1;

    /**
     * loadLongImageStatus
     * # For internal use only
     */
    public int loadLongImageStatus = PictureConfig.NORMAL;

    /**
     * isLongImage
     * # For internal use only
     */
    public boolean isLongImage;

    /**
     * bucketId
     */
    private long bucketId = -1;

    /**
     * isMaxSelectEnabledMask
     * # For internal use only
     */
    private boolean isMaxSelectEnabledMask;

    public LocalMedia() {
    }

    public LocalMedia(Uri uri, String path, String absolutePath, String fileName, String parentFolderName, long duration, int chooseModel,
                      String mimeType, int width, int height, long size, long bucketId) {
        this.uri = uri;
        this.path = path;
        this.realPath = absolutePath;
        this.fileName = fileName;
        this.parentFolderName = parentFolderName;
        this.duration = duration;
        this.chooseModel = chooseModel;
        this.mimeType = mimeType;
        this.width = width;
        this.height = height;
        this.size = size;
        this.bucketId = bucketId;
    }


    protected LocalMedia(Parcel in) {
        uri = in.readParcelable(Uri.class.getClassLoader());
        path = in.readString();
        realPath = in.readString();
        androidQToPath = in.readString();
        duration = in.readLong();
        isChecked = in.readByte() != 0;
        position = in.readInt();
        num = in.readInt();
        mimeType = in.readString();
        chooseModel = in.readInt();
        width = in.readInt();
        height = in.readInt();
        size = in.readLong();
        fileName = in.readString();
        parentFolderName = in.readString();
        orientation = in.readInt();
        loadLongImageStatus = in.readInt();
        isLongImage = in.readByte() != 0;
        bucketId = in.readLong();
        isMaxSelectEnabledMask = in.readByte() != 0;
    }

    public static final Creator<LocalMedia> CREATOR = new Creator<LocalMedia>() {
        @Override
        public LocalMedia createFromParcel(Parcel in) {
            return new LocalMedia(in);
        }

        @Override
        public LocalMedia[] newArray(int size) {
            return new LocalMedia[size];
        }
    };

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAndroidQToPath() {
        return androidQToPath;
    }

    public void setAndroidQToPath(String androidQToPath) {
        this.androidQToPath = androidQToPath;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getMimeType() {
        return TextUtils.isEmpty(mimeType) ? "image/jpeg" : mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getChooseModel() {
        return chooseModel;
    }

    public void setChooseModel(int chooseModel) {
        this.chooseModel = chooseModel;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getParentFolderName() {
        return parentFolderName;
    }

    public void setParentFolderName(String parentFolderName) {
        this.parentFolderName = parentFolderName;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public long getBucketId() {
        return bucketId;
    }

    public void setBucketId(long bucketId) {
        this.bucketId = bucketId;
    }

    public boolean isMaxSelectEnabledMask() {
        return isMaxSelectEnabledMask;
    }

    public void setMaxSelectEnabledMask(boolean maxSelectEnabledMask) {
        isMaxSelectEnabledMask = maxSelectEnabledMask;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(uri, flags);
        dest.writeString(path);
        dest.writeString(realPath);
        dest.writeString(androidQToPath);
        dest.writeLong(duration);
        dest.writeByte((byte) (isChecked ? 1 : 0));
        dest.writeInt(position);
        dest.writeInt(num);
        dest.writeString(mimeType);
        dest.writeInt(chooseModel);
        dest.writeInt(width);
        dest.writeInt(height);
        dest.writeLong(size);
        dest.writeString(fileName);
        dest.writeString(parentFolderName);
        dest.writeInt(orientation);
        dest.writeInt(loadLongImageStatus);
        dest.writeByte((byte) (isLongImage ? 1 : 0));
        dest.writeLong(bucketId);
        dest.writeByte((byte) (isMaxSelectEnabledMask ? 1 : 0));
    }
}
