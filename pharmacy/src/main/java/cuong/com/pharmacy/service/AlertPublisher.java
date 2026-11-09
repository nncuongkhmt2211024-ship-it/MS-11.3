package cuong.com.pharmacy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import cuong.com.pharmacy.dto.AlertMessage;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class AlertPublisher {

    private final StringRedisTemplate stringRedisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AlertPublisher(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void publishAlert(AlertMessage alertMessage) {
        try {
            // Chuyển đối tượng thành chuỗi JSON
            String jsonMessage = objectMapper.writeValueAsString(alertMessage);

            // Bắn tin nhắn vào topic được chỉ định (mặc định lấy từ thuộc tính topicListen)
            String topic = alertMessage.getTopicListen() != null ? alertMessage.getTopicListen() : "pharmacy-alerts";
            stringRedisTemplate.convertAndSend(topic, jsonMessage);

        } catch (Exception e) {
            System.err.println("Lỗi khi gửi tin nhắn: " + e.getMessage());
        }
    }
}