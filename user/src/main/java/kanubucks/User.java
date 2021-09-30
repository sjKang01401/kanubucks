package kanubucks;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="User_table")
@Getter @Setter
public class User {

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String phone;
    private String email;
    private Integer stamp;
    private String loginStatus;

    public final static String LOGIN_STATUS = "login";
    public final static String LOGOUT_STATUS = "logout";

    @PrePersist
    public void onPrePersist(){

        this.setStamp(0);
        this.setLoginStatus(User.LOGOUT_STATUS);
    }

    @PreUpdate
    public void onPreUpdate(){

        UserRegistered userRegistered = new UserRegistered();
        BeanUtils.copyProperties(this, userRegistered);
        userRegistered.publishAfterCommit();

    }
}