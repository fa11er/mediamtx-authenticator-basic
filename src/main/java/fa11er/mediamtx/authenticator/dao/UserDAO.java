package fa11er.mediamtx.authenticator.dao;

public interface UserDAO {
    boolean isPublishUserExist(String login, String password);
    boolean isReadUserExist(String login, String password);
}
