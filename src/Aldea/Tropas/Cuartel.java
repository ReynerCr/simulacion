package Aldea.Tropas;

import Aldea.Edificio;
import Aldea.TipoEdificio;

public class Cuartel extends Edificio {

    private int capacidadMaxima;
    private int colaEntrenamiento;

    public Cuartel() {
        super(1, 50, 5, TipoEdificio.CUARTEL, TipoEdificio.ALMACEN_ELIXIR);
        this.capacidadMaxima = 10 * nivel;
        this.colaEntrenamiento = 0;
    }

    public void aumentarCola(int tropa) {
        int entrada = this.colaEntrenamiento + tropa;

        if (capacidadMaxima > entrada) {
            this.colaEntrenamiento = entrada;
        } else {
            this.colaEntrenamiento = capacidadMaxima;
            System.out.println("No se pudieron entrenar " + (entrada - capacidadMaxima) + " tropas.");
        }
    }

    public void disminuirCola(int cantidad) {
        if (cantidad < this.colaEntrenamiento) {
            this.colaEntrenamiento = this.colaEntrenamiento - cantidad;
        } else {
            this.colaEntrenamiento = 0;
        }
    }

    public void upgrade() {
        upgradeEdificio();
        this.capacidadMaxima += this.nivel * 5 + 10;
    }

    public int getColaEntrenamiento() {
        return this.colaEntrenamiento;
    }

    public int getCapacidadMaxima() {
        return this.capacidadMaxima;
    }
}
