package Aldea;

// importar todas las clases de aldea
import Aldea.Recolectores.*;
import Aldea.Defensas.*;
import Aldea.Tropas.*;
import Aldea.Constructor.*;
import Aldea.Almacen.*;

public class Aldea {
    private Extractor extractor;
    private Mina mina;
    private Defensas defensa;
    private Constructor constructor;
    private AlmacenElixir almacenElixir;
    private AlmacenOro almacenOro;
    private Campamento campamento;
    private Cuartel cuartel;
    private Laboratorio laboratorio;

    public Aldea(){
        this.extractor = new Extractor(1);
        this.mina = new Mina(1);
        this.defensa = new Defensas();
        this.constructor = new Constructor();
        this.almacenElixir = new AlmacenElixir();
        this.almacenOro = new AlmacenOro();
        this.campamento = new Campamento();
        this.cuartel = new Cuartel();
        this.laboratorio = new Laboratorio();
    }

    public void producir() {
        mina.producir();
        extractor.producir();
    }

    public Mina getMina() {
        return mina;
    }

    public Extractor getExtractor() {
        return extractor;
    }

    public Defensas getDefensa() {
        return defensa;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public AlmacenElixir getAlmacenElixir() {
        return almacenElixir;
    }

    public AlmacenOro getAlmacenOro() {
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
        almacenElixir.almacenar(extractor.getElixirRecolectado());
        almacenOro.almacenar(mina.getOroRecolectado());
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
        this.almacenElixir.upgrade(almacenOro);
    }

    public void upgradeAlmacenOro(){
        this.almacenOro.upgrade(almacenElixir);
    }

    public void upgradeCuartel(){
        this.cuartel.upgrade();
    }

    public void aumentarDefensa(){
        this.defensa.aumentar();
    }

    public void entrenarTropa(){
        this.cuartel.aumentarCola(1);
    }

    public void getNumTropas() {
        this.cuartel.getColaEntrenamiento();
    }
}
