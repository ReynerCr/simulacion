import java.util.function.Consumer;

public class Event {
    int initTime; // tiempo en que el evento fue creado
    int duration; // duracion del evento
    int timeToHappen; // tiempo en que el evento ocurrira segun el reloj de simulación (initTime + duration)
    String description; // descripción del evento

    // Consumer para ejecutar la función asociada al evento
    private Consumer<Event> function;

    public Event(int initTime, int duration, String description, Consumer<Event> function) {
        this.initTime = initTime;
        this.duration = duration;
        this.timeToHappen = initTime + duration;
        this.description = description;
        this.function = function;
    }

    public int getTimeToHappen() {
        return timeToHappen;
    }

    public void getInitTime(int initTime) {
        this.initTime = initTime;
    }

    public void getDuration(int duration) {
        this.duration = duration;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void execute() {
        function.accept(this);
    }

    public void print() {
        System.out.println("Evento: " + description + " en tiempo: " + timeToHappen);
    }
}
