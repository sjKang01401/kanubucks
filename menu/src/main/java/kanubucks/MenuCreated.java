package kanubucks;

public class MenuCreated extends AbstractEvent {

    private Long id;
    private String name;
    private String temp;
    private String option;
    private Integer price;

    public MenuCreated() {
        super();
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
    public String getTemp() {
        return temp;
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
