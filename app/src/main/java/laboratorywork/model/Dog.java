package laboratorywork.model;


public class Dog {
    private static int counter = 0;
    public String getImageUrl() {
        return imageUrl;
    }


    private String imageUrl;

   public Dog(String imageURL){
        this.imageUrl = imageURL;
        counter++;
    }

    public static int getCounter() {
        return counter;
    }



}
