package Aldea.Almacen;

public class Almacen {
    private int max;
    private int nivel;
    private int acumulado;
    private int tasaPerdida;
    private int precioMejora;

    public Almacen(){
        this.max=10;
        this.nivel=1;
        this.acumulado=0;
        this.tasaPerdida=1;
        this.precioMejora = nivel * 5;
    }

    public void perder(){
        this.acumulado = this.acumulado - (this.max * ((this.tasaPerdida)/10));
    }

    public void almacenar(int cantidad){
        int entrada = this.acumulado + cantidad;

        if(max > entrada){
            this.acumulado=entrada;
        } else {
            this.acumulado=max;
        }
    }

    public void upgrade(){
        this.nivel = this.nivel + 1;
        this.max = this.max + (this.nivel * 5);
        this.tasaPerdida = tasaPerdida + 1;
        this.precioMejora = this.nivel * 5;
    }

    public boolean consumir(int cantidad){
        if(cantidad > this.acumulado){
            return false;
        }
        else{
            this.acumulado = this.acumulado - cantidad;
            return true;
        }
    }

    public int getMax(){ return this.max;}
    public int getNivel(){ return this.nivel;}
    public int getAcumulado(){ return this.acumulado;}
    public void setMax(int max){ this.max=max;}
    public void setNivel(int nivel){ this.nivel=nivel;}
    public void setAcumulado(int acumulado){ this.acumulado=acumulado;}
    public int getPrecioMejora(){ return this.precioMejora; }
}
