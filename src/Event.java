public class Event {
    int initTime; // time when the event was created
    int duration; // duration of the event
    int timeToHappen; // time when the event will happen (initTime + duration)
    
    int type;
    int id;

    public Event(int type, int id, int initTime, int duration) {
        this.type = type;
        this.id = id;
        this.initTime = initTime;
        this.duration = duration;
        this.timeToHappen = initTime + duration;
    }

    public int getTimeToHappen() {
        return timeToHappen;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void getInitTime(int initTime) {
        this.initTime = initTime;
    }

    public void getDuration(int duration) {
        this.duration = duration;
    }
}
