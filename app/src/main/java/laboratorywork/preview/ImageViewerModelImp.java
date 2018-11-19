package laboratorywork.preview;

public class ImageViewerModelImp implements ImageViewerModel {
    ImageViewerModel.OnFinishedListener mOnFinishedListener;

    public ImageViewerModelImp(OnFinishedListener onFinishedListener){
        mOnFinishedListener = onFinishedListener;
    }

    @Override
    public void getDog() {

    }
}
