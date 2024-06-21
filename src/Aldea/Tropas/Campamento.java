package Aldea.Tropas;

public class Campamento {

        private int capacidadMaxima;
        private int cantidadActualCampamento;
        private int nivelCampamento;
        private int nivelAtaque;

        public Campamento(){
            this.capacidadMaxima=10;
            this.cantidadActualCampamento=0;
            this.nivelCampamento=1;
            this.nivelAtaque=0;
        }

        // TODO cuidado con la cantidad de tropas
        public void agregar(Cuartel cuartel){
            int entrada = cantidadActualCampamento + 1;

            if(capacidadMaxima > entrada){
               this.cantidadActualCampamento = entrada;
               cuartel.disminuirCola(1); 
            }
        }

        public void vaciar(){
            this.cantidadActualCampamento = 0;
        }

        public int getCapacidadMaxima(){ return capacidadMaxima;}

        public int getCantidadActualCampamento(){ return cantidadActualCampamento; }

        public int getNivelCampamento(){ return nivelCampamento; }

        public int getNivelAtaque(){ return nivelAtaque;}

        public int setCapacidadMaxima(){ return this.capacidadMaxima;}

        public int setCantidadActualCampamento(){ return this.cantidadActualCampamento;}

        public int setNivelCampamento(){ return this.nivelCampamento;}

        public int setNivelAtaque(){ return this.nivelAtaque;}
}
