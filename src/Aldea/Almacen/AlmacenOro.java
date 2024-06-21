package Aldea.Almacen;

public class AlmacenOro {
    private int max;
    private int nivel;
    private int acum;

    public AlmacenOro(){
        this.max=10;
        this.nivel=1;
        this.acum=0;
    }

    public void almacenar(int cantidadOro){
        
        int entrada = this.acum + cantidadOro;

        if(max > entrada){
            this.acum=entrada;
        } else {
            this.acum=max;
        }
    }

    public void upgrade(AlmacenElixir almacen){
        int precio = this.nivel * 10;

        if(almacen.consumir(precio)){
            this.nivel = this.nivel + 1;
            this.max = this.max + (this.nivel * 5);
        }
    }

    public boolean consumir(int cantidadOro){
        if(cantidadOro > this.acum){
            return false;
        }
        else{
            this.acum = this.acum - cantidadOro;
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
