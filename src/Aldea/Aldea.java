package Aldea;

// importar todas las clases de aldea
import Aldea.Recolectores.*;
import Aldea.Defensas.*;
import Aldea.Tropas.*;
import Aldea.Constructor.*;
import Aldea.Almacen.*;

import java.util.HashMap;
import java.util.Random;

public class Aldea {
    // hashmap para almacenar los edificios
    // como se guarda el tipo en el edificio capaz no es necesario esto
    private HashMap<TipoEdificio, Edificio> edificios;

    public Aldea() {
        this.edificios = new HashMap<TipoEdificio, Edificio>();
        Recolector extractor = new Recolector(1, TipoEdificio.EXTRACTOR);
        Recolector mina = new Recolector(1, TipoEdificio.MINA);
        Defensa defensa = new Defensa();
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

    public void recolectar() {
        Recolector extractor = getExtractor();
        Recolector mina = getMina();

        getAlmacenElixir().almacenar(extractor.getAcumulado());
        getAlmacenOro().almacenar(mina.getAcumulado());
        extractor.vaciar();
        mina.vaciar();
    }

    public Edificio upgradeEdificio(TipoEdificio tipoEdificio) {
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

        if (edificio.getTipoEdificio() == TipoEdificio.LABORATORIO) {
            getLaboratorio().aumentarCola();
        }

        return edificio;
    }

    public void terminarConstruccion(Edificio edificio) {
        if (edificio == null || !edificio.getOcupado()) {
            return;
        }

        edificio.upgrade();
        if (edificio.getTipoEdificio() == TipoEdificio.LABORATORIO) {
            getLaboratorio().disminuirCola();
            getCampamento().calcularAtaque(edificio.getNivel());
        }
        getConstructor().disminuirCola();
        edificio.setOcupado(false);
    }

    private Aldea crearAldeaRandom(Random rand) {
        Aldea rival = new Aldea();

        int nivelDefensa = getCampamento().getNivel() + rand.nextInt(2) - 1;
        // upgrade a defensas hasta que tenga el nivel de defensa calculado
        while (rival.getDefensa().getNivel() < nivelDefensa) {
            rival.getDefensa().upgrade();
        }

        // ahora calculo el promedio de los niveles de los almacenes y recolectores
        int nivelAlmacenesPromedio = (int) (getAlmacenElixir().getNivel() + getAlmacenOro().getNivel()) / 2;
        int nivelRecolectoresPromedio = (int) (getExtractor().getNivel() + getMina().getNivel()) / 2;
        int nivelPromedio = (int) (nivelAlmacenesPromedio + nivelRecolectoresPromedio) / 2;

        Almacen almaElixR = rival.getAlmacenElixir();
        Almacen almaOroR = rival.getAlmacenOro();
        Recolector minaR = rival.getMina();
        Recolector extractorR = rival.getExtractor();

        // upgrade a almacenes y recolectores hasta que tengan el nivel promedio
        while (almaElixR.getNivel() < nivelPromedio) {
            almaElixR.upgrade();
            almaOroR.upgrade();
        }
        while (extractorR.getNivel() < nivelPromedio) {
            minaR.upgrade();
            extractorR.upgrade();
        }

        // configuro el campamento y el laboratorio rival
        Campamento campamentoR = rival.getCampamento();
        Laboratorio laboratorioR = rival.getLaboratorio();
        // calculo el promedio de los niveles de campamento y laboratorio de mi aldea
        int nivelCampamentoR = getCampamento().getNivel() + rand.nextInt(2) - 1;
        int nivelLaboratorioR = getCampamento().getNivel() + rand.nextInt(3) - 2;

        // upgrade a campamento y laboratorio rivales hasta que tengan el nivel promedio
        while (campamentoR.getNivel() < nivelCampamentoR) {
            campamentoR.upgrade();
        }
        while (laboratorioR.getNivel() < nivelLaboratorioR) {
            campamentoR.upgrade();
        }

        // ahora si un random de 0 a 100 de cuánto tiene cada almacen y recolector
        // set los valores de los almacenes y recolectores
        almaOroR.setAcumulado(almaOroR.getMax() * rand.nextInt(100) / 100);
        almaElixR.setAcumulado(almaElixR.getMax() * rand.nextInt(100) / 100);
        minaR.setAcumulado(minaR.getCapacidadMaxima() * rand.nextInt(100) / 100);
        extractorR.setAcumulado(extractorR.getCapacidadMaxima() * rand.nextInt(100) / 100);

        // ahora seteo la cantidad de tropas en el campamento
        // cantidad de tropas entre 50 y 100% de la capacidad máxima
        int cantidadTropas = campamentoR.getCapacidadMaxima() * (rand.nextInt(50) + 50) / 100;
        campamentoR.setCantidadActualCampamento(cantidadTropas);
        campamentoR.calcularAtaque(laboratorioR.getNivel());

        return rival;
    }

    public void atacarRival() {
        Random rand = new Random(43);
        Aldea rival = crearAldeaRandom(rand);
        ataque(this, rival, rand);
    }

    public void defenderDeRival() {
        Random rand = new Random(43);
        Aldea rival = crearAldeaRandom(rand);
        ataque(this, rival, rand);
    }

    private void ataque(Aldea atacante, Aldea defensor, Random rand) {
        int atacanteCapacidad = atacante.getCampamento().getCapacidadAtaque();
        if (atacanteCapacidad == 0) {
            System.out.println("Error: no hay tropas en el campamento");
            return;
        }
        
        // ahora si se puede atacar
        int defensorCapacidad = defensor.getDefensa().getCapacidadDefensa();
        boolean victoria;
        if (atacanteCapacidad == defensorCapacidad) {
            victoria = rand.nextBoolean(); // 50% de probabilidad
        } else {
            victoria = atacanteCapacidad > defensorCapacidad;
        }

        // calculando posibles recursos a robar
        // TODO si mi aldea defiende no deberia quitar los recursos asi
        int oro = defensor.getAlmacenOro().perder() + defensor.getMina().perder();
        int elixir = defensor.getAlmacenElixir().perder() + defensor.getExtractor().perder();

        atacante.getCampamento().vaciar();

        // TODO ojo esto al imprimir solo es para cuando ataca
        // esto se debe sacar de este metodo y ponerlo en cada metodo respectivo
        System.out.println("Rival: " + defensorCapacidad + " vs Atacante: " + atacanteCapacidad);
        if (victoria) {
            // robar recursos
            // al ganar se supondra que se roban todos los recursos
            atacante.getAlmacenOro().almacenar(oro);
            atacante.getAlmacenElixir().almacenar(elixir);

            System.out.println("Victoria en el ataque");
            System.out.printf("%-34.34s  %-25.25sn", "Oro total: " + oro, "Oro robado: " + oro);

            System.out.println("Elixir robado: " + elixir);
            System.out.println("Tropas restantes: " + atacante.getCampamento().getCantidadActualCampamento());
        } else {
            // al perder se supone que a lo mucho se roba el 50% de los recursos
            // se calcula de 0 a 50% de los recursos
            int oroRobado = oro * rand.nextInt(50) / 100;
            int elixirRobado = elixir * rand.nextInt(50) / 100;
            atacante.getAlmacenOro().almacenar(oroRobado);
            atacante.getAlmacenElixir().almacenar(elixirRobado);


            System.out.println("Derrota en el ataque");
            System.out.println("Oro robado: " + oro);
            System.out.println("Elixir robado: " + elixir);
            System.out.println("Tropas restantes: " + atacante.getCampamento().getCantidadActualCampamento());
        }
    }

    public Almacen getAlmacenElixir() {
        return (Almacen) edificios.get(TipoEdificio.ALMACEN_ELIXIR);
    }

    public Almacen getAlmacenOro() {
        return (Almacen) edificios.get(TipoEdificio.ALMACEN_ORO);
    }

    public Recolector getMina() {
        return (Recolector) edificios.get(TipoEdificio.MINA);
    }

    public Recolector getExtractor() {
        return (Recolector) edificios.get(TipoEdificio.EXTRACTOR);
    }

    public Defensa getDefensa() {
        return (Defensa) edificios.get(TipoEdificio.DEFENSA);
    }

    public Cuartel getCuartel() {
        return (Cuartel) edificios.get(TipoEdificio.CUARTEL);
    }

    public Campamento getCampamento() {
        return (Campamento) edificios.get(TipoEdificio.CAMPAMENTO);
    }

    public Laboratorio getLaboratorio() {
        return (Laboratorio) edificios.get(TipoEdificio.LABORATORIO);
    }

    public Constructor getConstructor() {
        return (Constructor) edificios.get(TipoEdificio.CONSTRUCTOR);
    }
}
