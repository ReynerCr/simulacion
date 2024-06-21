package Aldea.Almacen;

import Aldea.Edificio;
import Aldea.TipoEdificio;

public class Almacen extends Edificio {
    private int max;
    private int acumulado;
    private int tasaPerdida;

    public Almacen(TipoEdificio tipoEdificio) {
        super(1, 5, 2, tipoEdificio,
                (tipoEdificio == TipoEdificio.ALMACEN_ORO) ? TipoEdificio.ALMACEN_ELIXIR : TipoEdificio.ALMACEN_ORO);
        this.max = 10;
        this.acumulado = 0;
        this.tasaPerdida = 1;
    }

    public void perder() {
        this.acumulado = this.acumulado - (this.max * ((this.tasaPerdida) / 10));
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
        this.max = this.max + (this.nivel * 5);
        this.tasaPerdida = tasaPerdida + 1;
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
