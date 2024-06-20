package Aldea.Tropas;

public class Campamento {

        int capacidadMaxima;
        int cantidadActualCampamento;
        int nivelCampamento;
        int nivelAtaque;

        public Campamento(){
            this.capacidadMaxima=10;
            this.cantidadActualCampamento=0;
            this.nivelCampamento=1;
            this.nivelAtaque=0;
        }

        public void agregar(Cuartel cuartel){
            int entrada = cantidadActualCampamento + 1;

            if(capacidadMaxima<entrada){
               this.cantidadActualCampamento+=1;
               cuartel.disminuirCola(); 
            }
        }

        public void vaciar(){
            this.cantidadActualCampamento = 0;
        }

        public void getCapacidadMaxima(int capacidadMaxima){ this.capacidadMaxima = capacidadMaxima;}

        public void getCantidadActualCampamento(int cantidadActualCampamento){ this.cantidadActualCampamento = cantidadActualCampamento;}

        public void getNivelCampamento(int nivelCampamento){this.nivelCampamento = nivelCampamento;}

        public void getNivelAtaque(int nivelAtaque){ this.nivelAtaque = nivelAtaque;}

        public int setCapacidadMaxima(){ return this.capacidadMaxima;}

        public int setCantidadActualCampamento(){ return this.cantidadActualCampamento;}

        public int setNivelCampamento(){ return this.nivelCampamento;}

        public int setNivelAtaque(){ return this.nivelAtaque;}
}
