package Aldea.Recolectores;

public class Extractor {
    
    private int nivel;
    private int tasaProduccionElixir;
    private int capacidadMaxima;
    private int elixirRecolectado;
    private int tasaPerdida;

    public Extractor(int tasaProduccionElixir){
        this.tasaProduccionElixir = tasaProduccionElixir;
        this.capacidadMaxima = 10;
        this.elixirRecolectado = 0;
        this.tasaProduccionElixir = 1;
    }

    public void producir(){
        int entrada = this.tasaProduccionElixir +this.elixirRecolectado;
        if(capacidadMaxima >= entrada){
            this.elixirRecolectado = entrada;
        }
    }

    public void upgrade(){
        this.nivel = this.nivel + 1;
        this.tasaProduccionElixir = this.tasaProduccionElixir + (this.nivel * 2);
        this.capacidadMaxima = this.capacidadMaxima + (this.nivel * 5);
        this.tasaPerdida = this.tasaPerdida + (this.tasaPerdida * 2);
    }

    public void perder(){
        this.elixirRecolectado= this.elixirRecolectado - this.tasaPerdida;
    }
    public void vaciar(){
        this.elixirRecolectado=0;
    }

    public void setNivel(int nivel){ this.nivel=nivel;}

    public void setTasaProduccionElixir(int tasaProduccionElixir){ this.tasaProduccionElixir=tasaProduccionElixir; }

    public void setCapacidadMaxima(int capacidadMaxima){ this.capacidadMaxima = capacidadMaxima; }

    public void setElixirRecolectado(int elixirRecolectado){ this.elixirRecolectado = elixirRecolectado; }

    public int getNivel(){ return this.nivel;}

    public int getTasaProduccionElixir(){ return this.tasaProduccionElixir;}

    public int getCapacidadMaxima(){ return this.capacidadMaxima;}

    public int getElixirRecolectado(){ return this.elixirRecolectado;}

}
