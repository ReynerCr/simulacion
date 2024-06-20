package Aldea.Recolectores;

public class Extractor {
    
    int nivel;
    int tasa_produccion_elixir;
    int capacidad_maxima;
    int elixir_recolectado;
    int tasa_de_perdida;

    public Extractor(int tasa_produccion_elixir){
        this.tasa_produccion_elixir = tasa_produccion_elixir;
        this.capacidad_maxima = 10;
        this.elixir_recolectado = 0;
        this.tasa_produccion_elixir = 1;
    }

    public void Producir(){
        int entrada = this.tasa_produccion_elixir +this.elixir_recolectado;
        if(capacidad_maxima < entrada){
            this.elixir_recolectado = entrada;
        }
    }

    public void Upgrade(){
        this.nivel = this.nivel + 1;
        this.tasa_produccion_elixir = this.tasa_produccion_elixir + (this.nivel * 2);
        this.capacidad_maxima = this.capacidad_maxima + (this.nivel * 5);
        this.tasa_de_perdida = this.tasa_de_perdida + (this.tasa_de_perdida * 2);
    }

    public void Perder(){
        this.elixir_recolectado= this.elixir_recolectado - this.tasa_de_perdida;
    }
    public void Vaciar(){
        this.elixir_recolectado=0;
    }

    public void SetNivel(int nivel){ this.nivel=nivel;}

    public void Settasa_produccion_elixir(int tasa_produccion_elixir){ this.tasa_produccion_elixir=tasa_produccion_elixir; }

    public void Setcapacidad_maxima(int capacidad_maxima){ this.capacidad_maxima = capacidad_maxima; }

    public void Setelixir_recolectado(int elixir_recolectado){ this.elixir_recolectado = elixir_recolectado; }

    public int GetNivel(){ return this.nivel;}

    public int Gettasa_produccion_elixir(){ return this.tasa_produccion_elixir;}

    public int Getcapacidad_maxima(){ return this.capacidad_maxima;}

    public int Getelixir_recolecta(){ return this.elixir_recolectado;}

}
