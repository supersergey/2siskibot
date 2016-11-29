package ua.kiev.supersergey.siski_bot.entity;

/**
 * Created by sergey on 29.11.2016.
 */
public class Update {
    private String ok;
    private UpdateBody[] result;

    public Update() {
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    public UpdateBody[] getResult() {
        return result;
    }

    public void setResult(UpdateBody[] result) {
        this.result = result;
    }
}
