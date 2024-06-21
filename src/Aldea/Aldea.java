package Aldea;

// importar todas las clases de aldea
import Aldea.Recolectores.*;
import Aldea.Defensas.*;
import Aldea.Tropas.*;
import Aldea.Constructor.*;
import Aldea.Almacen.*;

public class Aldea {
    private Recolector extractor;
    private Recolector mina;
    private Defensas defensa;
    private Constructor constructor;
    private Almacen almacenElixir;
    private Almacen almacenOro;
    private Campamento campamento;
    private Cuartel cuartel;
    private Laboratorio laboratorio;

    public Aldea(){
        this.extractor = new Recolector(1);
        this.mina = new Recolector(1);
        this.defensa = new Defensas();
        this.constructor = new Constructor();
        this.almacenElixir = new Almacen();
        this.almacenOro = new Almacen();
        this.campamento = new Campamento();
        this.cuartel = new Cuartel();
        this.laboratorio = new Laboratorio();
    }

    public void producir() {
        mina.producir();
        extractor.producir();
    }

    public Recolector getMina() {
        return mina;
    }

    public Recolector getExtractor() {
        return extractor;
    }

    public Defensas getDefensa() {
        return defensa;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public Almacen getAlmacenElixir() {
        return almacenElixir;
    }

    public Almacen getAlmacenOro() {
        return almacenOro;
    }

    public Campamento getCampamento() {
        return campamento;
    }

    public Cuartel getCuartel() {
        return cuartel;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void recolectar(){
        almacenElixir.almacenar(extractor.getAcumulado());
        almacenOro.almacenar(mina.getAcumulado());
        extractor.vaciar();
        mina.vaciar();
    }

    public void upgradeExtractor(){
        this.extractor.upgrade();
    } 

    public void upgradeMina(){
        this.mina.upgrade();
    }

    public void upgradeDefensa(){
        this.defensa.upgrade();
    }

    public void upgradeConstructor(){
        this.constructor.upgrade();
    }

    public void upgradeAlmacenElixir(){
        this.almacenElixir.upgrade();
    }

    public void upgradeAlmacenOro(){
        this.almacenOro.upgrade();
    }

    public void upgradeCuartel(){
        this.cuartel.upgrade();
    }

    public void aumentarDefensa(){
        this.defensa.aumentar();
    }

    public void getNumTropas() {
        this.cuartel.getColaEntrenamiento();
    }

    public boolean encolarConstruccion(int nEdificios){
        boolean encolado = constructor.aumentarCola(nEdificios);
        if (!encolado) {
            System.out.println("Error: la cola de constructores está llena");
            return true;
        }
        return false;
    }
}
