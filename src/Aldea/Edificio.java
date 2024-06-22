package Aldea;

public abstract class Edificio {
    protected int nivel;
    protected int precioMejora;
    protected int tiempoMejora;
    protected TipoEdificio tipoEdificio;
    protected TipoEdificio almacenConsumo;
    protected boolean ocupado; // puede ser construido o mejora

    protected Edificio(int nivel, int precioMejora, int tiempoMejora, TipoEdificio tipoEdificio, TipoEdificio almacenConsumo) {
        this.nivel = nivel;
        int nivelRef = (nivel == 0) ? 1 : nivel;
        this.precioMejora = nivelRef * precioMejora;
        this.tiempoMejora = nivelRef * tiempoMejora;
        this.tipoEdificio = tipoEdificio;
        this.almacenConsumo = almacenConsumo;
    }

    public abstract void upgrade();

    protected void upgradeEdificio() {
        this.nivel++;
        this.precioMejora = this.nivel * precioMejora;
        this.tiempoMejora = this.nivel * tiempoMejora;
    }

    public boolean construir() {
        if (nivel > 0) {
           return false; 
        }
        setOcupado(true);
        ++nivel;
        return true;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public boolean getOcupado() {
        return this.ocupado;
    }

    public void setNivel(int nivel){ this.nivel=nivel;}

    public void setPrecioMejora(int precioMejora){ this.precioMejora=precioMejora;}

    public void setTiempoMejora(int tiempoMejora){ this.tiempoMejora=tiempoMejora;}

    public int getNivel(){ return this.nivel;}

    public int getPrecioMejora(){ return this.precioMejora;}

    public int getTiempoMejora(){ return this.tiempoMejora;}

    public TipoEdificio getTipoEdificio(){ return this.tipoEdificio;}

    public TipoEdificio getTipoAlmacenConsumo(){ return this.almacenConsumo;}
}
