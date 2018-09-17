package iot.nulp.com.laboratorywork.screens.Prefs;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Roman on 01-Apr-18.
 */

public class User implements Parcelable {

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private final String mName;
    private final String mSurname;
    private final String mPhoneNumber;


    User(String name,
         String surname,
         String phone) {
        mName = name;
        mSurname = surname;
        mPhoneNumber = phone;
    }

    private User(Parcel in) {
        mName = in.readString();
        mSurname = in.readString();
        mPhoneNumber = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
        parcel.writeString(mSurname);
        parcel.writeString(mPhoneNumber);
    }

    public String getSurname() {
        return mSurname;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public String getName() {
        return mName;
    }

    public static class Builder {

        private String mName;
        private String mSurname;
        private String mPhoneNumber;



        public Builder setName(String name) {
            mName = name;
            return this;
        }

        public Builder setSurname(String surname) {
            mSurname = surname;
            return this;
        }

        public Builder setPhone(String email) {
            mPhoneNumber = email;
            return this;
        }


        public User build() {
            return new User(mName, mSurname, mPhoneNumber);
        }
    }
}
