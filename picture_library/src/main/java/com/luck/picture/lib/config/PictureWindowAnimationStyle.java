package com.luck.picture.lib.config;

import android.os.Parcel;
import android.os.Parcelable;

import com.luck.picture.lib.R;

import androidx.annotation.AnimRes;

/**
 * @author：luck
 * @date：2019-11-25 18:17
 * @describe：PictureSelector Activity动画管理Style
 */
public class PictureWindowAnimationStyle implements Parcelable {
    /**
     * 相册启动动画
     */
    @AnimRes
    public int activityEnterAnimation;

    /**
     * 相册退出动画
     */
    @AnimRes
    public int activityExitAnimation;

    /**
     * 预览界面启动动画
     */
    @AnimRes
    public int activityPreviewEnterAnimation;

    /**
     * 预览界面退出动画
     */
    @AnimRes
    public int activityPreviewExitAnimation;




    public PictureWindowAnimationStyle() {
        super();
    }

    public PictureWindowAnimationStyle(@AnimRes int activityEnterAnimation,
                                       @AnimRes int activityExitAnimation) {
        super();
        this.activityEnterAnimation = activityEnterAnimation;
        this.activityExitAnimation = activityExitAnimation;
        this.activityPreviewEnterAnimation = activityEnterAnimation;
        this.activityPreviewExitAnimation = activityExitAnimation;
    }


    /**
     * 默认WindowAnimationStyle
     *
     * @return this
     */
    public static PictureWindowAnimationStyle ofDefaultWindowAnimationStyle() {
        return new PictureWindowAnimationStyle(R.anim.picture_anim_enter, R.anim.picture_anim_exit);
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.activityEnterAnimation);
        dest.writeInt(this.activityExitAnimation);
        dest.writeInt(this.activityPreviewEnterAnimation);
        dest.writeInt(this.activityPreviewExitAnimation);
    }

    protected PictureWindowAnimationStyle(Parcel in) {
        this.activityEnterAnimation = in.readInt();
        this.activityExitAnimation = in.readInt();
        this.activityPreviewEnterAnimation = in.readInt();
        this.activityPreviewExitAnimation = in.readInt();
    }

    public static final Parcelable.Creator<PictureWindowAnimationStyle> CREATOR = new Parcelable.Creator<PictureWindowAnimationStyle>() {
        @Override
        public PictureWindowAnimationStyle createFromParcel(Parcel source) {
            return new PictureWindowAnimationStyle(source);
        }

        @Override
        public PictureWindowAnimationStyle[] newArray(int size) {
            return new PictureWindowAnimationStyle[size];
        }
    };
}
