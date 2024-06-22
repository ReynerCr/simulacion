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
        Recolector extractor = new Recolector(10, TipoEdificio.EXTRACTOR);
        Recolector mina = new Recolector(10, TipoEdificio.MINA);
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
        int nivelDefensa = getCampamento().getNivel() + rand.nextInt(2);
        // upgrade a defensas hasta que tenga el nivel de defensa calculado
        while (rival.getDefensa().getNivel() < nivelDefensa) {
            rival.getDefensa().upgrade();
        }

        // ahora calculo el promedio de los niveles de los almacenes y recolectores
        int nivelAlmacenesPromedio = ((getAlmacenElixir().getNivel() + getAlmacenOro().getNivel()) / 2);
        int nivelRecolectoresPromedio = ((getExtractor().getNivel() + getMina().getNivel()) / 2);
        Almacen almaElixR = rival.getAlmacenElixir();
        Almacen almaOroR = rival.getAlmacenOro();
        Recolector minaR = rival.getMina();
        Recolector extractorR = rival.getExtractor();
        // upgrade a almacenes y recolectores hasta que tengan el nivel promedio
        while (almaElixR.getNivel() < nivelAlmacenesPromedio) {
            almaElixR.upgrade();
            almaOroR.upgrade();
        }
        while (extractorR.getNivel() < nivelRecolectoresPromedio) {
            minaR.upgrade();
            extractorR.upgrade();
        }

        // configuro el campamento y el laboratorio rival
        Campamento campamentoR = rival.getCampamento();
        Laboratorio laboratorioR = rival.getLaboratorio();
        // calculo el promedio de los niveles de campamento y laboratorio de mi aldea
        int nivelCampamentoR = getCampamento().getNivel() + rand.nextInt(2);
        int nivelLaboratorioR = getLaboratorio().getNivel() + rand.nextInt(2);

        // upgrade a campamento y laboratorio rivales hasta que tengan el nivel promedio
        while (campamentoR.getNivel() < nivelCampamentoR) {
            campamentoR.upgrade();
        }
        while (laboratorioR.getNivel() < nivelLaboratorioR) {
            laboratorioR.upgrade();
        }

        // ahora si un random de 0 a 100 de cuánto tiene cada almacen y recolector
        almaOroR.setAcumulado(almaOroR.getMax() * (rand.nextInt(95) + 5) / 100);
        almaElixR.setAcumulado(almaElixR.getMax() * (rand.nextInt(95) + 5) / 100);
        minaR.setAcumulado(minaR.getCapacidadMaxima() * (rand.nextInt(95) +5) / 100);
        extractorR.setAcumulado(extractorR.getCapacidadMaxima() * (rand.nextInt(95) + 5) / 100);

        // ahora seteo la cantidad de tropas en el campamento
        // cantidad de tropas entre 85% y 100% de la capacidad máxima
        int cantidadTropas = campamentoR.getCapacidadMaxima() * (rand.nextInt(20) + 85) / 100;
        campamentoR.setCantidadActualCampamento(cantidadTropas);
        campamentoR.calcularAtaque(laboratorioR.getNivel());

        /* System.out.println("Cantidad de tropas y capacidad ataque: " + cantidadTropas + " " + " " + campamentoR.getCapacidadMaxima() + " " + campamentoR.getCapacidadAtaque());
        System.out.println("Niveles de aldea rival laborat y campamento: " + nivelCampamentoR + " " + nivelLaboratorioR); */

        return rival;
    }

    public void atacarRival(Random rand) {
        Aldea rival = crearAldeaRandom(rand);
        ataque(this, rival, rand);
        System.out.println("----------------------- Fin del ataque -----------------------");

        // si tengo tropas entrenadas, las envio paso a campamento
        while (getCampamento().agregar(getCuartel())) { }
    }

    public void defenderDeRival(Random rand) {
        Aldea rival = crearAldeaRandom(rand);
        ataque(rival, this, rand);
        System.out.println("----------------------- Fin de defensa -----------------------");
    }

    private void ataque(Aldea atacante, Aldea defensor, Random rand) {
        int atacanteCapacidad = atacante.getCampamento().getCapacidadAtaque();
        if (atacanteCapacidad == 0) {
            System.out.println("Error: no hay tropas en el campamento");
            return;
        }
        
        // ahora si se puede atacar
        int defensorCapacidad = defensor.getDefensa().getCapacidadDefensa();
        boolean victoriaAtacante;
        if (atacanteCapacidad == defensorCapacidad) {
            victoriaAtacante = rand.nextBoolean(); // 50% de probabilidad
        } else {
            victoriaAtacante = atacanteCapacidad > defensorCapacidad;
        }

        System.out.printf("%-34.34s %-25.25s%n", "Capacidades atacante y defensor: ", atacanteCapacidad + " vs " + defensorCapacidad);

        atacante.getCampamento().vaciar(); // suponemos que se usan todas las tropas

        // calculando posibles recursos a robar
        int oroAlmacen = defensor.getAlmacenOro().getPosiblePerdida();
        int oroMina = defensor.getMina().getPosiblePerdida();
        int elixirAlmacen = defensor.getAlmacenElixir().getPosiblePerdida();
        int elixirExtractor = defensor.getExtractor().getPosiblePerdida();

        int oroTotal = oroAlmacen + oroMina;
        int elixirTotal = elixirAlmacen + elixirExtractor;

        int oroRobado = 0;
        int elixirRobado = 0;

        if (victoriaAtacante) {
            // al ganar se supondra que se roban todos los recursos
            // los guardo para mostrar y luego calculo y guardo el robo
            oroRobado = oroAlmacen + oroMina;
            elixirRobado = elixirAlmacen + elixirExtractor;
            defensor.getAlmacenElixir().perder();
            defensor.getAlmacenOro().perder();
            defensor.getMina().perder();
            defensor.getExtractor().perder();
            System.out.println("Gana el atacante");
        } else {
            // al perder se supone que a lo mucho se roba el 50% de los recursos
            // se calcula de 0 a 50% de los recursos
            int porcentajeOro = rand.nextInt(50) / 100;
            int porcentajeElixir = rand.nextInt(50) / 100;
            oroRobado = oroAlmacen * porcentajeOro + oroMina * porcentajeOro;
            elixirRobado = elixirAlmacen * porcentajeElixir + elixirExtractor * porcentajeElixir;
            defensor.getExtractor().perder(elixirExtractor * porcentajeElixir);
            defensor.getMina().perder(oroMina * porcentajeOro);
            defensor.getAlmacenOro().perder(oroAlmacen * porcentajeOro);
            defensor.getAlmacenElixir().perder(elixirAlmacen * porcentajeElixir);
            System.out.println("Gana el defensor");
        }

        // TODO no me cuadra, creo que no he calculado bien los recursos robados

        atacante.getAlmacenOro().almacenar(oroRobado);
        atacante.getAlmacenElixir().almacenar(elixirRobado);

        System.out.printf("%-34.34s  %-25.25s%n", "Oro total: " + oroTotal, "Oro robado: " + oroRobado);
        System.out.printf("%-34.34s  %-25.25s%n", "Elixir total: " + elixirTotal, "Elixir robado: " + elixirRobado);
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
