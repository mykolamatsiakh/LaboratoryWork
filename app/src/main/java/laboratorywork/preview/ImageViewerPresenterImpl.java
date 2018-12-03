package laboratorywork.preview;

public class ImageViewerPresenterImpl implements ImageViewerPresenter,
        ImageViewerModel.OnFinishedListener{
    ImageViewerModel mImageViewModel;
    ImageViewerView mImageViewerView;

    public ImageViewerPresenterImpl(ImageViewerView imageViewerView){
        mImageViewerView = imageViewerView;
        mImageViewModel = new ImageViewerModelImp(this);
    }

    @Override
    public void setDog(String path) {
        mImageViewerView.setDog(path);
    }

    @Override
    public void getDog() {
        mImageViewModel.getDog();
    }
}
