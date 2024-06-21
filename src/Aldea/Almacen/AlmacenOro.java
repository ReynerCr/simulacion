package Aldea.Almacen;

public class AlmacenOro {
    private int max;
    private int nivel;
    private int acum;
    private int tasaPerdida;
    private int precio;

    public AlmacenOro(){
        this.max=10;
        this.nivel=1;
        this.acum=0;
        this.tasaPerdida=1;
        this.precio=nivel*5;
    }

    public void perder(){
        this.acum = this.acum - (this.max * ((this.tasaPerdida)/10));
    }

    public void almacenar(int cantidadOro){
        int entrada = this.acum + cantidadOro;

        if(max > entrada){
            this.acum=entrada;
        } else {
            this.acum=max;
        }
    }

    public void upgrade(){
        this.nivel = this.nivel + 1;
        this.max = this.max + (this.nivel * 5);
        this.tasaPerdida = tasaPerdida + 1;
        this.precio = this.nivel * 5;
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
    public int getPrecio(){ return this.precio; }
}
