package Aldea.Tropas;

import Aldea.Edificio;
import Aldea.TipoEdificio;

public class Laboratorio extends Edificio {

    private int capacidadMaxima;
    private int cantidadTropas;

    public Laboratorio() {
        super(1, 5, 2, TipoEdificio.LABORATORIO, TipoEdificio.ALMACEN_ELIXIR);
        this.capacidadMaxima = 1;
        this.cantidadTropas = 0;
    }

    public void ingresar() {
        int entrada = this.cantidadTropas + 1;
        if (this.capacidadMaxima < entrada) {
            this.cantidadTropas = entrada;
        }
    }

    public void upgrade() {
        upgradeEdificio();
    }

    public void eliminar() {
        this.cantidadTropas -= 1;
    }
}
