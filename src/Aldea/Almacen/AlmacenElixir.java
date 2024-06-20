package Aldea.Almacen;

public class AlmacenElixir {
    
    int max;
    int nivel;
    int acum;
    int tasa_de_perdida;

    public AlmacenElixir(){
        this.max=10;
        this.nivel=1;
        this.acum=0;
        this.tasa_de_perdida=1;
    }

    public void Perder(){
        this.acum = this.acum - (this.max * ((this.tasa_de_perdida)/10));
    }

    public void Almacenar(int cantidadElixir){
        
        int entrada = this.acum + cantidadElixir;

        if(max < entrada){
            this.acum=entrada;
        }
    }

    public void Upgrade(AlmacenOro almacen){
        
        int precio = this.nivel * 10;

        if(almacen.Consumir(precio)){
            this.nivel = this.nivel + 1;
            this.max = this.max + (this.nivel * 5);
            this.tasa_de_perdida = tasa_de_perdida + 1;
        }

    }

    public boolean Consumir(int cantidadElixir){

        if(cantidadElixir > this.acum){
            return false;
        }
        else{
            this.acum = this.acum - cantidadElixir;
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
