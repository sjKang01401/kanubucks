package kanubucks;

public class MenuUpdated extends AbstractEvent {

    private Long id;
    private String name;
    private String temp;
    private String option;
    private Integer price;

    public MenuUpdated(){
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

    public void setOption(String option) {
        this.option = option;
    }
    public String getOption() {
        return option;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getPrice() {
        return price;
    }
}
