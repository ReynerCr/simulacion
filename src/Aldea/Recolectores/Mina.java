package Aldea.Recolectores;

public class Mina {
    private int nivel;
    private int tasaProduccionOro;
    private int capacidadMaxima;
    private int oroRecolectado;
    private int tasaPerdida;

    public Mina(int tasaProduccionOro){
        this.tasaProduccionOro = tasaProduccionOro;
        this.nivel = 1;
        this.capacidadMaxima = 10;
        this.oroRecolectado = 0;
        this.tasaPerdida= 1;     
    }
  
    public void producir(){
        int entrada = this.tasaProduccionOro + this.oroRecolectado;
        System.out.println(entrada);
        if(capacidadMaxima >= entrada){
            this.oroRecolectado = entrada;
        }
    }

    public void perder(){
        this.oroRecolectado = this.oroRecolectado - this.tasaPerdida;
    }
    
    public void upgrade(){
        this.nivel = this.nivel + 1;
        this.tasaProduccionOro = this.tasaProduccionOro + (this.nivel * 2);
        this.capacidadMaxima = this.capacidadMaxima + (this.nivel * 5);
        this.tasaPerdida = this.tasaPerdida + this.tasaPerdida * 2;
    }

    public void vaciar(){
        this.oroRecolectado=0;
    }

    public void setNivel(int nivel){ this.nivel=nivel;}

    public void setTasaProduccionOro(int tasaProduccionOro){ this.tasaProduccionOro=tasaProduccionOro; }

    public void setCapacidadMaxima(int capacidadMaxima){ this.capacidadMaxima = capacidadMaxima; }

    public void setOroRecolectado(int oroRecolectado){ this.oroRecolectado = oroRecolectado; }

    public int getNivel(){ return this.nivel;}

    public int getTasa(){ return this.tasaProduccionOro;}

    public int getCapacidadMaxima(){ return this.capacidadMaxima;}

    public int getOroRecolectado(){ return this.oroRecolectado;}
}
