package Aldea.Defensas;

import Aldea.Edificio;
import Aldea.TipoEdificio;

public class Defensa extends Edificio {
    private int capacidadDefensa;

    public Defensa() {
        super(1, 5, 2, TipoEdificio.DEFENSA, TipoEdificio.ALMACEN_ORO);
        this.capacidadDefensa = 10 * nivel;
    }

    public void upgrade(){
        upgradeEdificio();
        this.capacidadDefensa = this.nivel * 10;
    }

    public void aumentar(){
        this.capacidadDefensa +=1;
    }

    public void setCapacidadDefensa(int capacidadDefensa){
        this.capacidadDefensa = capacidadDefensa;
    }

    public int getCapacidadDefensa(){
        return this.capacidadDefensa;
    }
}
