package Aldea.Tropas;

public class Campamento {

        int capacidad_maxima;
        int cantidad_actual_campamento;
        int nivel_campamento;
        int nivel_ataque;

        public Campamento(){
            this.capacidad_maxima=10;
            this.cantidad_actual_campamento=0;
            this.nivel_campamento=1;
            this.nivel_ataque=0;
        }

        public void Agregar(Cuartel cuartel){
            int entrada = cantidad_actual_campamento + 1;

            if(capacidad_maxima<entrada){
               this.cantidad_actual_campamento+=1;
               cuartel.DisminuirCola(); 
            }
        }

        public void Vaciar(){
            this.cantidad_actual_campamento = 0;
        }

        public void GetCapacidad_Maxima(int capacidad_maxima){ this.capacidad_maxima = capacidad_maxima;}

        public void GetCantidad_Actual_Campamento(int cantidad_actual_campamento){ this.cantidad_actual_campamento = cantidad_actual_campamento;}

        public void GetNivel_Campamento(int nivel_campamento){this.nivel_campamento = nivel_campamento;}

        public void Get_Nivel_Ataque(int nivel_ataque){ this.nivel_ataque = nivel_ataque;}

        public int Set_Capacidad_Maxima(){ return this.capacidad_maxima;}

        public int Set_Cantidad_Actual_Campamento(){ return this.cantidad_actual_campamento;}

        public int Set_Nivel_Campamento(){ return this.nivel_campamento;}

        public int Set_Nivel_Ataque(){ return this.nivel_ataque;}
}
