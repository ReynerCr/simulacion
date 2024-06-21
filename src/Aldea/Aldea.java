package Aldea;

// importar todas las clases de aldea
import Aldea.Recolectores.*;
import Aldea.Defensas.*;
import Aldea.Tropas.*;
import Aldea.Constructor.*;
import Aldea.Almacen.*;

import java.util.HashMap;

public class Aldea {
    // hashmap para almacenar los edificios
    // como se guarda el tipo en el edificio capaz no es necesario esto
    private HashMap<TipoEdificio, Edificio> edificios;

    public Aldea(){
        this.edificios = new HashMap<TipoEdificio, Edificio>();
        Recolector extractor = new Recolector(1, TipoEdificio.EXTRACTOR);
        Recolector mina = new Recolector(1, TipoEdificio.MINA);
        Defensas defensa = new Defensas();
        Constructor constructor = new Constructor();
        Almacen almacenElixir = new Almacen(TipoEdificio.ALMACEN_ELIXIR);
        Almacen almacenOro = new Almacen(TipoEdificio.ALMACEN_ORO);
        Campamento campamento = new Campamento();
        Cuartel cuartel = new Cuartel();
        Laboratorio laboratorio = new Laboratorio();

        this.edificios.put(TipoEdificio.EXTRACTOR, extractor);
        this.edificios.put(TipoEdificio.MINA, mina);
        this.edificios.put(TipoEdificio.DEFENSA, defensa);
        this.edificios.put(TipoEdificio.CONSTRUCTOR, constructor);
        this.edificios.put(TipoEdificio.ALMACEN_ELIXIR, almacenElixir);
        this.edificios.put(TipoEdificio.ALMACEN_ORO, almacenOro);
        this.edificios.put(TipoEdificio.CAMPAMENTO, campamento);
        this.edificios.put(TipoEdificio.CUARTEL, cuartel);
        this.edificios.put(TipoEdificio.LABORATORIO, laboratorio);
    }

    public void producir() {
        getMina().producir();
        getExtractor().producir();
    }

    public Edificio getEdificio(TipoEdificio tipoEdificio) {
        return edificios.get(tipoEdificio);
    }

    public void recolectar(){
        Recolector extractor = getExtractor();
        Recolector mina = getMina();
        
        getAlmacenElixir().almacenar(extractor.getAcumulado());
        getAlmacenOro().almacenar(mina.getAcumulado());
        extractor.vaciar();
        mina.vaciar();
    }

    public Edificio upgradeEdificio(TipoEdificio tipoEdificio){
        Edificio edificio = getEdificio(tipoEdificio);
        Almacen almacen = null;

        // chequear si el edificio se puede mejorar
        if (edificio == null) {
            System.out.println("Error: el edificio no existe");
            return null;
        } else if (edificio.getNivel() == 10) {
            System.out.println("Error: el edificio ya está en el nivel máximo");
            return null;
        } else if (edificio.getNivel() == 0) {
            System.out.println("Error: el edificio no ha sido construido");
            return null;
        } else if (edificio.getOcupado()) {
            System.out.println("Error: el edificio está en proceso de construcción o mejora");
            return null;
        }

        // chequear si hay suficiente recurso de consumo para mejorar el edificio
        if (edificio.getTipoAlmacenConsumo() == TipoEdificio.ALMACEN_ORO) {
            almacen = getAlmacenOro();
        } else if (edificio.getTipoAlmacenConsumo() == TipoEdificio.ALMACEN_ELIXIR) {
            almacen = getAlmacenElixir();
        } else {
            almacen = getAlmacenOro();
        }

        // chequear la disponibilidad de constructores
        Constructor constructor = getConstructor();
        int dispConstructor = constructor.getDisponibilidad();
        if (dispConstructor == 0) {
            System.out.println("Error: la cola de constructores está llena");
            return null;
        }

        int precio = edificio.getPrecioMejora();
        boolean mejora = almacen.consumir(precio);
        if (!mejora) {
            System.out.println(
                "No hay suficiente " + (almacen.getTipoEdificio() == TipoEdificio.ALMACEN_ORO ? "oro" : "elixir")
                    + " para mejorar el edificio");
            return null;
        }

        constructor.aumentarCola(1);
        edificio.setOcupado(true);
        return edificio;
    }

    public void terminarConstruccion(Edificio edificio){
        if (edificio == null || !edificio.getOcupado()) {
            return;
        }
        
        edificio.upgrade();
        getConstructor().disminuirCola();
        edificio.setOcupado(false);
    }

    public Almacen getAlmacenElixir() { return (Almacen) edificios.get(TipoEdificio.ALMACEN_ELIXIR); }

    public Almacen getAlmacenOro() { return (Almacen) edificios.get(TipoEdificio.ALMACEN_ORO); }

    public Recolector getMina() { return (Recolector) edificios.get(TipoEdificio.MINA); }

    public Recolector getExtractor() { return (Recolector) edificios.get(TipoEdificio.EXTRACTOR); }

    public Defensas getDefensa() { return (Defensas) edificios.get(TipoEdificio.DEFENSA); }

    public Cuartel getCuartel() { return (Cuartel) edificios.get(TipoEdificio.CUARTEL); }

    public Campamento getCampamento() { return (Campamento) edificios.get(TipoEdificio.CAMPAMENTO); }

    public Laboratorio getLaboratorio() { return (Laboratorio) edificios.get(TipoEdificio.LABORATORIO); }

    public Constructor getConstructor() { return (Constructor) edificios.get(TipoEdificio.CONSTRUCTOR); }
}
