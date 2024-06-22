package Aldea.Almacen;

import Aldea.Edificio;
import Aldea.TipoEdificio;

public class Almacen extends Edificio {
    private int max;
    private int acumulado;
    private int tasaPerdida;

    public Almacen(TipoEdificio tipoEdificio) {
        super(1, 50, 2, tipoEdificio,
                (tipoEdificio == TipoEdificio.ALMACEN_ORO) ? TipoEdificio.ALMACEN_ELIXIR : TipoEdificio.ALMACEN_ORO);
        this.max = 100;
        this.acumulado = 0;
        this.tasaPerdida = 10;
    }

    public int getPosiblePerdida() {
        int perdida = this.acumulado * this.tasaPerdida / 100;
        if (perdida == 0 && this.acumulado > 0) { // no se puede perder menos de 1
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

    public void almacenar(int cantidad) {
        int entrada = this.acumulado + cantidad;

        if (max > entrada) {
            this.acumulado = entrada;
        } else {
            this.acumulado = max;
        }
    }

    public void upgrade() {
        upgradeEdificio();
        this.max = this.max + (this.nivel * 60);
        this.tasaPerdida = tasaPerdida + 2;
    }

    public boolean consumir(int cantidad) {
        if (cantidad > this.acumulado) {
            return false;
        } else {
            this.acumulado = this.acumulado - cantidad;
            return true;
        }
    }

    public int getMax() {
        return this.max;
    }

    public int getAcumulado() {
        return this.acumulado;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setAcumulado(int acumulado) {
        this.acumulado = acumulado;
    }
}
