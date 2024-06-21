import java.util.Scanner;

import Aldea.TipoEdificio;

public class App {
    private static Scanner scanner = new Scanner(System.in);

    public static void clearConsole() {
        System.out.println("\033[H\033[2J");
    }

    public static void menuAldea(Simulation sim) {
        clearConsole();

        char resp = ' ';
        
        while (resp != 'p') { // previous
            sim.printStatus();
            System.out.println("'r' -> recolectar recursos");
            System.out.println("'t' -> entrenar una tropa");
            System.out.println("'e' -> mejorar extractor");
            System.out.println("'m' -> mejorar mina");
            System.out.println("'x' -> mejorar almacen de oro");
            System.out.println("'z' -> para mejorar almacen de elixir");
            System.out.println("'p' -> volver al menú principal");

            System.out.println("'c' -> mejorar el cuartel");
            System.out.println("'l' -> mejorar tropa en el laboratorio");


            System.out.println("'b' -> mejorar constructor");

            System.out.println("'d' -> mejorar defensas");
            System.out.println("'a' -> atacar a otra aldea");

            try {
                resp = scanner.next().charAt(0);
            } catch (Exception e) {
                System.out.println("Error al leer la entrada");
                scanner.nextLine();
                System.out.flush();
                resp = ' ';
            }

            switch (resp) {
                case 'r':
                    System.out.println("Recolectando recursos...");
                    sim.aldeaRecolectar();
                    break;
                case 'c':
                    System.out.println("Construyendo edificaciones...");
                    break;
                case 'e':
                    System.out.println("Mejorando extractor...");
                    sim.aldeaUpgradeEdificio(TipoEdificio.EXTRACTOR);
                    break;
                case 'm':
                    System.out.println("Mejorando mina...");
                    sim.aldeaUpgradeEdificio(TipoEdificio.MINA);
                    break;
                case 'x':
                    System.out.println("Mejorando almacen de oro...");
                    sim.aldeaUpgradeEdificio(TipoEdificio.ALMACEN_ORO);
                    break;
                case 'z':
                    System.out.println("Mejorando almacen de elixir...");
                    sim.aldeaUpgradeEdificio(TipoEdificio.ALMACEN_ELIXIR);
                    break;
                case 't':
                    System.out.println("Entrenando tropas...");
                    sim.aldeaEntrenarTropa();
                    break;
                case 'a':
                    System.out.println("Atacando a otra aldea...");
                    sim.aldeaAtacar();
                    break;
                case 'p':
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Comando no reconocido");
                    break;
            }
            clearConsole();
        }

        return;
    }

    public static void main(String[] args) throws Exception {
        int seed = 43;
        int lambda = 1;
        int initTime = 0;
        int TIME_LIMIT = 100; // tiempo limite de simulación
         // tiempo actual

        /* //comprobar generador de numeros aleatorios
        double lista1[] = new double[1000];
        for (int i = 0; i < 1000; ++i) {
            lista1[i] = exp.getNext();
        }

        // Determinar si los numeros generados son exponenciales
        for (int i = 0; i < 1000; ++i) {
            System.out.println(lista1[i] + ", ");
        } */

        Simulation sim = new Simulation(initTime, TIME_LIMIT, seed, lambda);
        
        char response = ' ';
        
        while (sim.getTime() < TIME_LIMIT) {
            // ejecutar eventos que deben ocurrir en este instante
            sim.runEvents();

            // Con el generador de numeros aleatorios, determinar si se generará un nuevo evento
            
            // mostrar el estado de la simulación
            sim.printStatus();

            // Instrucciones para el usuario
            if (response != 'o') {
                System.out.println("'x' -> avance automático");
                System.out.println("'n' -> avanzar al siguiente evento");
                System.out.println("'j' -> avanzar un segundo");
                System.out.println("'a' -> acceder al menú de la aldea");
                System.out.println("'q' -> salir de la simulación");
            }   
            try {
                if (response != 'o') {
                    response = scanner.next().charAt(0);
                }
            } catch (Exception e) {
                System.out.println("Error al leer la entrada " + e);
                scanner.nextLine();
                System.out.flush();
                response = ' ';
            }
            
            switch (response) {
                case 'n':
                    if (sim.eventsEmpty()) {
                        System.out.println("No hay eventos futuros");
                    } else {
                        sim.skipToNextEvent();
                    }
                    break;
                case 'q':
                    sim.endSimulation();
                    break;
                case 'j':
                    sim.advanceOneStep();
                    break;
                case 'a':
                    menuAldea(sim);
                    break;
                case 'o':
                    sim.advanceOneStep();
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
