package kanubucks;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatusTableRepository extends CrudRepository<StatusTable, Long> {

    List<StatusTable> findAll();
    List<StatusTable> findByOrderId(Long orderId);
    List<StatusTable> findByStatusOrderByTimeDesc(List<String> status);
}