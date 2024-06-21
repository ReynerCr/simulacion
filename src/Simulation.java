import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;


import Aldea.Aldea;

// Clase comparadora para ordenar eventos por tiempo de ocurrencia
class SortByTimeToHappen implements Comparator<Event> {
    public int compare(Event a, Event b)
    {
        return a.timeToHappen - b.timeToHappen;
    }
}

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

    public void aldeaEntrenarTropa() {
        aldea.getCuartel().aumentarCola(1);
        Event e = new Event(time, 2, "Entrenar tropa", (event) -> {
            aldea.getCampamento().agregar(aldea.getCuartel());
        });
        addEvent(e);
    }

    public void aldeaAtacar() {
        // TODO falta atacar
        Event e = new Event(time, 5, "Atacar", (event) -> {
            //aldea.atacar();
        });
        addEvent(e);
    }

    public void aldeaConstruir() {
        // TODO falta construir
        Event e = new Event(time, 5, "Construir", (event) -> {
            //aldea.construir();
        });
        addEvent(e);
    }

    public void aldeaUpgrade() {
        // TODO falta upgrade
        Event e = new Event(time, 5, "Upgrade", (event) -> {
            //aldea.upgrade();
        });
        addEvent(e);
    }

    public void aldeaRecolectar() {
        Event e = new Event(time, 0, "Recolectar recursos", (event) -> {
            aldea.recolectar();
        });
        addEvent(e);
    }

    public void printStatus() {
        System.out.println("Tiempo: " + time);
        System.out.println("Cantidad de recurso recolectado: ");
        System.out.println("    - Oro: " + aldea.getExtractor().getElixirRecolectado());
        System.out.println("    - Elixir: " + aldea.getMina().getOroRecolectado());
        System.out.println("Cantidad de recurso Almacenado: ");
        System.out.println("    - Oro: " + aldea.getAlmacenOro().getAcum());
        System.out.println("    - Elixir: " + aldea.getAlmacenElixir().getAcum());

        System.out.println("Cantidad de tropas entrenamiento: " + aldea.getCuartel().getColaEntrenamiento());
        System.out.println("Cantidad de tropas listas: " + aldea.getCampamento().getCantidadActualCampamento());


        Event futuro = this.peekEvent();
        if (futuro != null) {
            futuro.print();
        } else {
            System.out.println("No hay eventos futuros");
        }
    }

    public void skipToNextEvent() {
        calcDiffResources();
        time = this.getTimeToNextEvent();
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
            event.execute();
        }
        return events;
    }
}
