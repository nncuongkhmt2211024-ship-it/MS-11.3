package cuong.com.pharmacy.config;

import cuong.com.pharmacy.service.AlertSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisPubSubConfig {

    // Liên kết Subscriber service với phương thức "receiveMessage"
    @Bean
    public MessageListenerAdapter listenerAdapter(AlertSubscriber subscriber) {
        return new MessageListenerAdapter(subscriber, "receiveMessage");
    }

    // Đăng ký container lắng nghe channel "pharmacy-alerts"
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        // Đăng ký lắng nghe channel cụ thể
        container.addMessageListener(listenerAdapter, new PatternTopic("pharmacy-alerts"));
        return container;
    }
}