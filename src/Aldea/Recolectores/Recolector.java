package Aldea.Recolectores;

public class Recolector {
    private int nivel;
    private int tasaProduccion;
    private int capacidadMaxima;
    private int acumulado;
    private int tasaPerdida;
    private int precioMejora;

    public Recolector(int tasaProduccion){
        this.tasaProduccion = tasaProduccion;
        this.nivel = 1;
        this.capacidadMaxima = 10;
        this.acumulado = 0;
        this.tasaPerdida = 1;
        this.precioMejora = nivel * 5;
    }
  
    public void producir(){
        int entrada = this.tasaProduccion + this.acumulado;
        if(capacidadMaxima >= entrada){
            this.acumulado = entrada;
        }
    }

    public void perder(){
        this.acumulado = this.acumulado - this.tasaPerdida;
    }
    
    public void upgrade(){
        this.nivel = this.nivel + 1;
        this.tasaProduccion = this.tasaProduccion + (this.nivel * 2);
        this.capacidadMaxima = this.capacidadMaxima + (this.nivel * 5);
        this.tasaPerdida = this.tasaPerdida + this.tasaPerdida * 2;
        this.precioMejora = this.nivel * 5;
    }

    public void vaciar(){
        this.acumulado=0;
    }

    public void setNivel(int nivel){ this.nivel=nivel;}

    public void setTasaProduccion(int tasaProduccion){ this.tasaProduccion=tasaProduccion; }

    public void setCapacidadMaxima(int capacidadMaxima){ this.capacidadMaxima = capacidadMaxima; }

    public void setAcumulado(int acumulado){ this.acumulado = acumulado; }

    public int getNivel(){ return this.nivel;}

    public int getTasa(){ return this.tasaProduccion;}

    public int getCapacidadMaxima(){ return this.capacidadMaxima;}

    public int getAcumulado(){ return this.acumulado;}

    public int getPrecioMejora() { return this.precioMejora; }
}
