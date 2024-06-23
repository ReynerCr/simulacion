package Aldea.Tropas;

import Aldea.Edificio;
import Aldea.TipoEdificio;

public class Cuartel extends Edificio {

    private int capacidadMaxima;
    private int colaEntrenamiento; // maneja la cola, incluye las entrenadas
    private int tropasEntrenadas; // solo las entrenadas

    public Cuartel() {
        super(0, 50, 5, TipoEdificio.CUARTEL, TipoEdificio.ALMACEN_ELIXIR);
        this.capacidadMaxima = 10 + (nivel * 5);
        this.colaEntrenamiento = 0;
        this.tropasEntrenadas = 0;
    }

    // poner a entrenar
    public boolean aumentarCola() {
        if (capacidadMaxima <= colaEntrenamiento) {
            System.out.println("La cola de entrenamiento está llena.");
        }
        ++this.colaEntrenamiento;
        return true;
    }

    public boolean finalizarEntrenamiento() {
        if (this.colaEntrenamiento == 0) {
            return false;
        }
        ++this.tropasEntrenadas;
        return true;
    }

    public boolean sacarTropaEntrenada() {
        if (this.tropasEntrenadas > 0) {
            --this.tropasEntrenadas;
            --this.colaEntrenamiento;
            return true;
        } else {
            return false;
        }
    }

    public int getTropasEntrenadas() {
        return this.tropasEntrenadas;
    }

    public void upgrade() {
        upgradeEdificio();
        this.capacidadMaxima = this.nivel * 5 + 10;
    }

    public int getColaEntrenamiento() {
        return this.colaEntrenamiento;
    }

    public int getCapacidadMaxima() {
        return this.capacidadMaxima;
    }
}
