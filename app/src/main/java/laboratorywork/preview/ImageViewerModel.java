package laboratorywork.preview;

public interface ImageViewerModel {

    interface OnFinishedListener {
        void setDog(String path);
    }
    void getDog();
}
