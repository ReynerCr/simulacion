package Aldea.Tropas;

public class Laboratorio {
    
        int capacidad_maxima;
        int cantidad_tropas;

        public Laboratorio(){

            this.capacidad_maxima=1;
            this.cantidad_tropas=0;

        }

        public void Ingresar(){
            int entrada = this.cantidad_tropas + 1;
            if(this.capacidad_maxima<entrada){
                this.cantidad_tropas= entrada;
            }
        }

        public void Eliminar(){
            this.cantidad_tropas-=1;
        }

        
}
