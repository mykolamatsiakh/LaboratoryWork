package laboratorywork.model;


public class DogModel {
    private static int mCounter = 0;
    private String mImageUrl;

    public DogModel(String imageURL) {
        this.mImageUrl = imageURL;
        mCounter++;
    }

    public String getImageUrl() {
        return mImageUrl;
    }


    public static int getCounter() {
        return mCounter;
    }


}
