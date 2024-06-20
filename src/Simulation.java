import java.util.ArrayList;
import java.util.PriorityQueue;

public class Simulation {
    // cola de prioridad para eventos futuros
    // hace las veces de lista de eventos futuros
    private PriorityQueue<Event> futureEvents = new PriorityQueue<Event>(new SortByTimeToHappen());

    // generador de numeros aleatorios con semilla
    int seed;
    int lambda;

    // TODO cambiar el lambda a uno con sentido y luego comprobar si los numeros generados son exponenciales
    Exponential exp;

    public Simulation(int seed, int lambda) {
        this.seed = seed;
        this.lambda = lambda;
        this.exp = new Exponential(seed, lambda);
    }

    public void addEvent(Event e) {
        futureEvents.add(e);
    }

    public Event getNextEvent() {
        return futureEvents.poll();
    }

    public int getTimeToNextEvent() {
        return futureEvents.peek().getTimeToHappen();
    }

    public int getSeed() {
        return seed;
    }

    public Exponential getExp() {
        return exp;
    }

    public void setExp(Exponential exp) {
        this.exp = exp;
    }

    public PriorityQueue<Event> getFutureEvents() {
        return futureEvents;
    }

    public Event peekEvent() {
        return futureEvents.peek();
    }

    public boolean eventsEmpty() {
        return futureEvents.isEmpty();
    }

    public ArrayList<Event> runEvents(int time) {
        // revisar si hay eventos que deben ocurrir en este instante
        ArrayList <Event> events = new ArrayList<Event>();
        while (!futureEvents.isEmpty() && futureEvents.peek().getTimeToHappen() == time) {
            Event event = futureEvents.poll();
            events.add(event);
            System.out.println("Ejecutando evento: " + event.getDescription());
        }
        return events;
    }
}
