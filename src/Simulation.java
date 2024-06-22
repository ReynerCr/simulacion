import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;
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
    public int compare(Event a, Event b) {
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

    // TODO cambiar el lambda a uno con sentido y luego comprobar si los numeros
    // generados son exponenciales
    private Exponential exp;
    private Aldea aldea;

    private Random random;

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
        this.random = new Random(seed + 2);

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
        if (!cuartel.aumentarCola()) {
            return;
        }
        Event e = new Event(time, 2, "Entrenar tropa", (event) -> {
            cuartel.finalizarEntrenamiento();
            if (campamento.agregar(cuartel)) {
                campamento.calcularAtaque(laboratorio.getNivel());
            }
        });
        addEvent(e);
    }

    public void aldeaAtacar() {
        Event e = new Event(time, 1, "Resultado de ataque", (event) -> {
            aldea.atacarRival(random);
        });
        addEvent(e);
    }

    public void aldeaDefender() {

        Event e = new Event(time, 1, "Resultado de defensa", (event) -> {
            aldea.defenderDeRival(random);
        });
        addEvent(e);
    }

    /*
     * Construcción o mejora de edificio
     * Si el nivel del edificio es 0, se construye
     * En caso contrario, se mejora
     */
    public void aldeaUpgradeEdificio(TipoEdificio tipo) {
        Edificio edificio = aldea.upgradeEdificio(tipo);
        if (edificio == null) {
            return;
        }

        Event e;
        if (edificio.getNivel() == 0) {
            e = new Event(time, 5, "Construcción de edificio", (event) -> {
                aldea.finConstruccionEdificio(edificio);
            });
        } else {
            e = new Event(time, 5, "Finalización de mejora de edificio", (event) -> {
                aldea.finMejoraEdificio(edificio);
            });
        }
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
        System.out.printf("%-34.34s  %-25.25s %-30.30s%n", "Mina: oro " + mina.getAcumulado(),
                "Tasa de produccion: " + mina.getTasa(), "nivel " + mina.getNivel());
        System.out.printf("%-34.34s  %-25.25s %-30.30s%n", "Extractor: " + extractor.getAcumulado(),
                "Tasa de produccion " + extractor.getTasa(), "nivel " + extractor.getNivel());
        // almacenes
        System.out.printf("%-34.34s  %-30.30s%n", "Almacen oro: almacenado " + almacenOro.getAcumulado(),
                "nivel " + almacenOro.getNivel());
        System.out.printf("%-34.34s  %-30.30s%n", "Almacen elixir: almacenado " + almacenElixir.getAcumulado(),
                "nivel " + almacenElixir.getNivel());

        // numero de constructores disponibles
        System.out.printf("%-34.34s  %-30.30s%n", "Constructores: disponibles " + constructor.getDisponibilidad(),
                "total " + constructor.getCapacidad());

        if (laboratorio.getNivel() > 0) {
            // laboratorio
            System.out.printf("%-34.34s  %-30.30s%n",
                    "Laboratorio: " + (laboratorio.getDisponibilidad() == true ? "disponible" : "ocupado"),
                    "nivel de tropas " + laboratorio.getNivel());
        }
        if (cuartel.getNivel() > 0) {
            // tropas
            System.out.printf("%-34.34s  %-25.25s %-30.30s%n",
                    "Cuartel entrenamiento: cola " + cuartel.getColaEntrenamiento(),
                    "capacidad " + cuartel.getCapacidadMaxima(), "tropas entrenadas " + cuartel.getTropasEntrenadas());
        }
        if (campamento.getNivel() > 0) {
            // campamento
            System.out.printf("%-34.34s  %-25.25s %-30.30s%n",
                    "Campamento: cantidad " + campamento.getCantidadActualCampamento(),
                    "capacidad " + campamento.getCapacidadMaxima(),
                    "capacidad de ataque " + campamento.getCapacidadAtaque());
        }
        if (defensa.getNivel() > 0) {
            // defensas
            System.out.printf("%-34.34s  %-30.30s%n", "Defensas: nivel " + defensa.getNivel(),
                    "capacidad de defensa " + defensa.getCapacidadDefensa());
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

    // revisar si hay eventos que deben ocurrir en este instante
    public ArrayList<Event> runEvents() {
        ArrayList<Event> events = new ArrayList<Event>();
        while (!futureEvents.isEmpty() && futureEvents.peek().getTimeToHappen() == time) {
            Event event = futureEvents.poll();
            events.add(event);
            System.out.println("Ejecutando evento: " + event.getDescription());
            event.execute();
        }
        return events;
    }
}
