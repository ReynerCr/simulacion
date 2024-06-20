import java.util.ArrayList;
import java.util.PriorityQueue;

import Aldea.Aldea;

public class Simulation {
    // cola de prioridad para eventos futuros
    // hace las veces de lista de eventos futuros
    private PriorityQueue<Event> futureEvents = new PriorityQueue<Event>(new SortByTimeToHappen());

    // generador de numeros aleatorios con semilla
    private int time = 0;
    private int TIME_LIMIT = 15;

    // TODO cambiar el lambda a uno con sentido y luego comprobar si los numeros generados son exponenciales
    private Exponential exp;
    private Aldea aldea;

    public Simulation(int time, int TIME_LIMIT, int seed, int lambda) {
        this.time = time;
        this.TIME_LIMIT = TIME_LIMIT;
        this.exp = new Exponential(seed, lambda);
        this.aldea = new Aldea();
    }

    private void calcDiffResources() {
        int deltaTiempo = getTimeToNextEvent();
        for (int i = time; i < deltaTiempo; i++) {
            aldea.producir();
        }
    }

    public void printStatus() {
        System.out.println("Tiempo: " + time);
        System.out.println("Cantidad de recurso recolectado: ");
        System.out.println("    - Oro: " + aldea.getOroRecolectado());
        System.out.println("    - Elixir: " + aldea.getElixirRecolectado());
        System.out.println("Cantidad de recurso Almacenado: ");
        System.out.println("    - Oro: " + aldea.getOroAlmacenado());
        System.out.println("    - Elixir: " + aldea.getElixirAlmacenado());

        Event futuro = this.peekEvent();
        System.out.println("Siguiente evento: " + (futuro == null ? "Ninguno" : futuro.getDescription() + " en " + (futuro.getTimeToHappen() - time) + " segundos"));
    }

    public void skipToNextEvent() {
        calcDiffResources();
        time = this.getTimeToNextEvent();
    }

    public void aldeaRecolectar() {
        aldea.recolectar();
    }

    public void endSimulation() {
        time = TIME_LIMIT;
    }

    public void advanceOneStep() {
        aldea.producir();
        ++time;
    }

    public void addEvent(Event e) {
        futureEvents.add(e);
    }

    public int getTime() {
        return time;
    }

    public Event getNextEvent() {
        return futureEvents.poll();
    }

    public int getTimeToNextEvent() {
        return futureEvents.peek().getTimeToHappen();
    }

    public Exponential getExp() {
        return exp;
    }

    public void setExp(Exponential exp) {
        this.exp = exp;
    }

    public Event peekEvent() {
        return futureEvents.peek();
    }

    public boolean eventsEmpty() {
        return futureEvents.isEmpty();
    }

    public ArrayList<Event>runEvents() {
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
