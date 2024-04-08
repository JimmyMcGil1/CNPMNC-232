package cnpmnc_232.cnpmnc_232_backend.repository;

import cnpmnc_232.cnpmnc_232_backend.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Integer> {
}
