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
        int time = 0; // tiempo actual
        final int TIME_LIMIT = 20; // tiempo limite de simulación

        // generador de numeros aleatorios con semilla
        int seed = 43;
        // TODO cambiar el lambda a uno con sentido y luego comprobar si los numeros generados son exponenciales
        Exponential exp = new Exponential(1, seed);

        /* //comprobar generador de numeros aleatorios
        double lista1[] = new double[1000];
        for (int i = 0; i < 1000; ++i) {
            lista1[i] = exp.getNext();
        }

        // Determinar si los numeros generados son exponenciales
        for (int i = 0; i < 1000; ++i) {
            System.out.println(lista1[i] + ", ");
        } */

        // TODO determinar si el generador de numeros aleatorios es correcto
        
        // cola de prioridad para eventos futuros
        // hace las veces de lista de eventos futuros
        PriorityQueue<Event> futureEvents = new PriorityQueue<Event>(new SortByTimeToHappen());

        // TODO Insertar eventos de prueba
        futureEvents.add(new Event(1, 1, 0, 5, "Crear tropa"));
        futureEvents.add(new Event(2, 2, 5, 0, "Recolectar recursos"));

        // simulamos el tiempo
        char response = ' ';

        // scaner
        Scanner scanner = new Scanner(System.in);

        while (time < TIME_LIMIT) {
            // revisar si hay eventos que deben ocurrir en este instante
            while (!futureEvents.isEmpty() && futureEvents.peek().getTimeToHappen() == time) {
                Event event = futureEvents.poll();
                System.out.println("Ejecutando evento: " + event.getDescription());
            }

            // Con el generador de numeros aleatorios, determinar si se generará un nuevo evento
            
            // leer el siguiente evento
            Event futuro = futureEvents.peek();

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
                    if (futureEvents.isEmpty()) {
                        System.out.println("No hay eventos futuros");
                    } else {
                        time = futureEvents.peek().getTimeToHappen();
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
