package com.androidbelieve.drawerwithswipetabs.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by teiyuueki on 2016/04/23.
 */
public class MatchItem implements Parcelable{
    public String id;
    public String name;

    public MatchItem(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public MatchItem(Parcel in){
        this.id = in.readString();
        this.name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(id);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }//FileDescriptor未使用の場合は0

    // 今は定型文という認識で
    public static final Creator<MatchItem> CREATOR = new Parcelable.Creator<MatchItem>() {
        public MatchItem createFromParcel(Parcel in) {
            return new MatchItem(in);
        }

        public MatchItem[] newArray(int size) {
            return new MatchItem[size];
        }
    };
}
