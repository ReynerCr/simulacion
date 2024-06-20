package Aldea.Constructor;

public class Constructor {
    
    private int capacidadMaxima;
    private int nivelConstructor;
    private int colaConstruccion;

    public Constructor(){
        this.capacidadMaxima=3;
        this.nivelConstructor=1;
        this.colaConstruccion = 0;
    }

    public void aumentarCola(int edificio){
        int entrada = this.colaConstruccion + 1;
        if(capacidadMaxima < entrada){
            this.colaConstruccion=entrada;
        }
    }

    public void disminuirCola(){
        this.colaConstruccion-=1;
    }

    public void upgrade(){
        this.nivelConstructor+=1;
        this.capacidadMaxima+=1;

    }

    public void setCapacidad(int capacidadMaxima){ this.capacidadMaxima = capacidadMaxima; }
    
    public void setNivel(int nivelConstructor){ this.nivelConstructor = nivelConstructor;}
    
    public void setCola(int colaConstruccion){ this.colaConstruccion = colaConstruccion;}

    public int getCapacidad(){ return this.capacidadMaxima; }

    public int getNivel(){ return this.nivelConstructor; }

    public int getCola(){ return this.colaConstruccion; }
}
