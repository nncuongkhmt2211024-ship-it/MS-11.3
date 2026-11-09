package cuong.com.pharmacy.dto;

public class AlertMessage {
    private String topicListen;
    private String type;
    private String message;

    // Getters và Setters
    public String getTopicListen() {
        return topicListen;
    }

    public void setTopicListen(String topicListen) {
        this.topicListen = topicListen;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}