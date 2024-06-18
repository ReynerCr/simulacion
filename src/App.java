import java.util.TreeMap;

public class App {
    public static void main(String[] args) throws Exception {
        int time = 0;
        final int TIME_LIMIT = 20;

        // lista de eventos futuros: mantiene orden por tiempo de ocurrencia y mantiene muchos eventos con el mismo tiempo en un array
        TreeMap<Integer, Event> futureEvents = new TreeMap<Integer, Event>();
        // No maneja eventos con el mismo tiempo de ocurrencia
        // Soluciones:
        // 1. Usar una lista (puede ser array simple o ArrayList) de eventos en vez de un solo evento
        // 2. Cambiar TreeMap por una clase nueva EventList que puede manejar un ArrayList de eventos y entonces ajustamos el código para manejar esta nueva clase

        // añadimos eventos
        int initTime = 5;
        int duration = 0;
        int timeToHappen = initTime + duration;
        futureEvents.put(timeToHappen, new Event(10, 4, initTime, duration));

        initTime = 2;
        duration = 5;
        timeToHappen = initTime + duration;
        futureEvents.put(timeToHappen, new Event(5, 3, initTime, duration));

        initTime = 0;
        duration = 2;
        timeToHappen = initTime + duration;
        futureEvents.put(timeToHappen, new Event(20, 2, initTime, duration));

        initTime = 0;
        duration = 2;
        timeToHappen = initTime + duration;
        futureEvents.put(timeToHappen, new Event(100, 1, initTime, duration));


        // mostrar lista de eventos futuros
        for (Event event : futureEvents.values()) {
            System.out.println("EVENTO: type " + event.getType() + " id: " + event.getId() + " time: " + event.getTimeToHappen());
        }

        // añadimos eventos

        // simulamos el tiempo
        while (time < TIME_LIMIT) {
            // procesamos eventos
            while (futureEvents.size() > 0 && futureEvents.firstKey() == time){
                // obtenemos el primer evento
                Event event = futureEvents.pollFirstEntry().getValue();
                System.out.println("EVENTO: type " + event.getType() + " id: " + event.getId() + " time: " + event.getTimeToHappen());
            }
           
            // añadimos un nuevo evento con probabilidad del 10%
            // deberia ser con el generador de numeros aleatorios
            // que se inicializa al principio del programa
            // y que tiene probabilidad exponencial 
            int probabilidadEvento = (int) (Math.random() * 100);
            if (probabilidadEvento < 10) {
                // añadimos un nuevo evento
                initTime = time;
                duration = 2;
                timeToHappen = initTime + duration;
                futureEvents.put(timeToHappen, new Event(100, 1, initTime, duration));
            }

            System.out.println("Time: " + time);
            ++time;
            // añadimos sleep de 1000 ms
            Thread.sleep(1000);
        }

        System.out.println("Simulation finished");
    }
}
