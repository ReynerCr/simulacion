package Aldea.Tropas;

import Aldea.Edificio;
import Aldea.TipoEdificio;

public class Campamento extends Edificio {
    private int capacidadMaxima;
    private int cantidadActualCampamento;
    private int capacidadAtaque;
    private int ataqueTropa;

    public Campamento() {
        super(0, 50, 5, TipoEdificio.CAMPAMENTO, TipoEdificio.ALMACEN_ELIXIR);
        this.capacidadMaxima = 10;
        this.cantidadActualCampamento = 0;
        this.ataqueTropa = 15;
        this.capacidadAtaque = this.ataqueTropa * this.cantidadActualCampamento;
    }

    public boolean agregar(Cuartel cuartel) {
        if (capacidadMaxima <= cantidadActualCampamento || !cuartel.sacarTropaEntrenada()) {
            return false;
        }
        ++cantidadActualCampamento;
        return true;
    }

    public void calcularAtaque(int nivelLaboratorio) {
        this.capacidadAtaque = (nivelLaboratorio * 1 + ataqueTropa) * cantidadActualCampamento;
    }

    public void upgrade() {
        upgradeEdificio();
        this.capacidadMaxima += 5;
    }

    public void vaciar() {
        this.cantidadActualCampamento = 0;
        this.capacidadAtaque = 0;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public int getCantidadActualCampamento() {
        return cantidadActualCampamento;
    }

    public int getCapacidadAtaque() {
        return capacidadAtaque;
    }

    public int setCapacidadMaxima() {
        return this.capacidadMaxima;
    }

    public boolean setCantidadActualCampamento(int cantidadActualCampamento) {
        if (cantidadActualCampamento > this.capacidadMaxima) {
            return false;
        }
        this.cantidadActualCampamento = cantidadActualCampamento;
        return true;
    }

    public int setNivelAtaque() {
        return this.capacidadAtaque;
    }
}
