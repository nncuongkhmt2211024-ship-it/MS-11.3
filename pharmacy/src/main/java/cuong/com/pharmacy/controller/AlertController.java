package cuong.com.pharmacy.controller;

import cuong.com.pharmacy.dto.AlertMessage;
import cuong.com.pharmacy.service.AlertPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/alerts")
public class AlertController {

    private final AlertPublisher alertPublisher;

    public AlertController(AlertPublisher alertPublisher) {
        this.alertPublisher = alertPublisher;
    }

    @PostMapping
    public ResponseEntity<String> sendAlert(@RequestBody AlertMessage alertMessage) {
        // Gửi tin nhắn đi
        alertPublisher.publishAlert(alertMessage);

        // Trả về response dạng chuỗi (String) giống hình Postman của bạn
        return ResponseEntity.ok("Đã gửi thông báo đến kênh : " + alertMessage.getTopicListen());
    }
}