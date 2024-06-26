import java.util.Scanner;

import Aldea.TipoEdificio;
import java.util.concurrent.CountDownLatch;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    public static CountDownLatch latch;
   
    // Nota: no funciona en todos los sistemas operativos
    // ni en todos los terminales
    public static void clearConsole() {
        System.out.print("\n\n");
        System.out.println("\033[H\033[2J");
    }
   
    public static void menuAldea(Simulation sim) {
        clearConsole();
        char resp = ' ';
        while (resp != 'p') {
            resp = ' ';
            sim.printStatus();
            System.out.println("---------------------- Menú de la aldea ----------------------");
            System.out.printf("%-34.34s  %-34.34s%n","'r' -> recolectar recursos","'t' -> entrenar una tropa");
            System.out.printf("%-34.34s  %-34.34s%n","'e' -> mejorar extractor","'m' -> mejorar mina");
            System.out.printf("%-34.34s  %-34.34s%n","'x' -> mejorar almacen oro","'z' -> mejorar almacen elixir");
            System.out.printf("%-34.34s  %-34.34s%n","'l' -> mejorar tropa laboratorio","'c' -> mejorar el cuartel");
            System.out.printf("%-34.34s  %-34.34s%n","'v' -> mejorar campamento","'b' -> mejorar constructor");
            System.out.printf("%-34.34s  %-34.34s%n","'d' -> mejorar defensas","'a' -> atacar a otra aldea");
            System.out.println("'p' -> volver al menú principal");
            
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
                    sim.aldeaAtacar(0);
                    break;
                case 'l':
                    System.out.println("Mejorando tropa en el laboratorio...");
                    sim.aldeaUpgradeEdificio(TipoEdificio.LABORATORIO);
                    break;
                case 'c':
                    System.out.println("Mejorando cuartel...");
                    sim.aldeaUpgradeEdificio(TipoEdificio.CUARTEL);
                    break;
                    case 'v':
                    System.out.println("Mejorando campamento...");
                    sim.aldeaUpgradeEdificio(TipoEdificio.CAMPAMENTO);
                    break;
                case 'b':
                    System.out.println("Mejorando constructor...");
                    sim.aldeaUpgradeEdificio(TipoEdificio.CONSTRUCTOR);
                    break;
                case 'd':
                    System.out.println("Mejorando defensas...");
                    sim.aldeaUpgradeEdificio(TipoEdificio.DEFENSA);
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

        Simulation sim = new Simulation(initTime, TIME_LIMIT, seed, lambda);
        Main main = new Main(sim);
        char response = ' ';

        while (sim.getTime() < TIME_LIMIT) {
            // ejecutar eventos que deben ocurrir en este instante
            sim.runEvents();

            // mostrar el estado de la simulación
            sim.printStatus();
            
            // Instrucciones para el usuario
            if (response != 'o') {
                System.out.println("------------------------ Menú general ------------------------");
                System.out.println("'j' -> avanzar un segundo");
                System.out.println("'n' -> avanzar al siguiente evento");
                System.out.println("'a' -> acceder al menú de la aldea");
                System.out.println("'o' -> avance automático");
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
                    main.Update();
                    break;
                case 'a':
                    menuAldea(sim);
                    break;
                case 'o':
                    response = ' ';
                    /* No se implemento la creación de eventos automáticos 
                        sim.advanceOneStep();
                        main.Update();
                        Thread.sleep(1000);
                    */
                    break;
                default:
                    System.out.println("Comando no reconocido");
                    break;
            }

            clearConsole();
        }

        scanner.close();
        System.out.println("Simulación finalizada");
    }
}
