package Aldea.Defensas;

public class Defensas {
    
    private int cantidad_defensas;
    private int nivel_de_defensa;
    private int capacidad_de_defensa;

    public Defensas(){
        this.cantidad_defensas=1;
        this.nivel_de_defensa=1;
        this.capacidad_de_defensa=1;
    }

    public void Upgrade(){
        this.nivel_de_defensa+=1;
        this.capacidad_de_defensa = this.nivel_de_defensa * 10;
        this.cantidad_defensas = this.nivel_de_defensa * 10;
    }

    public void Aumentar(){
        this.capacidad_de_defensa +=1;
    }

    public void SetCantidadDefensas(int cantidad_defensa){
        this.cantidad_defensas = cantidad_defensa;
       }
    
    public void SetNivelDefensa(int nivel_de_defensa){
        this.nivel_de_defensa = nivel_de_defensa;
    }

    public void SetCapacidadDefensa(int capacidad_de_defensa){
        this.capacidad_de_defensa = capacidad_de_defensa;
    }

    public int GetCantidadDefensas(){
        return this.cantidad_defensas;
    }

    public int GetNivelDefensas(){
        return this.nivel_de_defensa;
    }

    public int GetCapacidadDefensa(){
        return this.capacidad_de_defensa;
    }
}
