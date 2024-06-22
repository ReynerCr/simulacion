package Aldea.Tropas;

import Aldea.Edificio;
import Aldea.TipoEdificio;

public class Laboratorio extends Edificio {

    private int capacidadMaxima;
    private int cola;

    public Laboratorio() {
        super(0, 70, 6, TipoEdificio.LABORATORIO, TipoEdificio.ALMACEN_ELIXIR);
        this.capacidadMaxima = 1;
        this.cola = 0;
    }

    // añade a la cola de mejora de la tropa
    public void aumentarCola() {
        int entrada = this.cola + 1;
        if (this.capacidadMaxima < entrada) {
            this.cola = entrada;
        }
    }

    public void upgrade() {
        upgradeEdificio();
    }

    public boolean getDisponibilidad() {
        return this.cola < this.capacidadMaxima;
    }

    public void disminuirCola() {
        this.cola -= 1;
    }
}
