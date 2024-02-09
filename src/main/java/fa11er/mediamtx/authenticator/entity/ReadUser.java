package fa11er.mediamtx.authenticator.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.io.Serializable;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = ReadUser.READ_USER_TABLE_NAME)
public class ReadUser implements Serializable {

    protected static final String READ_USER_TABLE_NAME = "users_read";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    public ReadUser() {

    }

//    public String getLogin() {
//        return login;
//    }

 //   public String getPassword() {
 //       return password;
 //   }
}
