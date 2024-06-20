import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

// Clase comparadora para ordenar eventos por tiempo de ocurrencia
class SortByTimeToHappen implements Comparator<Event> {
    public int compare(Event a, Event b)
    {
        return a.timeToHappen - b.timeToHappen;
    }
}

// main
public class App {
    public static void main(String[] args) throws Exception {
        int seed = 43;
        int lambda = 1;
        int TIME_LIMIT = 100; // tiempo limite de simulación // TODO puede ser por args o del archivo de configuración
        int time = 0; // tiempo actual

        /* //comprobar generador de numeros aleatorios
        double lista1[] = new double[1000];
        for (int i = 0; i < 1000; ++i) {
            lista1[i] = exp.getNext();
        }

        // Determinar si los numeros generados son exponenciales
        for (int i = 0; i < 1000; ++i) {
            System.out.println(lista1[i] + ", ");
        } */

        
        Simulation sim = new Simulation(seed, lambda);
        sim.addEvent(new Event(0, 0, 0, 5, "Entrenar tropas"));

        // simulamos el tiempo
        char response = ' ';

        // scaner
        Scanner scanner = new Scanner(System.in);

        while (time < TIME_LIMIT) {
            // ejecutar eventos que deben ocurrir en este instante
            sim.runEvents(time);

            // Con el generador de numeros aleatorios, determinar si se generará un nuevo evento
            
            // leer el siguiente evento
            Event futuro = sim.peekEvent();

            // TODO esto se puede hacer más bonito, imprimiendo dos columnas

            // TODO mostrar el estado del sistema
            System.out.println("Tiempo: " + time);
            System.out.println("Siguiente evento: " + (futuro == null ? "Ninguno" : futuro.getDescription() + " en " + (futuro.getTimeToHappen() - time) + " segundos"));
            // TODO mostrar el estado de las aldeas, edificios, defensas y tropas

            // Instrucciones para el usuario
            if (response != 'o') {
                System.out.println("Instrucciones:");
                System.out.println("Presione 'o' para avance automático (se ignorarán las intrucciones siguientes)");
                System.out.println("Presione 'n' para avanzar al siguiente evento");
                System.out.println("Presione 'j' para avanzar un segundo");
                System.out.println("Presione 'a' para atacar otra aldea");
                System.out.println("Presione 'e' para mejorar un edificio");
                System.out.println("Presione 'd' para mejorar una defensa");
                System.out.println("Presione 'b' para entrenar un bárbaro");
                System.out.println("Presione 'q' para salir de la simulación");
            }
           
            // Tomamos input y ejecutamos la acción correspondiente
            try {
                if (response != 'o') {
                    response = scanner.next().charAt(0);
                }
            } catch (Exception e) {
                System.out.println("Error al leer la entrada");
                scanner.nextLine();
                System.out.flush();
                response = ' ';
            }
            
            switch (response) {
                case 'n':
                    if (sim.eventsEmpty()) {
                        System.out.println("No hay eventos futuros");
                    } else {
                        time = sim.getTimeToNextEvent();
                    }
                    break;
                case 'q':
                    time = TIME_LIMIT;
                    break;
                case 'j':
                    time++;
                    break;
                case 'a':
                    // TODO atacar otra aldea
                    break;
                case 'e':
                    // TODO mejorar un edificio
                    break;
                case 'd':
                    // TODO mejorar una defensa
                    break;
                case 'b':
                    // TODO entrenar un bárbaro
                    break;
                case 'o':
                    ++time;
                    Thread.sleep(1000);
                    break;
                default:
                    System.out.println("Comando no reconocido");
                    break;
            }

            // limpiar pantalla
            System.out.println("\033[H\033[2J");
        }

        scanner.close();
        System.out.println("Simulación finalizada");
    }
}
