package Aldea.Tropas;

public class Cuartel {
    
    int capacidad_maxima;
    int nivel_cuartel;
    int cola_de_entrenamiento;

    public Cuartel(){
        this.capacidad_maxima=3;
        this.nivel_cuartel = 1;
        this.cola_de_entrenamiento = 0;
    }

    public void AumentarCola(int tropa){
        int entrada = this.cola_de_entrenamiento + tropa;

        if(capacidad_maxima < entrada){
            this.cola_de_entrenamiento = this.capacidad_maxima;
        }
    }

    public void DisminuirCola(){
        this.cola_de_entrenamiento = this.cola_de_entrenamiento - 1;
    }

    public void Upgrade(){
        this.nivel_cuartel += 1;
        this.capacidad_maxima +=1;
    }
    
}
