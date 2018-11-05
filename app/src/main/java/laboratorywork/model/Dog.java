package laboratorywork.model;


public class Dog {
    private static int counter = 0;
    private String mImageUrl;

    public Dog(String imageURL) {
        this.mImageUrl = imageURL;
        counter++;
    }

    public String getImageUrl() {
        return mImageUrl;
    }


    public static int getCounter() {
        return counter;
    }


}
