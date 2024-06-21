package Aldea.Defensas;

import Aldea.Edificio;
import Aldea.TipoEdificio;

public class Defensas extends Edificio {
    
    private int cantidadDefensas;
    private int capacidadDefensa;

    public Defensas() {
        super(1, 5, 2, TipoEdificio.DEFENSA, TipoEdificio.ALMACEN_ORO);
        this.cantidadDefensas=1;
        this.capacidadDefensa=1;
    }

    public void upgrade(){
        upgradeEdificio();
        this.capacidadDefensa = this.nivel * 10;
        this.cantidadDefensas = this.nivel * 10;
    }

    public void aumentar(){
        this.capacidadDefensa +=1;
    }

    public void setCantidadDefensas(int cantidadDefensa){
        this.cantidadDefensas = cantidadDefensa;
       }
    
    public void setCapacidadDefensa(int capacidadDefensa){
        this.capacidadDefensa = capacidadDefensa;
    }

    public int getCantidadDefensas(){
        return this.cantidadDefensas;
    }

    public int getCapacidadDefensa(){
        return this.capacidadDefensa;
    }
}
