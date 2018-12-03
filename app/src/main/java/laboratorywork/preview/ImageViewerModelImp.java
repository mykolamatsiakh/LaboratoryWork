package laboratorywork.preview;

import laboratorywork.LaboratoryWorkApplication;

public class ImageViewerModelImp implements ImageViewerModel {
    private ImageViewerModel.OnFinishedListener mOnFinishedListener;

    ImageViewerModelImp(OnFinishedListener onFinishedListener) {
        mOnFinishedListener = onFinishedListener;
    }

    @Override
    public void getDog() {
        mOnFinishedListener.setDog(LaboratoryWorkApplication.getDogModel().getImageUrl());
    }
}
