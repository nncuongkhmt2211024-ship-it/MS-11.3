package cuong.com.pharmacy.service;



import cuong.com.pharmacy.entity.Medicine;
import cuong.com.pharmacy.repository.MedicineRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }


    @Cacheable(value = "medicines", key = "#id")
    public Medicine getMedicineById(Long id) {
        System.out.println("===========> TRUY VẤN DATABASE: Đang tìm thuốc với ID: " + id);
        return medicineRepository.findById(id).orElse(null);
    }

    // Hàm này dùng để thêm dữ liệu test
    public Medicine createMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    @CacheEvict(value = "medicines", key = "#id")
    public Medicine updateMedicine(Long id, Medicine updatedMedicine) {
        System.out.println("===========> CẬP NHẬT DATABASE VÀ XÓA CACHE: Thuốc ID: " + id);
        Medicine existing = medicineRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(updatedMedicine.getName());
            existing.setDescription(updatedMedicine.getDescription());
            existing.setPrice(updatedMedicine.getPrice());
            return medicineRepository.save(existing);
        }
        return null;
    }
}

