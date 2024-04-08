package cnpmnc_232.cnpmnc_232_backend.repository;

import cnpmnc_232.cnpmnc_232_backend.entity.OrderItem.OrderItem;
import cnpmnc_232.cnpmnc_232_backend.entity.OrderItem.OrderItemId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Transactional
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
    @Query(value = "SELECT * FROM ORDER_ITEM WHERE ID_ORDER = :order", nativeQuery = true)
    public List<OrderItem> findByOrder(Integer order);
    @Modifying
    @Query(value = "DELETE FROM ORDER_ITEM WHERE ID_ITEM = :itemId AND ID_ORDER = :orderId", nativeQuery = true)
    public void removeItem(Integer itemId, Integer orderId);
}
