import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

import Aldea.Aldea;
import Aldea.Edificio;
import Aldea.TipoEdificio;
import Aldea.Almacen.Almacen;
import Aldea.Recolectores.Recolector;
import Aldea.Tropas.Campamento;
import Aldea.Tropas.Cuartel;
import Aldea.Tropas.Laboratorio;
import Aldea.Constructor.Constructor;
import Aldea.Defensas.Defensa;

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

    // punteros a los edificios de la aldea para facilitar el acceso
    private Recolector mina;
    private Recolector extractor;
    private Almacen almacenOro;
    private Almacen almacenElixir;
    private Cuartel cuartel;
    private Campamento campamento;
    private Constructor constructor;
    private Laboratorio laboratorio;
    private Defensa defensa;

    public Simulation(int time, int TIME_LIMIT, int seed, int lambda) {
        this.time = time;
        this.TIME_LIMIT = TIME_LIMIT;
        this.exp = new Exponential(seed, lambda);
        this.aldea = new Aldea();

        // punteros a los edificios de la aldea
        this.mina = aldea.getMina();
        this.extractor = aldea.getExtractor();
        this.almacenOro = aldea.getAlmacenOro();
        this.almacenElixir = aldea.getAlmacenElixir();
        this.cuartel = aldea.getCuartel();
        this.campamento = aldea.getCampamento();
        this.constructor = aldea.getConstructor();
        this.laboratorio = aldea.getLaboratorio();
        this.defensa = aldea.getDefensa();
    }

    private void calcDiffResources() {
        int deltaTiempo = getTimeToNextEvent();
        for (int i = time; i < deltaTiempo; i++) {
            aldea.producir();
        }
    }

    public void aldeaEntrenarTropa() {
        cuartel.aumentarCola(1);
        Event e = new Event(time, 2, "Entrenar tropa", (event) -> {
            campamento.agregar(cuartel);
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

    public void aldeaUpgradeEdificio(TipoEdificio tipo) {
        Edificio edificio = aldea.upgradeEdificio(tipo);
        if (edificio == null) {
            return;
        }

        Event e = new Event(time, 5, "Finalización de mejora de edificio", (event) -> {
            aldea.terminarConstruccion(edificio);
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
        
        Event futuro = this.peekEvent();
        if (futuro != null) {
            futuro.print();
        } else {
            System.out.println("No hay eventos futuros");
        }

        // recolectores
        System.out.println("Mina: " + mina.getAcumulado() + "   Tasa de produccion: " + mina.getTasa() + "    nivel: " + mina.getNivel());
        System.out.println("Extractor: " + extractor.getAcumulado() + "    Tasa de produccion: " + extractor.getTasa() + "    nivel: " + extractor.getNivel());
        
        // almacenes
        System.out.println("Oro: " + almacenOro.getAcumulado() + "   " + "  nivel: " + almacenOro.getNivel());
        System.out.println("Elixir: " + almacenElixir.getAcumulado() + "     " + "nivel: " + almacenElixir.getNivel());
 
        // laboratorio
        System.out.println("Laboratorio: " + (
            laboratorio.getDisponibilidad() == true ? "disponible" : "ocupado")
                + "    nivel de tropas: " + laboratorio.getNivel());

        // tropas
        System.out.println("Cuartel entrenamiento: " + cuartel.getColaEntrenamiento() + "    capacidad: " + cuartel.getCapacidadMaxima());
        System.out.println("Cantidad de tropas en campamento: " + campamento.getCantidadActualCampamento() + "    capacidad: " + campamento.getCapacidadMaxima() + "    capacidad de ataque: " + campamento.getCapacidadAtaque());

        // numero de constructores disponibles
        System.out.println("Constructores disponibles: " + constructor.getDisponibilidad() + "   /   total: " + constructor.getCapacidad());

        // defensas
        System.out.println("Defensas: nivel " + defensa.getNivel() + "    capacidad de defensa: " + defensa.getCapacidadDefensa());
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

    // revisar si hay eventos que deben ocurrir en este instante
    public ArrayList<Event>runEvents() {
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
