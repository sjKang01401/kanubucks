package kanubucks;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Menu_table")
public class Menu {

    private Integer id;
    private String name;
    private String temp;
    private String option;
    private Integer price;

    @PostPersist
    public void onPostPersist(){
        MenuCreated menuCreated = new MenuCreated();
        BeanUtils.copyProperties(this, menuCreated);
        menuCreated.publishAfterCommit();

    }
    @PostUpdate
    public void onPostUpdate(){
        MenuUpdated menuUpdated = new MenuUpdated();
        BeanUtils.copyProperties(this, menuUpdated);
        menuUpdated.publishAfterCommit();

    }
    @PrePersist
    public void onPrePersist(){
    }
    @PreUpdate
    public void onPreUpdate(){
    }
    @PreRemove
    public void onPreRemove(){
        MenuDeleted menuDeleted = new MenuDeleted();
        BeanUtils.copyProperties(this, menuDeleted);
        menuDeleted.publishAfterCommit();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }




}