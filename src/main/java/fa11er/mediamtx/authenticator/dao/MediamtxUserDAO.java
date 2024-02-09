package fa11er.mediamtx.authenticator.dao;

import fa11er.mediamtx.authenticator.entity.PublishUser;
import fa11er.mediamtx.authenticator.entity.ReadUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MediamtxUserDAO implements UserDAO {

    private static final String HQL_PUBLISH_USER_SELECT =
            "FROM PublishUser WHERE login =:publishUserLogin AND password =:publishUserPassword";

    private static final String HQL_READ_USER_SELECT =
            "FROM ReadUser WHERE login =:readUserLogin AND password =:readUserPassword";

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Cacheable("publishUsers")
    public boolean isPublishUserExist(String login, String password) {
        Session session = entityManager.unwrap(Session.class);
        Query<PublishUser> query = session.createQuery
                (HQL_PUBLISH_USER_SELECT.formatted(),
                        PublishUser.class);
        query.setParameter("publishUserLogin", login);
        query.setParameter("publishUserPassword", password);
        List<PublishUser> foundUsers = query.getResultList();
        return foundUsers != null && !foundUsers.isEmpty();
    }

    @Override
    @Cacheable("readUsers")
    public boolean isReadUserExist(String login, String password) {
        Session session = entityManager.unwrap(Session.class);
        Query<ReadUser> query = session.createQuery
                (HQL_READ_USER_SELECT.formatted(),
                        ReadUser.class);
        query.setParameter("readUserLogin", login);
        query.setParameter("readUserPassword", password);
        List<ReadUser> foundUsers = query.getResultList();
        return foundUsers != null && !foundUsers.isEmpty();
    }
}
