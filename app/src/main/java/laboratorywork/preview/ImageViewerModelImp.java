package laboratorywork.preview;

import laboratorywork.LaboratoryWorkApplication;
import laboratorywork.model.DogModel;

public class ImageViewerModelImp implements ImageViewerModel {
    private ImageViewerModel.OnFinishedListener mOnFinishedListener;
    private LaboratoryWorkApplication laboratoryWorkApplication;

    ImageViewerModelImp(OnFinishedListener onFinishedListener) {
        mOnFinishedListener = onFinishedListener;
    }

}
