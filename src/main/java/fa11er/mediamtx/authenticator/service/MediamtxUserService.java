package fa11er.mediamtx.authenticator.service;

import fa11er.mediamtx.authenticator.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediamtxUserService implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean isPublishUserExist(String login, String password) {
        return userDAO.isPublishUserExist(login, password);
    }

    @Override
    public boolean isReadUserExist(String login, String password) {
        return userDAO.isReadUserExist(login, password);
    }
}
