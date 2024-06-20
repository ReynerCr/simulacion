package Aldea.Almacen;

public class AlmacenOro {
    
    int max;
    int nivel;
    int acum;

    public AlmacenOro(){
        this.max=10;
        this.nivel=1;
        this.acum=0;
    }

    public void Almacenar(int cantidadOro){
        
        int entrada = this.acum + cantidadOro;

        if(max < entrada){
            this.acum=entrada;
        }
    }

    public void Upgrade(AlmacenElixir almacen){
        
        int precio = this.nivel * 10;

        if(almacen.Consumir(precio)){
            this.nivel = this.nivel + 1;
            this.max = this.max + (this.nivel * 5);
        }

    }

    public boolean Consumir(int cantidadOro){

        if(cantidadOro > this.acum){
            return false;
        }
        else{
            this.acum = this.acum - cantidadOro;
            return true;
        }

    }

    public int GetMax(){ return this.max;}

    public int GetNivel(){ return this.nivel;}

    public int GetAcum(){ return this.acum;}

    public void SetMax(int max){ this.max=max;}

    public void SetNivel(int nivel){ this.nivel=nivel;}

    public void SetAcum(int acum){ this.acum=acum;}

}
