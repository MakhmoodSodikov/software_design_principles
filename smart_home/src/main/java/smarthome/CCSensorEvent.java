package smarthome;

public class CCSensorEvent {
    private final String eventType;
    private final String objectId;

    public CCSensorEvent(String eventType, String objectId) {
        this.eventType = eventType;
        this.objectId = objectId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getObjectId() {
        return objectId;
    }

}