package Aldea.Defensas;

import Aldea.Edificio;
import Aldea.TipoEdificio;

public class Defensa extends Edificio {
    private int capacidadDefensa;

    public Defensa() {
        super(1, 30, 5, TipoEdificio.DEFENSA, TipoEdificio.ALMACEN_ORO);
        this.capacidadDefensa = 150 * nivel;
    }

    public void upgrade(){
        upgradeEdificio();
        this.capacidadDefensa = 150 + this.nivel * 50;
    }

    public void setCapacidadDefensa(int capacidadDefensa){
        this.capacidadDefensa = capacidadDefensa;
    }

    public int getCapacidadDefensa(){
        return this.capacidadDefensa;
    }
}
