package Aldea.Constructor;

import Aldea.Edificio;
import Aldea.TipoEdificio;

public class Constructor extends Edificio {
    
    private int capacidadMaxima;
    private int colaConstruccion;

    public Constructor(){
        // TODO el nivel del constructor deberia ser 1, ajustar al final
        super(3, 5, 2, TipoEdificio.CONSTRUCTOR, TipoEdificio.ALMACEN_ORO);
        this.capacidadMaxima= 1 * nivel;
        this.colaConstruccion = 0;
    }

    public boolean aumentarCola(int edificio){
        int entrada = this.colaConstruccion + 1;
        if(capacidadMaxima >= entrada){
            this.colaConstruccion=entrada;
            return true;
        }
        return false;
    }

    public int getDisponibilidad(){
        return this.capacidadMaxima - this.colaConstruccion;
    }

    public void disminuirCola(){
        this.colaConstruccion-=1;
    }

    public void upgrade(){
        upgradeEdificio();
        this.capacidadMaxima+=1;
    }

    public void setCapacidad(int capacidadMaxima){ this.capacidadMaxima = capacidadMaxima; }
    
    public void setCola(int colaConstruccion){ this.colaConstruccion = colaConstruccion;}

    public int getCapacidad(){ return this.capacidadMaxima; }

    public int getCola(){ return this.colaConstruccion; }
}
