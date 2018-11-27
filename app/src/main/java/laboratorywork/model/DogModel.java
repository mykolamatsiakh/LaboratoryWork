package laboratorywork.model;


import android.os.Parcel;
import android.os.Parcelable;

public class DogModel implements Parcelable{
    public final String imageUrl;

    public DogModel(Parcel in) {
        imageUrl = in.readString();
    }

    public DogModel(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
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

}
