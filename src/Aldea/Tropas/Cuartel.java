package Aldea.Tropas;

public class Cuartel {
    
    private int capacidadMaxima;
    private int nivelCuartel;
    private int colaEntrenamiento;

    public Cuartel(){
        this.capacidadMaxima=3;
        this.nivelCuartel = 1;
        this.colaEntrenamiento = 0;
    }

    public void aumentarCola(int tropa){
        int entrada = this.colaEntrenamiento + tropa;

        if(capacidadMaxima > entrada){
            this.colaEntrenamiento = entrada;
        } else {
            this.colaEntrenamiento = capacidadMaxima;
            System.out.println("No se pudieron entrenar " + (entrada - capacidadMaxima) + " tropas.");
        }
    }

    // TODO cuidado con la cantidad de tropas
    public void disminuirCola(int cantidad){
        if (cantidad < this.colaEntrenamiento){
            this.colaEntrenamiento = this.colaEntrenamiento - cantidad;
        } else {
            this.colaEntrenamiento = 0;
        }
    }

    public void upgrade(){
        this.nivelCuartel += 1;
        this.capacidadMaxima +=1;
    }

    public int getColaEntrenamiento(){
        return this.colaEntrenamiento;
    }
}
