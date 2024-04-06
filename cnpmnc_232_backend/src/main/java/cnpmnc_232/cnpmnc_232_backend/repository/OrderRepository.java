package cnpmnc_232.cnpmnc_232_backend.repository;

import cnpmnc_232.cnpmnc_232_backend.entity.Item;
import cnpmnc_232.cnpmnc_232_backend.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
