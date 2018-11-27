package laboratorywork.model;


import android.os.Parcel;
import android.os.Parcelable;

public class DogModel implements Parcelable{
    private String mImageUrl;

    public DogModel(Parcel in) {
        mImageUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mImageUrl);
    }

    public static final Creator<DogModel> CREATOR = new Creator<DogModel>() {
        @Override
        public DogModel createFromParcel(Parcel in) {
            return new DogModel(in);
        }

        @Override
        public DogModel[] newArray(int size) {
            return new DogModel[size];
        }
    };

    public String getImageUrl() {
        return mImageUrl;
    }

}
