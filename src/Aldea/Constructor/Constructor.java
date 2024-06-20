package Aldea.Constructor;

public class Constructor {
    
    private int capacidad_maxima;
    private int nivel_constructor;
    private int cola_de_construccion;

    public Constructor(){
        this.capacidad_maxima=3;
        this.nivel_constructor=1;
        this.cola_de_construccion = 0;
    }

    public void AumentarCola(int Edificio){
        int entrada = this.cola_de_construccion + 1;
        if(capacidad_maxima<entrada){
            this.cola_de_construccion=entrada;
        }
    }

    public void DisminuirCola(){
        this.cola_de_construccion-=1;
    }

    public void Upgrade(){
        this.nivel_constructor+=1;
        this.capacidad_maxima+=1;

    }

    public void SetCapacidad(int capacidad_maxima){ this.capacidad_maxima = capacidad_maxima; }
    
    public void SetNivel(int nivel_constructor){ this.nivel_constructor = nivel_constructor;}
    
    public void SetCola(int cola_de_construccion){ this.cola_de_construccion = cola_de_construccion;}

    public int GetCapacidad(){ return this.capacidad_maxima; }

    public int GetNivel(){ return this.nivel_constructor; }

    public int GetCola(){ return this.cola_de_construccion; }
}
