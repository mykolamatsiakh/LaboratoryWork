package laboratorywork.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModel {
    @SerializedName("status")
    private String mStatus;

    @SerializedName("message")
    private List<String> mMessage = null;

    public List<String> getMessage() {
        return mMessage;
    }

    public void setMessage(List<String> message) {
        this.mMessage = message;
    }
}
