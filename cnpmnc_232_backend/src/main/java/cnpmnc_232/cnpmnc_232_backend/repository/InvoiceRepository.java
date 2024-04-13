package cnpmnc_232.cnpmnc_232_backend.repository;

import cnpmnc_232.cnpmnc_232_backend.entity.Bill;
import cnpmnc_232.cnpmnc_232_backend.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    @Query(value = ("select * from INVOICE where ID_ORDER = :orderId"), nativeQuery = true)
    public Optional<Invoice> findByOrderId(Integer orderId);
}
