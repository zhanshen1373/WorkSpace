package com.example.hd.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dubojian on 2018/12/6.
 */

public class Food implements Parcelable{

    private int price;
    private String type;


    protected Food(Parcel in) {
        price = in.readInt();
        type = in.readString();
    }

    public Food(int price, String type) {
        this.price = price;
        this.type = type;
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(price);
        dest.writeString(type);
    }

    @Override
    public String toString() {
        return getPrice()+".."+getType();
    }
}
