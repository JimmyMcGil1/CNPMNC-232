package cnpmnc_232.cnpmnc_232_backend.repository;

import cnpmnc_232.cnpmnc_232_backend.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {
}
