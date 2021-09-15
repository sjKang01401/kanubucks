package kanubucks;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="User_table")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String phone;
    private String email;
    private Integer stamp;
    private String loginStatus;

    @PostPersist
    public void onPostPersist(){
    }
    @PrePersist
    public void onPrePersist(){
    }
    @PreUpdate
    public void onPreUpdate(){
        UserRegistered userRegistered = new UserRegistered();
        BeanUtils.copyProperties(this, userRegistered);
        userRegistered.publishAfterCommit();

        Logined logined = new Logined();
        BeanUtils.copyProperties(this, logined);
        logined.publishAfterCommit();

        LoggedOut loggedOut = new LoggedOut();
        BeanUtils.copyProperties(this, loggedOut);
        loggedOut.publishAfterCommit();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getStamp() {
        return stamp;
    }

    public void setStamp(Integer stamp) {
        this.stamp = stamp;
    }
    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }




}