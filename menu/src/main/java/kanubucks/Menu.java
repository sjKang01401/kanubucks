package kanubucks;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Menu_table")
public class Menu {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    @Column(name = "menu_name")
    private String name;

    @Column(name = "menu_temp")
    private String temp;

    @Column(name = "menu_option")
    private String option;

    @Column(name = "menu_price")
    private Integer price;

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