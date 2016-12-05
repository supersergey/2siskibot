package ua.kiev.supersergey.siski_bot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * Created by sergey on 29.11.2016.
 */
public class Update {
    @JsonProperty("ok")
    private String status;
    private UpdateBody[] result;

    public Update() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UpdateBody[] getResult() {
        return result;
    }

    public void setResult(UpdateBody[] result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Update{" +
                "status='" + status + '\'' +
                ", result=" + Arrays.toString(result) +
                '}';
    }
}
