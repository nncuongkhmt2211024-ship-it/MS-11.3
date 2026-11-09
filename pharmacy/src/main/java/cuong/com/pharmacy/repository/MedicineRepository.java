package cuong.com.pharmacy.repository;



import cuong.com.pharmacy.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    // JpaRepository đã cung cấp sẵn các hàm cơ bản như findById, findAll, save, delete...
    // Bạn không cần phải viết thêm hàm lấy chi tiết thuốc bằng ID nữa.
}