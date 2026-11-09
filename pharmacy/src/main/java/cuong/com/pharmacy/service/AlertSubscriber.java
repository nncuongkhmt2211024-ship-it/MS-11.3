package cuong.com.pharmacy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import cuong.com.pharmacy.dto.AlertMessage;
import org.springframework.stereotype.Service;

@Service
public class AlertSubscriber {

    private final ObjectMapper objectMapper = new ObjectMapper();

    // Hàm này sẽ tự động được gọi khi có tin nhắn mới đẩy vào channel
    public void receiveMessage(String message) {
        try {
            // Dùng ObjectMapper chuyển chuỗi JSON thành đối tượng
            AlertMessage alert = objectMapper.readValue(message, AlertMessage.class);

            System.out.println("=== THÔNG BÁO DASHBOARD QUẢN LÝ ===");
            System.out.println("Type : " + alert.getType());
            System.out.println("Nội dung: " + alert.getMessage());
            System.out.println("========================================");

        } catch (Exception e) {
            System.err.println("Lỗi khi parse tin nhắn: " + e.getMessage());
        }
    }
}