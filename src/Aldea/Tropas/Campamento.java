package Aldea.Tropas;

import Aldea.Edificio;
import Aldea.TipoEdificio;

public class Campamento extends Edificio {
    private int capacidadMaxima;
    private int cantidadActualCampamento;
    private int capacidadAtaque;
    private int ataqueTropa;

    public Campamento() {
        super(1, 50, 5, TipoEdificio.CAMPAMENTO, TipoEdificio.ALMACEN_ELIXIR);
        this.capacidadMaxima = 10 * nivel;
        this.cantidadActualCampamento = 0;
        this.ataqueTropa = 10;
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
        this.capacidadAtaque = (nivelLaboratorio * 5 + ataqueTropa) * cantidadActualCampamento;
    }

    public void upgrade() {
        upgradeEdificio();
        this.capacidadMaxima += 10 + this.nivel * 5;
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

    public int setCantidadActualCampamento(int cantidadActualCampamento) {
        return this.cantidadActualCampamento;
    }

    public int setNivelAtaque() {
        return this.capacidadAtaque;
    }
}
