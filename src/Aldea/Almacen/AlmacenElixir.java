package Aldea.Almacen;

public class AlmacenElixir {
    int max;
    int nivel;
    int acum;
    int tasaPerdida;

    public AlmacenElixir(){
        this.max=10;
        this.nivel=1;
        this.acum=0;
        this.tasaPerdida=1;
    }

    public void perder(){
        this.acum = this.acum - (this.max * ((this.tasaPerdida)/10));
    }

    public void almacenar(int cantidadElixir){
        int entrada = this.acum + cantidadElixir;

        if(max > entrada){
            this.acum=entrada;
        } else {
            this.acum=max;
        }
    }

    public void upgrade(AlmacenOro almacen){
        int precio = this.nivel * 10;

        if(almacen.consumir(precio)){
            this.nivel = this.nivel + 1;
            this.max = this.max + (this.nivel * 5);
            this.tasaPerdida = tasaPerdida + 1;
        }
    }

    public boolean consumir(int cantidadElixir){
        if(cantidadElixir > this.acum){
            return false;
        }
        else{
            this.acum = this.acum - cantidadElixir;
            return true;
        }
    }

    public int getMax(){ return this.max;}
    public int getNivel(){ return this.nivel;}
    public int getAcum(){ return this.acum;}
    public void setMax(int max){ this.max=max;}
    public void setNivel(int nivel){ this.nivel=nivel;}
    public void setAcum(int acum){ this.acum=acum;}
}
