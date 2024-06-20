package Aldea.Defensas;

public class Defensas {
    
    private int cantidadDefensas;
    private int nivelDefensa;
    private int capacidadDefensa;

    public Defensas(){
        this.cantidadDefensas=1;
        this.nivelDefensa=1;
        this.capacidadDefensa=1;
    }

    public void upgrade(){
        this.nivelDefensa+=1;
        this.capacidadDefensa = this.nivelDefensa * 10;
        this.cantidadDefensas = this.nivelDefensa * 10;
    }

    public void aumentar(){
        this.capacidadDefensa +=1;
    }

    public void setCantidadDefensas(int cantidadDefensa){
        this.cantidadDefensas = cantidadDefensa;
       }
    
    public void setNivelDefensa(int nivelDefensa){
        this.nivelDefensa = nivelDefensa;
    }

    public void setCapacidadDefensa(int capacidadDefensa){
        this.capacidadDefensa = capacidadDefensa;
    }

    public int getCantidadDefensas(){
        return this.cantidadDefensas;
    }

    public int getNivelDefensas(){
        return this.nivelDefensa;
    }

    public int getCapacidadDefensa(){
        return this.capacidadDefensa;
    }
}
