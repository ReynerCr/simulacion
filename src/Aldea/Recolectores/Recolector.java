package Aldea.Recolectores;

import Aldea.Edificio;
import Aldea.TipoEdificio;

public class Recolector extends Edificio {
    private int tasaProduccion;
    private int capacidadMaxima;
    private int acumulado;
    private int tasaPerdida;

    public Recolector(int tasaProduccion, TipoEdificio tipoEdificio) {
        super(1, 5, 2, tipoEdificio,
                (tipoEdificio == TipoEdificio.EXTRACTOR) ? TipoEdificio.ALMACEN_ORO : TipoEdificio.ALMACEN_ELIXIR);
        this.tasaProduccion = tasaProduccion;
        this.capacidadMaxima = 10;
        this.acumulado = 0;
        this.tasaPerdida = 1;
    }

    public void producir() {
        int entrada = this.tasaProduccion + this.acumulado;
        if (capacidadMaxima >= entrada) {
            this.acumulado = entrada;
        }
    }

    public void perder() {
        this.acumulado = this.acumulado - this.tasaPerdida;
    }

    public void upgrade() {
        upgradeEdificio();
        this.tasaProduccion = this.tasaProduccion + (this.nivel * 2);
        this.capacidadMaxima = this.capacidadMaxima + (this.nivel * 5);
        this.tasaPerdida = this.tasaPerdida + this.tasaPerdida * 2;
    }

    public void vaciar() {
        this.acumulado = 0;
    }

    public void setTasaProduccion(int tasaProduccion) {
        this.tasaProduccion = tasaProduccion;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public void setAcumulado(int acumulado) {
        this.acumulado = acumulado;
    }

    public int getTasa() {
        return this.tasaProduccion;
    }

    public int getCapacidadMaxima() {
        return this.capacidadMaxima;
    }

    public int getAcumulado() {
        return this.acumulado;
    }
}
