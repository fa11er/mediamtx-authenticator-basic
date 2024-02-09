package fa11er.mediamtx.authenticator.service;

public interface UserService {
    boolean isPublishUserExist(String login, String password);
    boolean isReadUserExist(String login, String password);
}
