package Aldea.Recolectores;

public class Mina {
    
    int nivel;
    int tasa_produccion_oro;
    int capacidad_maxima;
    int oro_recolectado;
    int tasa_de_perdida;

    public Mina(int tasa_produccion_oro){
        this.tasa_produccion_oro = tasa_produccion_oro;
        this.nivel = 1;
        this.capacidad_maxima = 10;
        this.oro_recolectado = 0;
        this.tasa_de_perdida= 1;     
    }

  
    public void Producir(){
        int entrada = this.tasa_produccion_oro +this.oro_recolectado;
        if(capacidad_maxima < entrada){
            this.oro_recolectado = entrada;
        }
    }

    public void Perder(){
        this.oro_recolectado = this.oro_recolectado - this.tasa_de_perdida;
    }
    
    public void Upgrade(){
        this.nivel = this.nivel + 1;
        this.tasa_produccion_oro = this.tasa_produccion_oro + (this.nivel * 2);
        this.capacidad_maxima = this.capacidad_maxima + (this.nivel * 5);
        this.tasa_de_perdida = this.tasa_de_perdida + this.tasa_de_perdida * 2;
    }

    public void Vaciar(){
        this.oro_recolectado=0;
    }

    public void SetNivel(int nivel){ this.nivel=nivel;}

    public void Settasa_produccion_oro(int tasa_produccion_oro){ this.tasa_produccion_oro=tasa_produccion_oro; }

    public void Setcapacidad_maxima(int capacidad_maxima){ this.capacidad_maxima = capacidad_maxima; }

    public void Setoro_recolectado(int oro_recolectado){ this.oro_recolectado = oro_recolectado; }

    public int GetNivel(){ return this.nivel;}

    public int GetTasa(){ return this.tasa_produccion_oro;}

    public int Getcapacidad_maxima(){ return this.capacidad_maxima;}

    public int Getoro_recolectado(){ return this.capacidad_maxima;}

}
