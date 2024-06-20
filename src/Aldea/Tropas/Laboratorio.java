package Aldea.Tropas;

public class Laboratorio {
    
        int capacidadMaxima;
        int cantidadTropas;

        public Laboratorio(){
            this.capacidadMaxima=1;
            this.cantidadTropas=0;
        }

        public void ingresar(){
            int entrada = this.cantidadTropas + 1;
            if(this.capacidadMaxima<entrada){
                this.cantidadTropas= entrada;
            }
        }

        public void eliminar(){
            this.cantidadTropas-=1;
        }
}
