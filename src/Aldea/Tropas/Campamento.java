package Aldea.Tropas;

import Aldea.Edificio;
import Aldea.TipoEdificio;

public class Campamento extends Edificio {
    private int capacidadMaxima;
    private int cantidadActualCampamento;
    private int capacidadAtaque;

    public Campamento() {
        super(1, 5, 2, TipoEdificio.CAMPAMENTO, TipoEdificio.ALMACEN_ELIXIR);
        this.capacidadMaxima = 10 * nivel;
        this.cantidadActualCampamento = 0;
        this.capacidadAtaque = 1 * cantidadActualCampamento;
    }

    // TODO cuidado con la cantidad de tropas
    public void agregar(Cuartel cuartel) {
        int entrada = cantidadActualCampamento + 1;

        if (capacidadMaxima > entrada) {
            this.cantidadActualCampamento = entrada;
            cuartel.disminuirCola(1);
        }
    }

    public void calcularAtaque(int nivelLaboratorio) {
        this.capacidadAtaque = nivelLaboratorio * cantidadActualCampamento;
    }

    public void upgrade() {
        upgradeEdificio();
        this.capacidadMaxima += this.nivel * 5;
    }

    public void aumentarAtaque() {
        this.capacidadAtaque += 1;
    }

    public void vaciar() {
        this.cantidadActualCampamento = 0;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public int getCantidadActualCampamento() {
        return cantidadActualCampamento;
    }

    public int getCapacidadAtaque() {
        return capacidadAtaque;
    }

    public int setCapacidadMaxima() {
        return this.capacidadMaxima;
    }

    public int setCantidadActualCampamento() {
        return this.cantidadActualCampamento;
    }

    public int setNivelAtaque() {
        return this.capacidadAtaque;
    }
}
