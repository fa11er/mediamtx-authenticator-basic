package fa11er.mediamtx.authenticator.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthUser implements Serializable {
    private String user;
    private String password;
    private String ip;
    private String action;
    private String path;
    private String protocol;
    private String id;

    public AuthUser() {
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getIp() {
        return ip;
    }

    public String getAction() {
        return action;
    }

    public String getPath() {
        return path;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getId() {
        return id;
    }
}
