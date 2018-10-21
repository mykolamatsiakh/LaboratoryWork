package laboratorywork.model;


public class Dog {
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    String imageUrl;

   public Dog(String imageURL){
        this.imageUrl = imageURL;
    }
}
