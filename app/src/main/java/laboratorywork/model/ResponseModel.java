package laboratorywork.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ResponseModel {
        @SerializedName("status")
        private String status;

        @SerializedName("message")
        private List<String> message = null;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<String> getMessage() {
            return message;
        }

        public void setMessage(List<String> message) {
            this.message = message;
        }
}
