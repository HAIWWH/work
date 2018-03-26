package app.cddic.com.smarter.entity;

/**
 * Created by yfs on 5/26 0026.
 */

public class UpdateRetMSG extends MsgObject {
    String Version;
    String url;

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
