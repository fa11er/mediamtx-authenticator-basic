package fa11er.mediamtx.authenticator.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.io.Serializable;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = PublishUser.PUBLISH_USER_TABLE_NAME)
public class PublishUser implements Serializable {

    protected static final String PUBLISH_USER_TABLE_NAME = "users_publish";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    public PublishUser() {

    }

}
