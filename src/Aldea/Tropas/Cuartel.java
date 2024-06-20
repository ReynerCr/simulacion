package Aldea.Tropas;

public class Cuartel {
    
    int capacidadMaxima;
    int nivelCuartel;
    int colaEntrenamiento;

    public Cuartel(){
        this.capacidadMaxima=3;
        this.nivelCuartel = 1;
        this.colaEntrenamiento = 0;
    }

    public void aumentarCola(int tropa){
        int entrada = this.colaEntrenamiento + tropa;

        if(capacidadMaxima < entrada){
            this.colaEntrenamiento = this.capacidadMaxima;
        }
    }

    public void disminuirCola(){
        this.colaEntrenamiento = this.colaEntrenamiento - 1;
    }

    public void upgrade(){
        this.nivelCuartel += 1;
        this.capacidadMaxima +=1;
    }
    
}
