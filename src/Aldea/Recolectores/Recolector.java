
package Aldea.Recolectores;

import Aldea.Edificio;
import Aldea.TipoEdificio;

public class Recolector extends Edificio {
    private int tasaProduccion;
    private int capacidadMaxima;
    private int acumulado;
    private int tasaPerdida;

    public Recolector(int tasaProduccion, TipoEdificio tipoEdificio) {
        super(1, 40, 2, tipoEdificio,
                (tipoEdificio == TipoEdificio.EXTRACTOR) ? TipoEdificio.ALMACEN_ORO : TipoEdificio.ALMACEN_ELIXIR);
        this.tasaProduccion = tasaProduccion;
        this.capacidadMaxima = 80;
        this.acumulado = 0;
        this.tasaPerdida = 15;
    }

    public void producir() {
        int entrada = this.tasaProduccion + this.acumulado;
        if (capacidadMaxima >= entrada) {
            this.acumulado = entrada;
        }
    }

    public int getPosiblePerdida() {
        int perdida = this.acumulado * this.tasaPerdida / 100;
        if (perdida == 0 && this.acumulado > 0) {
            perdida = 1;
        }
        return perdida;
    }

    public void perder() {
        int perdida = getPosiblePerdida();
        this.acumulado = this.acumulado - perdida;
    }

    public void perder(int cantidad) {
        if (cantidad > this.acumulado) {
            cantidad = this.acumulado;
        }
        this.acumulado = this.acumulado - cantidad;
    }

    public void upgrade() {
        upgradeEdificio();
        this.tasaProduccion = this.tasaProduccion + (this.nivel * 10);
        this.capacidadMaxima = this.capacidadMaxima + (this.nivel * 40);
        this.tasaPerdida = tasaPerdida + 2;
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
