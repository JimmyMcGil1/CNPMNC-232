package cnpmnc_232.cnpmnc_232_backend.repository;

import cnpmnc_232.cnpmnc_232_backend.entity.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Modifying
    @Query(value = "delete from ORDER_ where id = :id", nativeQuery = true )
    public void deleteOrderById(Integer id);
    @Modifying
    @Query(value = "CALL calculate_total_cost(:id)", nativeQuery = true)
    public void calToalCost(Integer id);
}
