package kanubucks;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="_table")
public class  {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

}
